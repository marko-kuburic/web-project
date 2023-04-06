INSERT INTO USERR (name, surname, username, mail, password, birth_date, role, user_author) VALUES ( 'Marko', 'Kuburic', 'Gangula', 'kuburic.marko@gmail.com', 'volimkrupnijezene69', '2019-02-10', 0, 1);

INSERT INTO BOOK (title, isbn, release_date,number_of_pages, description, genre, rating) VALUES ('Srpsko-srpski recnik', '9788690448906', '2003-12-11', 144, 'Knjiga svih knjiga', null, 5.0  );

INSERT INTO SHELF (name, is_primary) VALUES ('polica istorije', true);

INSERT INTO SHELF_ITEM (book_id) VALUES (1);

INSERT INTO SHELVES_AND_ITEMS (shelf_id, item_id) VALUES (1,1);

INSERT INTO REVIEW (rating, text, date, user_id, item_id) VALUES (5.0, 'ODLICNA KNJIGA!', '2023-5-4', 1 , 1);
