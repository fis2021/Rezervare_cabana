package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;


public class OwnerHomeController
{

    public TableView tableView;

    public void switchStage_to_creare_anunt() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("creare-anunt.fxml")) ;
        stage.setTitle("Creare anunt");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }

    public void handleCreateAdAction(MouseEvent mouseEvent) throws IOException
    {
        switchStage_to_creare_anunt();
    }

    public void handleRespondReviewAdAction(MouseEvent mouseEvent) {
    }
}
