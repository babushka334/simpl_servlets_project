package by.mops.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class dbConnection {
    public static Connection getConnection() throws SQLException, IOException {
        Properties props = new Properties();
//        Files.newInputStream(Paths.get("db.properties"))
        try (InputStream in = dbConnection.class.getResourceAsStream("/resources/db.properties")) {
            props.load(in);
        }
        String url = props.getProperty("url");
        String user = props.getProperty("user");
        String password = props.getProperty("password");

        return DriverManager.getConnection(url, user, password);
    }
}
