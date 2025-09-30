package gruppe6.kea.kinobackend.Models;


import jakarta.persistence.*;

@Entity
public class BookedSeats {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name="show_id", nullable=false)
    private Show show;

    @ManyToOne
    @JoinColumn(name="seat_id", nullable=false)
    private Seat seat;

    @OneToOne
    private Ticket ticket;

    public BookedSeats() {
    }

    public BookedSeats(Ticket ticket, Seat seat, Show show) {
        this.ticket = ticket;
        this.seat = seat;
        this.show = show;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
