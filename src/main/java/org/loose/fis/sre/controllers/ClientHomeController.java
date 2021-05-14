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
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.RenterService;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Objects;
import java.util.ResourceBundle;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class ClientHomeController extends AdService implements Initializable {

    public TableView tableView;
    public TableColumn id;
    public TableColumn pret;
    public TableColumn locatie;
    public TableColumn nume_proprietate;
    public String ClientName ;


    public void switchStage_to_Search_to_Rent() throws IOException
    {
        Stage stage = new Stage();
        //FXMLLoader fxmlLoader = new FXMLLoader();

        //Pane root = FXMLLoader.load(getClass().getClassLoader().getResource("Search-to-Rent.fxml")) ;

        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("Search-to-Rent.fxml"));
        Pane root = fxmlLoader.load();
        SearchToRentController controller = fxmlLoader.getController() ;
        controller.getClientNameText(this.ClientName);
        controller.populateTable();
        //

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


    public void passClientNameText(String text)
    {
        this.ClientName = text ;
    }

    //
    private ObjectRepository<Renter> RenterRepository = RenterService.getDatabase();

    public static void initDatabase() {
        //Nitrite database = Nitrite.builder()
         //       .filePath(getPathToFile("renters.db").toFile())
         //       .openOrCreate("test", "test");
        //Nitrite database = (getPathToFile("renters.db").toFile());
        //RenterRepository = database.getRepository(Renter.class);
    }

    public List<Renter> results = RenterRepository.find(ObjectFilters.ALL).toList();

    public boolean checkIfRentedPropertiesByName()
    {
        //initDatabase();
    /*    for (Renter renter : RenterRepository.find()) {
            if (Objects.equals(this.ClientName, renter.getFull_name()))
            {
                System.out.println(renter.getFull_name());
                return true;
            }

        }
    */
        for (int i = 0; i < results.size(); i++)
        {
            //System.out.println(results.get(i).getFull_name());
            System.out.println(results.get(i).getNume_proprietate());
        }

        return false ;
    }
    //

    public void populateTable() {
        //System.out.println(rez.get(0).getLocatie());
        //getData();
        ObservableList<Ad> data = tableView.getItems();
        //ObservableList<Integer> dat = tableView.getItems();
        for (int j = 0 ; j < results.size() ; j ++ )
        {
            if ((results.get(j).getFull_name()).equals(this.ClientName))
            for (int i = 0; i < rez.size(); i++) {
                //if(checkIfRentedPropertiesByName())
                //checkIfRentedPropertiesByName();
                if (((rez.get(i).getNume_proprietate()) != null)&&((rez.get(i).getNume_proprietate()).equals(results.get(j).getNume_proprietate())))
                data.add(new Ad(rez.get(i).getNume_proprietar(),
                                rez.get(i).getNume_proprietate(),
                                rez.get(i).getLocatie(),
                                rez.get(i).getPret()
                        )
                );
                //id.setText( String.valueOf(i+1) );
            }
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
        //populateTable();
    }


}

