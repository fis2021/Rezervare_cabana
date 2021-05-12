package org.loose.fis.sre.exceptions;

public class NoPasswordException extends Exception {

    private String username;

    public NoPasswordException(String username) {
        super("Error : No password !");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
