package org.loose.fis.sre.controllers;

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
import org.dizitart.no2.RemoveOptions;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.RenterService;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import static org.dizitart.no2.objects.filters.ObjectFilters.and;
import static org.dizitart.no2.objects.filters.ObjectFilters.eq;

public class ClientHomeController extends AdService implements Initializable {

    public TableView<Ad> tableView;
    public TableColumn id;
    public TableColumn pret;
    public TableColumn locatie;
    public TableColumn nume_proprietate;
    public String ClientName;


    public void switchStage_to_Search_to_Rent() throws IOException {
        Stage stage = new Stage();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Search-to-Rent.fxml"));
        Pane root = fxmlLoader.load();
        SearchToRentController controller = fxmlLoader.getController();
        controller.getClientNameText(this.ClientName);
        controller.populateTable();
        //

        stage.setTitle("Search to Rent");
        stage.setScene(new Scene(root, 850, 900));
        stage.showAndWait();
        if (controller.checkIfPropertyRented2) {
            for (int i = 0; i < controller.returnAdRented.length; i++) {
                if (controller.returnAdRented[i] != null)
                    tableView.getItems().add(controller.returnAdRented[i]);
            }

            tableView.refresh();
        }
    }

    public void switchStage_to_Leave_Review() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("LeaveReview.fxml"));
        Pane root = fxmlLoader.load();
        LeaveReviewController controller = fxmlLoader.getController();
        controller.getAuthorNameText(this.ClientName);
        controller.setNumeProprietateText(property_name);
        stage.setTitle("Write review");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public void switchStage_to_Rent_Details() throws IOException {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("RentDetails.fxml"));
        Pane root = fxmlLoader.load();
        RentDetailsController controller = fxmlLoader.getController();
        Ad ad = tableView.getSelectionModel().getSelectedItem();
        controller.setTextFields(ad.getNume_proprietate(), ClientName);
        stage.setTitle("Rent Details");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();

    }


    public void passClientNameText(String text) {
        this.ClientName = text;
    }

    //
    private final ObjectRepository<Renter> RenterRepository = RenterService.getDatabase();

    public List<Renter> results = RenterRepository.find(ObjectFilters.ALL).toList();

    public boolean checkIfRentedPropertiesByName() {
        for (int i = 0; i < results.size(); i++) {
            System.out.println(results.get(i).getNume_proprietate());
        }

        return false;
    }
    //

    public void populateTable() {
        ObservableList<Ad> data = tableView.getItems();
        for (int j = 0; j < results.size(); j++) {
            if ((results.get(j).getFull_name()).equals(this.ClientName))
                for (int i = 0; i < rez.size(); i++) {
                    if (((rez.get(i).getNume_proprietate()) != null) && ((rez.get(i).getNume_proprietate()).equals(results.get(j).getNume_proprietate())))
                        data.add(new Ad(rez.get(i).getNume_proprietar(),
                                        rez.get(i).getNume_proprietate(),
                                        rez.get(i).getLocatie(),
                                        rez.get(i).getPret()
                                )
                        );
                }
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

    public void handleSearchToRentAction(MouseEvent mouseEvent) throws IOException {
        switchStage_to_Search_to_Rent();
    }

    public void handleLeaveReviewAction(MouseEvent mouseEvent) throws IOException {
        select();

        switchStage_to_Leave_Review();

    }

    public void handleCancelRentalAction(MouseEvent mouseEvent) throws IOException {
        select();
        RemoveOptions options = new RemoveOptions();
        options.setJustOne(true);
        RenterRepository.remove(and(eq("nume_proprietate", property_name), eq("full_name", this.ClientName)), options);
        tableView.getItems().remove(tableView.getSelectionModel().getSelectedItem());
        tableView.refresh();
    }

    public void handleRentDetailsAction(MouseEvent mouseEvent) throws IOException {
        switchStage_to_Rent_Details();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //populateTable();
    }


}

