package org.loose.fis.sre.exceptions;

public class NoUsernameException extends Exception {

    private final String username;

    public NoUsernameException(String username) {
        super("Error : No username !");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}