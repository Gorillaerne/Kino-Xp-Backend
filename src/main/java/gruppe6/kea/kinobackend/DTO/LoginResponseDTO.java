package gruppe6.kea.kinobackend.DTO;

import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.Enums.Authlevel;

import java.util.List;

public class LoginResponseDTO {

    private String username;

    private Authlevel authlevel;

    private List<Cinema> cinemas;

    public LoginResponseDTO() {
    }

    public LoginResponseDTO(String username, Authlevel authlevel, List<Cinema> cinemas) {
        this.username = username;
        this.authlevel = authlevel;
        this.cinemas = cinemas;
    }

    public List<Cinema> getCinemas() {
        return cinemas;
    }

    public void setCinemas(List<Cinema> cinemas) {
        this.cinemas = cinemas;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Authlevel getAuthlevel() {
        return authlevel;
    }

    public void setAuthlevel(Authlevel authlevel) {
        this.authlevel = authlevel;
    }
}
