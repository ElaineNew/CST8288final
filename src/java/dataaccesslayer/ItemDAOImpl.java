/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dataaccesslayer;

/**
 *
 * @author User
 */

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Item;

public class ItemDAOImpl implements ItemDAO {
    private final Connection connection;

    public ItemDAOImpl() {
        connection = DBConnection.getConnection();
    }

    /**
     *
     * @param item
     * @return
     */
    @Override
    public boolean addItem(Item item) {
    try {
        String query = "INSERT INTO items (name, quantity, expiration_date) VALUES (?, ?, ?)";
        PreparedStatement preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, item.getName());
        preparedStatement.setInt(2, item.getQuantity());
        preparedStatement.setDate(3, new java.sql.Date(item.getExpirationDate().getTime()));

        int rowsInserted = preparedStatement.executeUpdate();
        return rowsInserted > 0;
    } catch (SQLException e) {
        e.printStackTrace();
        return false;
    }
}


    @Override
    public boolean updateItemQuantity(int itemId, int newQuantity) {
        try {
            String query = "UPDATE items SET quantity = ? WHERE id = ?";
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setInt(1, newQuantity);
            preparedStatement.setInt(2, itemId);

            int rowsUpdated = preparedStatement.executeUpdate();
            return rowsUpdated > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public List<Item> getItems() {
        List<Item> items = new ArrayList<>();
        try {
            String query = "SELECT * FROM items";
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(query);

            while (resultSet.next()) {
                Item item = new Item();
                item.setId(resultSet.getInt("id"));
                item.setName(resultSet.getString("name"));
                item.setQuantity(resultSet.getInt("quantity"));
                item.setExpirationDate(resultSet.getDate("expiration_date"));
                items.add(item);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return items;
    }

    @Override
public List<Item> getSurplusItems() {
    // Implement logic to identify surplus items (items nearing expiration or excess of demand)
    // For example, you can filter items based on expiration date
    // You can modify the SQL query to retrieve items nearing expiration or in excess of demand
    List<Item> surplusItems = new ArrayList<>();
    try {
        String query = "SELECT * FROM items WHERE expiration_date <= DATE_ADD(CURDATE(), INTERVAL 7 DAY)";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);

        while (resultSet.next()) {
            Item item = new Item();
            item.setId(resultSet.getInt("id"));
            item.setName(resultSet.getString("name"));
            item.setQuantity(resultSet.getInt("quantity"));
            item.setExpirationDate(resultSet.getDate("expiration_date"));
            surplusItems.add(item);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
    return surplusItems;
}

}
