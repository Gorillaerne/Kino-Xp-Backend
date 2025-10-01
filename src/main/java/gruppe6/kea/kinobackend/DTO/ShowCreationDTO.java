package gruppe6.kea.kinobackend.DTO;

import java.time.LocalDateTime;

public class ShowCreationDTO {

    private LocalDateTime showTime;

    private int theatre_id;

    private int movie_id;


    public ShowCreationDTO() {
    }

    public ShowCreationDTO(LocalDateTime showTime, int movie_id, int theatre_id) {
        this.showTime = showTime;
        this.movie_id = movie_id;
        this.theatre_id = theatre_id;
    }

    public LocalDateTime getShowTime() {
        return showTime;
    }

    public void setShowTime(LocalDateTime showTime) {
        this.showTime = showTime;
    }

    public int getTheatre_id() {
        return theatre_id;
    }

    public void setTheatre_id(int theatre_id) {
        this.theatre_id = theatre_id;
    }

    public int getMovie_id() {
        return movie_id;
    }

    public void setMovie_id(int movie_id) {
        this.movie_id = movie_id;
    }
}
