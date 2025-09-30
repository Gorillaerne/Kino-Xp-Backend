package gruppe6.kea.kinobackend.Seat.Repository;

import gruppe6.kea.kinobackend.Models.Seat;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISeatRepository extends JpaRepository<Seat, Integer> {
}
