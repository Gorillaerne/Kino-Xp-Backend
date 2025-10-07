package gruppe6.kea.kinobackend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gruppe6.kea.kinobackend.Models.Enums.Authlevel;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name = "\"user\"")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(unique = true)
private String username;

    @JsonIgnore
    private String password;
    @Enumerated(EnumType.STRING)
    private Authlevel authlevel;

   @ManyToMany(mappedBy = "userList")
   @JsonManagedReference("cinema-user")
    private List<Cinema> cinemas;

    public User() {
    }

    public User(String username, String password, Authlevel authlevel, List<Cinema> cinemas) {
        this.username = username;
        this.password = password;
        this.authlevel = authlevel;
        this.cinemas = cinemas;
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

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }
}
