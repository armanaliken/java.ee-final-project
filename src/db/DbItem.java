package db;

import model.Item;
import model.ItemCategory;

import java.sql.*;
import java.util.ArrayList;

public class DbItem {
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

    public static ArrayList<Item> getItem() {
        ArrayList<Item> items = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT i.id, i.title, i.content, i.category_id, c.name, i.post_date " +
                    "FROM bitlab.final.items i " +
                    "INNER JOIN bitlab.final.items_categories c ON c.id = i.category_id " +
                    "ORDER BY i.post_date DESC ");

            ResultSet result = statement.executeQuery();
            while (result.next()) {

                Item i = new Item();
                i.setId(result.getLong("id"));
                i.setPostDate(result.getTimestamp("post_date").toLocalDateTime());
                i.setTitle(result.getString("title"));
                i.setContent(result.getString("content"));

                ItemCategory c = new ItemCategory();
                c.setId(result.getLong("id"));
                c.setName(result.getString("name"));
                i.setItemCategory(c);
                items.add(i);
            }

            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return items;
    }

    public static Item getItemById(int id) {
        Item item = null;
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT i.id, i.title, i.content, i.category_id, c.name, i.post_date " +
                    "FROM bitlab.final.items i " +
                    "INNER JOIN bitlab.final.items_categories c ON c.id = i.category_id " +
                    "WHERE i.id = ? ");

            statement.setLong(1, id);

            ResultSet result = statement.executeQuery();
            if (result.next()) {

                item = new Item();
                item.setId(result.getLong("id"));
                item.setPostDate(result.getTimestamp("post_date").toLocalDateTime());
                item.setTitle(result.getString("title"));
                item.setContent(result.getString("content"));

                ItemCategory c = new ItemCategory();
                c.setId(result.getLong("id"));
                c.setName(result.getString("name"));
                item.setItemCategory(c);

            }

            statement.close();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return item;
    }

    public static void addItem(Item item) {


        try {

            var statement = connection.prepareStatement(" " +
                    "INSERT INTO bitlab.final.items(post_date, category_id, title, content) " +
                    "VALUES (pg_catalog.now(), ?, ?, ?)");

            statement.setLong(1, item.getItemCategory().getId());
            statement.setString(2, item.getTitle());
            statement.setString(3, item.getContent());
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void deleteItem(int id) {
        try {
            PreparedStatement statement = connection.prepareStatement(" " +
                    "DELETE FROM bitlab.final.items WHERE id = ?");

            statement.setLong(1, id);
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static void updateItem(Item item) {
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "UPDATE bitlab.final.items i " +
                    "SET i.title = ?, i.content = ?, i.category_id = ? " +
                    "WHERE i.id = ? "
            );


            statement.setString(1, item.getTitle());
            statement.setString(2, item.getContent());
            statement.setLong(3, item.getItemCategory().getId());
            statement.setLong(4, item.getId());
            statement.executeUpdate();
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }



    public static ArrayList<ItemCategory> getCategories() {
        ArrayList<ItemCategory> category = new ArrayList<>();
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT * FROM bitlab.final.items_categories ORDER BY name ASC");

            ResultSet result = statement.executeQuery();
            while (result.next()) {
                ItemCategory itemCategory = new ItemCategory();
                itemCategory.setId(result.getLong("id"));
                itemCategory.setName(result.getString("name"));

                category.add(itemCategory);
            }
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return category;
    }


    public static ItemCategory getCategory(int id) {

        ItemCategory category = null;
        try {

            PreparedStatement statement = connection.prepareStatement(" " +
                    "SELECT * FROM bitlab.final.items_categories WHERE id = ?");
            statement.setLong(1, id);
            ResultSet result = statement.executeQuery();

            if (result.next()) {
                category = new ItemCategory();
                category.setName(result.getString("name"));
                category.setId(result.getLong("id"));
            }
            statement.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return category;
    }
}

