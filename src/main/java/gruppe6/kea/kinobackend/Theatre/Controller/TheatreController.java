package gruppe6.kea.kinobackend.Theatre.Controller;

import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Theatre.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @GetMapping
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }

    //Virker ikke endnu TODO fix
    @GetMapping("{id}")
    public List<Theatre> getTheatreById(@PathVariable int id) {
        return theatreService.getTheatreById(id);
    }
}
