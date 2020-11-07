package by.mops.db;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DBConnection {

    private Connection connection;

    public DBConnection(String dbURL, String user, String pwd, String driverClassName) throws ClassNotFoundException, SQLException{
        Class.forName(driverClassName);
        this.connection = DriverManager.getConnection(dbURL, user, pwd);
    }

    public Connection getConnection(){
        return this.connection;
    }
}
