package org.loose.fis.sre.controllers;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class ClientHomeController
{

    public TableView tableView;

    public void switchStage_to_Search_to_Rent() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("Search-to-Rent.fxml")) ;
        stage.setTitle("Search to Rent");
        stage.setScene(new Scene(root, 700, 700));
        stage.show();
    }

    public void handleSearchToRentAction(MouseEvent mouseEvent) throws IOException
    {
        switchStage_to_Search_to_Rent();
    }

    public void handleLeaveReviewAction(MouseEvent mouseEvent)
    {

    }
}
