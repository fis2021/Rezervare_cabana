package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import org.loose.fis.sre.exceptions.USERAlreadyExistsException;
import org.loose.fis.sre.exceptions.UsernameAlreadyExistsException;
import org.loose.fis.sre.exceptions.NoRoleSelectedException;
import org.loose.fis.sre.exceptions.NoPasswordException;
import org.loose.fis.sre.services.UserService;

public class RegistrationController  extends UserService{

    @FXML
    private Text registrationMessage;
    @FXML
    private Text loginMessage;
    @FXML
    private PasswordField passwordField;
    @FXML
    private TextField usernameField;
    @FXML
    private ChoiceBox role;

    @FXML
    public void initialize() {
        role.getItems().addAll("Client", "Owner");
    }

    @FXML
    public void handleRegisterAction() {
        try
        {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        }
        catch (NoPasswordException e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (NoRoleSelectedException e)
        {
            registrationMessage.setText(e.getMessage());
        }
        catch (UsernameAlreadyExistsException e)
        {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void handleLoginAction() throws InterruptedException {
        /*try
        {
            UserService.checkUserDoesNotAlreadyExist(usernameField.getText());
            loginMessage.setText("There is no account with this username !");
        }
        catch (UsernameAlreadyExistsException e)
        {
            //loginMessage.setText("Login successfull!");
            loginMessage.setText("Correct username !");
            //Thread.sleep(10);
            */try
            {
                UserService.checkUSERDoesNotAlreadyExist(usernameField.getText(), passwordField.getText(), (String) role.getValue());
                loginMessage.setText("Incorrect credentials !");
            }
            catch (USERAlreadyExistsException ex)
            {
                loginMessage.setText("Login successfull!");

            }

        //}

    }
}
