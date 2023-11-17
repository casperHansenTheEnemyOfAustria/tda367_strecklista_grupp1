package se.cholmers.backend;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DatabaseInterface {

    private static DatabaseInterface instance;

    // Replace these with your actual database information
    private static final String DB_URL = "jdbc:postgresql://your_database_url";
    private static final String DB_USER = "your_database_user";
    private static final String DB_PASSWORD = "your_database_password";

    private Connection connection;

    // Private constructor to enforce singleton pattern
    private DatabaseInterface() {
        try {
            // Load the JDBC driver
            Class.forName("org.postgresql.Driver");

            // Establish the database connection
            connection = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Get the singleton instance
    public static synchronized DatabaseInterface getInstance() {
        if (instance == null) {
            instance = new DatabaseInterface();
        }
        return instance;
    }

    // ... (previous methods)

    // LIST operation
    public List<Person> getAllPersons() {
        String sql = "SELECT * FROM Person";
        List<Person> persons = new ArrayList<>();

        try (PreparedStatement statement = connection.prepareStatement(sql);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                Person person = new Person(
                        resultSet.getInt("id"),
                        resultSet.getString("phone_number"),
                        resultSet.getString("person_name"),
                        resultSet.getString("person_nick")
                );
                persons.add(person);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return persons;
    }

    // CRUD operations for Product

    // CREATE operation for Product
    public void addProduct(Product product) {
        String sql = "INSERT INTO Product (id, name, price) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, product.getId());
            statement.setString(2, product.getName());
            statement.setFloat(3, product.getPrice());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ operation for Product
    public Product getProductById(int id) {
        String sql = "SELECT * FROM Product WHERE id = ?";
        Product product = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    product = new Product(
                            resultSet.getInt("id"),
                            resultSet.getString("name"),
                            resultSet.getFloat("price")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return product;
    }

    // UPDATE operation for Product
    public void updateProduct(Product product) {
        String sql = "UPDATE Product SET name = ?, price = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, product.getName());
            statement.setFloat(2, product.getPrice());
            statement.setInt(3, product.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE operation for Product
    public void deleteProduct(int id) {
        String sql = "DELETE FROM Product WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // CRUD operations for Committee

    // CREATE operation for Committee
    public void addCommittee(Committee committee) {
        String sql = "INSERT INTO Committee (id, group_name, year) VALUES (?, ?, ?)";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, committee.getId());
            statement.setString(2, committee.getGroupName());
            statement.setString(3, committee.getYear());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // READ operation for Committee
    public Committee getCommitteeById(int id) {
        String sql = "SELECT * FROM Committee WHERE id = ?";
        Committee committee = null;

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    committee = new Committee(
                            resultSet.getInt("id"),
                            resultSet.getString("group_name"),
                            resultSet.getString("year")
                    );
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return committee;
    }

    // UPDATE operation for Committee
    public void updateCommittee(Committee committee) {
        String sql = "UPDATE Committee SET group_name = ?, year = ? WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setString(1, committee.getGroupName());
            statement.setString(2, committee.getYear());
            statement.setInt(3, committee.getId());

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // DELETE operation for Committee
    public void deleteCommittee(int id) {
        String sql = "DELETE FROM Committee WHERE id = ?";

        try (PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
