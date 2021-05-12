package org.loose.fis.sre.exceptions;

public class USERAlreadyExistsException extends Exception {

    private String username;

    public USERAlreadyExistsException(String username) {
        super("User already exists");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

