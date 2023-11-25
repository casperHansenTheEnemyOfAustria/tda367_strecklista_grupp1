package se.cholmers.backend;

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
    if (instance == null){
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

  /**
   * Executes an update query.
   * @param query The query to execute.
   */
  public void executeUpdate(String query) {
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      statement.executeUpdate();
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  /**
   * Executes a query and returns a map with column names as keys and lists of values as values.
   * @param query The query to execute.
   * @param params The parameters to the query.
   * @return A map with column names as keys and lists of values as values.
   */
  public Map<String, List<String>> executeQuery(String query, List<String> params) {
    Map<String, List<String>> columnsWithValues = new HashMap<>();
    try (PreparedStatement statement = connection.prepareStatement(query)) {
      // Add parameters to query
      for (int i = 0; i < params.size(); i++) {
        statement.setString(i + 1, params.get(i));
      }
      try (ResultSet resultSet = statement.executeQuery()) {
        // Convert ResultSet to map
        columnsWithValues = ResultSetConverter.convert(resultSet);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    }
    return columnsWithValues;
  }

  /**
   * Inserts data into a table using executeUpdate.
   * @param tableName The name of the table to insert data into.
   * @param columnsWithData A map with column names as keys and lists of values as values.
   */
  private String generateInsertSQL(String tableName, Map<String, List<String>> columnsWithData) {
    StringBuilder sql = new StringBuilder("INSERT INTO " + tableName + " (");
    // Add column names
    for (String columnName : columnsWithData.keySet()) {
      sql.append(columnName).append(", ");
    }
    // After the last column name, remove the last comma and space
    sql.delete(sql.length() - 2, sql.length());
    sql.append(") VALUES (");
    // Add values in the correct order
    for (int i = 0; i < columnsWithData.values().iterator().next().size(); i++) {
      for (List<String> columnValues : columnsWithData.values()) {
        sql.append(columnValues.get(i)).append(", ");
      }
      // After the last value, remove the last comma and space
      sql.delete(sql.length() - 2, sql.length());
      sql.append("), (");
    }
    // After inserting the values for the last row, remove the last comma and space
    sql.delete(sql.length() - 3, sql.length());
    sql.append(";");
    return sql.toString();
  }

  /**
   * Updates data in a table.
   * @param tableName The name of the table to update data in.
   * @param columnsWithData A map with column names as keys and lists of values as values.
   */
  private String generateUpdateSQL(String tableName, Map<String, List<String>> columnsWithData) {
    StringBuilder sql = new StringBuilder("UPDATE " + tableName + " SET ");
    // Add column names and values
    for (String columnName : columnsWithData.keySet()) {
      sql.append(columnName).append(" = ").append(columnsWithData.get(columnName).get(0)).append(", ");
    }
    // After the last column name and value, remove the last comma and space
    sql.delete(sql.length() - 2, sql.length());
    sql.append(" WHERE ");
    // Add column names and values for the WHERE clause
    for (String columnName : columnsWithData.keySet()) {
      sql.append(columnName).append(" = ").append(columnsWithData.get(columnName).get(1)).append(" AND ");
    }
    // After the last column name and value for the WHERE clause, remove the last AND
    sql.delete(sql.length() - 5, sql.length());
    sql.append(";");
    return sql.toString();
  }

  // CRUD OPERATIONS
  // CREATE
  /**
   * Inserts data into a table.
   * @param tableName The name of the table to insert data into.
   * @param columnsWithData A map with column names as keys and lists of values as values.
   */
  private void insert(String tableName, Map<String, List<String>> columnsWithData) {
    String sql = generateInsertSQL(tableName, columnsWithData);
    executeUpdate(sql);
  }

  // READ
  /**
   * Selects data from a table.
   * @param tableName The name of the table to select data from.
   * @param columns The columns to select.
   * @param whereClause The WHERE clause.
   * @return A map with column names as keys and lists of values as values.
   */
  private Map<String, List<String>> select(String tableName, List<String> columns, String whereClause) {
    StringBuilder sql = new StringBuilder("SELECT ");
    // Add columns
    for (String column : columns) {
      sql.append(column).append(", ");
    }
    // After the last column, remove the last comma and space
    sql.delete(sql.length() - 2, sql.length());
    sql.append(" FROM ").append(tableName);
    // Add WHERE clause
    if (whereClause != null) {
      sql.append(" WHERE ").append(whereClause);
    }
    sql.append(";");
    return executeQuery(sql.toString(), new ArrayList<>());
  }

  // UPDATE
  /**
   * Updates data in a table.
   * @param tableName The name of the table to update data in.
   * @param columnsWithData A map with column names as keys and lists of values as values.
   */
  private void update(String tableName, Map<String, List<String>> columnsWithData) {
    String sql = generateUpdateSQL(tableName, columnsWithData);
    executeUpdate(sql);
  }

  // DELETE
  /**
   * Deletes data from a table.
   * @param tableName The name of the table to delete data from.
   * @param whereClause The WHERE clause.
   */
  private void delete(String tableName, String whereClause) {
    StringBuilder sql = new StringBuilder("DELETE FROM ").append(tableName);
    // Add WHERE clause
    if (whereClause != null) {
      sql.append(" WHERE ").append(whereClause);
    }
    sql.append(";");
    executeUpdate(sql.toString());
  }
}
