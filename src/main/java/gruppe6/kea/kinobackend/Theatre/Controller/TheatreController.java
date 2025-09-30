package gruppe6.kea.kinobackend.Theatre.Controller;

import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Theatre.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @GetMapping
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }
}
