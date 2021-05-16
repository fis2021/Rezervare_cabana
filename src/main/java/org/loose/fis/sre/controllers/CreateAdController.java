package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.AdService;

import java.io.IOException;

public class CreateAdController extends AdService {
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

    public boolean checkIfAdCreated = false;

    public Ad returnAdCreated() {
        return (new Ad(nume_proprietar.getText(), nume_proprietate.getText(), locatie.getText(), pret.getText()));
    }

    public void getOwnerNameText(String text) {
        nume_proprietar.setText(text);
    }

    public boolean checkForEmptyFields() {
        return (nume_proprietate.getText().equals("")) || (locatie.getText().equals("")) || (pret.getText().equals(""));
    }

    @FXML
    public void handleCreateAdAction() throws IOException {
        if (!checkForEmptyFields()) {
            try {
                AdService.addAd(nume_proprietar.getText(), nume_proprietate.getText(), locatie.getText(), pret.getText());
                creatingAdMessage.setText("Ad created successfully!");
                checkIfAdCreated = true;
            } catch (AdAlreadyExistsException e) {
                creatingAdMessage.setText(e.getMessage());
            }
        } else
            creatingAdMessage.setText("Error: There are some incomplete fields !");
    }
}
