package gruppe6.kea.kinobackend.Models;

import gruppe6.kea.kinobackend.Models.Enums.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Entity
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String title;

    private LocalTime duration;

    private String description;

    @Column(length = 999999999)
    private String posterImage;

    private Category category;

    @Column(length = 3)
    private int ageLimit;

    public Movie() {
    }

    public Movie(String title, int ageLimit, Category category, String posterImage, String description, LocalTime duration) {
        this.title = title;
        this.ageLimit = ageLimit;
        this.category = category;
        this.posterImage = posterImage;
        this.description = description;
        this.duration = duration;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
