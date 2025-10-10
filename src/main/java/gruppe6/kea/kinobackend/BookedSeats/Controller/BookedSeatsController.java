package gruppe6.kea.kinobackend.BookedSeats.Controller;

import gruppe6.kea.kinobackend.BookedSeats.Service.BookedSeatsService;
import gruppe6.kea.kinobackend.DTO.BookedSeatDTO;
import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Seat.Service.SeatService;
import gruppe6.kea.kinobackend.Show.Service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/bookedseats")
public class BookedSeatsController {

    private final BookedSeatsService bookedSeatsService;
    private final ShowService showService;
    private final SeatService seatService;

    public BookedSeatsController(BookedSeatsService bookedSeatsService, ShowService showService, SeatService seatService) {
        this.bookedSeatsService = bookedSeatsService;
        this.showService = showService;
        this.seatService = seatService;
    }

    // Hent alle BookedSeats for et show
    @GetMapping("/{showId}")
    public ResponseEntity<List<BookedSeatDTO>> getBookedSeatsByShow(@PathVariable int showId) {
        // exception hvis ikke fundet
        List<BookedSeatDTO> bookedSeats = bookedSeatsService.getBookedSeatsForShow(showId);
        return ResponseEntity.ok(bookedSeats); // 200 ok
    }

    // Tjek om et sæde allerede er booket
    @GetMapping("/check")
    public ResponseEntity<Boolean> isSeatBooked(@RequestParam int showId, @RequestParam int seatId) {
        Show show = showService.getShowById(showId);
        Seat seat = seatService.getSeatById(seatId);
        boolean booked = bookedSeatsService.isSeatBooked(show, seat);
        return ResponseEntity.ok(booked);

    }
}
