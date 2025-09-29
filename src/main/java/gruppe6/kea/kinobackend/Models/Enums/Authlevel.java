package gruppe6.kea.kinobackend.Models.Enums;

public enum Authlevel {
    ADMIN(1),EMPLOYEE(2);


    public final int level;

    Authlevel(int i) {
        this.level = i;
    }



}
