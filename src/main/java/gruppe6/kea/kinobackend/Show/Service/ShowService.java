package gruppe6.kea.kinobackend.Show.Service;

import gruppe6.kea.kinobackend.DTO.ShowCreationDTO;
import gruppe6.kea.kinobackend.Models.Show;
import gruppe6.kea.kinobackend.Movie.Repository.IMovieRepository;
import gruppe6.kea.kinobackend.Show.Repository.IShowRepository;
import gruppe6.kea.kinobackend.Theatre.Repository.TheatreRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ShowService {
private final IMovieRepository iMovieRepository;
    private final IShowRepository iShowRepository;
    private final TheatreRepository theatreRepository;

    public ShowService(IMovieRepository iMovieRepository, IShowRepository iShowRepository, TheatreRepository theatreRepository) {
        this.iMovieRepository = iMovieRepository;
        this.iShowRepository = iShowRepository;
        this.theatreRepository = theatreRepository;
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
}
