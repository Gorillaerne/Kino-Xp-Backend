package gruppe6.kea.kinobackend.DTO;

import gruppe6.kea.kinobackend.Models.Seat;

import java.time.LocalDateTime;
import java.util.List;

public class TicketDTO {
    private String cinemaName;
    private String name;
    private String email;
    private String movieTitle;
    private LocalDateTime showTime;
    private String theatreName;
    private Seat seats;

    public TicketDTO() {
    }

    // Constructor
    public TicketDTO(String name, String email, String movieTitle, LocalDateTime showTime,
                                String theatreName, Seat seats) {
        this.name = name;
        this.email = email;
        this.movieTitle = movieTitle;
        this.showTime = showTime;
        this.theatreName = theatreName;
        this.seats = seats;
    }

    // Getters
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getMovieTitle() { return movieTitle; }
    public LocalDateTime getShowTime() { return showTime; }
    public String getTheatreName() { return theatreName; }
    public Seat getSeats() { return seats; }


    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMovieTitle(String movieTitle) {
        this.movieTitle = movieTitle;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public void setSeats(Seat seats) {
        this.seats = seats;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
