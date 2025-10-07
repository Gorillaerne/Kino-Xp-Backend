package gruppe6.kea.kinobackend.BookedSeats.Service;

import gruppe6.kea.kinobackend.BookedSeats.Repository.IBookedSeatsRepository;
import gruppe6.kea.kinobackend.DTO.BookedSeatDTO;
import gruppe6.kea.kinobackend.Models.BookedSeats;
import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class BookedSeatsService {

    private final IBookedSeatsRepository iBookedSeatsRepository;
    private final IShowRepository showRepository;

    @Autowired
    public BookedSeatsService(IBookedSeatsRepository iBookedSeatsRepository, IShowRepository showRepository) {
        this.iBookedSeatsRepository = iBookedSeatsRepository;
        this.showRepository = showRepository;
    }

    public boolean isSeatBooked(Show show, Seat seat) {
        return iBookedSeatsRepository.existsByShowAndSeat(show, seat);
    }

    public List<BookedSeatDTO> getBookedSeatsForShow(int showId) {
       Show show = showRepository.findById(showId).orElseThrow(() -> new EntityNotFoundException("Kunne ikke finde show"));
       List<BookedSeatDTO> listToReturn = new ArrayList<>();
       for (BookedSeats bookedSeat : iBookedSeatsRepository.findAllByShow(show)){
           BookedSeatDTO bookedSeatDTO = new BookedSeatDTO();

           bookedSeatDTO.setId(bookedSeat.getId());
           bookedSeatDTO.setSeat(bookedSeat.getSeat());
           bookedSeatDTO.setShow(bookedSeat.getShow());
           bookedSeatDTO.setTicket(bookedSeat.getTicket());
           listToReturn.add(bookedSeatDTO);
       }
        return listToReturn;
    }
}
