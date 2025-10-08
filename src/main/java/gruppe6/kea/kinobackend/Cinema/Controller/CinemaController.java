package gruppe6.kea.kinobackend.Cinema.Controller;

import gruppe6.kea.kinobackend.Cinema.Service.CinemaService;
import gruppe6.kea.kinobackend.DTO.CinemaRequestDTO;
import gruppe6.kea.kinobackend.Models.Cinema;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/cinemas")
public class CinemaController {

    private final CinemaService cinemaService;

    public CinemaController(CinemaService cinemaService) {
        this.cinemaService = cinemaService;
    }


    @GetMapping("")
    public ResponseEntity<List<Cinema>> getAllCinemas(){
        return new ResponseEntity<>(cinemaService.findAll(),HttpStatus.OK);
    }


    @GetMapping("/displaying/{movieId}")
    public ResponseEntity<Set<Cinema>> getAllCinemasDisplaySpecificMovie(@PathVariable int movieId){
        return new ResponseEntity<>(cinemaService.getAllCinemasDisplaySpecificMovie(movieId),HttpStatus.OK);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cinema> findCinemaById(@PathVariable int id) {
        Cinema cinema = cinemaService.findById(id);
        return ResponseEntity.ok(cinema);
    }

    @PostMapping("")
    public ResponseEntity<Cinema> createCinema(@RequestBody Cinema cinema) {
        Cinema newCinema = cinemaService.createCinema(cinema);
        return ResponseEntity.status(HttpStatus.CREATED).body(newCinema);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCinema(@PathVariable int id) {
        cinemaService.deleteCinemaById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/name")
    public ResponseEntity<Cinema> createCinemaFromName(@RequestBody CinemaRequestDTO request) {
        Cinema newCinema = new Cinema(request.getName());
        Cinema saved = cinemaService.createCinema(newCinema);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }




}
