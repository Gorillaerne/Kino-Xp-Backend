package gruppe6.kea.kinobackend.Reservation.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.DTO.ReservationDTO;
import gruppe6.kea.kinobackend.Models.*;
import gruppe6.kea.kinobackend.Reservation.Repository.IReservationRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
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
    public Reservation createReservation(ReservationDTO dto) {

        // 1 Opretter Reservation entity

        Reservation reservation = new Reservation();
        reservation.setName(dto.getName());
        reservation.setEmail(dto.getEmail());
        reservation.setPhoneNumber(dto.getPhoneNumber());
        reservation.setTimeOfPurchase(LocalDateTime.now());

        // 2 Hent show fra DB
        Show show = iShowRepository.findById(dto.getShowId())
                .orElseThrow(() -> new EntityNotFoundException("Show Not Found!"));
        reservation.setShow(show);

        // 3 Opretter Tickets og Bookedseats
        if (dto.getSeatIds() != null) {
            for (int seatId : dto.getSeatIds()) {
                Seat seat = new Seat();
                seat.setId(seatId);

                BookedSeats bookedSeats = new BookedSeats();
                bookedSeats.setSeat(seat);
                bookedSeats.setShow(show);

                Ticket ticket = new Ticket();
                ticket.setBookedSeat(bookedSeats);
                ticket.setReservation(reservation);

                bookedSeats.setTicket(ticket);

                if (reservation.getTicketList() == null) {
                    reservation.setTicketList(new ArrayList<>());
                }
                reservation.getTicketList().add(ticket);
            }
        }

        // 4 Gem reservation
        Reservation savedReservation = iReservationRepository.save(reservation);

        // 5 Gem BookedSeats
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
