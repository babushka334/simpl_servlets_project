package by.mops.servlets;

import by.mops.db.DBConnection;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

@WebListener
public class AppContextListener implements ServletContextListener {

    public void contextInitialized(ServletContextEvent servletContextEvent) {
        ServletContext ctx = servletContextEvent.getServletContext();
        Properties properties = new Properties();
        //initialize DB Connection
        try {
            properties.load(new FileInputStream("C:\\Users\\User\\IdeaProjects\\mops\\src\\main\\resources\\db.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        String dbUrl = properties.getProperty("db.url");
        String dbUsername = properties.getProperty("db.username");
        String dbPassword = properties.getProperty("db.password");
        String driverClassName = properties.getProperty("db.driverClassName");
       /* String dbUrl = "jdbc:mysql://localhost:3306/mopsbetdb?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String dbUsername = "root";
        String dbPassword = "kalini.kostya";
        String driverClassName = "com.mysql.cj.jdbc.Driver";*/

        try {
            DBConnection connectionManager = new DBConnection(dbUrl, dbUsername, dbPassword, driverClassName);
            ctx.setAttribute("DBConnection", connectionManager.getConnection());
            System.out.println("DB Connection initialized successfully.");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }

        //initialize log4j
        /*String log4jConfig = ctx.getInitParameter("log4j-config");
        if(log4jConfig == null){
            System.err.println("No log4j-config init param, initializing log4j with BasicConfigurator");
            BasicConfigurator.configure();
        }else {
            String webAppPath = ctx.getRealPath("/");
            String log4jProp = webAppPath + log4jConfig;
            File log4jConfigFile = new File(log4jProp);
            if (log4jConfigFile.exists()) {
                System.out.println("Initializing log4j with: " + log4jProp);
                DOMConfigurator.configure(log4jProp);
            } else {
                System.err.println(log4jProp + " file not found, initializing log4j with BasicConfigurator");
                BasicConfigurator.configure();
            }
        }
        System.out.println("log4j configured properly");*/
    }

    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        Connection con = (Connection) servletContextEvent.getServletContext().getAttribute("DBConnection");
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
