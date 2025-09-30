package gruppe6.kea.kinobackend.Movie.Service;


import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieService {

    public IMovieRepository iMovieRepository;

    public MovieService(IMovieRepository iMovieRepository){
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

}

