import Controllers.*;
import Models.Resources.*;

import java.io.Console;

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
        Controllers.User userController = new Controllers.User();


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
                System.out.println("\nLasttname: ");
                String userLastname = console.readLine();
                System.out.println("\nPassword: ");
                String userPassword = console.readLine();
                System.out.println("Choose your role:\n1. User\n2.Worker\n");
                String userRole = console.readLine();

                Role role = Role.USER;
                if (userRole == "2") {
                    role = Role.WORKER;
                }

                if (userPassword.isEmpty()) {
                    userController.register(userFirstname, userLastname, role);
                } else {
                    userController.register(userFirstname, userLastname, userPassword, role);
                }

                break;
        }
	}

}
