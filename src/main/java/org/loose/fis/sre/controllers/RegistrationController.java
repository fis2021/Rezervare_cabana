package org.loose.fis.sre.controllers;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import org.loose.fis.sre.exceptions.*;
import org.loose.fis.sre.services.UserService;

import java.io.IOException;

public class RegistrationController extends UserService {

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
        try {
            UserService.addUser(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            registrationMessage.setText("Account created successfully!");
        } catch (NoUsernameException e) {
            registrationMessage.setText(e.getMessage());
        } catch (NoPasswordException e) {
            registrationMessage.setText(e.getMessage());
        } catch (NoRoleSelectedException e) {
            registrationMessage.setText(e.getMessage());
        } catch (UsernameAlreadyExistsException e) {
            registrationMessage.setText(e.getMessage());
        }
    }

    @FXML
    public void switchStage_to_Owner_Home() throws IOException {
        Stage stage = new Stage();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("OwnerHome.fxml"));
        Pane root = fxmlLoader.load();
        OwnerHomeController controller = fxmlLoader.getController();
        controller.passOwnerNameText(usernameField.getText());
        controller.populateTable();
        //
        stage.setTitle("Owner Home");
        stage.setScene(new Scene(root, 650, 500));
        stage.show();
    }

    public void switchStage_to_Client_Home() throws IOException {
        Stage stage = new Stage();
        //
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("ClientHome.fxml"));
        Pane root = fxmlLoader.load();
        ClientHomeController controller = fxmlLoader.getController();
        controller.passClientNameText(usernameField.getText());
        controller.populateTable();
        //
        stage.setTitle("Client Home");
        stage.setScene(new Scene(root, 800, 500));
        stage.show();

    }

    @FXML
    public void handleLoginAction() throws IOException {
        try {
            UserService.checkUSERDoesNotAlreadyExist(usernameField.getText(), passwordField.getText(), (String) role.getValue());
            loginMessage.setText("Incorrect credentials !");
        } catch (USERAlreadyExistsException ex) {

            loginMessage.setText("Login successfull!");
            if (role.getValue().equals("Owner"))
                switchStage_to_Owner_Home();
            else if (role.getValue().equals("Client"))
                switchStage_to_Client_Home();
        }
    }
}
