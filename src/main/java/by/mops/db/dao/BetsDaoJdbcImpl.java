package by.mops.db.dao;

import by.mops.models.Bet;
import by.mops.models.Event;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class BetsDaoJdbcImpl implements CrudDao<Bet> {
    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM bets\n" +
                    "JOIN types_of_bet ON bets.type_of_bet_id = types_of_bet.id\n" +
                    "JOIN events ON bets.event_id = events.id";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM bets\n" +
                    "JOIN types_of_bet ON bets.type_of_bet_id = types_of_bet.id\n" +
                    "JOIN events ON bets.event_id = events.id\n" +
                    "WHERE bets.id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE FROM bets WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE bets SET coefficient = ?, value = ? WHERE id = ?";

    //language=SQL
    private final String SQL_INSERT_NEW =
            "INSERT bets(value, coefficient, event_id, type_of_bet_id) " +
                    "VALUES (?, ?, ?, ?)";


    private Connection connection;

    public BetsDaoJdbcImpl(Connection con) {
        this.connection = con;
    }


    @Override
    public Optional<Bet> find(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            Bet bet = null;
            if(resultSet.next()) {
                Long event_id = resultSet.getLong("events.id");
                Long type_of_bet_id = resultSet.getLong("types_of_bet.id");
                String team1 = resultSet.getString("team1");
                String team2 = resultSet.getString("team2");
                String status = resultSet.getString("status");
                Event event = new Event(event_id, team1, team2, status);
                double coef = resultSet.getDouble("coefficient");
                String value = resultSet.getString("value");
                String type_of_bet = resultSet.getString("name");
                bet = new Bet(id, coef, value, event, type_of_bet, type_of_bet_id);
            }
            return Optional.of(bet);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(Bet model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_INSERT_NEW);
            statement.setString(1, model.getValue());
            statement.setDouble(2, model.getCoefficient());
            statement.setLong(3, model.getEvent().getId());
            statement.setLong(4, model.getType_of_bet_id());
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean update(Bet model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setDouble(1, model.getCoefficient());
            statement.setString(2, model.getValue());
            statement.setLong(3, model.getId());
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
    public List<Bet> findAll() {
        try {
            List<Bet> bets = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {

                Long event_id = resultSet.getLong("events.id");
                Long type_of_bet_id = resultSet.getLong("types_of_bet.id");
                String team1 = resultSet.getString("team1");
                String team2 = resultSet.getString("team2");
                String status = resultSet.getString("status");
                Event event = new Event(event_id, team1, team2, status);
                Long id = resultSet.getLong("bets.id");
                double coef = resultSet.getDouble("coefficient");
                String value = resultSet.getString("value");
                String type_of_bet = resultSet.getString("name");
                Bet bet = new Bet(id, coef, value, event, type_of_bet, type_of_bet_id);
                bets.add(bet);
            }
            return bets;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
