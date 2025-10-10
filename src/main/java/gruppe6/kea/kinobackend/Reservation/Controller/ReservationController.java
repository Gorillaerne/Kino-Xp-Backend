package gruppe6.kea.kinobackend.Reservation.Controller;

import gruppe6.kea.kinobackend.DTO.ReservationDTO;
import gruppe6.kea.kinobackend.Models.Reservation;
import gruppe6.kea.kinobackend.Reservation.Service.EmailService;
import gruppe6.kea.kinobackend.Reservation.Service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reservations")
public class ReservationController {

    private final EmailService emailService;
    private final ReservationService reservationService;

    public ReservationController(EmailService emailService, ReservationService reservationService) {
        this.emailService = emailService;
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable int id) {
        Reservation reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("")
    public ResponseEntity<Reservation> createReservations(@RequestBody ReservationDTO dto) {
        try {
            Reservation savedReservation = reservationService.createReservation(dto);
            emailService.sendTicksThroughMail(savedReservation);
            return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
        } catch (Exception e) {
            throw new RuntimeException("DET VIRKER IK");
        }

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable int id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

}
