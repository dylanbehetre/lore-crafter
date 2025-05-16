DO
$$
    BEGIN
        IF NOT EXISTS (SELECT 1 FROM universe) THEN
            INSERT INTO universe (name, description)
            VALUES ('Middle-earth',
                    'Univers fantasy créé par J.R.R. Tolkien, avec des elfes, des nains et des anneaux magiques.'),
                   ('The Matrix',
                    'Une réalité simulée contrôlée par des machines, où l’humanité lutte pour sa liberté.'),
                   ('Cyberpunk 2077',
                    'Un futur dystopique où la cybernétique et les mégacorporations dominent le monde.'),
                   ('The Witcher', 'Monde de magie et de monstres inspiré des romans de Andrzej Sapkowski.'),
                   ('Star Wars', 'Galaxie lointaine peuplée de Jedi, Sith et de conflits interstellaires.');
        END IF;
    END
$$;
