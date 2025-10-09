package gruppe6.kea.kinobackend.Movie.Service;

import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.Models.Enums.Category;
import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationContext;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MovieServiceTest {

    // Inject a mock ApplicationContext
    @Mock
    private ApplicationContext context;

    @Mock
    private IMovieRepository iMovieRepository;

    @Mock
    private ICinemaRepository iCinemaRepository;

    @Mock
    private IShowRepository iShowRepository;

    @InjectMocks
    private MovieService movieService;
    private List<Show> shows = new ArrayList<>();
    private Movie movie = new Movie(
            "test",
            LocalTime.of(2, 28), // 2 timer og 28 minutter
            "test desription",
            "https://example.com/inception.jpg",
            Category.ACTION,
            13,
            shows
    );
    @Test
    void getAllMovies()  {

        List<Movie> movieList = Arrays.asList(movie);


        when(iMovieRepository.findAll()).thenReturn(movieList);

        // Act
        List<Movie> result = movieService.getAllMovies(); // eller din controller/service metode

        // Assert
        assertEquals(1, result.size());
        assertEquals("test", result.get(0).getTitle());
        assertEquals(Category.ACTION, result.get(0).getCategory());
    }


    @Test
void deleteMovie () {
        movieService.deleteMovie(1);

        verify(iMovieRepository).deleteById(1);
    }

    @Test
    void createMovie(){
        movieService.createMovie(movie);


        verify(iMovieRepository).save(movie);
    }
}