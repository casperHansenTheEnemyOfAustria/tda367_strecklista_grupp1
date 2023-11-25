package se.cholmers.backend;

import ch.qos.logback.core.joran.sanity.Pair;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
  public void insert(String tableName, Pair<String, String> columnValuePair) {
    try {
      PreparedStatement preparedStatement;
      preparedStatement = connection.prepareStatement(
          "INSERT INTO ?(?) VALUES (?)"
      );
      preparedStatement.setString(1, tableName);
      preparedStatement.setString(2, columnValuePair.first);
      preparedStatement.setString(3, columnValuePair.second);
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
      preparedStatement.setString(2, updatedColumnValuePair.first);
      preparedStatement.setString(3, updatedColumnValuePair.second);
      preparedStatement.setString(4, columnValuePair.first);
      preparedStatement.setString(5, columnValuePair.second);
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
      preparedStatement.setString(2, columnValuePair.first);
      preparedStatement.setString(3, columnValuePair.second);
      preparedStatement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }
}
