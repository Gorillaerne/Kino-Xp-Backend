package gruppe6.kea.kinobackend.Theatre.Controller;

import gruppe6.kea.kinobackend.Theatre.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    TheatreService theatreService;
}
