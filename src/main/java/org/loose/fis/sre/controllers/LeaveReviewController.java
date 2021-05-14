package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class LeaveReviewController {
    @FXML
    public TextField numeAutor;

    public void handleLeaveReviewAction(MouseEvent mouseEvent)
    {

    }

    public void getAuthorNameText(String clientName)
    {
        numeAutor.setText(clientName);
    }
}
