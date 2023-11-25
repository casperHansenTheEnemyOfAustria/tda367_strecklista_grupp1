package se.cholmers.backend;

import javafx.util.Pair;
import se.cholmers.backend.Interface.IDatabaseInterface;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
   * Creates a new committee using the CRUD operations.
   * @param group_name The name of the committee.
   * @param year The year of the committee.
   */
  public void createCommittee(String group_name, String year) {
    insert("committees", new Pair<>("group_name", group_name));
    insert("committees", new Pair<>("year", year));
  }

  /**
   * Creates a new product using the CRUD operations.
   * @param name The name of the product.
   * @param price The price of the product.
   * @param committee The committee of the product.
   * @param amount The amount of the product.
   */
  public void createProduct(String name, Float price, String committee, Integer amount) {
    insert("products", new Pair<>("name", name));
    insert("products", new Pair<>("price", price));
    insert("products", new Pair<>("amount", amount));

  }

  public String createUser(String userName, String userNick, String phoneNumber, String committeeID, String saldo, String password) throws RequestException {
    return null;
  }

  public Float getProductPrice(String id) {
    return null;
  }

  public int getProductAmount(String id) {
    return 0;
  }

  public String getProductName(String id) {
    return null;
  }

  public String getUserIDFromName(String nick, String password) {
    return null;
  }

  public String getUserName(String id) {
    return null;
  }

  public String getUserNick(String id) {
    return null;
  }

  public String getUserPhoneNumber(String id) {
    return null;
  }

  public List<String> getCommitteesOfUser(String id) {
    return null;
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
   * @param columnValuePair A pair with column name as first and value as second.
   */
  public <V> void insert(String tableName, Pair<String, V> columnValuePair) {
    try {
      PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(
          "INSERT INTO ?(?) VALUES (?)"
      );
      preparedStatement.setString(1, tableName);
      preparedStatement.setString(2, columnValuePair.getKey());
      if (columnValuePair.getValue() instanceof String) {
        preparedStatement.setString(3, (String) columnValuePair.getValue());
      } else if (columnValuePair.getValue() instanceof Integer) {
        preparedStatement.setInt(3, (Integer) columnValuePair.getValue());
      } else if (columnValuePair.getValue() instanceof Float) {
        preparedStatement.setFloat(3, (Float) columnValuePair.getValue());
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
}
