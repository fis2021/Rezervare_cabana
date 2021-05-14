package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.DateCell;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.services.ReviewService;

import java.util.Date;

public class LeaveReviewController extends ReviewService{
    @FXML
    public TextField numeAutor;
    @FXML
    public TextField nume_proprietate;
    @FXML
    public TextArea textReview;
    @FXML
    public Text creatingReviewMessage;
    @FXML
    //public Date date;

    public void handleLeaveReviewAction(MouseEvent mouseEvent)
    {

    }

    public void getAuthorNameText(String clientName)
    {
        numeAutor.setText(clientName);
    }

    public boolean checkForEmptyFields()
    {
        if ((nume_proprietate.getText().equals(""))||(numeAutor.getText().equals(""))||(textReview.getText().equals("")))
            return true ;
        return false ;
    }

    public void handleCreateReviewAction()
    {
        if (!checkForEmptyFields())
        {
            try {
                ReviewService.addReview(nume_proprietate.getText(), numeAutor.getText(), textReview.getText(), new Date());
                creatingReviewMessage.setText("Ad created successfully!");
            } catch (AdAlreadyExistsException e) {
                creatingReviewMessage.setText(e.getMessage());
            }
        }
        else
            creatingReviewMessage.setText("Error: There are some incomplete fields !");
    }
}
