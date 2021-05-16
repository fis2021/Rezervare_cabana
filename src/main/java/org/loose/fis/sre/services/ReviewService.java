package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.controllers.ClientHomeController;
import org.loose.fis.sre.model.Review;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ReviewService extends ClientHomeController {
    private static Nitrite database;
    private static ObjectRepository<Review> ReviewRepository;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("reviews2.db").toFile())
                .openOrCreate("test", "test");

        ReviewRepository = database.getRepository(Review.class);
    }

    public static void closeDatabase() {
        database.close();
    }

    public static DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    public static void addReview(String nume_proprietate, String nume_autor, String text_review) {
        ReviewRepository.insert(new Review(nume_proprietate, nume_autor, text_review, dateFormat.format(new Date())));
    }

    public List<Review> reviewsList = ReviewRepository.find(ObjectFilters.ALL).toList();

    public static List<Review> getAllReviews() {
        return ReviewRepository.find().toList();
    }


}
