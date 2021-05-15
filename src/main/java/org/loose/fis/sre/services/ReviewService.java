package org.loose.fis.sre.services;

import org.apache.commons.lang3.text.CompositeFormat;
import org.dizitart.no2.IndexOptions;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import javafx.scene.Scene;
import javafx.scene.control.*;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.controllers.ClientHomeController;
import org.loose.fis.sre.controllers.SearchToRentController;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Review;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ReviewService extends ClientHomeController
{
    private static ObjectRepository<Review> ReviewRepository;
    //private static CompositeFormat dateFormat;

    public static void initDatabase()
    {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("reviews2.db").toFile())
                .openOrCreate("test", "test");

        ReviewRepository = database.getRepository(Review.class);
    }

    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
    public static void addReview(String nume_proprietate,String nume_autor,  String text_review) throws AdAlreadyExistsException {
        //checkAdDoesNotAlreadyExist(nume_proprietate);

        ReviewRepository.insert(new Review(nume_proprietate,nume_autor,  text_review, dateFormat.format(new Date())));
    }

    protected static void checkReviewDoesNotAlreadyExist(String nume_proprietate) throws AdAlreadyExistsException {
        for (Review review : ReviewRepository.find()) {
            if (Objects.equals(nume_proprietate, review.getNume_proprietate()))
                throw new AdAlreadyExistsException(nume_proprietate);
        }
    }

    public List<Review> reviewsList = ReviewRepository.find(ObjectFilters.ALL).toList();

    public String initializareNume_proprietate()
    {
        //System.out.println(property_name);
        return property_name;
    }

}
