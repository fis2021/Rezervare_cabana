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
import javafx.scene.control.TextField;
import org.loose.fis.sre.services.ReviewService;

public class ReviewsController extends ReviewService
{
    @FXML
    public TextField nume_proprietate;
    @FXML
    public TextField author_name;

    public boolean checkForEmptyFields()
    {
        if ((nume_proprietate.getText().equals(""))||(author_name.getText().equals("")))
            return true ;
        return false ;
    }



}
