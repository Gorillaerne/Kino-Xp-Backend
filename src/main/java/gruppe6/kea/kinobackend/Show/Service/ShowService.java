package gruppe6.kea.kinobackend.Show.Service;

import gruppe6.kea.kinobackend.Cinema.Repository.ICinemaRepository;
import gruppe6.kea.kinobackend.DTO.ShowCreationDTO;
import gruppe6.kea.kinobackend.Models.Cinema;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import gruppe6.kea.kinobackend.Theatre.Repository.TheatreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class ShowService {
private final IMovieRepository iMovieRepository;
    private final IShowRepository iShowRepository;
    private final TheatreRepository theatreRepository;
    private final ICinemaRepository cinemaRepository;

    public ShowService(IMovieRepository iMovieRepository, IShowRepository iShowRepository, TheatreRepository theatreRepository, ICinemaRepository cinemaRepository) {
        this.iMovieRepository = iMovieRepository;
        this.iShowRepository = iShowRepository;
        this.theatreRepository = theatreRepository;
        this.cinemaRepository = cinemaRepository;
    }

    public Show getShowById(int id) {
        return iShowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Show not found with id:" + id));
    }

    public Show postNewShow(ShowCreationDTO showDto) {
        Show show = new Show();
        show.setShowTime(showDto.getShowTime());
        show.setMovie(iMovieRepository.findById(showDto.getMovie_id()).orElseThrow(()-> new EntityNotFoundException("Movie not found")));
        show.setTheatre(theatreRepository.findById(showDto.getTheatre_id()).orElseThrow(()-> new EntityNotFoundException("Theatre not found")));
        return iShowRepository.save(show);
    }

    public List<Show> findAll() {
        return iShowRepository.findAll();
    }

    public boolean deleteShowFromId(int id) {
        Show foundShow = iShowRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Show not found"));

        iShowRepository.delete(foundShow);
        if (iShowRepository.findById(foundShow.getId()).isPresent()){
            return false;
        }else {
            return true;
        }

    }

    public Set<Show> getAllShowsInCinemaOnSpecificDay(int cinemaId, int movieId, LocalDate date) {
        Set<Show> showsToReturn = new HashSet<>();
        Cinema foundCinema = cinemaRepository.findById(cinemaId).orElseThrow(() -> new EntityNotFoundException("Cinema not found"));
        List<Show> showList = iShowRepository.findAll();
        for (Show show : showList){
            if (show.getShowTime().toLocalDate().isEqual(date) && show.getMovie().getId() == movieId){
              for (Theatre theatre : foundCinema.getTheatreList()){
                  if (theatre.getId() == show.getTheatre().getId()){
                      showsToReturn.add(show);
                  }
              }

            }
        }
return showsToReturn;
    }
}
