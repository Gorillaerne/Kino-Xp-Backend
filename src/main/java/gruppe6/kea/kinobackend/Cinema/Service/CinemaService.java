package gruppe6.kea.kinobackend.Cinema.Service;

import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CinemaService {

    private final ICinemaRepository iCinemaRepository;
    private final IMovieRepository movieRepository;

    public CinemaService(ICinemaRepository iCinemaRepository, IMovieRepository movieRepository) {
        this.iCinemaRepository = iCinemaRepository;
        this.movieRepository = movieRepository;
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

    public List<Cinema> findAll() {
        return iCinemaRepository.findAll();
    }

    public Set<Cinema> getAllCinemasDisplaySpecificMovie(int movieId) {
        Movie foundMovie = movieRepository.findById(movieId).orElseThrow(() -> new EntityNotFoundException("Movie not Found"));

        Set<Cinema> cinemasDisplayingMovie = new HashSet<>();

        for (Show show : foundMovie.getShowList()) {
            if (show.getShowTime().isAfter(LocalDateTime.now())) {
                cinemasDisplayingMovie.add(show.getTheatre().getCinema());
            }
        }

        return cinemasDisplayingMovie;
    }
}
