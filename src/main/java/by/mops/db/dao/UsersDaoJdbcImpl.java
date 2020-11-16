package by.mops.db.dao;

import by.mops.db.DBConnection;
import by.mops.models.User;

import javax.sql.DataSource;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


public class UsersDaoJdbcImpl implements UsersDao {

    //language=SQL
    private final String SQL_SELECT_ALL =
            "SELECT * FROM users";

    //language=SQL
    private final String SQL_DELETE_BY_ID =
            "DELETE FROM users WHERE id = ?";

    //language=SQL
    private final String SQL_SELECT_BY_ID =
            "SELECT * FROM users WHERE id = ?";
    //language=SQL
    private final String SQL_UPDATE_BY_ID =
            "UPDATE users SET isAdmin = ?, role = ? WHERE id = ?";

    private Connection connection;

    public UsersDaoJdbcImpl(Connection con) {
        this.connection = con;
    }

    @Override
    public List<User> findAllByFirstName(String firstName) {
        return null;
    }

    @Override
    public Optional<User> find(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_SELECT_BY_ID);
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            resultSet.next();
            String username = resultSet.getString("username");
            Boolean isAdmin = resultSet.getBoolean("isAdmin");
            Integer role = resultSet.getInt("role");
            LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
            String firstName = resultSet.getString("firstName");
            String lastName = resultSet.getString("lastName");
            User user = new User(id, username, isAdmin, role, birthday, firstName, lastName);


            return Optional.of(user);
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }

    @Override
    public void save(User model) {

    }

    @Override
    public boolean update(User model) {
        try {
            PreparedStatement statement = connection.prepareStatement(SQL_UPDATE_BY_ID);
            statement.setBoolean(1, model.getIsAdmin());
            statement.setInt(2, model.getRole());
            statement.setLong(3, model.getId());
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public boolean delete(Long id) {
        try {
            PreparedStatement statement = connection.prepareStatement("DELETE FROM users WHERE id = ?");
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }

    }

    @Override
    public List<User> findAll() {
        try {
            List<User> users = new ArrayList<>();
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(SQL_SELECT_ALL);
            while (resultSet.next()) {
                Long id = resultSet.getLong("id");
                String username = resultSet.getString("username");
                Boolean isAdmin = resultSet.getBoolean("isAdmin");
                Integer role = resultSet.getInt("role");
                LocalDate birthday = resultSet.getDate("birthday").toLocalDate();
                String firstName = resultSet.getString("firstName");
                String lastName = resultSet.getString("lastName");
                User user = new User(id, username, isAdmin, role, birthday, firstName, lastName);
                users.add(user);
            }
            return users;
        } catch (SQLException e) {
            throw new IllegalStateException(e);
        }
    }
}