package org.loose.fis.sre.services;

import org.dizitart.no2.IndexOptions;
import org.dizitart.no2.IndexType;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.Cursor;
import org.dizitart.no2.objects.ObjectFilter;
import org.dizitart.no2.objects.ObjectRepository;
import org.dizitart.no2.objects.filters.ObjectFilters;
import org.loose.fis.sre.exceptions.AdAlreadyExistsException;
import org.loose.fis.sre.model.Ad;
import org.loose.fis.sre.model.Renter;
import org.loose.fis.sre.model.User;


import java.util.Arrays;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class AdService
{
    private static Nitrite database;
    private static ObjectRepository<Ad> AdRepository;
    public static void initDatabase()
    {
        database = Nitrite.builder()
                .filePath(getPathToFile("places-to-rent2.db").toFile())
                .openOrCreate("test", "test");
        AdRepository = database.getRepository(Ad.class);
    }

    public static ObjectRepository<Ad> getAdDatabase()
    {
        return AdRepository;
    }

    public static void addAd(String nume_proprietar, String nume_proprietate, String locatie, String pret) throws AdAlreadyExistsException {
        checkAdDoesNotAlreadyExist(nume_proprietate);
        AdRepository.insert(new Ad(nume_proprietar, nume_proprietate, locatie, pret));
    }
    public static ObjectRepository<Ad> getAds()
    {
        return AdRepository;
    }

    protected static void checkAdDoesNotAlreadyExist(String nume_proprietate) throws AdAlreadyExistsException {
        for (Ad ad : AdRepository.find()) {
            if (Objects.equals(nume_proprietate, ad.getNume_proprietate()))
                throw new AdAlreadyExistsException(nume_proprietate);
        }
    }
    public List<Ad> rez = AdRepository.find(ObjectFilters.ALL).toList();

    public static List<Ad> getAllAds() {
        return AdRepository.find().toList();
    }

    public static void closeDatabase()
    {
        database.close();
    }

    protected void getData()
    {

        Cursor<Ad> results = AdRepository.find();
        System.out.println(rez);

        Ad[] ads = new Ad[20];
        int i = 0 ;
        for (Ad ad : AdRepository.find())
        {
            ads[i] = ad;
            System.out.println(ad.getLocatie());
        }
        System.out.println(ads[0].getLocatie());
        System.out.println(rez.get(0).getLocatie());
    }



}
