package org.loose.fis.sre.model;

import org.dizitart.no2.IndexType;
import org.dizitart.no2.objects.Id;
import org.dizitart.no2.objects.Index;
import org.dizitart.no2.objects.Indices;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Indices({
        @Index(value = "nume_autor", type = IndexType.NonUnique),
        @Index(value = "nume_proprietate", type = IndexType.NonUnique),
})

public class Review 
{
    @Id
    private String nume_proprietate;
    private String nume_autor;
    private String text_review;
    private String data;
    //DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    //dateFormat.format(data);

    public Review(String nume_proprietate, String nume_autor, String text_review, String data)
    {
        
        this.nume_proprietate = nume_proprietate;
        this.nume_autor = nume_autor;
        this.text_review = text_review;
        this.data = data;
    }

    public Review() 
    {
        
    }

    public String getNume_autor() {
        return nume_autor;
    }

    public void setNume_autor(String nume_autor) {
        this.nume_autor = nume_autor;
    }
    
    public String getNume_proprietate() {
        return nume_proprietate;
    }

    public void setNume_Proprietate(String nume_proprietate) {
        this.nume_proprietate = nume_proprietate;
    }

    public String getText_review() {
        return text_review;
    }

    public void setText_review(String text_review) {
        this.text_review = text_review;
    }

    public String getDate() {
        return data;
    }

    public void setDate(String data) {
        this.data = data;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Review Review = (Review) o;
        if (nume_autor != null ? !nume_autor.equals(Review.nume_autor) : Review.nume_autor != null) return false;
        if (nume_proprietate != null ? !nume_proprietate.equals(Review.nume_proprietate) : Review.nume_proprietate != null) return false;
        if (text_review != null ? !text_review.equals(Review.text_review) : Review.text_review != null) return false;
        return data != null ? data.equals(Review.data) : Review.data == null;
    }
    
}
