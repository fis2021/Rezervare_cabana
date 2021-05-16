package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.model.Review;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

public class ReviewServiceTest {
    public static final String NUME_PROPRIETATE="Casa din munti";
    public static final String NUME_AUTOR="Chirias";
    public static final String TEXT_REVIEW="A fost frumos";

    @BeforeEach
    void setUp2() throws IOException {
        FileSystemService.APPLICATION_FOLDER=".test-rezervare-cabana";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDirectory();
        ReviewService.initDatabase();

    }

    @AfterEach
    void aftereach() {
        ReviewService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no reviews")
    void testDatabaseIsInitializedAndNoRenterIsPersisted() {
        assertThat(ReviewService.getAllReviews()).isNotNull();
        assertThat(ReviewService.getAllReviews()).isEmpty();
    }

    @Test
    @DisplayName("Renter is successfully persisted to Database")
    void testRenterIsAddedToDatabase()  {
        ReviewService.addReview(NUME_PROPRIETATE,NUME_AUTOR,TEXT_REVIEW);
        Review review = ReviewService.getAllReviews().get(0);
        assertThat(review).isNotNull();
        assertThat(review.getNume_proprietate()).isEqualTo(NUME_PROPRIETATE);
        assertThat(review.getNume_autor()).isEqualTo(NUME_AUTOR);
        assertThat(review.getText_review()).isEqualTo(TEXT_REVIEW);

    }
}
