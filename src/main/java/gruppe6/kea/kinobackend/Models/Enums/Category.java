package gruppe6.kea.kinobackend.Models.Enums;

public enum Category {
    // Core Genres
    ACTION("Action"),
    ADVENTURE("Adventure"),
    ANIMATION("Animation"),
    COMEDY("Comedy"),
    CRIME("Crime"),
    DRAMA("Drama"),
    DOCUMENTARY("Documentary"),
    FAMILY("Family"),
    FANTASY("Fantasy"),
    HISTORY("History"),
    HORROR("Horror"),
    MUSICAL("Musical"),
    MYSTERY("Mystery"),
    ROMANCE("Romance"),
    SCIFI("Sci-Fi"),
    THRILLER("Thriller"),
    WAR("War"),
    WESTERN("Western"),

    // Subgenres / Hybrids
    BIOGRAPHY("Biography"),
    SPORT("Sport"),
    MUSIC("Music"),
    SUPERHERO("Superhero"),
    EXPERIMENTAL("Experimental"),
    SHORT("Short"),
    ANIME("Anime"),
    CULT("Cult"),
    NOIR("Film-Noir"),
    SATIRE("Satire"),
    DISASTER("Disaster"),
    MARTIAL_ARTS("Martial Arts"),
    PERIOD("Period Piece"),
    SLICE_OF_LIFE("Slice of Life"),

    // Modern Streaming Categories
    TRUE_CRIME("True Crime"),
    REALITY("Reality"),
    TALK_SHOW("Talk Show"),
    GAME_SHOW("Game Show"),
    NEWS("News"),
    TRAVEL("Travel"),
    FOOD("Food"),
    LIFESTYLE("Lifestyle");

    public final String displayName;

    Category(String displayName) {
        this.displayName = displayName;
    }
}
