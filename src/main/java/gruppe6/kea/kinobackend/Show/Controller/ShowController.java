package gruppe6.kea.kinobackend.Show.Controller;

import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Show.Service.ShowService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/shows")
public class ShowController {

    private final ShowService showService;

    public ShowController(ShowService showService) {
        this.showService = showService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable int id){
        Show show = showService.getShowById(id);
        return ResponseEntity.ok(show);
    }

    @PostMapping("/add")
    @ResponseStatus(HttpStatus.CREATED)
    public Show addShow(@RequestBody Show show) {
        System.out.println(show);
        return showService.addShow(show);
    }

    @GetMapping("/allshows")
    public List<Show> getAllShows() {
        System.out.println("Fetching all shows");
        return showService.getAllShows();
    }
}
