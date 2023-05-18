package org.example.front;


import org.example.dto.ResponseOfBasket;
import org.example.entities.User;
import org.example.service.category.CategoryService;
import org.example.service.category.CategoryServiceImp;
import org.example.service.subCategory.SubCategoryService;
import org.example.service.subCategory.SubCategoryServiceImp;
import org.example.service.user.UserService;
import org.example.service.user.UserServiceImp;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

import static org.example.front.ManagerFront.*;

public class UserFront {
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImp();
    static CategoryService categoryService = new CategoryServiceImp();
    static SubCategoryService subCategoryService = new SubCategoryServiceImp();

    public static void UserPage(User currentUser) throws SQLException {
        while (true) {
            System.out.println("1.Purchase product\t\t2.Search\t\t3.History\t\t4.Basket\t\t0.Exit");
            int option = scannerInt.nextInt();
            if (option == 1) {
                enterToCategory(currentUser);
            } else if (option == 2) {

            } else if (option == 3) {

            } else if (option == 4) {
                enterToBasket(currentUser);
            } else if (option == 0) {
                break;
            } else {
                System.out.println("Wrong option!");
            }
        }
    }

    private static void enterToBasket(User currentUser) throws SQLException {
//        List<ResponseOfBasket> productsFromBasket = productService.getProductsFromBasket(currentUser.getId());
//        int[] cnt = {1};
//        productsFromBasket.forEach(product -> {
//            System.out.println(cnt[0] + ". name: " + product.getName() + " => price: " + product.getPrice() +
//                    " => date: " + product.getCreatedDate() + " => color: " + product.getColor() +
//                    " => brand: " + product.getBrand() + " => description: " + product.getDescription() +
//                    " => size: " + product.getSize() + " => quantity: " + product.getQuantity());
//            cnt[0]++;
//        });
//
//        double sumOfPriceFromBasket = productService.getSumOfPriceFromBasket(currentUser.getId());
//        if (sumOfPriceFromBasket != 0) {
//            System.out.println("Sum of price of products: " + sumOfPriceFromBasket);
//        }
//

    }

    private static void enterToCategory(User currentUser) throws SQLException {
//        seeCategory(currentUser);
//        System.out.println("Enter category name: ");
//        String categoryName = scannerStr.nextLine();
//        var category = categoryService.get(categoryName);
//        if (category != null) {
//            seeSubCategory(currentUser);
//            System.out.println("Enter sub category: ");
//            String subCategoryName = scannerStr.nextLine();
//            var subCategory = subCategoryService.get(subCategoryName);
//            if (subCategory != null) {
//                seeProduct(currentUser);
//                System.out.println("Enter id of product: ");
//                int productId = scannerInt.nextInt();
//                var product = productService.get(productId);
//                if (product != null) {
//                    boolean result = productService.addToBasket(currentUser.getId(), productId);
//                    if (result) {
//                        System.out.println("Added to Basket");
//                    } else {
//                        System.out.println("Error");
//                    }
//                } else {
//                    System.out.println("Product not found");
//                }
//            } else {
//                System.out.println("Sub category not found");
//            }
//        } else {
//            System.out.println("Category not found");
//        }
    }
}

