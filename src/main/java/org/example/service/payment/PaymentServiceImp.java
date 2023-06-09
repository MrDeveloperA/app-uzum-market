package org.example.service.payment;

import org.example.entities.Payment;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaymentServiceImp implements PaymentService{
    @Override
    public int create(Payment payment) throws SQLException {
        var connection = getConnection();
        String query = "select add_payment(?,?)";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,payment.getCardId());
        preparedStatement.setDouble(2,payment.getPrice());
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
        String query = "delete from payment where = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1,id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result>0;
    }

    @Override
    public Payment get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from payment where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            var payment = Payment.builder()
                    .id(resultSet.getInt("id"))
                    .cardId(resultSet.getInt("card_id"))
                    .price(resultSet.getDouble("price"))
                    .createdDate(resultSet.getTimestamp("created_date"))
                    .build();
            return payment;
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Payment> getAll() throws SQLException {
        List<Payment> payments = new ArrayList<>();
        var connection = getConnection();
        String query = "select * from payment";
        var preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            var payment = Payment.builder()
                    .id(resultSet.getInt("id"))
                    .cardId(resultSet.getInt("card_id"))
                    .price(resultSet.getDouble("price"))
                    .createdDate(resultSet.getTimestamp("created_date"))
                    .build();
            payments.add(payment);
        }
        preparedStatement.close();
        connection.close();
        return payments;
    }

    @Override
    public boolean update(int id, Payment payment) throws SQLException {
        var connection = getConnection();
        String query = "update payment set card_id = ?, price = ?, created_date = ? where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, payment.getId());
        preparedStatement.setDouble(2, payment.getPrice());
        preparedStatement.setTimestamp(3, payment.getCreatedDate());
        preparedStatement.setInt(4, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }
}
