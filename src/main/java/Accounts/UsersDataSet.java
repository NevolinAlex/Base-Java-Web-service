package Accounts;
import javax.persistence.*;
import java.io.Serializable;

/**
 * Created by Alex Nevolinf on 22.12.2016.
 */
@Entity
@Table(name = "users")
public class UsersDataSet implements Serializable {

    @Column(name = "login")
    private String login;

    @Column(name = "password", unique = true, updatable = false)
    private String password;


    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet() {
    }

    @SuppressWarnings("UnusedDeclaration")
    public UsersDataSet(String login, String password) {
        this.setLogin(login);
        this.setPassword(password);
    }

    public UsersDataSet(String password) {
        this.setLogin("");
        this.setPassword(password);
    }

    @SuppressWarnings("UnusedDeclaration")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Override
    public String toString() {
        return "UsersDataSet{" +
                "login=" + login +
                ", password='" + password + '\'' +
                '}';
    }
}