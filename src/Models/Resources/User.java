package Models.Resources;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.14
 */
public class User extends Library.Db.DbItem {

    /**
     * Database table name
     */
    protected static String name = "User";

    private String  _firstname;
    private String  _lastname;
    private String  _password;
    private Role   _role;

    public User(){
        super(name);
    }

    public User(String firstname, String lastname, String password, Role role) {
        super(name);

        this._firstname = firstname;
        this._lastname  = lastname;
        this._password  = password;
        this._role      = role;
    }

    public User(String firstname, String lastname, String password) {
        this(firstname, lastname, password, Role.USER);
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

    public void setPassword(String password) {
        this._password = password;
    }

    public Boolean validatePassword(String password) {
        return password.equals(this._password);
    }

    public void setRole(Role role) {
        this._role = role;
    }

    public Role getRole() {
        return this._role;
    }

    public String toString()
    {
        return String.format("Firstname: %s\nLastname: %s\nPassword: %s\nRole: %s\n", this._firstname, this._lastname, this._password, this._role);
    }
}
