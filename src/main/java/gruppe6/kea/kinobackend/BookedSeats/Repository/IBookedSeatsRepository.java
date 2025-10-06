package gruppe6.kea.kinobackend.BookedSeats.Repository;

import gruppe6.kea.kinobackend.Models.BookedSeats;
import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Show;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IBookedSeatsRepository extends JpaRepository<BookedSeats, Integer> {

    boolean existsByShowAndSeat(Show show, Seat seat);

    List<BookedSeats> findAllByShow(Show show);

}
