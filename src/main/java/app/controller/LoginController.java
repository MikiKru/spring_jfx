package app.controller;

import app.service.LoginService;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
            System.out.println("Zalogowano");
        } else {
            System.out.println("Błąd logowania!");
        }
    }

}