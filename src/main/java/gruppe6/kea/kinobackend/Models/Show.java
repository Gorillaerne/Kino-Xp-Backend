package gruppe6.kea.kinobackend.Models;


import jakarta.persistence.*;

import java.time.LocalTime;

@Entity
public class Show {

    @Id
    private int id;


    @Column(nullable = false)
    private LocalTime showTime;


    @ManyToOne
    @JoinColumn(name = "theatre_id")
    private Theatre theatre;

   @ManyToOne
   @JoinColumn(name = "movie_id")
    private Movie movie;





}
