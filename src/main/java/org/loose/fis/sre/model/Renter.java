package org.loose.fis.sre.model;

import java.util.Objects;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

@Indices({
        @Index(value = "full_name", type = IndexType.NonUnique),
        @Index(value = "nume_proprietate", type = IndexType.NonUnique),
})

public class Renter
{

    @Id
    private String nume_proprietate;
    private String full_name;
    private String email;
    private String phone;
    private boolean over_18;

    public Renter(String nume_proprietate, String full_name, String email, String phone, boolean over_18)
    {
        this.nume_proprietate = nume_proprietate;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.over_18= over_18;
    }

    public Renter() {

    }

    public String getNume_proprietate() {
        return nume_proprietate;
    }

    public void setNume_proprietate(String nume_proprietate) {
        this.nume_proprietate = nume_proprietate;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public boolean getOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }
    
    

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Renter Renter = (Renter) o;

        if (nume_proprietate != null ? !nume_proprietate.equals(Renter.nume_proprietate) : Renter.nume_proprietate != null) return false;
        if (full_name != null ? !full_name.equals(Renter.full_name) : Renter.full_name != null) return false;
        if (email != null ? !email.equals(Renter.email) : Renter.email != null) return false;
        if (phone != null ? !phone.equals(Renter.phone) : Renter.phone != null) return false;
        return Objects.equals(over_18, Renter.over_18);
    }

}