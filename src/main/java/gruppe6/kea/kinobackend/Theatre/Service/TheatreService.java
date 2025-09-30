package gruppe6.kea.kinobackend.Theatre.Service;

import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Theatre.Repository.TheatreRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;

    public TheatreService(TheatreRepository theatreRepository) {
        this.theatreRepository = theatreRepository;
    }

    public List<Theatre> getTheatres() {
    return theatreRepository.findAll();
    }


}
