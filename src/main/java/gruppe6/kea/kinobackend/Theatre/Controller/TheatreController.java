package gruppe6.kea.kinobackend.Theatre.Controller;

import gruppe6.kea.kinobackend.DTO.TheatreMangeDTO;
import gruppe6.kea.kinobackend.Models.Theatre;
import gruppe6.kea.kinobackend.Theatre.Service.TheatreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/theatres")
public class TheatreController {

    @Autowired
    TheatreService theatreService;

    @GetMapping
    public List<Theatre> getTheatres() {
        return theatreService.getTheatres();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Theatre> getTheatreById(@PathVariable int id) {
        return new ResponseEntity<>(theatreService.getTheatreById(id), HttpStatus.OK);
    }

    @GetMapping("/dto")
    public ResponseEntity<List<TheatreMangeDTO>> getTheatreManageDtoById() {
        return new ResponseEntity<>(theatreService.getTheatreManageDtos(), HttpStatus.OK);
    }

    @PostMapping("/dto")
    public ResponseEntity<Theatre> getTheatreManageDtoById(@RequestBody TheatreMangeDTO dto) {
        return new ResponseEntity<>(theatreService.createNewTheatreFromDto(dto), HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheatre(@PathVariable int id){
        theatreService.deleteTheatre(id);
        return new ResponseEntity<>(true,HttpStatus.OK);
    }



}
