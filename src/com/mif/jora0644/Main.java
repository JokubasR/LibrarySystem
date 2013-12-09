//package com.mif.jora0644;

import Controllers.*;
import Models.Resources.*;
import Models.Resources.User;
import Library.*;

import java.io.Console;
import java.util.*;

/*
javac -cp "C:\Users\jokub_000\projects\LibrarySystem\drivers\postgresql-9.2-1003.jdbc4.jar;." Main.java
java -cp "C:\Users\jokub_000\projects\LibrarySystem\drivers\postgresql-9.2-1003.jdbc4.jar;." Main
 */
/**
 * @author Jokubas
 * @since 2013.09.09
 * Task: Library: staff, book catalogue, customers. *
 */
 public class Main{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Console console = System.console();
//        Config config = new Config();
        /*
            Controllers initialization
         */
        Controllers.UserController userController = new Controllers.UserController();


        /*
            Menu
         */
        System.out.println("Welcome to Library System \n Here's what you can do:");
        System.out.println("1. Register a new user");
        System.out.println("2. Login");

        /*
            Managing user choice
         */
        String choice = console.readLine();

        char option = '0';
        if (choice.length() == 1) {
            option = choice.charAt(0);
        }

        switch (option) {
            case '1':
                try {
                    System.out.println("Firstname: ");
                    String userFirstname = console.readLine();
                    System.out.println("Lasttname: ");
                    String userLastname = console.readLine();
                    System.out.println("Username: ");
                    String userUsername = console.readLine();
                    System.out.println("Password: ");
                    String userPassword = console.readLine();
                    if (userPassword.length() < 5 && userPassword.length() != 0) {
                         throw new Library.UserException(userPassword.length());
                    }
                    System.out.println("Choose your role:\n1. User\n2. Worker\n");
                    String userRole = console.readLine();

                    Role role = Role.USER;
                    if (userRole.equals("2")) {
                        role = Role.WORKER;
                    }

                    int userId;
                    if (userPassword.isEmpty()) {
                        userId = userController.register(userFirstname, userLastname, userUsername, role);
                    } else {
                        userId = userController.register(userFirstname, userLastname, userPassword, userUsername, role);
                    }

                    System.out.println(String.format("\nUser successfully registered.\nUserID: %s", userId));
                    User user = (User)(userController.getUser());

                    System.out.println("\n" + user.toString());
                }
                catch (Library.UserException userException) {
                    System.out.println(userException +
                            ". Current password length: " + userException.getPasswordLength() +
                            ". Required password length: " + 5);
                }
                finally {
                    System.out.println("Finnaly");
                }

                break;
            case '2':
                String loginUsername;
                String loginPassword;

                Object user;
                do {
                    System.out.println("Enter your username: ");
                    loginUsername = console.readLine();
                    System.out.println("Enter your password: ");
                    loginPassword = console.readLine();

//                    user = userController.login(loginUsername, loginPassword);
                } while (!userController.login(loginUsername, loginPassword));

                System.out.println("\nAuthentication successful");

                break;
        }
	}

}
