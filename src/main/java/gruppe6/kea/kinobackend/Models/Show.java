package gruppe6.kea.kinobackend.Models;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "shows")
public class Show {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @Column(nullable = false)
    private LocalDateTime showTime;


    @ManyToOne
    @JoinColumn(name = "theatre_id")
    @JsonBackReference("theatre-show")
    private Theatre theatre;

   @ManyToOne
   @JoinColumn(name = "movie_id")
   @JsonBackReference("show-movie")
    private Movie movie;

   @OneToMany(mappedBy = "show")
   @JsonManagedReference("show-bookedseats")
    private List<BookedSeats> bookedSeats;

   @OneToMany(mappedBy = "show")
   @JsonManagedReference("show-reservation")
    private List<Reservation> reservations;

    public Show() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(List<Reservation> reservations) {
        this.reservations = reservations;
    }

    public List<BookedSeats> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<BookedSeats> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }
}
