package org.loose.fis.sre.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.NoPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.RenterAlreadyExistsException;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.RenterService;

import java.net.URL;
import java.util.ResourceBundle;

public class RentController extends RenterService implements Initializable {
    @FXML
    public TextField nume_proprietate;
    @FXML
    public HBox RentButton;
    @FXML
    private Text rentingMessage;
    @FXML
    private TextField full_name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private CheckBox over_18;

    public boolean checkIfPropertyRented = false;

    public void getClientNameText_as_Renter(String clientName) {
        full_name.setText(clientName);
    }

    public void closeStageAfterRentButtonPressed()
    {
        Stage stage = (Stage) RentButton.getScene().getWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }

    public void handleRentingAction()
    {
        try
        {
            RenterService.addRenter(nume_proprietate.getText(),full_name.getText(), email.getText(), phone.getText(), over_18.isSelected());
            rentingMessage.setText("Rental process complete!");
            checkIfPropertyRented = true ;
            closeStageAfterRentButtonPressed();
        }
        catch (RenterAlreadyExistsException e)
        {
            rentingMessage.setText(e.getMessage());
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nume_proprietate.setText(initializareNume_proprietate());
    }

}
