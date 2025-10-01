INSERT INTO cinema (id, name) VALUES
                                  (1, 'CineMax'),
                                  (2, 'MoviePalace');

INSERT INTO Theatre (id, name, cinema_id)
VALUES
    (1, 'Theatre 1', 1),
    (2, 'Theatre 2', 1),
    (3, 'Theatre 3', 2);
-- Testdata for Movie tabel

INSERT INTO Movie (title, duration, description, poster_image, category, age_limit) VALUES
                                                                                        ('The Matrix', '02:16:00', 'A computer hacker learns about the true nature of reality and his role in the war against its controllers.', 'https://example.com/matrix.jpg', 3, 16),
                                                                                        ('The Godfather', '02:55:00', 'The aging patriarch of an organized crime dynasty transfers control of his clandestine empire to his reluctant son.', 'https://example.com/godfather.jpg', 4, 18);



