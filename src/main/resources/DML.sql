INSERT INTO Movie (title, duration, description, posterImage, category, ageLimit) VALUES
                                                                                      ('The Matrix', '02:16:00', 'A computer hacker learns about the true nature of reality.', 'matrix_poster.jpg', 'SCI_FI', 16),
                                                                                      ('Inception', '02:28:00', 'A thief who steals corporate secrets through dream-sharing technology.', 'inception_poster.jpg', 'SCI_FI', 12),
                                                                                      ('The Lion King', '01:29:00', 'A young lion prince flees his kingdom only to learn the true meaning of responsibility.', 'lionking_poster.jpg', 'ANIMATION', 6),
                                                                                      ('Avengers: Endgame', '03:01:00', 'The Avengers assemble to undo Thanos\' actions and restore balance to the universe.', 'endgame_poster.jpg', 'ACTION', 12),
                                                                                      ('Parasite', '02:12:00', 'Greed and class discrimination threaten the newly formed symbiotic relationship between the wealthy Park family and the destitute Kim clan.', 'parasite_poster.jpg', 'DRAMA', 16);
INSERT INTO Cinema (name) VALUES
                              ('Cineworld'),
                              ('Grand Cinema'),
                              ('MoviePalace'),
                              ('FilmHouse');
INSERT INTO Theatre (name, seats, cinema_id) VALUES
                                                 ('Screen 1', 100, 1),
                                                 ('Screen 2', 80, 1),
                                                 ('VIP Hall', 50, 2),
                                                 ('IMAX', 200, 3),
                                                 ('Family Room', 60, 4);
INSERT INTO Theatre (name, seats, cinema_id) VALUES
                                                 ('Screen 1', 100, 1),
                                                 ('Screen 2', 80, 1),
                                                 ('VIP Hall', 50, 2),
                                                 ('IMAX', 200, 3),
                                                 ('Family Room', 60, 4);
INSERT INTO shows (showTime, theatre_id, movie_id) VALUES
                                                       ('2025-10-01 14:00:00', 1, 1),
                                                       ('2025-10-01 17:00:00', 1, 2),
                                                       ('2025-10-01 20:00:00', 1, 3),
                                                       ('2025-10-01 19:00:00', 2, 4),
                                                       ('2025-10-02 16:00:00', 3, 5),
                                                       ('2025-10-02 18:30:00', 4, 2),
                                                       ('2025-10-03 15:00:00', 5, 3);
INSERT INTO User (username, authlevel) VALUES
                                           ('alice', 'CUSTOMER'),
                                           ('bob', 'CUSTOMER'),
                                           ('carol', 'EMPLOYEE'),
                                           ('dave', 'ADMIN');
-- BookedSeats eksempel
INSERT INTO BookedSeats (seatNumber, show_id) VALUES
                                                  ('A1', 1),
                                                  ('A2', 1),
                                                  ('B1', 2),
                                                  ('B2', 3);

-- Reservation eksempel
INSERT INTO Reservation (user_id, show_id) VALUES
                                               (1, 1),
                                               (2, 2),
                                               (3, 3);
