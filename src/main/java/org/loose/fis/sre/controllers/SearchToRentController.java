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

public class SearchToRentController extends AdService
{
    public TableView tableView;
    @FXML
    private Text creatingAdMessage;
    @FXML
    private TextField nume_proprietate;
    @FXML
    private TextField locatie;
    @FXML
    private TextField pret;

    //private int i = 1;

    public void switchStage_to_Rent() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("Rent.fxml")) ;
        stage.setTitle("Rental Process");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public void handleSearchByNameAction(MouseEvent mouseEvent) 
    {
        
    }

    public void handleSearchByLocationAction(MouseEvent mouseEvent) 
    {
        
    }

    public void handleSearchByPrice(MouseEvent mouseEvent) 
    {
        
    }

    public void handleRentAction(MouseEvent mouseEvent) throws IOException {
        switchStage_to_Rent();
    }
}
