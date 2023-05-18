package org.example;

import org.example.entities.User;
import org.example.enums.Gender;
import org.example.enums.Role;
import org.example.front.ManagerFront;
import org.example.front.UserFront;
import org.example.service.user.UserService;
import org.example.service.user.UserServiceImp;

import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Scanner;

public class Main {
    static Scanner scannerInt = new Scanner(System.in);
    static Scanner scannerStr = new Scanner(System.in);
    static UserService userService = new UserServiceImp();

    public static void main(String[] args) throws Exception {
        while (true) {
            System.out.println("1.Catalog\t\t2.Sign up\t\t3.Sign in\t\t0.Exit");
            int option = scannerInt.nextInt();
            if (option == 1) {

            } else if (option == 2) {
                signUp();
            } else if (option == 3) {
                signIn();
            } else if (option == 4) {

            } else if (option == 0) {
                break;
            } else {
                System.out.println("Wrong option!");
            }
        }
    }

    private static void signIn() throws SQLException {
        System.out.println("Enter your phone number: ");
        String phoneNumber = scannerStr.nextLine();
        User currentUser = userService.getByPhone(phoneNumber);
        if (currentUser != null) {
            while (true) {
                System.out.println("1.ManagerFront\t\t2.UserFront\t\t0.Exit");
                int option = scannerInt.nextInt();
                if (option == 1) {
                    ManagerFront.CategoryPage(currentUser);
                } else if (option == 2) {
                    UserFront.UserPage(currentUser);
                } else if (option == 0) {
                    break;
                } else {
                    System.out.println("Wrong option!");
                }
            }
        } else {
            System.out.println("User not found");
        }
    }


    private static void signUp() throws SQLException {
        System.out.println("Enter your name : ");
        String name = scannerStr.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scannerStr.nextLine();
        System.out.println("Enter gender [1.MALE,2.FEMALE]: ");
        int i = scannerInt.nextInt();
        var gender = i == 1 ? Gender.MALE : Gender.FEMALE;

        var user = User
                .builder()
                .name(name)
                .phoneNumber(phoneNumber)
                .gender(gender)
                .role(Role.USER)
                .build();
        int result = userService.create(user);
        if (result > 0)
            System.out.println("Success");
        else
            System.out.println("Error!");
    }}