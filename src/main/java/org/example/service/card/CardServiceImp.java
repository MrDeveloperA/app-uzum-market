package org.example.service.card;


import org.example.entities.Card;
import org.example.enums.CardType;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class CardServiceImp implements CardService {
    @Override
    public int create(Card card) throws SQLException {
        var connection = getConnection();
        String query = "select add_card(?,?,?,?,?)";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, card.getSerialNumber());
        preparedStatement.setString(2, card.getCardType().name());
        preparedStatement.setTimestamp(3, card.getDate());
        preparedStatement.setInt(4, card.getPassword());
        preparedStatement.setDouble(5, card.getBalance());
        ResultSet resultSet = preparedStatement.executeQuery();
        var result = -1;
        if (resultSet.next())
            result = resultSet.getInt(1);
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return result;
    }

    @Override
    public boolean delete(int id) throws SQLException {
        var connection = getConnection();
        String query = "delete from card where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Card get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from users where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            var card = Card.builder()
                    .id(resultSet.getInt("id"))
                    .serialNumber(resultSet.getString("serial_number"))
                    .cardType(CardType.getCardType(resultSet.getString("card_type")))
                    .date(resultSet.getTimestamp("expiration_date"))
                    .password(resultSet.getInt("password"))
                    .balance(resultSet.getDouble("balance"))
                    .build();
            return card;
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Card> getAll() throws SQLException {
        List<Card> cards = new ArrayList<>();
        var connection = getConnection();
        String query = "select * from card";
        var preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            var card = Card.builder()
                    .id(resultSet.getInt("id"))
                    .serialNumber(resultSet.getString("serial_number"))
                    .cardType(CardType.getCardType(resultSet.getString("card_type")))
                    .date(resultSet.getTimestamp("expiration_date"))
                    .password(resultSet.getInt("password"))
                    .balance(resultSet.getDouble("balance"))
                    .build();
            cards.add(card);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return cards;
    }

    @Override
    public boolean update(int id, Card card) throws SQLException {
        var connection = getConnection();
        String query = "update card set serial_number = ?, card_type = ? card_date = ?, password = ?, balance = ? where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, card.getSerialNumber());
        preparedStatement.setString(2, card.getCardType().name());
        preparedStatement.setTimestamp(3, card.getDate());
        preparedStatement.setInt(4, card.getPassword());
        preparedStatement.setDouble(5, card.getBalance());
        preparedStatement.setInt(6, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }
}
