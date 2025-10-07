package gruppe6.kea.kinobackend.DTO;

import gruppe6.kea.kinobackend.Models.Enums.Authlevel;

import java.util.List;

public class CreateUserDTO {
    private String username;
    private String password;
    private Authlevel authlevel;
    private List<Integer> cinemaIds;

    public CreateUserDTO(String username, String password, Authlevel authlevel, List<Integer> cinemaIds) {
        this.username = username;
        this.password = password;
        this.authlevel = authlevel;
        this.cinemaIds = cinemaIds;
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

    public List<Integer> getCinemaIds() {
        return cinemaIds;
    }

    public void setCinemaIds(List<Integer> cinemaIds) {
        this.cinemaIds = cinemaIds;
    }
}
