package app.service;

import app.model.User;
import app.repository.LoginRepository;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.codec.Hex;
import org.springframework.stereotype.Service;

import java.nio.charset.Charset;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;


@Service
public class LoginService {

    private LoginRepository loginRepository;
    @Autowired
    public LoginService(LoginRepository loginRepository) {
        this.loginRepository = loginRepository;
    }
    public String getPasswordEncodedByMd5(String password) throws NoSuchAlgorithmException, NoSuchAlgorithmException {
        MessageDigest md5Encoder = MessageDigest.getInstance("MD5");
        md5Encoder.update(password.getBytes(Charset.forName("UTF8")));
        final byte[] resultByte = md5Encoder.digest();
        final String result = new String(Hex.encode(resultByte));
        System.out.println(result);
        return result;
    }
    // logowanie użytkownika
    public User loginUserByLogin(String login){
        return loginRepository.findFirstByLogin(login);
    }

    public User loginUser(String login, String password) throws NoSuchAlgorithmException {
        return loginRepository
                .findFirstByLoginAndPassword(
                        login,
                        getPasswordEncodedByMd5(password));
    }
    // rejestracja użytkownika
    public User registerUser(String login, String password) throws NoSuchAlgorithmException {
        return loginRepository.save(new User(login,getPasswordEncodedByMd5(password)));
    }
    public void getAlertWindow(Alert.AlertType alertType, String title, String header, String content){
        Alert a = new Alert(alertType);
        a.setTitle(title);
        a.setHeaderText(header);
        a.setContentText(content);
        a.show();
    }
    // czyszczenie pól TF i PF
    public void clearField(TextField textField){
        textField.clear();
    }
    // aktywacja dezaktywacja użytkownika
    public void changeStatus(int user_id){
        if(loginRepository.findById(user_id).isPresent()) {
            User user = loginRepository.findById(user_id).get();
            user.setStatus(!user.isStatus());
            loginRepository.save(user);
        }
    }
    public void incrementUserProbes(int user_id){
        if(loginRepository.findById(user_id).isPresent()) {
            User user = loginRepository.findById(user_id).get();
            user.setProbes(user.getProbes() + 1);
            loginRepository.save(user);
        }
    }
}
