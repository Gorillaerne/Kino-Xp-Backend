package gruppe6.kea.kinobackend.Movie.Service;


import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.DTO.CategoryDTO;
import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.Enums.Category;
import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
public class MovieService {


    private final ICinemaRepository cinemaRepository;
    private final IMovieRepository iMovieRepository;

    public MovieService(ICinemaRepository cinemaRepository, IMovieRepository iMovieRepository){
        this.cinemaRepository = cinemaRepository;
        this.iMovieRepository = iMovieRepository;
    }

    public List<Movie> getAllMovies(){
        return iMovieRepository.findAll();
    }

    public Optional<Movie> getMovieById(int id){
        return iMovieRepository.findById(id);
    }

    public Movie createMovie(Movie movie){
        return iMovieRepository.save(movie);
    }

    public void deleteMovie(int id){
        iMovieRepository.deleteById(id);
    }

    public List<CategoryDTO> getCategories() {
        ArrayList<CategoryDTO> categories = new ArrayList<>();

        for (Category cat : Category.values()){
            categories.add(new CategoryDTO(cat.displayName,cat.toString()));
        }
        return categories;
    }

    public Set<Movie> getAllActiveMoviesFromCinemaID(int cinemaId) {
        Cinema cinema = cinemaRepository.findById(cinemaId).orElseThrow(()->  new EntityNotFoundException("Cinema not found with id: " + cinemaId));

        Set<Movie> movieSet = new HashSet<>();

        List<Theatre> theatreList = cinema.getTheatreList();

        for (Theatre theatre : theatreList){
            for (Show show : theatre.getShowList()){
                if (!show.getShowTime().isBefore(LocalDateTime.now())){
                    movieSet.add(show.getMovie());
                }
            }

        }




        return movieSet;
    }
}

