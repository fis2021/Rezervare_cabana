package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.services.ReviewService;
import java.net.URL;
import java.util.ResourceBundle;

public class LeaveReviewController extends ReviewService implements Initializable {
    @FXML
    public TextField numeAutor;
    @FXML
    public TextField Nume_proprietate;
    @FXML
    public TextArea textReview;
    @FXML
    public Text creatingReviewMessage;


    public void getAuthorNameText(String clientName) {
        numeAutor.setText(clientName);
    }

    public boolean checkForEmptyFields() {
        return (Nume_proprietate.getText().equals("")) || (numeAutor.getText().equals("")) || (textReview.getText().equals(""));
    }

    public void handleCreateReviewAction() {
        if (!checkForEmptyFields()) {
            ReviewService.addReview(Nume_proprietate.getText(), numeAutor.getText(), textReview.getText());
            creatingReviewMessage.setText("Review created successfully!");

        } else
            creatingReviewMessage.setText("Error: There are some incomplete fields !");
    }

    public void setNumeProprietateText(String text) {
        Nume_proprietate.setText(text);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //Nume_proprietate.setText(initializareNume_proprietate());
    }
}
