package gruppe6.kea.kinobackend.Reservation.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.DTO.ReservationDTO;
import gruppe6.kea.kinobackend.DTO.TicketDTO;
import gruppe6.kea.kinobackend.Models.*;
import gruppe6.kea.kinobackend.Reservation.Repository.IReservationRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.hibernate.proxy.EntityNotFoundDelegate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.File;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.stream.Collectors;

@Service
public class ReservationService {

    private final IReservationRepository iReservationRepository;
    private final IBookedSeatsRepository iBookedSeatsRepository;
    private final IShowRepository iShowRepository;
    private final EmailService emailService;

    public ReservationService(IReservationRepository iReservationRepository, IBookedSeatsRepository iBookedSeatsRepository, IShowRepository iShowRepository, EmailService emailService) {
        this.iReservationRepository = iReservationRepository;
        this.iBookedSeatsRepository = iBookedSeatsRepository;
        this.iShowRepository = iShowRepository;
        this.emailService = emailService;
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

        // 1. Opret Reservation entity
        Reservation reservation = new Reservation();
        reservation.setName(dto.getName());
        reservation.setEmail(dto.getEmail());
        reservation.setPhoneNumber(dto.getPhoneNumber());
        reservation.setTimeOfPurchase(LocalDateTime.now());

        // 2. Hent show fra DB
        Show show = iShowRepository.findById(dto.getShowId())
                .orElseThrow(() -> new EntityNotFoundException("Show Not Found!"));
        reservation.setShow(show);

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

        // 4. Gem reservation
        Reservation savedReservation = iReservationRepository.save(reservation);

        // 5. Gem BookedSeats
        for (Ticket ticket : savedReservation.getTicketList()) {
            iBookedSeatsRepository.save(ticket.getBookedSeat());
        }


        //EMAIL FIS
        List<byte[]> pdfBytesList = new ArrayList<>();
        // 6. Generer TicketDTO med Seat-objekter
        for (Ticket ticket : savedReservation.getTicketList()) {
            // Generer TicketDTO med ét sæde
            TicketDTO ticketDTO = new TicketDTO();
            ticketDTO.setName(savedReservation.getName());
            ticketDTO.setEmail(savedReservation.getEmail());
            ticketDTO.setMovieTitle(savedReservation.getShow().getMovie().getTitle());
            ticketDTO.setShowTime(savedReservation.getShow().getShowTime());
            ticketDTO.setTheatreName(savedReservation.getShow().getTheatre().getName());
            ticketDTO.setCinemaName(savedReservation.getShow().getTheatre().getCinema().getName());
            ticketDTO.setSeats(ticket.getBookedSeat().getSeat());


            // 7. Generer PDF som byte-array og send mail
            byte[] pdfBytes = emailService.generateTicketPdfBytes(ticketDTO);
pdfBytesList.add(pdfBytes);




        }
        emailService.sendTicketMail(savedReservation.getEmail(), pdfBytesList);
        return savedReservation;
    }


        @Transactional
        public void deleteReservationById (int id){
            Reservation reservation = iReservationRepository.findById(id)
                    .orElseThrow(() -> new EntityNotFoundException("Reservation not found"));

            iReservationRepository.delete(reservation);
        }

    }

