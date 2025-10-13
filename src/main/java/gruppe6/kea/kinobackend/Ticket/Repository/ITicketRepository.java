package gruppe6.kea.kinobackend.Ticket.Repository;

import gruppe6.kea.kinobackend.Models.Ticket;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ITicketRepository extends JpaRepository<Ticket, Integer> {
}
