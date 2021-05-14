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
import org.loose.fis.sre.services.RenterService;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class SearchToRentController extends AdService implements Initializable {
    public TableView<Ad> tableView;
    public TableColumn id;
    public TableColumn Locatie;
    public TableColumn NumeProprietate;
    public TableColumn Pret;
    public TableColumn NumeProprietar;
    @FXML
    private Text creatingAdMessage;
    @FXML
    private TextField nume_proprietate;
    @FXML
    private TextField locatie;
    @FXML
    private TextField pret;

    public String ClientName ;


    //private int i = 1;

    public void switchStage_to_Rent() throws IOException {
        Stage stage = new Stage();
       // FXMLLoader fxmlLoader = new FXMLLoader();

        //Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("Rent.fxml"));

        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Rent.fxml"));
        Pane root = fxmlLoader.load();
        RentController controller = fxmlLoader.getController() ;
        controller.getClientNameText_as_Renter(this.ClientName);
        //

        stage.setTitle("Rental Process");
        stage.setScene(new Scene(root, 500, 500));
        stage.show();
    }

    public void getClientNameText(String text)
    {
        this.ClientName = text ;
    }

    public void populateTable() {
        //System.out.println(rez.get(0).getLocatie());
        //getData();
        ObservableList<Ad> data2 = tableView.getItems();
        //ObservableList<Integer> dat = tableView.getItems();
        for (int i = 0; i < rez.size(); i++) {
            data2.add(new Ad(rez.get(i).getNume_proprietar(),
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
                    setText(Integer.toString(index + 1));
                }
            }
        });
    }
/*
    public void select()
    {
        TablePosition pos = (TablePosition) tableView.getSelectionModel().getSelectedCells().get(0);
        int row = pos.getRow();

        TableView item = (TableView) tableView.getItems().get(row);

        TableColumn col = pos.getTableColumn();

        String data = (String) col.getCellObservableValue(item).getValue();

        System.out.println(data);
    }
*/
    public static String property_name ;
    public void select2()
    {
        Ad ad = tableView.getSelectionModel().getSelectedItem();

        property_name = ad.getNume_proprietate();
        //System.out.println(x);

        //return x ;
        //System.out.println(ad);
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

    public void handleRentAction(MouseEvent mouseEvent) throws IOException
    {
        //System.out.println(ClientName);

        select2();
        switchStage_to_Rent();
        //System.out.println(property_name);
    }

    public void handleViewReviewsAction(MouseEvent mouseEvent)
    {
        select2();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //populateTable();

    }


}
