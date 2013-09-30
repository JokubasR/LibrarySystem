package Models;
import Models.Resources.*;

import java.util.Random;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 16.55
 *
 */
public class User {

    /**
     * User resource
     */
    protected Models.Resources.User _userResource = null;

    protected final String passwordAbc = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonpqrstuvwxyz";
    protected final int passwordLength = 8;

    /**
     * Initializes resources
     */
    public User() {
        this._userResource = new Models.Resources.User();
    }


    public int save(String firstname, String lastname, String password, Role role) {
        this.assignValues(firstname, lastname, password, role);
        int userId = this._userResource.saveRow(new String[]{firstname, lastname, password, role.toString()});

        return userId;
    }

    public Models.Resources.User getUser() {
        return this._userResource;
    }

    public String generatePassword() {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(this.passwordLength);
        for( int i = 0; i < this.passwordLength; i++ )
            sb.append(this.passwordAbc.charAt(rnd.nextInt(this.passwordAbc.length())));

        return sb.toString();
    }

    private void assignValues(String firstname, String lastname, String password, Role role) {
        this._userResource.setFirstname(firstname);
        this._userResource.setLastname(lastname);
        this._userResource.setPassword(password);
        this._userResource.setRole(role);
    }

}
