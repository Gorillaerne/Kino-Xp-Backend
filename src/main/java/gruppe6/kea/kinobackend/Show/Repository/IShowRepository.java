package gruppe6.kea.kinobackend.Show.Repository;

import gruppe6.kea.kinobackend.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface IShowRepository extends JpaRepository<Show, Integer> {
    Optional<Show> findById(int id);
}
