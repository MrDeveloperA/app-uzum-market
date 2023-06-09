package org.example.service.order;

import org.example.entities.Order;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class OrderServiceImp implements OrderService{
    @Override
    public int create(Order order) throws SQLException {
        var connection = getConnection();
        String query = "select create_order(?,?,?)";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,order.getUserId());
        preparedStatement.setInt(2,order.getProductId());
        preparedStatement.setInt(3,order.getAmountProduct());
        var resultSet = preparedStatement.executeQuery();
        var result = -1;
        if(resultSet.next()){
            result = resultSet.getInt(1);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        var connection = getConnection();
        String query = "delete from orders where = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result>0;
    }

    @Override
    public Order get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from orders where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            var order = Order.builder()
                    .id(resultSet.getInt("id"))
                    .userId(resultSet.getInt("user_id"))
                    .productId(resultSet.getInt("product_id"))
                    .amountProduct(resultSet.getInt("amount"))
                    .build();
            return order;
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Order> getAll() throws SQLException {
        List<Order> orders = new ArrayList<>();
        var connection = getConnection();
        String query = "select * from orders";
        var preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            var order = Order.builder()
                    .id(resultSet.getInt("id"))
                    .userId(resultSet.getInt("user_id"))
                    .productId(resultSet.getInt("product_id"))
                    .amountProduct(resultSet.getInt("amount"))
                    .build();
            orders.add(order);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return orders;
    }

    @Override
    public boolean update(int id, Order order) throws SQLException {
        var connection = getConnection();
        String query = "update orders set user_id = ?, product_id = ?, amount = ? where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, order.getUserId());
        preparedStatement.setInt(2, order.getProductId());
        preparedStatement.setInt(3, order.getAmountProduct());
        preparedStatement.setInt(4, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }
}
