package Controllers;

import Models.Resources.*;
import Models.*;

/**
 * User: Jokubas
 * Date: 13.9.29
 * Time: 13.49
 */
public class User{

    /**
     * User model object
     */
    protected Models.User _userModel = null;

    /**
     * Intializes models
     */
    public User() {
        this._userModel = new Models.User();
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
        int userId = this._userModel.save(firstname, lastname, password, role);

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
        String password = this._userModel.generatePassword();
        int userId = this._userModel.save(firstname, lastname, password, role);

        return userId;
    }

    public Models.Resources.User getUser() {
        return this._userModel.getUser();
    }
}
