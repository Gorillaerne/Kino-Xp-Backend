package gruppe6.kea.kinobackend.Reservation.Repository;

import gruppe6.kea.kinobackend.Models.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IReservationRepository extends JpaRepository<Reservation, Integer> {
    Optional<Reservation> findById(int id);
}
