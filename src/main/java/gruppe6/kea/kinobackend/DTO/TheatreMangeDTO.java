package gruppe6.kea.kinobackend.DTO;

public class TheatreMangeDTO {
    private int id;

    private int rows;

    private int seatPrRow;

    private String name;

 private String cinemaName;

 private int cinemaId;

    public TheatreMangeDTO() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getSeatPrRow() {
        return seatPrRow;
    }

    public int getCinemaId() {
        return cinemaId;
    }

    public void setCinemaId(int cinemaId) {
        this.cinemaId = cinemaId;
    }

    public void setSeatPrRow(int seatPrRow) {
        this.seatPrRow = seatPrRow;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCinemaName() {
        return cinemaName;
    }

    public void setCinemaName(String cinemaName) {
        this.cinemaName = cinemaName;
    }
}
