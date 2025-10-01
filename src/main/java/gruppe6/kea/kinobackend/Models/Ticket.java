package gruppe6.kea.kinobackend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;


    @ManyToOne
    @JoinColumn(name = "reservation_id")
    @JsonBackReference("reservation-ticket")
    private Reservation reservation;

    @OneToOne
    private BookedSeats bookedSeat;

    public Ticket() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BookedSeats getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(BookedSeats bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public Reservation getReservation() {
        return reservation;
    }

    public void setReservation(Reservation reservation) {
        this.reservation = reservation;
    }
}
