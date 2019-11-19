package app.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Value;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data // -> dodaje get/set & toString()
@NoArgsConstructor  // -> dodaje bezparametrowy konstruktor
@AllArgsConstructor // -> dodaje konstruktor z wszystkimi polami
@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int user_id;
    private String login;
    private String password;
//    @Value("${value:true}")
    private boolean status = true;
    private int probes;

    public User(String login, String password) {
        this.login = login;
        this.password = password;
    }
}
