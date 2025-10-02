package gruppe6.kea.kinobackend.DTO;

public class CategoryDTO {

    private String name;

    private String value;

    public CategoryDTO() {
    }

    public CategoryDTO(String name, String value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
