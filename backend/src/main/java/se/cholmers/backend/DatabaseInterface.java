package se.cholmers.backend;
import java.sql.*;
import java.util.Properties;

public class DatabaseInterface {

    // Database connection
    private Connection conn;

    //Name the database, can be left blank to use default
    static final String DBNAME = "";

    //TODO Information for connecting to the database

    //URL
    private static final String DATABASE = "";
    private static final String USERNAME = "postgres";
    private static final String PASSWORD = "";
    

    public DatabaseInterface() throws SQLException, ClassNotFoundException {
        this(DATABASE, USERNAME, PASSWORD);
    }

    public DatabaseInterface(String db, String user, String password) throws SQLException, ClassNotFoundException {
        Class.forName("org.postgresql.Driver");
        Properties props = new Properties();
        props.setProperty("user", user);
        props.setProperty("password", password);
        conn = DriverManager.getConnection(db, props);
    }

    // Method for sending a query to the database 

    public String request(String request) {
        try {}
    }



}
