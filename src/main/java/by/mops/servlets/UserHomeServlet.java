package by.mops.servlets;

import by.mops.db.dao.BetsDaoJdbcImpl;
import by.mops.db.dao.EventsDaoJdbcImpl;
import by.mops.db.dao.UsersDaoJdbcImpl;
import by.mops.models.Bet;
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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/userHomePage")
public class UserHomeServlet extends HttpServlet {


    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");
        List<User> users = null;
        List<Event> events = null;
        List<Bet> bets = null;
        UsersDaoJdbcImpl usersDaoJdbc = new UsersDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        EventsDaoJdbcImpl eventsDaoJdbc = new EventsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        BetsDaoJdbcImpl betsDaoJdbc = new BetsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        events = eventsDaoJdbc.findAll();
        req.setAttribute("events", events);
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
                case "delete_bet":
                    doDeleteBet(req.getParameter("id"), betsDaoJdbc, req, resp);
                    break;
                case "edit_bet":
                    doEditBet(req.getParameter("id"), betsDaoJdbc, req, resp);
                    break;
                case "add_bet":
                    doAddBet(req, resp);
                    break;
            }
        }

        users = usersDaoJdbc.findAll();
        req.setAttribute("users", users);
        bets = betsDaoJdbc.findAll();
        req.setAttribute("bets", bets);


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

    private void doDeleteBet(String id, BetsDaoJdbcImpl betsDaoJdbc, HttpServletRequest request, HttpServletResponse response) throws IOException {
        long user_id = Long.parseLong(id);
        betsDaoJdbc.delete(user_id);
        response.sendRedirect("userHomePage");
    }

    private void doEditBet(String id, BetsDaoJdbcImpl betsDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Long user_id = Long.parseLong(id);
        Bet bet = betsDaoJdbc.find(user_id).orElse(new Bet());

        request.setAttribute("bet", bet);
        request.setAttribute("types_of_bet", getTypesOfBet());

        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/edit_or_add_bet.jsp");
        dispatcher.forward(request, response);
    }

    private void doAddBet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Event event = new Event(Long.parseLong(request.getParameter("id")), request.getParameter("team1"),
                                request.getParameter("team2"));
        request.setAttribute("types_of_bet", getTypesOfBet());
        request.setAttribute("event", event);
        RequestDispatcher dispatcher = request.getRequestDispatcher("jsp/edit_or_add_bet.jsp");
        dispatcher.forward(request, response);
    }

    public Map<Long, String> getTypesOfBet(){
        try {
            Map<Long, String> types_of_bet = new HashMap<>();
            Statement statement = ((Connection) getServletContext().getAttribute("DBConnection")).createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM types_of_bet");
            while (resultSet.next()) {
                Long type_of_bet_id = resultSet.getLong("id");
                String name = resultSet.getString("name");

                types_of_bet.put(type_of_bet_id, name);
            }
            return types_of_bet;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDaoJdbcImpl usersDaoJdbc = new UsersDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        EventsDaoJdbcImpl eventsDaoJdbc = new EventsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        BetsDaoJdbcImpl betsDaoJdbc = new BetsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
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
                    case "edit_bet":
                        updateBet(betsDaoJdbc, req, resp);
                        break;
                    case "add_bet":
                        addBet(betsDaoJdbc, req, resp);
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

    private void updateBet(BetsDaoJdbcImpl betsDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String value = request.getParameter("value");
        double coefficient = Double.parseDouble(request.getParameter("coefficient"));
        Long id = Long.parseLong(request.getParameter("id"));
        Bet bet = new Bet(id, value, coefficient);
        betsDaoJdbc.update(bet);
        //response.sendRedirect("userHomePage");
    }

    private void addBet(BetsDaoJdbcImpl betsDaoJdbc, HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String value = request.getParameter("value");
        double coefficient = Double.parseDouble(request.getParameter("coefficient"));
        Event event = new Event(Long.parseLong(request.getParameter("id")));
        Long type_of_bet_id = Long.parseLong(request.getParameter("type_list"));
        Bet bet = new Bet(value, coefficient, event, type_of_bet_id);
        betsDaoJdbc.save(bet);
        //response.sendRedirect("userHomePage");
    }
}
