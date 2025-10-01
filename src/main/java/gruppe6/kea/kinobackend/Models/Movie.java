package gruppe6.kea.kinobackend.Models;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import gruppe6.kea.kinobackend.Models.Enums.Category;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

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
    @Enumerated(EnumType.STRING)
    private Category category;

    @Column(length = 3)
    private int ageLimit;

    @OneToMany(mappedBy = "movie")
    @JsonIgnore
    private List<Show> showList;


    public Movie() {
    }

    public Movie(String title, LocalTime duration, String description, String posterImage, Category category, int ageLimit, List<Show> showList) {
        this.title = title;
        this.duration = duration;
        this.description = description;
        this.posterImage = posterImage;
        this.category = category;
        this.ageLimit = ageLimit;
        this.showList = showList;
    }


    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalTime getDuration() {
        return duration;
    }

    public void setDuration(LocalTime duration) {
        this.duration = duration;
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

    public List<Show> getShowList() {
        return showList;
    }

    public void setShowList(List<Show> showList) {
        this.showList = showList;
    }
}
