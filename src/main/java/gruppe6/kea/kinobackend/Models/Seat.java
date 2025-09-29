package gruppe6.kea.kinobackend.Models;

import jakarta.persistence.*;

@Entity
public class Seat {

    @Id
    private int id;

   @Column(length = 1)
    private char row;

    private int seatNumber;

    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

    public Seat(char row, int seatNumber, Theatre theatre) {
        this.row = row;
        this.seatNumber = seatNumber;
        this.theatre = theatre;
    }

    public Seat() {
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
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
