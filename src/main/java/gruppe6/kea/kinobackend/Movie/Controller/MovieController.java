package gruppe6.kea.kinobackend.Movie.Controller;

import gruppe6.kea.kinobackend.DTO.CategoryDTO;
import gruppe6.kea.kinobackend.Models.Enums.Category;
import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Movie.Service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/movies")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping("/categories")
    public ResponseEntity<List<CategoryDTO>> getCategories(){
        return new ResponseEntity<>(movieService.getCategories(),HttpStatus.OK);
    }


    @GetMapping("/active/{cinemaId}")
    public ResponseEntity<Set<Movie>> getAllActiveMoviesFromCinemaID(@PathVariable int cinemaId){
        return new ResponseEntity<>(movieService.getAllActiveMoviesFromCinemaID(cinemaId),HttpStatus.OK);
    }

    @GetMapping("/active")
    public ResponseEntity<Set<Movie>> getAllActiveMovies(){
        return new ResponseEntity<>(movieService.getAllActiveMovies(),HttpStatus.OK);
    }


    @GetMapping("")
    @ResponseStatus(HttpStatus.OK)
    public List<Movie> getMovies(){
        return movieService.getAllMovies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getMovieById(@PathVariable int id){
        return movieService.getMovieById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public Movie createMovie(@RequestBody Movie movie){
        return movieService.createMovie(movie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable int id){
        movieService.deleteMovie(id);
        return ResponseEntity.noContent().build();
    }




}
