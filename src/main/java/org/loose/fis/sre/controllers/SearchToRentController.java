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
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.services.AdService;

import java.io.IOException;
import java.net.URL;
import java.util.Locale;
import java.util.ResourceBundle;

public class SearchToRentController extends AdService implements Initializable {
    public TableView<Ad> tableView ;
    public TableColumn id;
    public TableColumn Locatie;
    public TableColumn NumeProprietate;
    public TableColumn Pret;
    public TableColumn NumeProprietar;
    @FXML
    private Text creatingAdMessage;
    @FXML
    private TextField nume_proprietatateField;
    @FXML
    private TextField locatieField;
    @FXML
    private TextField pretField;

    public String ClientName ;

    public boolean checkIfPropertyRented2 ;

    private int countNewPropertiesRented = 1;

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
        stage.setScene(new Scene(root, 650, 500));
        stage.showAndWait();
        checkIfPropertyRented2 = false ;
        if (controller.checkIfPropertyRented)
        {
            checkIfPropertyRented2 = true ;
        }
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
    public Ad[] returnAdRented = new Ad[rez.size()];
    private int i = 0 ;
    //Ad ad ;
    public void select2()
    {
        //Ad ad = tableView.getSelectionModel().getSelectedItem();
        //property_name = ad.getNume_proprietate();
        Ad ad = tableView.getSelectionModel().getSelectedItem();
        property_name = ad.getNume_proprietate();
        returnAdRented[i++] = ad;
        //System.out.println(x);

        //return x ;
        //System.out.println(ad);
    }

    public void handleSearchByNameAction(MouseEvent mouseEvent) 
    {
        ObservableList<Ad> Ads = tableView.getItems();
        tableView.getItems().clear();
        for (int i = 0; i < rez.size(); i++) {
            if(rez.get(i).getNume_proprietate().toLowerCase().contains(nume_proprietatateField.getText().toLowerCase())){
                Ads.add(new Ad(rez.get(i).getNume_proprietar(),
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

    public void handleSearchByLocationAction(MouseEvent mouseEvent) 
    {
        ObservableList<Ad> Ads = tableView.getItems();
        tableView.getItems().clear();
        for (int i = 0; i < rez.size(); i++) {
            if(rez.get(i).getLocatie().toLowerCase().contains(locatieField.getText().toLowerCase())){
                Ads.add(new Ad(rez.get(i).getNume_proprietar(),
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

    public void handleSearchByPrice(MouseEvent mouseEvent)
    {
        ObservableList<Ad> Ads = tableView.getItems();
        tableView.getItems().clear();
        for (int i = 0; i < rez.size(); i++) {
            if(Integer.parseInt(rez.get(i).getPret())<=Integer.parseInt(pretField.getText())){
                Ads.add(new Ad(rez.get(i).getNume_proprietar(),
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

    public void ShowAll(MouseEvent mouseEvent)
    {
        ObservableList<Ad> Ads = tableView.getItems();
        tableView.getItems().clear();
        for (int i = 0; i < rez.size(); i++) {
                Ads.add(new Ad(rez.get(i).getNume_proprietar(),
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

    public void switchStage_to_view_reviews(String proprietate_cautata) throws IOException
    {
        Stage stage = new Stage();
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ViewReviews.fxml"));
        Pane root = fxmlLoader.load();
        ReviewsController controller = fxmlLoader.getController() ;
        controller.populateTableReviews(proprietate_cautata);
        stage.setTitle("View Reviews");
        stage.setScene(new Scene(root, 900 , 900));
        stage.show();


    }

    public void handleRentAction(MouseEvent mouseEvent) throws IOException
    {
        //System.out.println(ClientName);

        select2();
        switchStage_to_Rent();
        //System.out.println(property_name);
    }

    public void handleViewReviewsAction(MouseEvent mouseEvent) throws IOException
    {
        Ad ad = tableView.getSelectionModel().getSelectedItem();
        String nume_proprietate=ad.getNume_proprietate();
        switchStage_to_view_reviews(nume_proprietate);
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        //populateTable();

    }


}
