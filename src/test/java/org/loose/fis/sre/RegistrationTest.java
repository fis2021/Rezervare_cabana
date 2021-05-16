package org.loose.fis.sre;

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
import org.loose.fis.sre.services.FileSystemService;
import org.loose.fis.sre.services.UserService;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.io.File;
import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)



class RegistrationTest {

    public static final String USERNAME = "usertest";
    public static final String PASSWORD = "parola";
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
    }


    @BeforeEach
    void setUp2() throws IOException {
        FileSystemService.APPLICATION_FOLDER=".test-rezervare-cabana";
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());
        FileSystemService.initDirectory();
        UserService.initDatabase();

    }

    @AfterEach
    void aftereach() {
        UserService.closeDatabase();
    }

    @Start
    void start(Stage PrimaryStage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("register.fxml"));
        PrimaryStage.setTitle("Registration Example");
        PrimaryStage.setScene(new Scene(root, 400, 400));
        PrimaryStage.show();
    }

    @Test
    void testRegistration_Owner(FxRobot robot) {
        robot.clickOn("#username_css");
        robot.write(USERNAME);
        robot.clickOn("#password_css");
        robot.write(PASSWORD);
        robot.clickOn("#role_css").clickOn("Owner");

        robot.clickOn("#registerbutton_css");
        assertEquals("Account created successfully!", robot.lookup("#registrationmessage_css").queryText().getText());
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#registerbutton_css");
        assertEquals(String.format("An account with the username %s already exists!", USERNAME), robot.lookup("#registrationmessage_css").queryText().getText());


    }

    @Test
    void testRegistration_Client(FxRobot robot) {
        robot.clickOn("#username_css");
        robot.write(USERNAME);
        robot.clickOn("#password_css");
        robot.write(PASSWORD);
        robot.clickOn("#role_css").clickOn("Client");

        robot.clickOn("#registerbutton_css");
        assertEquals("Account created successfully!", robot.lookup("#registrationmessage_css").queryText().getText());
        assertThat(UserService.getAllUsers()).size().isEqualTo(1);

        robot.clickOn("#registerbutton_css");
        assertEquals(String.format("An account with the username %s already exists!", USERNAME), robot.lookup("#registrationmessage_css").queryText().getText());

    }

    @Test
    void testRegistration_no_role(FxRobot robot){
        robot.clickOn("#registerbutton_css");
        assertEquals("Error : No Role Selected !",robot.lookup("#registrationmessage_css").queryText().getText());

        robot.clickOn("#username_css");
        robot.write(USERNAME);
        robot.clickOn("#password_css");
        robot.write(PASSWORD);
        robot.clickOn("#registerbutton_css");
        assertEquals("Error : No Role Selected !",robot.lookup("#registrationmessage_css").queryText().getText());
    }
    @Test
    void testRegistration_username_emptyfield(FxRobot robot){
        robot.clickOn("#password_css");
        robot.write(PASSWORD);
        robot.clickOn("#role_css");
        robot.clickOn("Client");
        robot.clickOn("#registerbutton_css");
        assertEquals("Error : No username !",robot.lookup("#registrationmessage_css").queryText().getText());

    }

    @Test
    void testRegistration_password_emptyfield(FxRobot robot){
        robot.clickOn("#username_css");
        robot.write(USERNAME);
        robot.clickOn("#role_css");
        robot.clickOn("Client");
        robot.clickOn("#registerbutton_css");
        assertEquals("Error : No password !",robot.lookup("#registrationmessage_css").queryText().getText());
    }



}