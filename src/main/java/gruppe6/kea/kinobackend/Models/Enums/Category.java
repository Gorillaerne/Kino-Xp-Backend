package gruppe6.kea.kinobackend.Models.Enums;

public enum Category {
    HORROR("Horror"),
    ROMANCE("Romance"),
    COMEDY("Comedy"),
    ACTION("Action"),
    DRAMA("Drama"),
    THRILLER("Thriller"),
    DOCUMENTARY("Documentary"),
    FANTASY("Fantasy");

    public final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
