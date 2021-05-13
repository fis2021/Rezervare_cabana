package org.loose.fis.sre.controllers;

import javafx.beans.binding.Bindings;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.AdService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ClientHomeController extends AdService implements Initializable {

    public TableView tableView;
    public TableColumn id;
    public TableColumn pret;
    public TableColumn<Ad,String> locatie;
    public TableColumn nume_proprietate;


    public void switchStage_to_Search_to_Rent() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("Search-to-Rent.fxml")) ;
        stage.setTitle("Search to Rent");
        stage.setScene(new Scene(root, 850, 700));
        stage.show();
    }

    public void switchStage_to_Leave_Review() throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader();

        Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("LeaveReview.fxml")) ;
        stage.setTitle("Write review");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }


    public void populateTable() {
        //System.out.println(rez.get(0).getLocatie());
        //getData();
        ObservableList<Ad> data = tableView.getItems();
        ObservableList<Integer> dat = tableView.getItems();
        for (int i = 0; i < rez.size(); i++)
        {
            data.add(new Ad(rez.get(i).getNume_proprietar(),
                            rez.get(i).getNume_proprietate(),
                            rez.get(i).getLocatie(),
                            rez.get(i).getPret()
                    )
            );
            //id.setText( String.valueOf(i+1) );
        }

        /*
        id.setCellFactory(col -> {
            TableCell<Task, String> cell = new TableCell<>();
            cell.textProperty().bind(Bindings.when(cell.emptyProperty())
                    .then("")
                    .otherwise(cell.indexProperty().asString()));
            return cell ;
        });
        */
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


    public void handleSearchToRentAction(MouseEvent mouseEvent) throws IOException
    {
        switchStage_to_Search_to_Rent();
    }

    public void handleLeaveReviewAction(MouseEvent mouseEvent) throws IOException
    {

        switchStage_to_Leave_Review();

    }

    public void handleCancelRentalAction(MouseEvent mouseEvent)
    {

    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        populateTable();
    }
}

