package org.loose.fis.sre.controllers;

import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.Window;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.*;
import org.testfx.api.FxRobot;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;
import static org.testfx.assertions.api.Assertions.assertThat;

@ExtendWith(ApplicationExtension.class)

class ClientHomeControllerTest
{
    public static final String USERNAME = "usertest";

    @BeforeAll
    static void setUp() throws Exception {
        FileSystemService.setApplicationFolder(".test-cabana-rezervare");
        FileSystemService.initDirectory();
        FileUtils.cleanDirectory(FileSystemService.getApplicationHomeFolder().toFile());

        AdService.initDatabase();
        RenterService.initDatabase();
        ReviewService.initDatabase();
    }

    @AfterEach
    void aftereach() throws InterruptedException {
        Thread.sleep(1000);
    }

    @AfterAll
    static void afterall() {
        //UserService.closeDatabase();
        AdService.closeDatabase();
        RenterService.closeDatabase();
        ReviewService.closeDatabase();
    }

    public int index ;
    @Start
    void start(Stage PrimaryStage) throws Exception {

        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ClientHome.fxml"));
        Pane root = fxmlLoader.load();
        ClientHomeController controller = fxmlLoader.getController() ;
        controller.passClientNameText(USERNAME);
        controller.tableView.getItems().add(new Ad("PROPRIETAR","PROPRIETATE","LOCATIE","0000"));
        stage.setTitle("Client Home");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();
    }

    @Test
    @DisplayName("Table Row successfully selected")
    void testRentedPlacesTable(FxRobot robot) throws InterruptedException {

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(700);
        robot.push(KeyCode.ALT,KeyCode.F4);
    }

    @Test
    @DisplayName("Search to Rent button is working")
    void testSearchToRentButton(FxRobot robot) throws InterruptedException {

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(200);
        robot.clickOn("#searchToRentButton_css");
        ObservableList<Window> stages =  Stage.getWindows().filtered(Window::isShowing);
        assertThat(((Stage)stages.get(0)).getTitle().contains("Search to Rent")||((Stage)stages.get(1)).getTitle().contains("Search to Rent"));
        Thread.sleep(500);
        robot.push(KeyCode.ALT,KeyCode.F4);
    }

    @Test
    @DisplayName("Reservation Details button is working")
    void testReservationDetailsButton(FxRobot robot) throws InterruptedException {

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(200);
        robot.clickOn("#RentDetailsButton_css");
        ObservableList<Window> stages =  Stage.getWindows().filtered(Window::isShowing);
        assertThat(((Stage)stages.get(0)).getTitle().contains("Rent Details")||((Stage)stages.get(1)).getTitle().contains("Rent Details"));
        Thread.sleep(500);
        robot.push(KeyCode.ALT,KeyCode.F4);
    }

    @Test
    @DisplayName("Leave Review button is working")
    void testLeaveReviewButton(FxRobot robot) throws InterruptedException {

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(200);
        robot.clickOn("#LeaveReviewButton_css");
        ObservableList<Window> stages =  Stage.getWindows().filtered(Window::isShowing);
        assertThat(((Stage)stages.get(0)).getTitle().contains("Write review")||((Stage)stages.get(1)).getTitle().contains("Write review"));
        Thread.sleep(500);
        robot.push(KeyCode.ALT,KeyCode.F4);
    }

    @Test
    @DisplayName("Cancel Rental button is working")
    void testCancelRentalButton(FxRobot robot) throws InterruptedException {

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(200);
        robot.clickOn("#CancelRentalButton_css");

        robot.moveTo("#id_css");
        robot.moveBy(50,30);
        robot.clickOn();
        Thread.sleep(200);
        assertThat(robot.lookup("#table_css").query().isFocused()); //if focused, then the field was successfully deleted
        robot.push(KeyCode.ALT,KeyCode.F4);
    }


}