package Models;
import Models.Resources.*;

import java.util.Random;
import java.util.*;

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
        userResource = new Models.Resources.User();
    }


    public int save(String firstname, String lastname, String password, String username, Role role) {
        assignValues(firstname, lastname, password, username, role);

        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("firstname", firstname);
        data.put("lastname", lastname);
        data.put("password", password);
        data.put("username", username);
        data.put("userRole", role);

        int userId = userResource.saveRow(data);

        return userId;
    }

    public Object getUser() {
        return userResource.clone();
    }

    public String generatePassword() {
        Random random = new Random();

        StringBuilder stringBuilder = new StringBuilder(passwordLength);
        for( int i = 0; i < passwordLength; i++ )
            stringBuilder.append(passwordAbc.charAt(random.nextInt(passwordAbc.length())));

        return stringBuilder.toString();
    }

    public boolean authenticate(String username, String password) {
        HashMap<String, Object> data = new HashMap<String, Object>();
        data.put("password", password);
        data.put("username", username);

        HashMap<String, Object> user = userResource.fetchRow(data);
        if (user.size() > 0) {
            return true;
        } else {
            return false;
        }
    }

    private void assignValues(String firstname, String lastname, String password, String username, Role role) {
       userResource.setFirstname(firstname);
       userResource.setLastname(lastname);
       userResource.setPassword(password);
       userResource.setUsername(username);
       userResource.setRole(role);
    }

}
