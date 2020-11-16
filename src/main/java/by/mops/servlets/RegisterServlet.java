package by.mops.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class guru_register
 */

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html");
        String path = "/html/register_1.html";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.forward(req, resp);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String password = request.getParameter("password");
        String username = request.getParameter("username");
        String birth = request.getParameter("birthday");
        LocalDate birthday = null;

        String errorMsg = null;
        if(birth == "" || birth == null){
            errorMsg = "Birthday can't be null or incorrect.";
        }else{
             birthday = LocalDate.parse(request.getParameter("birthday"));
            if(birthday.isAfter(LocalDate.parse("2000-01-01")) || birthday.isBefore(LocalDate.parse("1950-01-01"))){
                errorMsg = "Birthday can't be null or incorrect.";
            }
        }
        if(firstName == null || firstName.equals("")){
            errorMsg = "First name can't be null or empty.";
        }
        if(password == null || password.equals("")){
            errorMsg = "Password can't be null or empty.";
        }
        if(username == null || username.equals("")){
            errorMsg = "Username can't be null or empty.";
        }
        if(lastName == null || lastName.equals("")){
            errorMsg = "Last name can't be null or empty.";
        }



        if(errorMsg != null){
            RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/register_1.html");
            PrintWriter out= response.getWriter();
            out.println("<font color=red>"+errorMsg+"</font>");
            rd.include(request, response);
        }else{

            Connection con = (Connection) getServletContext().getAttribute("DBConnection");
            try (PreparedStatement ps = con.prepareStatement("insert into Users(username,password,isAdmin,role,birthday,firstName,lastName,balance) values (?,?,?,?,?,?,?,?)")) {
                ps.setString(1, username);
                ps.setString(2, password);
                ps.setBoolean(3, false);
                ps.setInt(4, 0);
                ps.setDate(5, java.sql.Date.valueOf(birthday));
                ps.setString(6, firstName);
                ps.setString(7, lastName);
                ps.setDouble(8, 0);

                ps.execute();

                //forward to login page to login
                RequestDispatcher rd = getServletContext().getRequestDispatcher("/html/register_3.html");
                PrintWriter out = response.getWriter();
                out.println("<font color=green>Registration successful, please login below.</font>");
                rd.include(request, response);
            } catch (SQLException e) {
                e.printStackTrace();
                throw new ServletException("DB Connection problem.");
            }
        }

    }

}