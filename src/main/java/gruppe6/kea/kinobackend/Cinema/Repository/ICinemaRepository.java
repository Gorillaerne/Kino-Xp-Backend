package gruppe6.kea.kinobackend.Cinema.Repository;

import gruppe6.kea.kinobackend.Models.Cinema;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ICinemaRepository extends JpaRepository<Cinema, Integer> {
}
