package gruppe6.kea.kinobackend.Movie.Controller;

import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import gruppe6.kea.kinobackend.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    IMovieRepository iMovieRepository;
    @Autowired
    MovieService movieService;
    
    @GetMapping("/movies")
    List<Movie> getMovies(){
        return iMovieRepository.findAll();
    }




}
