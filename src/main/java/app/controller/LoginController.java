package app.controller;

import app.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import app.model.User;

import java.security.NoSuchAlgorithmException;

@Controller
public class LoginController {

    private LoginService loginService;
    @Autowired
    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    @FXML
    private TextField tfLogin;

    @FXML
    private PasswordField pfPassword;

    @FXML
    void loginAction(ActionEvent event) throws NoSuchAlgorithmException {
        User user = loginService.loginUser(
                tfLogin.getText(), pfPassword.getText());
        if(user != null){
            loginService.clearField(tfLogin);
            loginService.clearField(pfPassword);
            if(user.isStatus()) {
                loginService.getAlertWindow(
                        Alert.AlertType.INFORMATION,
                        "logowanie",
                        "ZALOGOWANO",
                        "Zalogowano użytkownika o loginie " + user.getLogin());
            } else {
                loginService.getAlertWindow(
                        Alert.AlertType.INFORMATION,
                        "logowanie",
                        "KONTO NIEAKTYWNE",
                        "Twoje konto został zablokowane!");

            }
        } else {
            loginService.getAlertWindow(
                    Alert.AlertType.INFORMATION,
                    "logowanie",
                    "BŁAD LOGOWANIA",
                    "Podaj poprawne login i hasło");
        }
    }

    public void initialize() throws NoSuchAlgorithmException {
        loginService.registerUser("x","x");
        loginService.registerUser("y","y");
        loginService.registerUser("z","z");
    }

}