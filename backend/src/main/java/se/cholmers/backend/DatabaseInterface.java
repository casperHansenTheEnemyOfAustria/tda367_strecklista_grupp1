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



    //Custom CREATE methods
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


    //READS

    //PRODUCT

    /**
     * Returns a list of attributes of a product
     * 
     * @param id product id
     * @return {id, name, price, amount}
     * @throws IllegalArgumentException if product does not exist
     */
    private List<String> getProduct(String id) {
        return extractAttributes(getData("Product", Integer.parseInt(id)));
    }

    /**
     * Returns the price of a Product
     * Precondition: product exists
     * @param id product id
     * @return the price
     * @throws IllegalArgumentException if product does not exist
     */
    public int getProductPrice(String id) {
        return Integer.parseInt(getProduct(id).get(2));
    }

    /**
     * Returns the amount of a product
     * precondition: product exists
     * @param id the product id
     * @return the amount
     * @throws IllegalArgumentException if product does not exist
     */
    public int getProductAmount(String id) {
        return Integer.parseInt(getProduct(id).get(3));
    }

    /**
     * Returns the name of a product
     * Precondition: product exists
     * @param id the product id
     * @return the name
     * @throws IllegalArgumentException if product does not exist
     */
    public String getProductName(String id) {
        return getProduct(id).get(1);
    }

    //USER

    /**
     * Returns a list of attributes of a user
     * Precondition: user exists
     * @param id
     * @return {id, phone_number, person_name, person_nick}
     * @throws IllegalArgumentException if user does not exist
     */
    private List<String> getUser(String id) {
        return extractAttributes(getData("Person", Integer.parseInt(id)));
    }

    /**
     * Returns the name of a user
     * Precondition: user exists
     * @param id the user id
     * @return the name
     * @throws IllegalArgumentException if user does not exist
     */
    public String getUserName(String id) {
        return getUser(id).get(2);
    }

    /**
     * Returns the nick of a user
     * Precondition: user exists
     * @param id the user id
     * @return the nick
     * @throws IllegalArgumentException if user does not exist
     */
    public String getUserNick(String id) {
        return getUser(id).get(3);
    }

    /**
     * Returns the phone number of a user
     * Precondition: user exists
     * @param id the user id
     * @return the phone number
     * @throws IllegalArgumentException if user does not exist
     */
    public String getUserPhoneNumber(String id) {
        return getUser(id).get(1);
    }

    /**
     * Returns the committees of a user
     * Precondition: user exists users committees exist
     * @param id
     * @return
     * 
     * postcondition: returns a list of committee ids
     */
    public List<String> getCommitteesOfUser(String id){
        String sql = "SELECT * FROM PersonInCommittee WHERE person_id = ?";
        List<Object> params = List.of(id);
        return extractAttributes(executeQuery(sql, params));
    }
    
    /**
     * Returns the saldo of a user in a committee
     * Precondition: user exists, committee exists, user is in committee
     * @param userID
     * @param committeeID
     * @return
     * @throws IllegalArgumentException if user or the committee does not exist
     */
    public Float getSaldoFromUserInCommittee(String userID, String committeeID) {
        String sql = "SELECT saldo FROM PersonInCommittee WHERE person_id = ? AND committee_id = ?";
        List<Object> params = List.of(userID, committeeID);
        return Float.parseFloat(extractAttributes(executeQuery(sql, params)).get(0));
    }

    //COMMITTEE
    /**
     * Returns a list of attributes of a committee
     * Precondition: committee exists
     * @param id
     * @return {id, name, year}
     * @throws IllegalArgumentException if committee does not exist
     */
    private List<String> getCommittee(String id) {
        return extractAttributes(getData("Committee", Integer.parseInt(id)));
    }

    /**
     * Returns the name of a committee
     * @param id the committee id
     * @return the name
     * @throws IllegalArgumentException if committee does not exist
     */
    public String getCommitteeName(String id) {
        return getCommittee(id).get(1);
    }

    /**
     * Returns the year of a committee
     * @param id the committee id
     * @return the year
     * @throws IllegalArgumentException if committee does not exist
     */
    public String getCommitteeYear(String id) {
        return getCommittee(id).get(2);
    }



    /**
     * Returns a list of all the products in a committee
     * Precondition: The committee exists and the committee has products that exist
     * @param committeeID
     * @return List of productIDs
     * @throws IllegalArgumentException if committee does not exist
     * Postcondition: A list of productIDs is returned
     */
    public List<String> getProductsInCommittee(String committeeID) {
        String sql = "SELECT * FROM ProductInCommittee WHERE committee_id = ?";
        List<Object> params = List.of(committeeID);
        return extractAttributes(executeQuery(sql, params));
    }


    //UPDATE methods

    /**
     * Adds an existing user to a committe
     * 
     * Precondition: The user and committee exists
     * 
     * @param id
     * @param committeeID
     * @param saldo
     * @throws IllegalArgumentException if user or committee does not exist
     * 
     * Postcondition: The user is added to the committee
     */
    public void putUserInCommittee(String id, String committeeID, String saldo) {
        Map<String, Object> parametersPersonInCommittee = new HashMap();
        parametersPersonInCommittee.put("person_id", id);
        parametersPersonInCommittee.put("committee_id",committeeID);
        parametersPersonInCommittee.put("saldo", saldo);
        addData("ProductInCommittee", parametersPersonInCommittee);
    }


    /**
     * Updates the saldo of a user in a committee
     * Precondition: The user and committee exists
     * @param id
     * @param committeeId
     * @param saldo
     * @throws IllegalArgumentException if user or committee does not exist
     * Postcondition: The saldo of the user in the committee is updated
     */
    public void updateUserSaldo(String id, String committeeId, String saldo) {
        String sql = "UPDATE ProductInCommittee SET saldo = ? WHERE person_id = ? AND committee_id = ?";
        List<Object> params = List.of(saldo, id, committeeId);
        executeUpdate(sql, params);
    }

    //SQL generators

    //TODO refactor
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

    private String generateUpdateSQL(String tableName, int[] ids, Map<String, Object> updatedData) {
        StringBuilder sqlBuilder = new StringBuilder("UPDATE ");
        sqlBuilder.append(tableName).append(" SET ");

        // Append updated column names and placeholders
        for (String columnName : updatedData.keySet()) {
            sqlBuilder.append(columnName).append(" = ?, ");
        }

        // Remove the trailing comma and space
        sqlBuilder.setLength(sqlBuilder.length() - 2);

        sqlBuilder.append(" WHERE id = ").append(id);
        if (ids.length > 1) {
            sqlBuilder.append(" AND ")
        }

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
