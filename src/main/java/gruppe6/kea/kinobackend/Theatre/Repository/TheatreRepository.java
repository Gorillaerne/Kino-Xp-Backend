package gruppe6.kea.kinobackend.Theatre.Repository;

import gruppe6.kea.kinobackend.Models.Theatre;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TheatreRepository extends JpaRepository<Theatre, Integer> {

}
