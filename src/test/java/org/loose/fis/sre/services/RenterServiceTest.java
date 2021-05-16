package org.loose.fis.sre.services;

import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.model.User;

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class RenterServiceTest {
    public static final String NUME_PROPRIETATE="Owner";
    public static final String FULL_NAME="Renter";
    public static final String EMAIL="blabla@yahoo.com";
    public static final String PHONE="0698456542";
    public static final String DATA_INITIAL="22/05/2021";
    public static final String DATA_FINAL="28/05/2021";
    public static final int NR_NOPTI=6;
    public static final int PRET_SEJUR=1200;
    public static final int PRET_NOAPTE=200;
    public static final boolean OVER18=true;


    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-cabana-rezervare");
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        RenterService.initDatabase();
    }


    @BeforeEach
    void setUp2() throws IOException {
        FileSystemService.APPLICATION_FOLDER=".test-rezervare-cabana";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDirectory();
        RenterService.initDatabase();

    }

    @AfterEach
    void aftereach() {
        RenterService.closeDatabase();
    }

    @Test
    @DisplayName("Database is initialized, and there are no users")
    void testDatabaseIsInitializedAndNoRenterIsPersisted() {
        assertThat(RenterService.getAllRenters()).isNotNull();
        assertThat(RenterService.getAllRenters()).isEmpty();
    }

    @Test
    @DisplayName("Renter is successfully persisted to Database")
    void testRenterIsAddedToDatabase()  {
        RenterService.addRenter(NUME_PROPRIETATE,FULL_NAME,EMAIL,PHONE,OVER18,DATA_INITIAL,DATA_FINAL,PRET_NOAPTE);
        Renter renter = RenterService.getAllRenters().get(0);
        assertThat(renter).isNotNull();
        assertThat(renter.getNume_proprietate()).isEqualTo(NUME_PROPRIETATE);
        assertThat(renter.getFull_name()).isEqualTo(FULL_NAME);
        assertThat(renter.getEmail()).isEqualTo(EMAIL);
        assertThat(renter.getOver_18()).isEqualTo(OVER18);
        assertThat(renter.getPhone()).isEqualTo(PHONE);
        assertThat(renter.getData_inceput()).isEqualTo(DATA_INITIAL);
        assertThat(renter.getData_final()).isEqualTo(DATA_FINAL);
        assertThat(renter.getPret_noapte()).isEqualTo(PRET_NOAPTE);

    }


}
