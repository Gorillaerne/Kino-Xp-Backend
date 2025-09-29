package gruppe6.kea.kinobackend.Models.Enums;

public enum Category {
    HORROR("Horror"),ROMANCE("Romance"),COMEDY("Comedy"),ACTION("Action")
    ;

    public final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
