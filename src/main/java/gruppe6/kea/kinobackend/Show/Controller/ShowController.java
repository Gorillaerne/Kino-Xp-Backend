package gruppe6.kea.kinobackend.Show.Controller;

import gruppe6.kea.kinobackend.DTO.ShowCreationDTO;
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


    @GetMapping("")
    public ResponseEntity<List<Show>> getAllShow(){
        return new ResponseEntity<>(showService.findAll(),HttpStatus.OK) ;
    }


    @GetMapping("/{id}")
    public ResponseEntity<Show> getShowById(@PathVariable int id){
        Show show = showService.getShowById(id);
        return ResponseEntity.ok(show);
    }

    @PostMapping("")
    public ResponseEntity<Show> postNewShow(@RequestBody ShowCreationDTO show){
return new ResponseEntity<>(showService.postNewShow(show),HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteShowFromId(@PathVariable int id){
        return new ResponseEntity<>(showService.deleteShowFromId(id),HttpStatus.OK);
    }

}
