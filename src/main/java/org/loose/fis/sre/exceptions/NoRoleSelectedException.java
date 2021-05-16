package org.loose.fis.sre.exceptions;

public class NoRoleSelectedException extends Exception {

    private final String username;

    public NoRoleSelectedException(String username) {
        super("Error : No Role Selected !");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}

