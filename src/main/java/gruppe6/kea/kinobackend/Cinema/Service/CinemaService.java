package gruppe6.kea.kinobackend.Cinema.Service;

import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.Models.Cinema;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CinemaService {

    private final ICinemaRepository iCinemaRepository;

    public CinemaService(ICinemaRepository iCinemaRepository) {
        this.iCinemaRepository = iCinemaRepository;
    }

    public Cinema createCinema(Cinema cinema) {
        return iCinemaRepository.save(cinema);
    }

    public Cinema findById(int id) {
        return iCinemaRepository.findById(id).
                orElseThrow(() -> new EntityNotFoundException("Cinema with ID: " + id + " not found"));
    }

    public void deleteCinemaById(int id) {
        Cinema cinema = iCinemaRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Cinema not found"));

        iCinemaRepository.delete(cinema);
    }
}
