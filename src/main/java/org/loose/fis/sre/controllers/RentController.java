package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.NoPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.RenterAlreadyExistsException;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.RenterService;

public class RentController extends RenterService
{
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


    public void handleRentingAction()
    {
        try
        {
            RenterService.addRenter(full_name.getText(), email.getText(), phone.getText(), over_18.isSelected());
            rentingMessage.setText("Renting process complete!");
        }
        catch (RenterAlreadyExistsException e)
        {
            rentingMessage.setText(e.getMessage());
        }
    }
}
