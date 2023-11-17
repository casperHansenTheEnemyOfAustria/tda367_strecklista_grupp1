package se.cholmers.backend;
import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

public class DatabaseInterface {

    private static DatabaseInterface instance;

    // Replace these with your actual database information
    private static final String DB_URL = "jdbc:postgresql://your_database_url";
    private static final String DB_USER = "your_database_user";
    private static final String DB_PASSWORD = "your_database_password";

    private Connection connection;

    //List<String> represenation of tables
    
/*     private static final String[] person = {"id", "phone_number", "person_name", "person_nick"};
    private static final String[] committee = {"id", "group_name", "year"};
    private static final String[] product = {"id", "name", "price"};   */  


    private DatabaseInterface() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static DatabaseInterface getInstance() {
        if (instance == null) {
            instance = new DatabaseInterface();
        }
        return instance;
    }

    public void closeConnection() {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void executeUpdate(String sql, List<Object> parameters) {
        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, parameters);
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public List<Map<String, Object>> executeQuery(String sql, List<Object> parameters) {
        List<Map<String, Object>> results = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            setParameters(statement, parameters);
            try (ResultSet resultSet = statement.executeQuery()) {
                results = ResultSetConverter.convertResultSetToList(resultSet);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return results;
    }

    private void setParameters(PreparedStatement statement, List<Object> parameters) throws SQLException {
        if (parameters != null) {
            for (int i = 0; i < parameters.size(); i++) {
                statement.setObject(i + 1, parameters.get(i));
            }
        }
    }

    // Additional methods for CRUD operations

    // CREATE operation
    private void addData(String tableName, Map<String, Object> data) {
        // Generate SQL for insertion based on the data
        String sql = generateInsertSQL(tableName, data);
        executeUpdate(sql, null);
    }   

    // READ operation
    private List<Map<String, Object>> getData(String tableName, int id) {
        String sql = "SELECT * FROM " + tableName + " WHERE id = ?";
        List<Object> params = List.of(id);
        return executeQuery(sql, params);
    }

    // UPDATE operation
    private void updateData(String tableName, int id, Map<String, Object> updatedData) {
        // Generate SQL for update based on the data
        String sql = generateUpdateSQL(tableName, id, updatedData);
        executeUpdate(sql, null);
    }

    // DELETE operation
    private void deleteData(String tableName, int id) {
        String sql = "DELETE FROM " + tableName + " WHERE id = ?";
        List<Object> params = List.of(id);
        executeUpdate(sql, params);
    }

    //Custom CREATE
    public void createCommittee(String group_name, String year) {
        Map<String, Object> parameters = new HashMap<>();
        parameters.put("group_name", group_name);
        parameters.put("year", year);
        String id = UUID.randomUUID().toString();
        parameters.put("id", id);
    }

    public void createProduct(String productName, String price, String committee, String amount) {
        Map<String, Object> parametersProduct = new HashMap();
        Map<String, Object> parametersProductInCommittee = new HashMap();
        String id = UUID.randomUUID().toString();
        parametersProduct.put("id", id);
        parametersProduct.put("name", productName);
        parametersProduct.put("price", price);

        parametersProductInCommittee.put("committee_id", committee);
        parametersProductInCommittee.put("product_id", id);
        parametersProductInCommittee.put("amount", amount);
        addData("Product", parametersProduct);
        addData("ProductInCommittee", parametersProductInCommittee);
    }

    //A committee needs the exists before a user can be inserted into it.
    public void createUser(String userName, String userNick, String phoneNumber, String committeeID, String saldo) {
        Map<String, Object> parametersUser = new HashMap();
        

        String id = UUID.randomUUID().toString();
        int idString = Integer.parseInt(id);
        parametersUser.put("id", id);
        parametersUser.put("phone_number", phoneNumber);
        parametersUser.put("person_name", userName);
        parametersUser.put("person_nick", userNick);


        addData("Person", parametersUser);
        
    }

    

    public void putUserInCommittee(String id, String committeeID, String saldo) {
        Map<String, Object> parametersPersonInCommittee = new HashMap();
        parametersPersonInCommittee.put("person_id", id);
        parametersPersonInCommittee.put("committee_id",committeeID);
        parametersPersonInCommittee.put("saldo", saldo);
        addData("ProductInCommittee", parametersPersonInCommittee);
    }

    


    //String getters for Tables

    public List<String> getProduct(String id) {
        return extractAttributes(getData("Product", Integer.parseInt(id)));
    }

    public List<String> getUser(String id) {
        return extractAttributes(getData("Person", Integer.parseInt(id)));
    }

    public List<String> getCommittee(String id) {
        return extractAttributes(getData("Committee", Integer.parseInt(id)));
    }

    // String getter for which committees (every row in PersonInCommittee with the corresponding ID) a user is in.
    public List<String> getPersonInCommittee(String id) {
        return extractAttributes(getData("PersonInCommittee", Integer.parseInt(id)));
    }

    //Update methods






    private List<String> extractAttributes(List<Map<String, Object>> attributes) {
         List<String> returnList = new ArrayList<>();

        Map<String, Object> attributesObject = attributes.get(0);
        for (Map.Entry<String, Object> obj : attributesObject.entrySet()) {
            returnList.add(obj.getValue().toString());
        }
        return returnList;
    }

    private String generateInsertSQL(String tableName, Map<String, Object> data) {
        StringBuilder sqlBuilder = new StringBuilder("INSERT INTO ");
        sqlBuilder.append(tableName).append(" (");

        // Append column names
        for (String columnName : data.keySet()) {
            sqlBuilder.append(columnName).append(", ");
        }

        // Remove the trailing comma and space
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(") VALUES (");

        // Append placeholders for values
        for (int i = 0; i < data.size(); i++) {
            sqlBuilder.append("?, ");
        }

        // Remove the trailing comma and space
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(")");

        return sqlBuilder.toString();
    }

    private String generateUpdateSQL(String tableName, int id, Map<String, Object> updatedData) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
        sqlBuilder.append(tableName).append(" SET ");

        // Append updated column names and placeholders
        for (String columnName : updatedData.keySet()) {
            sqlBuilder.append(columnName).append(" = ?, ");
        }

        // Remove the trailing comma and space
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(" WHERE id = ").append(id);

        return sqlBuilder.toString();
    }



    private class ResultSetConverter {

        private static List<Map<String, Object>>convertResultSetToList(ResultSet resultSet) throws SQLException {
            List<Map<String, Object>> resultList = new ArrayList<>();

            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();

            while (resultSet.next()) {
                Map<String, Object> row = new HashMap<>();
                for (int i = 1; i <= columnCount; i++) {
                    String columnName = metaData.getColumnName(i);
                    Object columnValue = resultSet.getObject(i);
                    row.put(columnName, columnValue);
                }
                resultList.add(row);
            }

            return resultList;
        }
    }
}
