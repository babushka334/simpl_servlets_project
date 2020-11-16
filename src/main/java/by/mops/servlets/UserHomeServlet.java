package by.mops.servlets;

import by.mops.db.dao.EventsDaoJdbcImpl;
import by.mops.db.dao.UsersDaoJdbcImpl;
import by.mops.models.Event;
import by.mops.models.User;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userHomePage")
public class UserHomeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        List<User> users = null;
        List<Event> events = null;
        UsersDaoJdbcImpl usersDaoJdbc = new UsersDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        EventsDaoJdbcImpl eventsDaoJdbc = new EventsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));

        if (req.getParameter("action") != null) {
            switch (req.getParameter("action")) {
                case "delete_user":
                    doDeleteUser(req.getParameter("id"), usersDaoJdbc, req, resp);
                    break;
                case "edit_user":
                    doEditUser(req.getParameter("id"), usersDaoJdbc, req, resp);
                    break;
                case "delete_event":
                    doDeleteEvent(req.getParameter("id"), eventsDaoJdbc, req, resp);
                    break;
                case "edit_event":
                    doEditEvent(req.getParameter("id"), eventsDaoJdbc, req, resp);
                    break;
                case "add_event":
                    doAddEvent(req, resp);
                    break;
                /* case "delete_bet":
                case "edit_bet":
                case "add_bet":*/
            }
        }

        users = usersDaoJdbc.findAll();
        req.setAttribute("users", users);
        events = eventsDaoJdbc.findAll();
        req.setAttribute("events", events);

        String path = "/jsp/register_4.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.include(req, resp);
    }

    private void doEditUser(String id, UsersDaoJdbcImpl usersDaoJdbc, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long user_id = Long.parseLong(id);
        User existingUser = usersDaoJdbc.find(user_id).orElse(new User());
        request.setAttribute("user", existingUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/edit_user.jsp");
        dispatcher.forward(request, response);
    }

    private void doDeleteUser(String id, UsersDaoJdbcImpl usersDaoJdbc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        long user_id = Long.parseLong(id);
        usersDaoJdbc.delete(user_id);
        response.sendRedirect("userHomePage");
    }
    private void doDeleteEvent(String id, EventsDaoJdbcImpl eventsDaoJdbc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        long event_id = Long.parseLong(id);
        eventsDaoJdbc.delete(event_id);
        response.sendRedirect("userHomePage");
    }

    private void doEditEvent(String id, EventsDaoJdbcImpl eventsDaoJdbc, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Long user_id = Long.parseLong(id);
        Event event = eventsDaoJdbc.find(user_id).orElse(new Event());
        request.setAttribute("event", event);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/edit_or_add_event.jsp");
        dispatcher.forward(request, response);
    }

    private void doAddEvent(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/edit_or_add_event.jsp");
        dispatcher.forward(request, response);
    }


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDaoJdbcImpl usersDaoJdbc = new UsersDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        EventsDaoJdbcImpl eventsDaoJdbc = new EventsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        if(req.getParameter("action") != null) {
            try {
                switch(req.getParameter("action")){
                    case "edit_user":
                        updateUser(usersDaoJdbc, req, resp);
                        break;
                    case "edit_event":
                        updateEvent(eventsDaoJdbc, req, resp);
                        break;
                    case "add_event":
                        addEvent(eventsDaoJdbc, req, resp);
                        break;
                }

            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        doGet(req, resp);
    }

    private void updateUser(UsersDaoJdbcImpl usersDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Boolean isAdmin = Boolean.parseBoolean(request.getParameter("isAdmin"));
        Integer role = Integer.parseInt(request.getParameter("role"));
        Long id = Long.parseLong(request.getParameter("id"));
        User user = usersDaoJdbc.find(id).orElse(new User());
        user.setIsAdmin(isAdmin);
        user.setRole(role);
        usersDaoJdbc.update(user);
        //response.sendRedirect("userHomePage");
    }

    private void updateEvent(EventsDaoJdbcImpl eventsDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String team1 = request.getParameter("team1");
        String team2 = request.getParameter("team2");
        Long id = Long.parseLong(request.getParameter("id"));
        Event event = eventsDaoJdbc.find(id).orElse(new Event());
        event.setTeam1(team1);
        event.setTeam2(team2);
        eventsDaoJdbc.update(event);
        //response.sendRedirect("userHomePage");
    }

    private void addEvent(EventsDaoJdbcImpl eventsDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String team1 = request.getParameter("team1");
        String team2 = request.getParameter("team2");
        Event event = new Event(team1, team2);
        event.setTeam1(team1);
        event.setTeam2(team2);
        eventsDaoJdbc.save(event);
        //response.sendRedirect("userHomePage");
    }
}
