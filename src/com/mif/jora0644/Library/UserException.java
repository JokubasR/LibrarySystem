package Library;

import java.io.*;

public class UserException extends BaseException {
    private int passwordLength;

    public int getPasswordLength() {
        return passwordLength;
    }

    /**
     * Constructor with no-args
     */
    public UserException() {
        super("Exception occurred");
    }

    /**
     * Constructor with message parameter
     * @param message
     */
    public UserException(String message) {
        super(message);
    }

    public UserException(String message, int passwordLength) {
        super(message);

        this.passwordLength = passwordLength;
    }

    public UserException(int passwordLength) {
        this("Password is too short");

        this.passwordLength = passwordLength;
    }
}