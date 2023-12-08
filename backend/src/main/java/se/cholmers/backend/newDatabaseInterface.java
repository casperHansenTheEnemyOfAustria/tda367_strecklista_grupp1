package se.cholmers.backend;

import javafx.util.Pair;
import se.cholmers.backend.Interface.IDatabaseInterface;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

//todo: implement IDatabaseInterface

public class newDatabaseInterface implements IDatabaseInterface {

    private static newDatabaseInterface instance;

    private static final String DB_URL = "jdbc:postgresql:strecklista";
    private static final String DB_USER = "postgres";
    private static final String DB_PASSWORD = "";

    private Connection connection;

    private newDatabaseInterface() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static newDatabaseInterface getInstance() {
        if (instance == null) {
            instance = new newDatabaseInterface();
        }
        return instance;
    }

    public void closeConnection() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Creates a new product using the CRUD operations.
     *
     * @param name   The name of the product.
     * @param price  The price of the product.
     * @param amount The amount of the product.
     */
    public String createProduct(String name, Float price, String committeeid, Integer amount) {
        String id = UUID.randomUUID().toString();
        try {
            insert("products", new HashMap<>(Map.of(
                    "id", id,
                    "name", name,
                    "price", price,
                    "amount", amount)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        try {
            insert("productInCommittee", new HashMap<>(Map.of(
                    "product_id", id,
                    "committee_id", committeeid)));
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Float getProductPrice(String id) {
        return Float.parseFloat(selectSingleValue("products", "price", new Pair<>("id", id)));
    }

    public int getProductAmount(String id) {
        return Integer.parseInt(selectSingleValue("products", "amount", new Pair<>("id", id)));
    }

    public String getProductName(String id) {
        return selectSingleValue("products", "name", new Pair<>("id", id));
    }

    public String authenticateUser(String nick, String password) throws RequestException {
        Map<String, List<Object>> map = selectWhere("users", "user_nick", nick);
        if (map.get("user_nick").isEmpty()) {
            throw new RequestException("User does not exist");
        }
        if (map.get("password").get(0).equals(password)) {
            return (String) map.get("id").get(0);
        } else {
            throw new RequestException("Wrong password");
        }
    }

    private String getUseridFromNick(String nick) throws RequestException {
        Map<String, List<Object>> map = selectWhere("users", "user_nick", nick);
        if (map.get("user_nick").isEmpty()) {
            throw new RequestException("User does not exist");
        }
        return (String) map.get("id").get(0);
    }

    /**
     * Creates a new committee using the CRUD operations.
     *
     * @param group_name The name of the committee.
     * @param year       The year of the committee.
     * @return the id of the committee
     * @throws RequestException if the committee already exists
     */
    public String createCommittee(String group_name, String year) throws RequestException {
        String id = UUID.randomUUID().toString();
        try{
            insert("committees", new HashMap<>(Map.of(
                    "id", id,
                    "group_name", group_name,
                    "year", year)));
        }
        catch (SQLException e){
            throw new RequestException("Committee already exists or something went wrong");
        }
        return id;
    }

    // Creates user and puts them in a committee with a saldo. Throws
    // RequestException if user already exists or if the committee does not exist.
    // initializes saldo to 0.
    public String createUser(String userName, String phoneNumber, String userNick,
                             String password) throws RequestException {
        String user_id = UUID.randomUUID().toString();
        try {
            insert("Users", new HashMap<>(Map.of(
                    "id", user_id,
                    "user_name", userName,
                    "user_nick", userNick,
                    "phone_number", phoneNumber,
                    "password", password)));
        } catch (SQLException e) {
            throw new RequestException("User already exists or something idk");
        }

        return user_id;
    }

    public String getUserName(String id) {
        return selectSingleValue("Users", "user_name", new Pair<>("id", id));
    }

    public String getUserNick(String id) {
        return selectSingleValue("Users", "user_nick", new Pair<>("id", id));
    }

    public String getUserPhoneNumber(String id) {
        return selectSingleValue("Users", "phone_number", new Pair<>("id", id));
    }

    public List<String> getCommitteesOfUser(String id) {
        List<String> rv = new ArrayList<>();
        for (Object committeeID : selectWhere("userInCommittee", "user_id", id).get("committee_id")) {
            rv.add((String) committeeID);
        }
        return rv;
    }

    public Float getSaldoFromUserInCommittee(String userid, String committeeID) {
        Float output = Float.parseFloat(selectSingleValue("userInCommittee", "saldo",
                new Pair<>("(user_id, committee_id)", userid + ", " + committeeID)));
        return output;
    }

    public String getCommitteeName(String id) {
        return selectSingleValue("committees", "group_name", new Pair<>("id", id));
    }

    public String getCommitteeYear(String id) {
        return selectSingleValue("committees", "year", new Pair<>("id", id));
    }

    public List<String> getProductsInCommittee(String committeeID) {
        List<String> rv = new ArrayList<>();
        for (Object productID : selectWhere("productInCommittee", "committee_id", committeeID).get("product_id")) {
            rv.add((String) productID);
        }
        return rv;
    }

    public void putUserInCommittee(String username, String committeeID, Float saldo) throws RequestException {
        String userid = getUseridFromNick(username);
        try{
            insert("userInCommittee", new HashMap<>(Map.of(
                    "user_id", userid,
                    "committee_id", committeeID,
                    "saldo", saldo)));
        }
        catch (SQLException e){
            throw new RequestException(e.getMessage());
        }
    }

    public void updateUserSaldo(String userid, String committeeID, String saldo) {
        update("userInCommittee", new Pair<>("saldo", saldo),
                new Pair<>("(user_id, committee_id)", userid + ", " + committeeID));
    }

    public void updateProductAmount(String productID, String amount) {
        update("products", new Pair<>("amount", amount), new Pair<>("id", productID));
    }

    /**
     * Converts a ResultSet to a map with column names as keys and lists of values
     * as values.
     */
    private static class ResultSetConverter {
        /**
         * Converts a ResultSet to a map with column names as keys and lists of values
         * as values.
         *
         * @param resultSet The ResultSet to convert.
         * @return A map with column names as keys and lists of values as values.
         * @throws SQLException If the ResultSet is invalid.
         */
        public static Map<String, List<Object>> convert(ResultSet resultSet) throws SQLException {
            Map<String, List<Object>> columnsWithValues = new HashMap<>();
            ResultSetMetaData metaData = resultSet.getMetaData();
            int columnCount = metaData.getColumnCount();
            // Add column names to map
            for (int i = 1; i <= columnCount; i++) {
                columnsWithValues.put(metaData.getColumnName(i), new ArrayList<>());
            }
            // Add values to map
            while (resultSet.next()) {
                for (int i = 1; i <= columnCount; i++) {
                    columnsWithValues.get(metaData.getColumnName(i)).add(resultSet.getString(i));
                }
            }
            return columnsWithValues;
        }
    }
    // CRUD operations
    // Create

    /**
     * Inserts a new row into the table.
     * To avoid SQL injection, use prepared statements.
     *
     * @param tableName      The name of the table to insert into.
     * @param columnValueMap A pair with column name as first and value as second.
     */
    public void insert(String tableName, Map<String, Object> columnValueMap) throws SQLException {
        StringBuilder columns = new StringBuilder();
        for (String column : columnValueMap.keySet()) {
            columns.append(column).append(", ");
        }
        columns.delete(columns.length() - 2, columns.length() - 1);
        PreparedStatement preparedStatement;
        preparedStatement = connection.prepareStatement(
                // Format string to have the correct number of question marks
                // This is based on the size of columnValueMap
                "INSERT INTO %s(%s)".formatted(tableName, columns.toString()) + "VALUES (" + "?,".repeat(columnValueMap.size() - 1) + "?)");
        int i = 1;
        for (Object value : columnValueMap.values()) {
            if (value instanceof String) {
                preparedStatement.setObject(i, value);
            }
            if (value instanceof Integer) {
                preparedStatement.setInt(i, (Integer) value);
            }
            if (value instanceof Float) {
                preparedStatement.setFloat(i, (Float) value);
            }
            i++;
        }
        System.out.println(preparedStatement.toString());
        int numExecutes = preparedStatement.executeUpdate();
        if (numExecutes == 0) {
            throw new SQLException("Insert failed, no rows affected.");
        }

    }

    // Read

    /**
     * Selects all rows from the table.
     *
     * @param tableName The name of the table to select from.
     * @return A map with column names as keys and lists of values as values.
     */
    public Map<String, List<Object>> selectAll(String tableName) {
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM " + tableName);
            return ResultSetConverter.convert(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Selects all rows from the table where the column has the specified value.
     *
     * @param tableName  The name of the table to select from.
     * @param columnName The name of the column to select from.
     * @param value      The value to select.
     * @return A map with column names as keys and lists of values as values.
     */
    public Map<String, List<Object>> selectWhere(String tableName, String columnName, String value) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT * FROM %s WHERE %s = ?".formatted(tableName, columnName));
            preparedStatement.setString(1, value);
            ResultSet resultSet = preparedStatement.executeQuery();
            System.out.println(resultSet.getFetchSize());
            return ResultSetConverter.convert(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Selects a single value from a cell in the table.
     *
     * @param tableName       The name of the table to select from.
     * @param columnName      The name of the column to select from.
     * @param columnValuePair A pair with column name as first and value as second.
     */
    public String selectSingleValue(String tableName, String columnName, Pair<String, String> columnValuePair) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "SELECT %s FROM %s WHERE %s = ?".formatted(columnName, tableName, columnValuePair.getKey()));
            preparedStatement.setString(1, columnValuePair.getValue());
            String rv = null;
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                System.out.println(resultSet.getString(1));
                rv = resultSet.getString(1);
            }
            return rv;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Update

    /**
     * Updates a row in the table.
     *
     * @param tableName              The name of the table to update.
     * @param updatedColumnValuePair A pair with column name as first and value as
     *                               second.
     *                               This contains the column to update and the new
     *                               value.
     * @param columnValuePair        A pair with column name as first and value as
     *                               second.
     *                               This contains the data to select the row to
     *                               update.
     */
    private void update(String tableName, Pair<String, String> updatedColumnValuePair,
                        Pair<String, String> columnValuePair) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "UPDATE ? SET ? = ? WHERE ? = ?");
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, updatedColumnValuePair.getKey());
            preparedStatement.setString(3, updatedColumnValuePair.getValue());
            preparedStatement.setString(4, columnValuePair.getKey());
            preparedStatement.setString(5, columnValuePair.getValue());
            int numExecutes = preparedStatement.executeUpdate();
            if (numExecutes == 0) {
                throw new SQLException("Update failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete

    /**
     * Deletes a row from the table.
     *
     * @param tableName       The name of the table to delete from.
     * @param columnValuePair A pair with column name as first and value as second.
     */
    public void delete(String tableName, Pair<String, String> columnValuePair) {
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(
                    "DELETE FROM ? WHERE ? = ?");
            preparedStatement.setString(1, tableName);
            preparedStatement.setString(2, columnValuePair.getKey());
            preparedStatement.setString(3, columnValuePair.getValue());
            int numExecutes = preparedStatement.executeUpdate();
            if (numExecutes == 0) {
                throw new SQLException("Delete failed, no rows affected.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Clear the entire database
    public void clearDatabase() {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate("DELETE FROM users");
            statement.executeUpdate("DELETE FROM committees");
            statement.executeUpdate("DELETE FROM products");
            statement.executeUpdate("DELETE FROM userInCommittee");
            statement.executeUpdate("DELETE FROM productincommittee");
            statement.executeUpdate("DELETE FROM transaction");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public List<String> getOrder(String committeeID, LocalDateTime orderTime) {
        List<String> rv = new ArrayList<>();
        List<Object> committeeTransactions = getCommitteeTransactions(committeeID);
        for(Object transactionId : committeeTransactions){
            LocalDateTime transactionTime = getTransactionDateTime((String) transactionId);

            rv = getTransactionProducts((String) transactionId);
            
        }
        return rv;
    }

    private List<String> getTransactionProducts(String transactionID){
        List<String> rv = new ArrayList<>();
        List<Object> transactionProducts = selectWhere("transaction", "transaction_id", transactionID).get("product_id");
        for(Object product : transactionProducts){
            rv.add((String) product);
        }
        return rv;
    }

    private List<Object> getCommitteeTransactions(String committeeID) {
        List<Object> committeeTransactions = selectWhere("transaction", "committee_id", committeeID).get("id");
        return committeeTransactions;
    }

    @Override
    public List<Map<LocalDateTime, List<String>>> getAllOrders(String committeeID) {
        List<Object> committeeTransactions = getCommitteeTransactions(committeeID);
        List<Map<LocalDateTime, List<String>>> rv = new ArrayList<>();
        for(Object transactionId : committeeTransactions){
            LocalDateTime transactionTime = getTransactionDateTime((String) transactionId);
            Map<LocalDateTime, List<String>> map = new HashMap<>();
            map.put(transactionTime, getTransactionProducts(committeeID));
            rv.add(map);
        }
        return rv;
    }

    private LocalDateTime getTransactionDateTime(String transactionId) {
        Time time = Time.valueOf(selectSingleValue("transaction", "transaction_time", new Pair<>("id",  transactionId)));
        Date date = Date.valueOf(selectSingleValue("transaction", "transaction_date", new Pair<>("id",  transactionId)));
        LocalDateTime transactionTime = LocalDateTime.of(date.toLocalDate(), time.toLocalTime());
        return transactionTime;
    }

    @Override
    public void addOrder(String groupID, LocalDateTime timeStamp, List<String> products) {
        for(String product : products){
            try{
                insert("transaction", new HashMap<>(Map.of(
                        "id", UUID.randomUUID().toString(),
                        "product_id", product,
                        "committee_id", groupID,
                        "transaction_time", timeStamp.toLocalTime(),
                        "transaction_date", timeStamp.toLocalDate())));
            }
            catch (SQLException e){
                throw new NullPointerException(e.getMessage());
            }
        }
    }
}
