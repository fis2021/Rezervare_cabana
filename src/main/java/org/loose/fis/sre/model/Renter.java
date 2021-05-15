package org.loose.fis.sre.model;

import java.time.LocalDate;
import java.util.Objects;
import java.util.Date;

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
    private String data_inceput;
    private String data_final;

    public Renter(){
        super();
    }
    public Renter(String nume_proprietate, String full_name, String email, String phone, boolean over_18,String data_inceput,String data_final)
    {
        this.nume_proprietate = nume_proprietate;
        this.full_name = full_name;
        this.email = email;
        this.phone = phone;
        this.over_18= over_18;
        this.data_inceput= data_inceput;
        this.data_final= data_final;

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

    public String getData_inceput() { return data_inceput;}

    public void setData_inceput(String data_inceput) { this.data_inceput=data_inceput;}

    public String getData_final() { return data_final;}

    public void setData_final(String data_final) { this.data_final=data_final;}
    
    

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