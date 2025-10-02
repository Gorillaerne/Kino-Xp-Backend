package gruppe6.kea.kinobackend.Cinema.Controller;

import gruppe6.kea.kinobackend.Cinema.Service.CinemaService;
import gruppe6.kea.kinobackend.Models.Cinema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/cinema")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }

    @PostMapping("")
    public ResponseEntity<Cinema> createCinema(@RequestBody Cinema cinema) {
        Cinema newCinema = cinemaService.createCinema(cinema);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCinema);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> findCinemaById(@PathVariable int id) {
        Cinema cinema = cinemaService.findById(id);
        return ResponseEntity.ok(cinema);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinemaById(id);
        return ResponseEntity.noContent().build();
    }


}
