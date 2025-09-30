package gruppe6.kea.kinobackend.Movie.Repository;

import gruppe6.kea.kinobackend.Models.Movie;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IMovieRepository extends JpaRepository<Movie, Integer> {
}
