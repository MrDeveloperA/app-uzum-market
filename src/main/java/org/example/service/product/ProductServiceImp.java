package org.example.service.product;

import org.example.dto.ResponseOfBasket;
import org.example.entities.Product;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductServiceImp implements ProductService {
    @Override
    public int create(Product product) throws SQLException {
        var connection = getConnection();
        String query = "select add_product(?,?,?,?,?,?,?,?,?)";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getSubCategoryId());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setString(5, product.getColor());
        preparedStatement.setString(6, product.getSize());
        preparedStatement.setInt(7, product.getOwnerId());
        preparedStatement.setInt(8, product.getAmount());
        preparedStatement.setString(9, product.getBrand());
        var resultSet = preparedStatement.executeQuery();
        var result = -1;
        if (resultSet.next()) {
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
        String query = "delete from product where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }

    @Override
    public Product get(int id) throws SQLException {
        var connection = getConnection();
        String query = "select * from product where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setInt(1, id);
        ResultSet resultSet = preparedStatement.executeQuery();
        if (resultSet.next()) {
            var product = Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .subCategoryId(resultSet.getInt("sub_category_id"))
                    .description(resultSet.getString("description"))
                    .color(resultSet.getString("color"))
                    .size(resultSet.getString("size"))
                    .ownerId(resultSet.getInt("owner_id"))
                    .amount(resultSet.getInt("amount"))
                    .brand(resultSet.getString("brand"))
                    .build();
            return product;
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return null;
    }

    @Override
    public List<Product> getAll() throws SQLException {
        List<Product> products = new ArrayList<>();
        var connection = getConnection();
        String query = "select * from product";
        var preparedStatement = connection.prepareStatement(query);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()) {
            var product = Product.builder()
                    .id(resultSet.getInt("id"))
                    .name(resultSet.getString("name"))
                    .price(resultSet.getInt("price"))
                    .subCategoryId(resultSet.getInt("sub_category_id"))
                    .description(resultSet.getString("description"))
                    .color(resultSet.getString("color"))
                    .size(resultSet.getString("size"))
                    .ownerId(resultSet.getInt("owner_id"))
                    .amount(resultSet.getInt("amount"))
                    .brand(resultSet.getString("brand"))
                    .build();
            products.add(product);
        }
        resultSet.close();
        preparedStatement.close();
        connection.close();
        return products;
    }

    @Override
    public boolean update(int id, Product product) throws SQLException {
        var connection = getConnection();
        String query = "update product set name = ?, price = ?, sub_category_id = ?, description = ?, color = ?, size = ?, owner_id = ?, amount = ?, brand = ? where id = ?";
        var preparedStatement = connection.prepareStatement(query);
        preparedStatement.setString(1, product.getName());
        preparedStatement.setDouble(2, product.getPrice());
        preparedStatement.setInt(3, product.getSubCategoryId());
        preparedStatement.setString(4, product.getDescription());
        preparedStatement.setString(5, product.getColor());
        preparedStatement.setString(6, product.getSize());
        preparedStatement.setInt(7, product.getOwnerId());
        preparedStatement.setInt(8, product.getAmount());
        preparedStatement.setString(9, product.getBrand());
        preparedStatement.setInt(10, id);
        int result = preparedStatement.executeUpdate();
        preparedStatement.close();
        connection.close();
        return result > 0;
    }


//    @Override
//    public boolean addToBasket(int userId, int productId) throws SQLException {
//        var connection = getConnection();
//        String query = "select add_product_to_basket(?,?)";
//        var preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, userId);
//        preparedStatement.setInt(2, productId);
//        var resultSet = preparedStatement.executeQuery();
//        var result = false;
//        if (resultSet.next()) {
//            result = resultSet.getBoolean(1);
//        }
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//        return result;
//    }
//
//    @Override
//    public List<ResponseOfBasket> getProductsFromBasket(int userId) throws SQLException {
//        var connection = getConnection();
//        String query = "select * from see_products_from_basket(?)";
//        var preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1, userId);
//        var resultSet = preparedStatement.executeQuery();
//        List<ResponseOfBasket> responseOfBaskets = new ArrayList<>();
//        while (resultSet.next()) {
//          var  responseOfBasket = ResponseOfBasket.builder()
//                    .name(resultSet.getString("u_name"))
//                    .price(resultSet.getDouble("price"))
//                    .createdDate(resultSet.getTimestamp("created_date"))
//                    .color(resultSet.getString("color"))
//                    .brand(resultSet.getString("brand"))
//                    .description(resultSet.getString("description"))
//                    .size(resultSet.getString("size"))
//                    .quantity(resultSet.getInt("quantity"))
//                    .build();
//          responseOfBaskets.add(responseOfBasket);
//        }
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//        return responseOfBaskets;
//    }
//
//    @Override
//    public double getSumOfPriceFromBasket(int userId) throws SQLException {
//        var connection = getConnection();
//        String query = "select sum(price) from product p\n" +
//                "join basket b on p.id = b.product_id\n" +
//                "where b.user_id = ?";
//        var preparedStatement = connection.prepareStatement(query);
//        preparedStatement.setInt(1,userId);
//        ResultSet resultSet = preparedStatement.executeQuery();
//        double sumOfPrice = 0;
//        if (resultSet.next()){
//            sumOfPrice = resultSet.getDouble(1);
//        }
//        resultSet.close();
//        preparedStatement.close();
//        connection.close();
//        return sumOfPrice;
//    }
}
