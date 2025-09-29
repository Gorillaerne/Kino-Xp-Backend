package gruppe6.kea.kinobackend.Models;

import gruppe6.kea.kinobackend.Models.Enums.Authlevel;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Cinema {

   @Id
    private int id;

   @Column(unique = true)
    private String username;

    private String password;

    private Authlevel authlevel;

    public Cinema() {
    }

    public Cinema(String username, String password, Authlevel authlevel) {
        this.username = username;
        this.password = password;
        this.authlevel = authlevel;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Authlevel getAuthlevel() {
        return authlevel;
    }

    public void setAuthlevel(Authlevel authlevel) {
        this.authlevel = authlevel;
    }
}
