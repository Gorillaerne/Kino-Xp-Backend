package gruppe6.kea.kinobackend.DTO;

import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Models.Ticket;

import java.util.List;

public class BookedSeatDTO {
    int id;
    Seat seat;
    Ticket ticket;
    Show show;

    public BookedSeatDTO(int id, Seat seat, Ticket ticket, Show show) {
        this.id = id;
        this.seat = seat;
        this.ticket = ticket;
        this.show = show;
    }

    public BookedSeatDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public Ticket getTicket() {
        return ticket;
    }

    public void setTicket(Ticket ticket) {
        this.ticket = ticket;
    }

    public Seat getSeat() {
        return seat;
    }

    public void setSeat(Seat seat) {
        this.seat = seat;
    }
}
