package org.loose.fis.sre.exceptions;

public class RenterAlreadyExistsException extends Exception
{
    private String full_name;

    public RenterAlreadyExistsException(String full_name)
    {
        super(String.format("Renter with the username %s doesn't exist!", full_name));
        this.full_name = full_name;
    }

    public String getUsername() {
        return full_name;
    }
}