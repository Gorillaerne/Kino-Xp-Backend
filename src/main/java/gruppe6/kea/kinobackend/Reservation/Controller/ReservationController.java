package gruppe6.kea.kinobackend.Reservation.Controller;

import gruppe6.kea.kinobackend.DTO.ReservationDTO;
import gruppe6.kea.kinobackend.Models.Reservation;
import gruppe6.kea.kinobackend.Reservation.Service.ReservationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/reservations")
public class ReservationController {

    private final ReservationService reservationService;

    public ReservationController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationsById(@PathVariable int id) {
        Reservation reservation = reservationService.findById(id);
        return ResponseEntity.ok(reservation);
    }

    @PostMapping("")
    public ResponseEntity<Reservation> createReservations(@RequestBody ReservationDTO dto) {
        Reservation savedReservation = reservationService.createReservation(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedReservation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservationById(@PathVariable int id) {
        reservationService.deleteReservationById(id);
        return ResponseEntity.noContent().build();
    }

}
