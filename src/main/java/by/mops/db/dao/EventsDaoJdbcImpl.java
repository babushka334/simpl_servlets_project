package by.mops.db.dao;

import by.mops.db.DBConnection;
import by.mops.models.Event;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class EventsDaoJdbcImpl implements CrudDao<Event> {

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM events";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM events WHERE id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE FROM events WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE events SET team1 = ?, team2 = ?, status = ?, result = ?, total = ? WHERE id = ?";

    //language=SQL
    private final String SQL_INSERT_NEW =
            "INSERT events(team1, team2, status) " +
                    "VALUES (?, ?, ?);";


    private Connection connection;

    public EventsDaoJdbcImpl(Connection con) {
        this.connection = con;
    }


    @Override
    public Optional<Event> find(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Event event = null;
            if(resultSet.next()) {
                String team1 = resultSet.getString("team1");
                String team2 = resultSet.getString("team2");
                String status = resultSet.getString("status");
                event = new Event(id, team1, team2, status);
            }


            return Optional.of(event);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Event model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW);
            statement.setString(1, model.getTeam1());
            statement.setString(2, model.getTeam2());
            statement.setString(2, "не завершен");

            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean update(Event model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, model.getTeam1());
            statement.setString(2, model.getTeam2());
            statement.setString(3, model.getStatus());
            if(model.getResult() != null)
                statement.setString(4, model.getResult());
            else
                statement.setString(4, "");
            statement.setInt(5, model.getTotal());
            statement.setLong(6, model.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public boolean delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_DELETE_BY_ID);
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<Event> findAll() {
        try {
            List<Event> events = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String team1 = resultSet.getString("team1");
                String team2 = resultSet.getString("team2");
                String status = resultSet.getString("status");
                Event event = new Event(id, team1, team2, status);
                events.add(event);
            }
            return events;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}