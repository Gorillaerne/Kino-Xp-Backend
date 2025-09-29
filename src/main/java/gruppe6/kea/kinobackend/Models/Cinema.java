package gruppe6.kea.kinobackend.Models;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import org.springframework.data.repository.cdi.Eager;

@Entity
public class Cinema {
    @Id
    private Long id;

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
