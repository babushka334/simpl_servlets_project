package by.mops.db.dao;

import by.mops.models.Bet;
import by.mops.models.Event;
import by.mops.models.UserBet;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class UserBetsDaoJdbcImpl implements CrudDao<UserBet>{

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM user_bets";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM user_bets WHERE id = ?";

    //language=SQL
    private final String SQL_ALL_SELECT_BY_ID =
            "SELECT * FROM user_bets WHERE user_id = ?";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE FROM user_bets WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE user_bets SET status = ? WHERE id = ?";

    //language=SQL
    private final String SQL_INSERT_NEW =
            "INSERT user_bets(user_id, bet_id, value, status) VALUES (?, ?, ?, ?)";


    private Connection connection;

    public UserBetsDaoJdbcImpl(Connection con) {
        this.connection = con;
    }

    @Override
    public Optional<UserBet> find(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            UserBet bet = null;
            if(resultSet.next()) {
                Long bet_id = resultSet.getLong("bet_id");
                Long user_id = resultSet.getLong("user_id");
                String status = resultSet.getString("status");
                double value = resultSet.getDouble("value");

                bet = new UserBet(id, bet_id, user_id, value, status);
            }
            return Optional.of(bet);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(UserBet model) {
        try {
            PreparedStatement statement = connection.prepareStatement("INSERT user_bets(user_id, bet_id, value, status) VALUES (?, ?, ?, ?)");
            statement.setLong(1, model.getUser_id());
            statement.setLong(2, model.getBet_id());
            statement.setDouble(3, model.getValue());
            statement.setString(4, "нерассчитано");
            statement.executeUpdate();
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean update(UserBet model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setString(1, model.getStatus());
            statement.setLong(2, model.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public boolean delete(Long id) {
        return false;
    }

    @Override
    public List<UserBet> findAll() {
        try {
            List<UserBet> bets = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long bet_id = resultSet.getLong("bet_id");
                Long user_id = resultSet.getLong("user_id");
                String status = resultSet.getString("status");
                double value = resultSet.getDouble("value");

                UserBet bet = new UserBet(id, bet_id, user_id, value, status);
                bets.add(bet);
            }
            return bets;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    public List<UserBet> findAllById(Long user_id) {
        try {
            List<UserBet> bets = new ArrayList<>();
            PreparedStatement statement = connection.prepareStatement(SQL_ALL_SELECT_BY_ID);
            statement.setLong(1, user_id);
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                Long bet_id = resultSet.getLong("bet_id");
                String status = resultSet.getString("status");
                double value = resultSet.getDouble("value");

                UserBet bet = new UserBet(id, bet_id, user_id, value, status);
                bets.add(bet);
            }
            return bets;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}
