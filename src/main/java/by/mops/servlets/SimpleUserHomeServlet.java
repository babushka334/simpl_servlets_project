package by.mops.servlets;

import by.mops.db.dao.BetsDaoJdbcImpl;
import by.mops.db.dao.EventsDaoJdbcImpl;
import by.mops.db.dao.UserBetsDaoJdbcImpl;
import by.mops.db.dao.UsersDaoJdbcImpl;
import by.mops.models.Bet;
import by.mops.models.Event;
import by.mops.models.User;
import by.mops.models.UserBet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@WebServlet("/userPage")
public class SimpleUserHomeServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        resp.setContentType("text/html");

        List<Event> events = null;
        List<Bet> bets = null;
        List<UserBet> userBets = null;

        EventsDaoJdbcImpl eventsDaoJdbc = new EventsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        BetsDaoJdbcImpl betsDaoJdbc = new BetsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        UserBetsDaoJdbcImpl userBetsDaoJdbc = new UserBetsDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));
        HttpSession session = req.getSession();
        User user = (User)session.getAttribute("User");
        userBets = userBetsDaoJdbc.findAllById(user.getId());
        req.setAttribute("user_bets", userBets);
        events = eventsDaoJdbc.findAll();
        req.setAttribute("events", events);
        bets = betsDaoJdbc.findAll();
        req.setAttribute("bets", bets);

        if (req.getParameter("action") != null) {
            switch (req.getParameter("action")) {
                case "Balance":
                    doAddMoney(req, resp);
                    break;
            }
        }

        String path = "/jsp/register_5.jsp";
        ServletContext servletContext = getServletContext();
        RequestDispatcher requestDispatcher = servletContext.getRequestDispatcher(path);
        requestDispatcher.include(req, resp);

    }

    private void doAddMoney(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        RequestDispatcher dispatcher = req.getRequestDispatcher("jsp/edit_balance.jsp");
        dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UsersDaoJdbcImpl usersDaoJdbcImpl = new UsersDaoJdbcImpl((Connection) getServletContext().getAttribute("DBConnection"));


        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String var = req.getParameter("user_id");
        String var0 = req.getParameter("bet_id");
        String var1 = req.getParameter("value");
        if (req.getParameter("action") != null) {
            switch (req.getParameter("action")) {

                case "Balance":
                    doAddMoney(req, resp);
                    break;
                case "Add money to account":
                    updateMoney(req, resp, Double.parseDouble(req.getParameter("value")), usersDaoJdbcImpl);
                    break;
                case "Get money from account":
                    updateMoney(req, resp, Double.parseDouble(req.getParameter("value")) * (-1), usersDaoJdbcImpl);
                    break;
                case "add_bet":
                    doAddBet(req, resp);
            }
        }
        doGet(req, resp);
    }

    private void doAddBet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Connection con = (Connection) getServletContext().getAttribute("DBConnection");
        UsersDaoJdbcImpl usersDaoJdbcImpl = new UsersDaoJdbcImpl(con);
        Double value = Double.parseDouble(req.getParameter("value"));
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("User");
        user.setBalance(user.getBalance() - value);
        usersDaoJdbcImpl.updateBalance(user);
        try {
            PreparedStatement statement = con.prepareStatement("INSERT user_bets(user_id, bet_id, value, status) VALUES (?, ?, ?, ?)");
            statement.setLong(1, Long.parseLong(req.getParameter("user_id")));
            statement.setLong(2, Long.parseLong(req.getParameter("bet_id")));
            statement.setDouble(3, value);
            statement.setString(4, "нерассчитано");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    private void updateMoney(HttpServletRequest req, HttpServletResponse resp, Double value, UsersDaoJdbcImpl usersDaoJdbcImpl) {
        HttpSession session = req.getSession(false);
        User user = (User) session.getAttribute("User");
        user.setBalance(user.getBalance() + value);
        usersDaoJdbcImpl.updateBalance(user);
    }

}
