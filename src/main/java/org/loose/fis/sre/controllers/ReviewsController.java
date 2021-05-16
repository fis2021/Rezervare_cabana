package org.loose.fis.sre.controllers;

import javafx.application.Platform;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.util.Callback;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Review;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;
import javafx.scene.control.TextField;
import org.loose.fis.sre.services.ReviewService;

import java.net.URL;
import java.util.ResourceBundle;

public class ReviewsController extends ReviewService implements Initializable
{
    @FXML
    public TextField Nume_proprietate;
    @FXML
    public TableView<Review> table;
    @FXML
    public TextField author_name;
    @FXML
    public TableColumn Nr;
    @FXML
    public TableColumn Date;
    @FXML
    public TableColumn nume_autor;
    @FXML
    public TableColumn textReview;
    @FXML
    public TableColumn Nume;


    public void populateTableReviews(String proprietate_cautata) {
        ObservableList<Review> data2 = table.getItems();
        {
                for (int i = 0; i < reviewsList.size(); i++) {
                    if(reviewsList.get(i).getNume_proprietate().equals(proprietate_cautata))
                        data2.add(new Review(reviewsList.get(i).getNume_proprietate(),
                                            reviewsList.get(i).getNume_autor(),
                                            reviewsList.get(i).getText_review(),
                                            reviewsList.get(i).getDate()
                                )
                                
                        );
                }
        }

        final Callback<TableColumn<Review,String>, TableCell<Review,String>> WRAPPING_CELL_FACTORY =
                new Callback<TableColumn<Review,String>, TableCell<Review,String>>() {

                    @Override public TableCell<Review,String> call(TableColumn<Review,String> param) {
                        TableCell<Review,String> tableCell = new TableCell<Review,String>() {
                            @Override protected void updateItem(String item, boolean empty) {
                                if (item == getItem()) return;

                                super.updateItem(item, empty);

                                if (item == null) {
                                    super.setText(null);
                                    super.setGraphic(null);
                                } else {
                                    super.setText(null);
                                    Label l = new Label(item);
                                    l.setWrapText(true);
                                    VBox box = new VBox(l);
                                    l.heightProperty().addListener((observable,oldValue,newValue)-> {
                                        box.setPrefHeight(newValue.doubleValue()+7);
                                        Platform.runLater(()->this.getTableRow().requestLayout());
                                    });
                                    super.setGraphic(box);
                                }
                            }
                        };
                        return tableCell;
                    }
                };

        textReview.setCellFactory(tc -> {
            TableCell<Review, String> cell = new TableCell<>();
            Text text = new Text();
            cell.setGraphic(text);
            cell.setPrefHeight(Control.USE_COMPUTED_SIZE);
            text.wrappingWidthProperty().bind(textReview.widthProperty());
            text.textProperty().bind(cell.itemProperty());
            return cell ;
        });

        Date.setStyle( "-fx-alignment: CENTER;");
        Nr.setStyle( "-fx-alignment: CENTER;");
        nume_autor.setStyle( "-fx-alignment: CENTER;");

        Nr.setCellFactory(col -> new TableCell<Task, String>() {
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

}
