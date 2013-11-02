import Controllers.*;
import Models.Resources.*;
import Models.Resources.User;

import java.io.Console;
import java.util.*;

/*
java -cp "C:\Users\jokub_000\projects\LibrarySystem\drivers\postgresql-9.2-1003.jdbc4.jar;." Main
 */
/**
 * @author Jokubas
 * @since 2013.09.09
 * Task: Library: staff, book catalogue, customers. *
 */
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
        Console console = System.console();
        /*
            Controllers initialization
         */
        Controllers.UserController userController = new Controllers.UserController();


        /*
            Menu
         */
        System.out.println("Welcome to Library System \n Here's what you can do:");
        System.out.println("1. Register a new user");

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
                System.out.println("Firstname: ");
                String userFirstname = console.readLine();
                System.out.println("Lasttname: ");
                String userLastname = console.readLine();
                System.out.println("Password: ");
                String userPassword = console.readLine();
                System.out.println("Choose your role:\n1. User\n2. Worker\n");
                String userRole = console.readLine();

                Role role = Role.USER;
                if (userRole.equals("2")) {
                    role = Role.WORKER;
                }

                int userId;
                if (userPassword.isEmpty()) {
                    userId = userController.register(userFirstname, userLastname, role);
                } else {
                    userId = userController.register(userFirstname, userLastname, userPassword, role);
                }

                System.out.println(String.format("User successfully registered.\nUserID: %s", userId));
                User user = userController.getUser();

                System.out.println(user.toString());
                break;
        }
	}

}
