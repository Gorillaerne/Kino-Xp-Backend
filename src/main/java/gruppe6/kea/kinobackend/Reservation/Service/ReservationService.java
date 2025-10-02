package gruppe6.kea.kinobackend.Reservation.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.Models.*;
import gruppe6.kea.kinobackend.Reservation.Repository.IReservationRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
public class ReservationService {

    private final IReservationRepository iReservationRepository;
    private final IBookedSeatsRepository iBookedSeatsRepository;
    private final IShowRepository iShowRepository;

    public ReservationService(IReservationRepository iReservationRepository, IBookedSeatsRepository iBookedSeatsRepository, IShowRepository iShowRepository) {
        this.iReservationRepository = iReservationRepository;
        this.iBookedSeatsRepository = iBookedSeatsRepository;
        this.iShowRepository = iShowRepository;
    }

    public Reservation findById(int id) {
        return iReservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found with id: " + id));
    }

    @Transactional
    public Reservation createReservation(Reservation reservation) {

        // 1 Hent show fra DB
        Show show = iShowRepository.findById(reservation.getShow().getId())
                .orElseThrow(() -> new EntityNotFoundException("Show Not Found!"));
        reservation.setShow(show);

        // 2 Tjek alle tickets og opret midlertidige BookedSeats
        if (reservation.getTicketList() != null) {
            for (Ticket ticket : reservation.getTicketList()) {

                Seat seat = ticket.getBookedSeat().getSeat(); // hent Seat fra BookedSeats

                if (iBookedSeatsRepository.existsByShowAndSeat(show, seat)) {
                    throw new IllegalArgumentException("Seat nr: " + seat.getSeatNumber() + " is already booked for this show!");
                }

                // Sæt show på bookedSeat og ticket på bookedSeat
                BookedSeats bookedSeats = ticket.getBookedSeat();
                bookedSeats.setShow(show);
                bookedSeats.setTicket(ticket);

                // Sæt bookedSeat på ticket
                ticket.setBookedSeat(bookedSeats);

                // Sæt reservation på ticket
                ticket.setReservation(reservation);
            }
        }

        // 3 Gem reservation
        Reservation savedReservation = iReservationRepository.save(reservation);

        // 4 Gem BookedSeats
        for (Ticket ticket : reservation.getTicketList()) {
            iBookedSeatsRepository.save(ticket.getBookedSeat());
        }

        return savedReservation;
    }

    @Transactional
    public void deleteReservationById(int id) {
        Reservation reservation = iReservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        iReservationRepository.delete(reservation);
    }


}
