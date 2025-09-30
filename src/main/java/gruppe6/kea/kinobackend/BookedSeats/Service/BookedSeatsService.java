package gruppe6.kea.kinobackend.BookedSeats.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.Models.BookedSeats;
import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Show;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookedSeatsService {

    private final IBookedSeatsRepository iBookedSeatsRepository;

    @Autowired
    public BookedSeatsService(IBookedSeatsRepository iBookedSeatsRepository) {
        this.iBookedSeatsRepository = iBookedSeatsRepository;
    }

    public boolean isSeatBooked(Show show, Seat seat) {
        return iBookedSeatsRepository.existsByShowAndSeat(show, seat);
    }

    public List<BookedSeats> getBookedSeatsForShow(Show show) {
        return iBookedSeatsRepository.findByShow(show);
    }
}
