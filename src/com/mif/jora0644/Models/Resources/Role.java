package Models.Resources;

/**
 * User: Jokubas
 * Date: 13.9.23
 * Time: 17.08
 */

/**
 * Enum defining user roles
 */
public enum Role{
        USER,
        WORKER,
        DEFAULT;

    @Override
    public String toString() {
        switch (this) {
            case USER:
                return "User";
            case WORKER:
                return "Worker";

            default:
                return "User";
        }
    }
}
