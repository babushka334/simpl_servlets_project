package by.mops.servlets;

import by.mops.models.User;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class guru_login
 */
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    public LoginServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String path = "/html/register_3.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        if (request.getParameter("Login") != null) {
            String username = request.getParameter("username");
            String password = request.getParameter("password");
            String errorMsg = null;
            if (username == null || username.equals("")) {
                errorMsg = "User Email can't be null or empty";
            }
            if (password == null || password.equals("")) {
                errorMsg = "Password can't be null or empty";
            }

            if (errorMsg != null) {
                RequestDispatcher requestDispatcher = getServletContext().getRequestDispatcher("/html/register_3.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=red>" + errorMsg + "</font>");
                requestDispatcher.include(request, response);
            } else {

                Connection con = (Connection) getServletContext().getAttribute("DBConnection");
                PreparedStatement ps = null;
                ResultSet rs = null;
                try {
                    ps = con.prepareStatement("select id, username, firstName, lastName, birthday, role, isAdmin from Users where username=? and password=? limit 1");
                    ps.setString(1, username);
                    ps.setString(2, password);
                    rs = ps.executeQuery();

                    if (rs != null && rs.next()) {

                        User user = new User(rs.getLong("id"),
                                rs.getString("username"),
                                rs.getBoolean("isAdmin"),
                                rs.getInt("role"),
                                rs.getDate("birthday").toLocalDate(),
                                rs.getString("firstName"),
                                rs.getString("lastName")
                        );
                        HttpSession session = request.getSession();
                        session.setAttribute("User", user);
                        /*RequestDispatcher rd = getServletContext().getRequestDispatcher("/userHomePage");
                        rd.include(request, response);*/
                        response.sendRedirect("userHomePage");
                    } else {
                        RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/register_3.html");
                        PrintWriter out = response.getWriter();
                        out.println("<font color=red>No user found with given email id, please register first.</font>");
                        rd.include(request, response);
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    throw new ServletException("DB Connection problem.");
                } finally {
                    try {
                        assert rs != null;
                        rs.close();
                        ps.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
            }

        }

    }
}