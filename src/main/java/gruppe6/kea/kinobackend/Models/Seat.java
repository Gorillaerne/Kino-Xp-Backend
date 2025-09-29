package gruppe6.kea.kinobackend.Models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Seat {

    @Id
    private int id;

   @Column()
    private int row;

    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;


    @ManyToMany
    @JoinTable(name ="booked_seats", joinColumns = @JoinColumn(name = "seat_id"),inverseJoinColumns = @JoinColumn(name="show_id"))
    private List<Show> showList;

    public Seat() {
    }

    public Seat(int row, int seatNumber, Theatre theatre, List<Show> showList) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.theatre = theatre;
        this.showList = showList;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRow() {
        return row;
    }

    public void setRow(int row) {
        this.row = row;
    }

    public int getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(int seatNumber) {
        this.seatNumber = seatNumber;
    }

    public Theatre getTheatre() {
        return theatre;
    }

    public void setTheatre(Theatre theatre) {
        this.theatre = theatre;
    }
}
