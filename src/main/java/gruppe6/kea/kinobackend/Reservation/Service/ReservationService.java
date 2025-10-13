package gruppe6.kea.kinobackend.Reservation.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.DTO.ReservationDTO;
import gruppe6.kea.kinobackend.DTO.TicketDTO;
import gruppe6.kea.kinobackend.Models.*;
import gruppe6.kea.kinobackend.Reservation.Repository.IReservationRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

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
    public Reservation createReservation(ReservationDTO dto) throws Exception {

        // Tjek om nogle af sæderne allerede er booket
        List<BookedSeats> bookedSeatsFromShow = iBookedSeatsRepository.findAllByShow(
                iShowRepository.findById(dto.getShowId())
                        .orElseThrow(() -> new EntityNotFoundException("Show not found"))
        );

        for (BookedSeats bookedSeats : bookedSeatsFromShow) {
            for (Integer seatId : dto.getSeatIds()) {
                if (bookedSeats.getSeat().getId() == seatId) {
                    throw new Exception("Nogle af sæderne er booket");
                }
            }
        }

        System.out.println("step 1 færdog");
        // 1. Opret Reservation entity
        Reservation reservation = new Reservation();
        reservation.setName(dto.getName());
        reservation.setEmail(dto.getEmail());
        reservation.setPhoneNumber(dto.getPhoneNumber());
        reservation.setTimeOfPurchase(LocalDateTime.now());

        System.out.println("step 2 færdog");
        // 2. Hent show fra DB
        Show show = iShowRepository.findById(dto.getShowId())
                .orElseThrow(() -> new EntityNotFoundException("Show Not Found!"));
        reservation.setShow(show);
        System.out.println("step 3 færdog");
        // 3. Opret Tickets og BookedSeats
        if (dto.getSeatIds() != null) {
            for (int seatId : dto.getSeatIds()) {
                Seat seat = show.getTheatre().getSeatList().stream()
                        .filter(s -> s.getId() == seatId)
                        .findFirst()
                        .orElseThrow(() -> new EntityNotFoundException("Seat not found"));

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
        System.out.println("step 4 færdog");
        // 4. Gem reservation
        Reservation savedReservation = iReservationRepository.save(reservation);
        System.out.println("step 5 færdog");
        // 5. Gem BookedSeats
        for (Ticket ticket : savedReservation.getTicketList()) {
            iBookedSeatsRepository.save(ticket.getBookedSeat());
        }


        System.out.println("Færdig");
        return savedReservation;
    }


    @Transactional
    public void deleteReservationById(int id) {
        Reservation reservation = iReservationRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

        iReservationRepository.delete(reservation);
    }



}

