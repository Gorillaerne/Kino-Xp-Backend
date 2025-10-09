package gruppe6.kea.kinobackend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
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
  @JsonBackReference("cinema-user")
   private List<User> userList;

 @OneToMany(mappedBy = "cinema", cascade = CascadeType.ALL, orphanRemoval = true)
 @JsonManagedReference("cinema-theatre")
  private List<Theatre> theatreList;


    public Cinema() {
    }

    public Cinema(String name, List<User> userList) {
        this.name = name;
        this.userList = userList;
    }

    public Cinema(String name) {
        this.name = name;
    }

    public Cinema(String name, List<User> userList, List<Theatre> theatreList) {
        this.name = name;
        this.userList = userList;
        this.theatreList = theatreList;
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

    public List<Theatre> getTheatreList() {
        return theatreList;
    }

    public void setTheatreList(List<Theatre> theatreList) {
        this.theatreList = theatreList;
    }
}
