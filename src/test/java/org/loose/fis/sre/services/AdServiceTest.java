package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Ad;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class AdServiceTest {
    public static final String NUME_PROPRIETATE="Casa din munti";
    public static final String NUME_PROPRIETAR="Owner";
    public static final String LOCATIE="Brasov";
    public static final String PRET="300";

    @BeforeEach
    void setUp2() throws IOException {
        FileSystemService.APPLICATION_FOLDER=".test-rezervare-cabana";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDirectory();
        AdService.initDatabase();

    }

    @AfterEach
    void aftereach() {
        AdService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no ads")
    void testDatabaseIsInitializedAndNoRenterIsPersisted() {
        assertThat(AdService.getAllAds()).isNotNull();
        assertThat(AdService.getAllAds()).isEmpty();
    }

    @Test
    @DisplayName("Ad is successfully persisted to Database")
    void testRenterIsAddedToDatabase() throws AdAlreadyExistsException {
        AdService.addAd(NUME_PROPRIETAR,NUME_PROPRIETATE,LOCATIE,PRET);
        Ad ad = AdService.getAllAds().get(0);
        assertThat(ad).isNotNull();
        assertThat(ad.getNume_proprietate()).isEqualTo(NUME_PROPRIETATE);
        assertThat(ad.getNume_proprietar()).isEqualTo(NUME_PROPRIETAR);
        assertThat(ad.getLocatie()).isEqualTo(LOCATIE);
        assertThat(ad.getPret()).isEqualTo(PRET);

    }

    @Test
    @DisplayName("Ad can not be added twice")
    void testUserCanNotBeAddedTwice() {
        assertThrows(AdAlreadyExistsException.class, () -> {
            AdService.addAd(NUME_PROPRIETAR,NUME_PROPRIETATE,LOCATIE,PRET);
            AdService.addAd(NUME_PROPRIETAR,NUME_PROPRIETATE,LOCATIE,PRET);
        });
    }

}
