package gruppe6.kea.kinobackend.Seat.Controller;

import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Seat.Service.SeatService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/seats")
public class SeatController {

    private final SeatService seatService;

    public SeatController(SeatService seatService) {
        this.seatService = seatService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Seat> getSeatById(@PathVariable int id) {
        Seat seat = seatService.getSeatById(id);
        return ResponseEntity.ok(seat);
    }
}
