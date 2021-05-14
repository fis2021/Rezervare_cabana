package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.controllers.SearchToRentController;
import org.loose.fis.sre.exceptions.RenterAlreadyExistsException;
import org.loose.fis.sre.model.Renter;

import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class RenterService extends SearchToRentController {
    private static ObjectRepository<Renter> RenterRepository;

    public static void initDatabase() {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("renters2.db").toFile())
                .openOrCreate("test", "test");

        RenterRepository = database.getRepository(Renter.class);
    }

    public static ObjectRepository<Renter> getDatabase()
    {
        return RenterRepository;
    }

    public static void addRenter(String nume_proprietate, String full_name, String email, String phone, boolean over_18) throws RenterAlreadyExistsException {
        //checkRenterDoesNotAlreadyExist(full_name);
        RenterRepository.insert(new Renter(nume_proprietate, full_name, email, phone, over_18));
    }

    protected static void checkRenterDoesNotAlreadyExist(String full_name) throws RenterAlreadyExistsException {
        for (Renter renter : RenterRepository.find()) {
            if (Objects.equals(full_name, renter.getFull_name()))
                throw new RenterAlreadyExistsException(full_name);
        }
    }

    //public List<Renter> results = RenterRepository.find(ObjectFilters.ALL).toList();
    public String initializareNume_proprietate()
    {
        //System.out.println(property_name);
        return property_name;
    }

}
