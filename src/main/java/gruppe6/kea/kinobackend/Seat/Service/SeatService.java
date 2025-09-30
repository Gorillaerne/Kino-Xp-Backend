package gruppe6.kea.kinobackend.Seat.Service;


import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Seat.Repository.ISeatRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SeatService {

    private final ISeatRepository iSeatRepository;

    public SeatService(ISeatRepository iSeatRepository) {
        this.iSeatRepository = iSeatRepository;
    }

    public Seat getSeatById(int id) {
        return iSeatRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Seat not found with id:" + id));
    }
}
