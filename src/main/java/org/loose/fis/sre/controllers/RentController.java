package org.loose.fis.sre.controllers;

import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.exceptions.NoPasswordException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.RenterAlreadyExistsException;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.UserService;
import org.loose.fis.sre.services.RenterService;

import java.net.URL;
import java.time.LocalDate;
import java.time.Period;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import java.util.Date;
import java.time.format.DateTimeFormatter;

public class RentController extends RenterService implements Initializable {
    @FXML
    public TextField nume_proprietate;
    @FXML
    public HBox RentButton;
    @FXML
    private Text rentingMessage;
    @FXML
    private TextField full_name;
    @FXML
    private TextField email;
    @FXML
    private TextField phone;
    @FXML
    private CheckBox over_18;
    @FXML
    private DatePicker data_inceput;
    @FXML
    private DatePicker data_final;
    @FXML
    private TextField pret_noapte;


    public boolean checkIfPropertyRented = false;

    public boolean checkForEmptyFields()
    {
        if ((nume_proprietate.getText().equals(""))||(full_name.getText().equals(""))||(email.getText().equals("")))
        {
            rentingMessage.setText("Error: There are some incomplete fields !");
            return true;
        }
        else if (over_18.isSelected()==false)
        {
            rentingMessage.setText("Error: You must be over 18 in order to rent a property !");
            return true;
        }
        else if (checkIfDateAvailable()==false)
        {
            rentingMessage.setText("Error: The selected date interval is nod available !");
            return true;
        }
        return false ;
    }

    public void getClientNameText_as_Renter(String clientName) {
        full_name.setText(clientName);
    }

    public void setPret_noapte_Text_as_Renter(String Pret) {
        full_name.setText(Pret);
    }

    public void closeStageAfterRentButtonPressed()
    {
        Stage stage = (Stage) RentButton.getScene().getWindow();
        PauseTransition delay = new PauseTransition(Duration.seconds(1));
        delay.setOnFinished( event -> stage.close() );
        delay.play();
    }

    public int Data1_minus_Data2(String data_final, String data_inceput)
    {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        LocalDate data1 = LocalDate.parse(data_inceput,formatter);
        LocalDate data2 = LocalDate.parse(data_final,formatter);
        Period diff = Period.between(data1,data2);
        return diff.getDays();
    }

    private List<Renter> ListOfRenters=  getAllRenters() ;
    private boolean checkIfDateAvailable()
    {

        ArrayList<Boolean> Disponibilitate = new ArrayList<>();
        for(int i = 0 ; i < ListOfRenters.size() ; i ++)
        {
            String data_aleasa1 = data_inceput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String data_aleasa2 = data_final.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy"));
            String data_bd1 = ListOfRenters.get(i).getData_inceput();   // data din baza de date
            String data_bd2 = ListOfRenters.get(i).getData_final();

            //System.out.println(data_final.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
            if(ListOfRenters.get(i).getNume_proprietate().equals(nume_proprietate.getText())&&(data_bd1 != null)&&(data_bd2 != null))
            {
                if(!(((Data1_minus_Data2(data_bd1,data_aleasa1)>0)
                        &&(Data1_minus_Data2(data_bd2,data_aleasa1)>0))
                        ||((Data1_minus_Data2(data_bd1,data_aleasa2)<0)
                        &&(Data1_minus_Data2(data_bd2,data_aleasa2)<0))))
                {
                    Disponibilitate.add(Boolean.FALSE);
                    //Disponibilitate.get(i).toString();
                }
            }

        }
        System.out.println(Disponibilitate.toString());
        if(Disponibilitate.contains(Boolean.FALSE))
        {
            return false;
        }
        else
            return true;
    }

    public void handleRentingAction()
    {
        if (!checkForEmptyFields())
        {
            try
            {
                RenterService.addRenter(nume_proprietate.getText(),full_name.getText(), email.getText(), phone.getText(), over_18.isSelected(),data_inceput.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) ,data_final.getValue().format(DateTimeFormatter.ofPattern("dd/MM/yyyy")),Integer.parseInt(pret_noapte.getText()));
                rentingMessage.setText("Rental process complete!");
                checkIfPropertyRented = true ;
                closeStageAfterRentButtonPressed();
            }
            catch (RenterAlreadyExistsException e)
            {
                rentingMessage.setText(e.getMessage());
            }
        }
        //else
            //rentingMessage.setText("Error: There are some incomplete fields !");
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle)
    {
        nume_proprietate.setText(initializareNume_proprietate());
        pret_noapte.setText(initializarePret_noapte());
        ///pret_noapte.setEditable(false);
    }

}
