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
    protected static String name = "users";

    /**
     * Fields
     */
    private String  firstname;
    private String  lastname;
    private String  password;
    private String  username;
    private Role    role;

    public User(){
        super(name);
    }

    public User(String _firstname, String _lastname, String _password, String _username, Role _role) {
        super(name);

        firstname = _firstname;
        lastname  = _lastname;
        password  = _password;
        username  = _username;
        role      = _role;
    }

    public User(String _firstname, String _lastname, String _password, String _username) {
        this(_firstname, _lastname, _password, _username, Role.USER);
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String _firstname) {
        firstname = _firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String _lastname) {
        lastname = _lastname;
    }

    public void setPassword(String _password) {
        password = _password;
    }

    public Boolean validatePassword(String _password) {
        return _password.equals(password);
    }

    public void setUsername(String _username) {
        username = _username;
    }

    public String getUsername() {
        return username;
    }

    public void setRole(Role _role) {
        role = _role;
    }

    public Role getRole() {
        return role;
    }

    public String toString()
    {
        return String.format("Firstname: %s\nLastname: %s\nUsername: %s\nPassword: %s\nRole: %s\n", firstname, lastname, username, password, role);
    }
}
