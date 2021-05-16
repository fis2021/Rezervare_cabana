package org.loose.fis.sre.exceptions;

public class AdAlreadyExistsException extends Exception {
    private final String nume_proprietate;

    public AdAlreadyExistsException(String nume_proprietate) {
        super("Ad already exists");
        this.nume_proprietate = nume_proprietate;
    }

    public String getNume_proprietate() {
        return nume_proprietate;
    }
}
