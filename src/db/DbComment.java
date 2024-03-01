package db;

import model.Comment;
import model.Item;
import model.User;

import java.sql.*;
import java.util.ArrayList;

public class DbComment {

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

    public static ArrayList<Comment> getComments(int itemId) {

        ArrayList<Comment> comments = new ArrayList<>();

        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT co.id, co.comment, co.post_date, co.item_id, co.user_id, u.full_name " +
                    "FROM comments co " +
                    "INNER JOIN users u ON u.id = co.user_id " +
                    "WHERE co.item_id = ? " +
                    "ORDER BY co.post_date DESC");

            statement.setLong(1, itemId);

            ResultSet result = statement.executeQuery();

            while (result.next()) {

                Comment comment = new Comment();
                comment.setId(result.getLong("id"));
                comment.setComment(result.getString("comment"));
                comment.setPostDate(result.getTimestamp("post_date").toLocalDateTime());
                User user = new User();
                user.setId(result.getLong("user_id"));
                user.setFullName(result.getString("full_name"));
                comment.setUser(user);

                Item item = new Item();
                item.setId(result.getLong("item_id"));
                comment.setItem(item);
                comments.add(comment);
            }
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return comments;
    }

    public static void addComment(Comment comment) {
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "INSERT INTO comments (comment, user_id, item_id, post_date) " +
                    "VALUES (?, ?, ?, pg_catalog.now())");

            statement.setString(1, comment.getComment());
            statement.setLong(2, comment.getUser().getId());
            statement.setLong(3, comment.getItem().getId());
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
