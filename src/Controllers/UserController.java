package Controllers;

import Models.Resources.*;
import Models.*;

/**
 * User: Jokubas
 * Date: 13.9.29
 * Time: 13.49
 */
public class UserController{

    /**
     * User model object
     */
    protected Models.UserModel userModel = null;

    /**
     * Intializes models
     */
    public UserController() {
        userModel = new Models.UserModel();
    }

    /**
     * Registers a new user
     * @param firstname  user firstname
     * @param lastname user lastname
     * @param password user password
     * @param role user Role. User, Worker
     * @return userId
     */
    public int register(String firstname, String lastname, String password, Role role) {
        int userId = userModel.save(firstname, lastname, password, role);

        return userId;
    }

    /**
     * Registers a new user. Password will be generated.
     * @param firstname user firstname
     * @param lastname user lastname
     * @param role user Role. User, Worker
     * @return userId
     */
    public int register(String firstname, String lastname, Role role) {
        String password = userModel.generatePassword();
        int userId = userModel.save(firstname, lastname, password, role);

        return userId;
    }

    public Models.Resources.User getUser() {
        return userModel.getUser();
    }
}
