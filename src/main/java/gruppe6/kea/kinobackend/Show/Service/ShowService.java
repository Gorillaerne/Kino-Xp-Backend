package gruppe6.kea.kinobackend.Show.Service;

import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShowService {

    private final IShowRepository iShowRepository;

    public ShowService(IShowRepository iShowRepository) {
        this.iShowRepository = iShowRepository;
    }

    public Show getShowById(int id) {
        return iShowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seat not found with id:" + id));
    }

}
