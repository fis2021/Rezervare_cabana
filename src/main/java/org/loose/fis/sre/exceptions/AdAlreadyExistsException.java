package org.loose.fis.sre.exceptions;

public class AdAlreadyExistsException extends Exception
{
    private String username;

    public AdAlreadyExistsException(String username)
    {
        super(String.format("Account with the username %s doesn't exist!", username));
        this.username = username;
    }

    public String getUsername() {
        return username;
    }
}
