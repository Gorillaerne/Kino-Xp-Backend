package gruppe6.kea.kinobackend.Show.Controller;

import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Show.Service.ShowService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public ResponseEntity<?> postNewShow(@RequestBody Show show){
//
//
//
//    }

}
