package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.services.AdService;
import org.loose.fis.sre.services.RenterService;
import javafx.scene.text.Text;

import java.util.Objects;


public class RentDetailsController extends RenterService implements Initializable {

    @FXML
    public Text nume_proprietate_details;
    @FXML
    public Text nume_proprietar_details;
    @FXML
    public Text locatie_details;
    @FXML
    public Text data_initiala_details;
    @FXML
    public Text data_finala_details;
    @FXML
    public Text nr_nopti_details;
    @FXML
    public Text pret_sejur_details;


    public void setTextFields(String nume_proprietate, String nume_chirias) {
        ObjectRepository<Renter> RenterRepository = RenterService.getDatabase();
        ObjectRepository<Ad> AdRepository = AdService.getAds();
        for (Renter renter : RenterRepository.find()) {
            if (Objects.equals(nume_proprietate, renter.getNume_proprietate()) && Objects.equals(nume_chirias, renter.getFull_name())) {
                nume_proprietate_details.setText(renter.getNume_proprietate());
                for (Ad ad : AdRepository.find()) {
                    if (Objects.equals(renter.getNume_proprietate(), ad.getNume_proprietate()))
                        nume_proprietar_details.setText(ad.getNume_proprietar());
                    locatie_details.setText(ad.getLocatie());
                }
                data_initiala_details.setText(renter.getData_inceput());
                data_finala_details.setText(renter.getData_final());
                nr_nopti_details.setText(renter.getNr_nopti() + "");
                pret_sejur_details.setText(renter.getPret_sejur() + "");
            }
        }
    }
}
