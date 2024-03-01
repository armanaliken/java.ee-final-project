package db;

import model.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class DbManager {
    private static final Connection connection;

    static {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection(
                    "jdbc:postgresql://localhost:5432/bitlab?currentSchema=final",
                    "postgres",
                    "Arman!05"
            );
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static void addUser(User user) {
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "INSERT INTO users (email, password, full_name, role_id) " +
                    "VALUES (?, ?, ?, ?)");

            statement.setString(1, user.getEmail());
            statement.setString(2, user.getPassword());
            statement.setString(3, user.getFullName());
            statement.setInt(4, user.getRoleId());
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static User getUser(String email) {
        User user = null;
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT * FROM users WHERE email = ?");
            statement.setString(1, email);
            var result = statement.executeQuery();

            if (result.next()) {
                user = new User();
                user.setId(result.getLong("id"));
                user.setEmail(result.getString("email"));
                user.setPassword(result.getString("password"));
                user.setFullName(result.getString("full_name"));
                user.setRoleId(result.getInt("role_id"));
            }
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
            }
        return user;
        }

    public static void updateUser(User user) {
        try {
            String query = "UPDATE users SET password = ?, full_name = ? WHERE id = ?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, user.getPassword());
            statement.setString(2, user.getFullName());
            statement.setLong(3, user.getId());
            statement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
            throw new RuntimeException("Failed to update user.");
        }
    }
}