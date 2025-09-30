package gruppe6.kea.kinobackend.DTO;

import gruppe6.kea.kinobackend.Models.Enums.Category;
import gruppe6.kea.kinobackend.Models.Movie;
import gruppe6.kea.kinobackend.Models.Theatre;

import java.time.LocalTime;

public class MovieTheatreDTO {

    // Movie fields
    private int movieId;
    private String title;
    private LocalTime duration;
    private String description;
    private String posterImage;
    private Category category;
    private int ageLimit;

    // Theatre fields
    private int theatreId;
    private String theatreName;
    private int cinemaId; // reference til cinema uden at hente hele objektet

    public MovieTheatreDTO() {
    }

    public MovieTheatreDTO(int movieId, String title, LocalTime duration, String description, String posterImage,
                           Category category, int ageLimit, int theatreId, String theatreName, int cinemaId) {
        this.movieId = movieId;
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.posterImage = posterImage;
        this.category = category;
        this.ageLimit = ageLimit;
        this.theatreId = theatreId;
        this.theatreName = theatreName;
        this.cinemaId = cinemaId;
    }

    // ✅ Mapper fra entities til DTO
    public static MovieTheatreDTO fromEntity(Movie movie, Theatre theatre) {
        if (movie == null || theatre == null) {
            return null; // sikkerhed mod null
        }

        return new MovieTheatreDTO(
                movie.getId(),
                movie.getTitle(),
                movie.getDuration(),
                movie.getDescription(),
                movie.getPosterImage(),
                movie.getCategory(),
                movie.getAgeLimit(),
                theatre.getId(),
                theatre.getName(),
                theatre.getCinema() != null ? theatre.getCinema().getId() : 0
        );
    }

    // Getters og setters
    public int getMovieId() {
        return movieId;
    }

    public void setMovieId(int movieId) {
        this.movieId = movieId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public int getAgeLimit() {
        return ageLimit;
    }

    public void setAgeLimit(int ageLimit) {
        this.ageLimit = ageLimit;
    }

    public int getTheatreId() {
        return theatreId;
    }

    public void setTheatreId(int theatreId) {
        this.theatreId = theatreId;
    }

    public String getTheatreName() {
        return theatreName;
    }

    public void setTheatreName(String theatreName) {
        this.theatreName = theatreName;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }
}
