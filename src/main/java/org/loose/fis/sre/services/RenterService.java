package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.controllers.SearchToRentController;
import org.loose.fis.sre.model.Renter;
import java.util.List;
import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RenterService extends SearchToRentController {
    private static Nitrite database;
    private static ObjectRepository<Renter> RenterRepository;

    public static void initDatabase() {
        database = Nitrite.builder()
                .filePath(getPathToFile("renters2.db").toFile())
                .openOrCreate("test", "test");

        RenterRepository = database.getRepository(Renter.class);
    }

    public static void closeDatabase() {
        database.close();
    }


    public static ObjectRepository<Renter> getDatabase() {
        return RenterRepository;
    }

    public static void addRenter(String nume_proprietate, String full_name, String email, String phone, boolean over_18, String data_inceput, String data_final, int pret_noapte) {
        RenterRepository.insert(new Renter(nume_proprietate, full_name, email, phone, over_18, data_inceput, data_final, pret_noapte));
    }

    public static List<Renter> getAllRenters() {
        return RenterRepository.find().toList();
    }


    public String initializareNume_proprietate() {
        return property_name;
    }

    public String initializarePret_noapte() {
        return price_per_night;
    }

}
