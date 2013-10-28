package Models;
import Models.Resources.*;

import java.util.Random;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 16.55
 *
 */
public class UserModel {

    /**
     * User resource
     */
    protected Models.Resources.User userResource = null;

    protected final String passwordAbc = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmonpqrstuvwxyz";
    protected final int passwordLength = 8;

    /**
     * Initializes resources
     */
    public UserModel() {
        this.userResource = new Models.Resources.User();
    }


    public int save(String firstname, String lastname, String password, Role role) {
        this.assignValues(firstname, lastname, password, role);
        int userId = this.userResource.saveRow(new String[]{firstname, lastname, password, role.toString()});

        return userId;
    }

    public Models.Resources.User getUser() {
        return this.userResource;
    }

    public String generatePassword() {
        Random rnd = new Random();

        StringBuilder sb = new StringBuilder(this.passwordLength);
        for( int i = 0; i < this.passwordLength; i++ )
            sb.append(this.passwordAbc.charAt(rnd.nextInt(this.passwordAbc.length())));

        return sb.toString();
    }

    private void assignValues(String firstname, String lastname, String password, Role role) {
        this.userResource.setFirstname(firstname);
        this.userResource.setLastname(lastname);
        this.userResource.setPassword(password);
        this.userResource.setRole(role);
    }

}
