// package se.cholmers.backend;

// import java.sql.Connection;
// import java.sql.DriverManager;
// import java.sql.SQLException;

// public class Dbi {
//     private static DatabaseInterface instance;

//     // Replace these with your actual database information
//     private static final String DB_URL = "jdbc:postgresql://your_database_url";
//     private static final String DB_USER = "your_database_user";
//     private static final String DB_PASSWORD = "your_database_password";

//     private Connection connection;

//     //List<String> represenation of tables
    
// /*     private static final String[] person = {"id", "phone_number", "person_name", "person_nick"};
//     private static final String[] committee = {"id", "group_name", "year"};
//     private static final String[] product = {"id", "name", "price"};   */  


//     private Dbi() {
//         try {
//             Class.forName("org.postgresql.Driver");
//             connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
//         } catch (ClassNotFoundException | SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     public static DatabaseInterface getInstance() {
//         if (instance == null) {
//             instance = new DatabaseInterface();
//         }
//         return instance;
//     }

//     public void closeConnection() {
//         try {
//             if (connection != null && !connection.isClosed()) {
//                 connection.close();
//             }
//         } catch (SQLException e) {
//             e.printStackTrace();
//         }
//     }

//     //TODO
//     //SQL querys in each method

    
    


// }
