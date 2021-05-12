package org.loose.fis.sre.model;

import org.dizitart.no2.objects.Id;

public class Ad 
{

    @Id
    private String nume_proprietate;
    private String locatie;
    private String pret;

    public Ad(String nume_proprietate, String locatie, String pret) 
    {
        this.nume_proprietate = nume_proprietate;
        this.locatie = locatie;
        this.pret = pret;
    }

    public Ad() {
    }

    public String getNume_proprietate() {
        return nume_proprietate;
    }

    public void setNume_proprietate(String nume_proprietate) {
        this.nume_proprietate = nume_proprietate;
    }

    public String getLocatie() {
        return locatie;
    }

    public void setLocatie(String locatie) {
        this.locatie = locatie;
    }

    public String getPret() {
        return pret;
    }

    public void setPret(String pret) {
        this.pret = pret;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Ad Ad = (Ad) o;

        if (nume_proprietate != null ? !nume_proprietate.equals(Ad.nume_proprietate) : Ad.nume_proprietate != null) return false;
        if (locatie != null ? !locatie.equals(Ad.locatie) : Ad.locatie != null) return false;
        return pret != null ? pret.equals(Ad.pret) : Ad.pret == null;
    }
    
}
