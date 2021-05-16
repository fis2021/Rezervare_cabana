package org.loose.fis.sre.controllers;

import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.AdService;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class OwnerHomeController extends AdService implements Initializable {

    public TableView<Ad> tableView;
    public TableColumn id;
    public TableColumn nume_proprietate;
    public TableColumn locatie;
    public TableColumn pret;
    public String OwnerName;

    public void switchStage_to_creare_anunt() throws IOException {
        Stage stage = new Stage();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("creare-anunt.fxml"));
        Pane root = fxmlLoader.load();
        CreateAdController controller1 = fxmlLoader.getController();
        controller1.getOwnerNameText(this.OwnerName);
        //
        stage.setTitle("Creare anunt");
        stage.setScene(new Scene(root, 500, 400));
        stage.showAndWait();
        if (controller1.checkIfAdCreated) {
            tableView.getItems().add(controller1.returnAdCreated());
            tableView.refresh();
        }
    }

    public void switchStage_to_Leave_Review() throws IOException {
        Stage stage = new Stage();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LeaveReview.fxml"));
        Pane root = fxmlLoader.load();
        LeaveReviewController controller = fxmlLoader.getController();
        controller.getAuthorNameText(this.OwnerName);
        controller.setNumeProprietateText(property_name);
        //controller.populateTable();
        //
        stage.setTitle("Write review");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public void switchStage_to_view_reviews() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ViewReviews.fxml"));
        Pane root = fxmlLoader.load();
        ReviewsController controller = fxmlLoader.getController();
        Ad ad = tableView.getSelectionModel().getSelectedItem();
        String nume_proprietate = ad.getNume_proprietate();
        controller.populateTableReviews(nume_proprietate);
        controller.Nume_proprietate.setText(nume_proprietate);
        stage.setTitle("View Reviews");
        stage.setScene(new Scene(root, 800, 800));
        stage.show();
    }

    public void passOwnerNameText(String text) {
        //System.out.println( text );
        this.OwnerName = text;
        //System.out.println( this.OwnerName );
    }

    public void populateTable() {
        ObservableList<Ad> data = tableView.getItems();
        for (int i = 0; i < rez.size(); i++) {
            if (((rez.get(i).getNume_proprietar()) != null) && ((rez.get(i).getNume_proprietar()).equals(this.OwnerName)))
                data.add(new Ad(rez.get(i).getNume_proprietar(),
                                rez.get(i).getNume_proprietate(),
                                rez.get(i).getLocatie(),
                                rez.get(i).getPret()
                        )
                );
        }
        id.setCellFactory(col -> new TableCell<Task, String>() {
            @Override
            public void updateIndex(int index) {
                super.updateIndex(index);
                if (isEmpty() || index < 0) {
                    setText(null);
                } else {
                    setText(Integer.toString(index + 1));
                }
            }
        });
    }


    public static String property_name;

    public void select() {
        Ad ad = tableView.getSelectionModel().getSelectedItem();

        property_name = ad.getNume_proprietate();
    }

    public void handleCreateAdAction(MouseEvent mouseEvent) throws IOException {
        switchStage_to_creare_anunt();

    }

    public void handleRespondReviewAdAction(MouseEvent mouseEvent) throws IOException {
        select();
        switchStage_to_Leave_Review();
    }

    public void handleViewReviewsAction(MouseEvent mouseEvent) throws IOException {
        switchStage_to_view_reviews();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //populateTable();
    }
}
