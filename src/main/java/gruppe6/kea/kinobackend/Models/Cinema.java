package gruppe6.kea.kinobackend.Models;

import gruppe6.kea.kinobackend.Models.Enums.Authlevel;
import jakarta.persistence.*;

import java.util.List;

@Entity
public class Cinema {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

   @Column(unique = true)
    private String name;

  @ManyToMany
  @JoinTable(name = "cinema_user",joinColumns = @JoinColumn(name = "cinema_id"),inverseJoinColumns = @JoinColumn(name = "user_id"))
   private List<User> userList;


    public Cinema() {
    }

    public Cinema(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }

    public Cinema(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<User> getUserList() {
        return userList;
    }

    public void setUserList(List<User> userList) {
        this.userList = userList;
    }
}
