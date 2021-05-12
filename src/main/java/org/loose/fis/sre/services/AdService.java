package org.loose.fis.sre.services;

import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.model.Ad;


import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AdService
{
    private static ObjectRepository<Ad> AdRepository;

    public static void initDatabase()
    {
        Nitrite database = Nitrite.builder()
                .filePath(getPathToFile("places-to-rent.db").toFile())
                .openOrCreate("test", "test");

        AdRepository = database.getRepository(Ad.class);
    }

    public static void addAd(String nume_proprietate, String locatie, String pret) throws AdAlreadyExistsException {
        checkAdDoesNotAlreadyExist(nume_proprietate);
        AdRepository.insert(new Ad(nume_proprietate, locatie, pret));
    }

    protected static void checkAdDoesNotAlreadyExist(String nume_proprietate) throws AdAlreadyExistsException {
        for (Ad ad : AdRepository.find()) {
            if (Objects.equals(nume_proprietate, ad.getNume_proprietate()))
                throw new AdAlreadyExistsException(nume_proprietate);
        }
    }

}
