package gruppe6.kea.kinobackend.Movie.Controller;

import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("")
    public List<Movie> getMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/post")
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }




}
