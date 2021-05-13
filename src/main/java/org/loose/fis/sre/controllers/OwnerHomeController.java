package org.loose.fis.sre.controllers;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;


import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OwnerHomeController extends AdService implements Initializable
{

    public TableView tableView;
    public TableColumn id;
    public TableColumn nume_proprietate;
    public TableColumn locatie;
    public TableColumn pret;
    public String OwnerName ;

    public void switchStage_to_creare_anunt() throws IOException
    {
        Stage stage = new Stage();
        //FXMLLoader fxmlLoader = new FXMLLoader();

        //Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("creare-anunt.fxml")) ;

        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("creare-anunt.fxml"));
        Pane root = fxmlLoader.load();
        CreateAdController controller = fxmlLoader.getController() ;
        controller.getOwnerNameText(this.OwnerName);
        //

        stage.setTitle("Creare anunt");
        stage.setScene(new Scene(root, 500, 400));
        stage.show();
    }


    public void passOwnerNameText(String text)
    {
        //System.out.println( text );
        this.OwnerName = text ;
        //System.out.println( this.OwnerName );
    }

    public void populateTable() {
        //System.out.println(rez.get(0).getLocatie());
        //getData();
        ObservableList<Ad> data = tableView.getItems();
        ObservableList<Integer> dat = tableView.getItems();
        for (int i = 0; i < rez.size(); i++)
        {
            if (((rez.get(i).getNume_proprietar()) != null)&&((rez.get(i).getNume_proprietar()).equals(this.OwnerName)))
            data.add(new Ad(rez.get(i).getNume_proprietar(),
                            rez.get(i).getNume_proprietate(),
                            rez.get(i).getLocatie(),
                            rez.get(i).getPret()
                    )
            );
            //id.setText( String.valueOf(i+1) );
        }

        id.setCellFactory(col -> new TableCell<Task, String>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index+1));
                }
            }
        });
    }

    public void handleCreateAdAction(MouseEvent mouseEvent) throws IOException
    {
        switchStage_to_creare_anunt();
    }

    public void handleRespondReviewAdAction(MouseEvent mouseEvent) {
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //populateTable();
    }
}
