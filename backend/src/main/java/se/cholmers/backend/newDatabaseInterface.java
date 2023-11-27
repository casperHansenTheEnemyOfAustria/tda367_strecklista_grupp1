package se.cholmers.backend;

import javafx.util.Pair;
import se.cholmers.backend.Interface.IDatabaseInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//todo: implement IDatabaseInterface

public class newDatabaseInterface {

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
   * Creates a new committee using the CRUD operations.
   * @param group_name The name of the committee.
   * @param year The year of the committee.
   */
  public void createCommittee(String group_name, String year) {
    insert("committees", new HashMap<>(Map.of(
        "group_name", group_name,
        "year", year
    )));
  }

  /**
   * Creates a new product using the CRUD operations.
   * @param name The name of the product.
   * @param price The price of the product.
   * @param amount The amount of the product.
   */
  public void createProduct(String name, Float price, Integer amount) {
    insert("products", new HashMap<>(Map.of(
        "name", name,
        "price", price,
        "amount", amount
    )));
  }
  //Creates user and puts them in a committee with a saldo. Throws RequestException if user already exists or if the committee does not exist. initializes saldo to 0.
  public void createUser(String personName, String personNick, String phoneNumber, String password, String committeeId) throws RequestException {
    insert("users", new HashMap<>(Map.of(
        "name", personName,
        "nick", personNick,
        "phone_number", phoneNumber,
        "password", password
    )));
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

  public String getUserIDFromName(String nick, String password) throws RequestException {
    if (password.equals(selectSingleValue("person", "password", new Pair<>("person_nick", nick)))) {
      return selectSingleValue("person", "id", new Pair<>("person_nick", nick));
    } else {
      throw new RequestException("Wrong password");
    }
  }

  public String getUserName(String id) {
    return selectSingleValue("person", "person_name", new Pair<>("id", id));
  }

  public String getUserNick(String id) {
    return selectSingleValue("person", "person_nick", new Pair<>("id", id));
  }

  public String getUserPhoneNumber(String id) {
    return selectSingleValue("person", "phone_number", new Pair<>("id", id));
  }

  public List<String> getCommitteesOfUser(String id) {
    return selectWhere("PersonInCommittee", "person_id", id).get("committee_id");
  }

  public Float getSaldoFromUserInCommittee(String userID, String committeeID) {
    return null;
  }

  public String getCommitteeName(String id) {
    return null;
  }

  public String getCommitteeYear(String id) {
    return null;
  }

  public List<String> getProductsInCommittee(String committeeID) {
    return null;
  }

  public void putUserInCommittee(String id, String committeeID, String saldo) {

  }

  public void updateUserSaldo(String id, String committeeId, String saldo) {

  }

  public void updateProductAmount(int productID, String amount) {

  }

  /**
   * Converts a ResultSet to a map with column names as keys and lists of values as values.
   */
  private static class ResultSetConverter {
    /**
     * Converts a ResultSet to a map with column names as keys and lists of values as values.
     *
     * @param resultSet The ResultSet to convert.
     * @return A map with column names as keys and lists of values as values.
     * @throws SQLException If the ResultSet is invalid.
     */
    public static Map<String, List<String>> convert(ResultSet resultSet) throws SQLException {
      Map<String, List<String>> columnsWithValues = new HashMap<>();
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
   * @param tableName The name of the table to insert into.
   * @param columnValueMap A pair with column name as first and value as second.
   */
  public <V> void insert(String tableName, Map<String, V> columnValueMap) {
    try {
      PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(
          "INSERT INTO ?(?) VALUES (?)"
      );
      preparedStatement.setString(1, tableName);
      StringBuilder columns = new StringBuilder();
      for (String column : columnValueMap.keySet()) {
        columns.append(column).append(", ");
      }
      columns.delete(columns.length() - 2, columns.length() - 1);
      preparedStatement.setString(2, columns.toString());
      StringBuilder values = new StringBuilder();
      if (columnValueMap.values().iterator().next() instanceof String) {
        for (String value : (List<String>) columnValueMap.values()) {
          values.append("'").append(value).append("', ");
        }
      } else {
        for (V value : columnValueMap.values()) {
          values.append(value).append(", ");
        }
      }


    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Read
  /**
   * Selects all rows from the table.
   * @param tableName The name of the table to select from.
   * @return A map with column names as keys and lists of values as values.
   */
  public Map<String, List<String>> selectAll(String tableName) {
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
   * @param tableName The name of the table to select from.
   * @param columnName The name of the column to select from.
   * @param value The value to select.
   * @return A map with column names as keys and lists of values as values.
   */
  public Map<String, List<String>> selectWhere(String tableName, String columnName, String value) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(
          "SELECT * FROM ? WHERE ? = ?"
      );
      preparedStatement.setString(1, tableName);
      preparedStatement.setString(2, columnName);
      preparedStatement.setString(3, value);
      ResultSet resultSet = preparedStatement.executeQuery();
      return ResultSetConverter.convert(resultSet);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  /**
   * Selects a single value from a cell in the table.
   * @param tableName The name of the table to select from.
   * @param columnName The name of the column to select from.
   * @param columnValuePair A pair with column name as first and value as second.
   */
  public String selectSingleValue(String tableName, String columnName, Pair<String, String> columnValuePair) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(
          "SELECT ? FROM ? WHERE ? = ?"
      );
      preparedStatement.setString(1, columnName);
      preparedStatement.setString(2, tableName);
      preparedStatement.setString(3, columnValuePair.getKey());
      preparedStatement.setString(4, columnValuePair.getValue());
      ResultSet resultSet = preparedStatement.executeQuery();
      return resultSet.getString(1);
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return null;
  }

  // Update
  /**
   * Updates a row in the table.
   * @param tableName The name of the table to update.
   * @param updatedColumnValuePair A pair with column name as first and value as second.
   *                        This contains the column to update and the new value.
    * @param columnValuePair A pair with column name as first and value as second.
   *                        This contains the data to select the row to update.
   */
  public void update(String tableName, Pair<String, String> updatedColumnValuePair, Pair<String, String> columnValuePair) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(
          "UPDATE ? SET ? = ? WHERE ? = ?"
      );
      preparedStatement.setString(1, tableName);
      preparedStatement.setString(2, updatedColumnValuePair.getKey());
      preparedStatement.setString(3, updatedColumnValuePair.getValue());
      preparedStatement.setString(4, columnValuePair.getKey());
      preparedStatement.setString(5, columnValuePair.getValue());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Delete
  /**
   * Deletes a row from the table.
   * @param tableName The name of the table to delete from.
   * @param columnValuePair A pair with column name as first and value as second.
   */
  public void delete(String tableName, Pair<String, String> columnValuePair) {
    try {
      PreparedStatement preparedStatement = connection.prepareStatement(
          "DELETE FROM ? WHERE ? = ?"
      );
      preparedStatement.setString(1, tableName);
      preparedStatement.setString(2, columnValuePair.getKey());
      preparedStatement.setString(3, columnValuePair.getValue());
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  // Clear the entire database
  public void clearDatabase() {
    try {
      Statement statement = connection.createStatement();
      statement.executeUpdate("DELETE FROM Person");
      statement.executeUpdate("DELETE FROM committee");
      statement.executeUpdate("DELETE FROM product");
      statement.executeUpdate("DELETE FROM PersonInCommittee");
      statement.executeUpdate("DELETE FROM productincommittee");
      statement.executeUpdate("DELETE FROM transaction");
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
