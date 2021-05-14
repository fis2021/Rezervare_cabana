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
    public TextField nume_proprietar;
    @FXML
    private Text creatingAdMessage;
    @FXML
    private TextField nume_proprietate;
    @FXML
    private TextField locatie;
    @FXML
    private TextField pret;

    public void getOwnerNameText(String text)
    {
        nume_proprietar.setText(text) ;
    }

    public boolean checkForEmptyFields()
    {
        if ((nume_proprietate.getText().equals(""))||(locatie.getText().equals(""))||(pret.getText().equals("")))
            return true ;
        return false ;
    }

    @FXML
    public void handleCreateAdAction()
    {
        if (!checkForEmptyFields())
        {
            try {
                AdService.addAd(nume_proprietar.getText(), nume_proprietate.getText(), locatie.getText(), pret.getText());
                creatingAdMessage.setText("Ad created successfully!");
            } catch (AdAlreadyExistsException e) {
                creatingAdMessage.setText(e.getMessage());
            }
        }
        else
            creatingAdMessage.setText("Error: There are some incomplete fields !");
    }
}
