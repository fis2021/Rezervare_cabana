package org.loose.fis.sre.exceptions;

public class AdAlreadyExistsException extends Exception
{
    private String username;

    public AdAlreadyExistsException(String username)
    {
        super("Ad already exists");
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
