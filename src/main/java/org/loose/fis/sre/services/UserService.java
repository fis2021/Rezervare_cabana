package org.loose.fis.sre.services;


import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import org.dizitart.no2.Nitrite;
import org.dizitart.no2.objects.ObjectRepository;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.model.User;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Objects;

import static org.loose.fis.sre.services.FileSystemService.getPathToFile;

public class UserService {
    private static Nitrite database;
    private static ObjectRepository<User> userRepository;

    public static void initDatabase() {
        //FileSystemService.initDirectory();
        database = Nitrite.builder()
                .filePath(getPathToFile("rezervare-cabana.db").toFile())
                .openOrCreate("test", "test");

        userRepository = database.getRepository(User.class);
    }

    public static void closeDatabase(){
        database.close();
    }
    public static void addUser(String username, String password, String role)
            throws UsernameAlreadyExistsException, NoRoleSelectedException, NoPasswordException , NoUsernameException
    {
        if ((!Objects.equals(role, "Client"))&&(!Objects.equals(role, "Owner")))
            throw new NoRoleSelectedException(username);
        else if (password.equals(""))
            throw new NoPasswordException(username) ;
        else if(username.equals(""))
            throw new NoUsernameException(username);
        else
        {
            checkUserDoesNotAlreadyExist(username);
            userRepository.insert(new User(username, encodePassword(username, password), role));
        }

    }

    public static List<User> getAllUsers() {
        return userRepository.find().toList();
    }

    protected static void checkUserDoesNotAlreadyExist(String username) throws UsernameAlreadyExistsException {
        for (User user : userRepository.find()) {
            if (Objects.equals(username, user.getUsername()))
                throw new UsernameAlreadyExistsException(username);
        }
    }

    protected static void checkUSERDoesNotAlreadyExist(String username, String password, String role ) throws USERAlreadyExistsException {
        for (User user : userRepository.find()) {
            if ( (Objects.equals(encodePassword(username, password), user.getPassword()))
                  && (Objects.equals(username, user.getUsername()))
                  && (Objects.equals(role, user.getRole()))
               )
                throw new USERAlreadyExistsException(username);
        }
    }



    static String encodePassword(String salt, String password) {
        MessageDigest md = getMessageDigest();
        md.update(salt.getBytes(StandardCharsets.UTF_8));

        byte[] hashedPassword = md.digest(password.getBytes(StandardCharsets.UTF_8));

        // This is the way a password should be encoded when checking the credentials
        return new String(hashedPassword, StandardCharsets.UTF_8)
                .replace("\"", ""); //to be able to save in JSON format
    }

    private static MessageDigest getMessageDigest() {
        MessageDigest md;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new IllegalStateException("SHA-512 does not exist!");
        }
        return md;
    }


}
