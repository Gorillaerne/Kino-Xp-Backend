package gruppe6.kea.kinobackend.Theatre.Service;

import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.DTO.TheatreMangeDTO;
import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.Seat;
import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Seat.Repository.ISeatRepository;
import gruppe6.kea.kinobackend.Theatre.Repository.TheatreRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class TheatreService {

    private final TheatreRepository theatreRepository;
    private final ICinemaRepository cinemaRepository;
    private final ISeatRepository seatRepository;

    public TheatreService(TheatreRepository theatreRepository, ICinemaRepository cinemaRepository, ISeatRepository seatRepository) {
        this.theatreRepository = theatreRepository;
        this.cinemaRepository = cinemaRepository;
        this.seatRepository = seatRepository;
    }

    public List<Theatre> getTheatres() {
    return theatreRepository.findAll();
    }

    public Theatre getTheatreById(int id) {
        return theatreRepository.findById(id).orElseThrow(()-> new EntityNotFoundException("Theater not found"));

    }


    public List<TheatreMangeDTO> getTheatreManageDtos() {
List<TheatreMangeDTO> listToReturn = new ArrayList<>();

        for(Theatre theatre : theatreRepository.findAll()){
            TheatreMangeDTO dtoToReturn = new TheatreMangeDTO();

            dtoToReturn.setId(theatre.getId());
            dtoToReturn.setName(theatre.getName());
            dtoToReturn.setCinemaName(theatre.getCinema().getName());
            System.out.println(theatre.getCinema().getName());

            Map<Integer,Integer> countMap = new HashMap<>();
            for (Seat seat : theatre.getSeatList()){
                if (!countMap.containsKey(seat.getRow())){
                    countMap.put(seat.getRow(),1);
                }else {
                    countMap.put(seat.getRow(),countMap.get(seat.getRow())+1);
                }

            }

            dtoToReturn.setRows(countMap.size());
            dtoToReturn.setSeatPrRow(countMap.values().stream().findFirst().orElse(0));
            listToReturn.add(dtoToReturn);
        }

        return listToReturn;
    }

    @Transactional
    public Theatre createNewTheatreFromDto(TheatreMangeDTO dto) {
        Theatre theatreToBeCreated = new Theatre();
        theatreToBeCreated.setName(dto.getName());

        Cinema foundCinema = cinemaRepository.findById(dto.getCinemaId())
                .orElseThrow(() -> new EntityNotFoundException("Cinema not found"));
        theatreToBeCreated.setCinema(foundCinema);

        theatreToBeCreated.setSeatList(new ArrayList<>()); // ensure list is initialized

        int numberOfRows = dto.getRows();
        int seatsPerRow = dto.getSeatPrRow();

        for (int row = 1; row <= numberOfRows; row++) {
            for (int seatNum = 1; seatNum <= seatsPerRow; seatNum++) {
                Seat seat = new Seat();
                seat.setRow(row);
                seat.setSeatNumber(seatNum);

                seat.setTheatre(theatreToBeCreated); // important

                theatreToBeCreated.getSeatList().add(seat); // important
            }
        }

        return theatreRepository.save(theatreToBeCreated); // cascades seats
    }

    public void deleteTheatre(int id) {

            Theatre foundTheatre = theatreRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Theatre not found"));
            theatreRepository.delete(foundTheatre);




    }
}
