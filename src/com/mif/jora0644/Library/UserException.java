package com.mif.jora0644.Library;

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

        this.passwordLength = length;
    }

    public UserException(int passwordLength) {
        this("Password is too short");

        this.passwordLength = passwordLength;
    }
}