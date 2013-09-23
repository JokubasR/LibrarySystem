package Models.Resources;

import Items.Items.DbItem;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.14
 */
public class User extends DbItem {

    /**
     * Database table name
     */
    protected String _name = "User";

    private String  _firstname;
    private String  _lastname;
    private String  _password;
    private Role   _role;

    public User(String firstname, String lastname, String password, Role role) {
        this._firstname = firstname;
        this._lastname  = lastname;
        this._password  = password;
        this._role      = role;

        this.Init(this._name);
    }

    public String getFirstname() {
        return this._firstname;
    }

    public void setFirstname(String firstname) {
        this._firstname = firstname;
    }

    public String getLastname() {
        return this._lastname;
    }

    public void setLastname(String lastname) {
        this._lastname = lastname;
    }

    public String setPassword(String password) {
        this._password = password;
    }

    public Boolean validatePassword(String password) {
        return password.equals(this._password);
    }
}
