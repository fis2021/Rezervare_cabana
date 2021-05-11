package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;

public class CreateAdController extends AdService
{
    @FXML
    private Text creatingAdMessage;
    @FXML
    private TextField nume_proprietate;
    @FXML
    private TextField locatie;
    @FXML
    private TextField pret;

    @FXML
    public void handleCreateAdAction() {
        try {
            AdService.addAd(nume_proprietate.getText(), locatie.getText(), pret.getText());
            creatingAdMessage.setText("Ad created successfully!");
        } catch (AdAlreadyExistsException e) {
            creatingAdMessage.setText(e.getMessage());
        }
    }
}
