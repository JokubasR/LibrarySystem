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
     * @param username user username
     * @param role user Role. User, Worker
     * @return userId
     */
    public int register(String firstname, String lastname, String password, String username, Role role) {
        int userId = userModel.save(firstname, lastname, password, username, role);

        return userId;
    }

    /**
     * Registers a new user. Password will be generated.
     * @param firstname user firstname
     * @param lastname user lastname
     * @param username user username
     * @param role user Role. User, Worker
     * @return userId
     */
    public int register(String firstname, String lastname, String username, Role role) {
        String password = userModel.generatePassword();
        int userId = userModel.save(firstname, lastname, password, username, role);

        return userId;
    }

    public boolean login(String username, String password) {
        return userModel.authenticate(username, password);
    }

    public Models.Resources.User getUser() {
        return userModel.getUser();
    }
}
