package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.FileSystem;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.User;
import org.loose.fis.sre.model.Review;
import org.loose.fis.sre.services.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)


class ReviewsControllerTest {

    public static final String USERNAME1 = "usertest";
    public static final String USERNAME2 = "usertest2";
    public static final String PASSWORD1 = "parola";
    public static final String PASSWORD2 = "parola2";
    public static final String NUME_PROPRIETATE = "Casa cu tei";
    public static final String LOCATIE = "Timisoara";
    public static final String PRET = "300";
    public static final String EMAIL = "denismanaila@gmail.com";
    public static final String PHONENUMBER = "078640652";

    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-cabana-rezervare");
        FileSystemService.initDirectory();

        //System.gc();
        //Thread.sleep(2000);
        //FileDeleteStrategy.FORCE.delete(FileSystemService.getApplicationHomeFolder().toFile());
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        //Files.delete(FileSystemService.getApplicationHomeFolder());
        UserService.initDatabase();
        ReviewService.initDatabase();
        AdService.initDatabase();
        RenterService.initDatabase();
    }


    @BeforeEach
    void setUp2() throws IOException {
        FileSystemService.APPLICATION_FOLDER = ".test-rezervare-cabana";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDirectory();
        UserService.initDatabase();
        ReviewService.initDatabase();
        AdService.initDatabase();
        RenterService.initDatabase();

    }

    @AfterEach
    void aftereach() {
        UserService.closeDatabase();

        ReviewService.closeDatabase();
        AdService.closeDatabase();
        RenterService.closeDatabase();
    }

    @Start
    void start(Stage PrimaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        PrimaryStage.setTitle("Registration Example");
        PrimaryStage.setScene(new Scene(root, 400, 400));
        PrimaryStage.show();
    }

    @Test
    void testLeave_review(FxRobot robot) {
        robot.clickOn("#username_css");
        robot.write(USERNAME1);
        robot.clickOn("#password_css");
        robot.write(PASSWORD1);
        robot.clickOn("#role_css").clickOn("Owner");

        robot.clickOn("#registerbutton_css");
        robot.clickOn("#loginbutton_css");
        robot.clickOn("#createadbutton_css");
        robot.clickOn("#nume_proprietate_add_css");
        robot.write(NUME_PROPRIETATE);
        robot.clickOn("#locatie_add_css");
        robot.write(LOCATIE);
        robot.clickOn("#pret_add_css");
        robot.write(PRET);
        robot.clickOn("#createadbuttonconfirm_css");
        robot.push(KeyCode.ALT,KeyCode.F4);
        robot.push(KeyCode.ALT,KeyCode.F4);
        robot.clickOn("#username_css");
        robot.push(KeyCode.CONTROL,KeyCode.A);
        robot.push(KeyCode.BACK_SPACE);
        robot.write(USERNAME2);
        robot.clickOn("#password_css");
        robot.push(KeyCode.CONTROL,KeyCode.A);
        robot.push(KeyCode.BACK_SPACE);
        robot.write(PASSWORD2);
        robot.clickOn("#role_css").clickOn("Client");
        robot.clickOn("#registerbutton_css");
        robot.clickOn("#loginbutton_css");
        robot.clickOn("#searchtorentbutton_css");
        robot.clickOn(NUME_PROPRIETATE);
        robot.clickOn("#rentbutton_css");
        robot.clickOn("#email_rent_css");
        robot.write(EMAIL);
        robot.clickOn("#phone_css");
        robot.write(PHONENUMBER);
        robot.clickOn("#over18_css");








    }
}