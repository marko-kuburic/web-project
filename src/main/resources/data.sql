INSERT INTO USERR (name, surname, username, mail, password, birth_date, role, user_author) VALUES ( 'Marko', 'Kuburic', 'Gangula', 'kuburic.marko@gmail.com', 'volimkrupnijezene69', '2019-02-10', 0, 0);
INSERT INTO USERR (name, surname, username, mail, password, birth_date, role, user_author) VALUES ( 'Danilo', 'Damjanovic', 'Djani', 'daniloliman@gmail.com', '23cmFruskogorska29', '2002-02-10', 0, 0);

INSERT INTO BOOK (title, isbn, image_path, release_date, number_of_pages, description, genre, rating) VALUES ('Srpsko-srpski recnik', '9788690448906', '/home/danilo/Pictures/RadovanDamjanovic', '2003-12-11', 144, 'Knjiga svih knjiga', null, 5.0  );
INSERT INTO BOOK (title, isbn, image_path, release_date, number_of_pages, description, genre, rating) VALUES ('Smisao zivota u lirici Ane Nikolic', '9783849548906', '/home/marko/Pictures/AnaNikolicSlikeSVE', '2023-02-14', 142, 'Kruna svih dela knjizevno-filosofskog pravca egzistencijalizma', null, 5.0);

INSERT INTO SHELF (name, is_primary) VALUES ('polica istorije', true);

INSERT INTO SHELF_ITEM (book_id) VALUES (1);
INSERT INTO SHELF_ITEM (book_id) VALUES (2);

INSERT INTO SHELVES_AND_ITEMS (shelf_id, item_id) VALUES (1,1);

INSERT INTO REVIEW (rating, text, date, user_id, item_id) VALUES (5.0, 'ODLICNA KNJIGA!', '2023-5-4', 1 , 1);