-- Schema = ROOT
-- User = root
-- Pass = password

-- Books table
CREATE TABLE BOOKS (
	book_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	title VARCHAR(250) NOT NULL,
	edition VARCHAR(25) NOT NULL,
	sales_price DOUBLE NOT NULL,
	publish_year INT NOT NULL,
	thumbnail VARCHAR(37) NOT NULL,
	PRIMARY KEY (book_id)
);

-- User table
CREATE TABLE USERS (
	user_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	email_address VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
	admin BOOLEAN NOT NULL,
	recent_book_id INT REFERENCES BOOKS(book_id),
    basket_total DOUBLE NOT NULL,
	login_date TIMESTAMP NOT NULL,
	PRIMARY KEY (user_id)
);

-- Card table
CREATE TABLE CARDS (
	card_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INT NOT NULL REFERENCES USERS(user_id),
	name VARCHAR(250) NOT NULL,
	expiration_month INT NOT NULL,
	expiration_year INT NOT NULL,
	number VARCHAR(45) NOT NULL,
	PRIMARY KEY (card_id)
);

-- Address table
CREATE TABLE ADDRESSES (
	address_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	user_id INT REFERENCES USERS(user_id),
	street VARCHAR(250) NOT NULL,
	city VARCHAR(250) NOT NULL,
	state VARCHAR(250) NOT NULL,
	postcode VARCHAR(250) NOT NULL,
	country VARCHAR(250) NOT NULL,
	PRIMARY KEY (address_id)
);
-- Not NOT NULL because a user might not wanna' associate with it but we still need the record.

-- Order table
CREATE TABLE ORDERS (
	order_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	total DOUBLE NOT NULL,
	status VARCHAR(250) NOT NULL,
	user_id INT NOT NULL REFERENCES USERS(user_id),
	card_id INT NOT NULL REFERENCES CARDS(card_id),
	address_id INT NOT NULL REFERENCES ADDRESSES(address_id),
	ordered TIMESTAMP NOT NULL,
	out_for_delivery TIMESTAMP,
	delivered TIMESTAMP,
	cancelled TIMESTAMP,
	PRIMARY KEY (order_id)
);

CREATE TABLE ORDER_HAS_BOOK (
    order_id INT NOT NULL REFERENCES ORDERS(order_id),
	book_id INT NOT NULL REFERENCES BOOKS(book_id),
    quantity INT NOT NULL,
    item_cost_on_purchase DOUBLE NOT NULL,
    PRIMARY KEY(order_id, book_id)
);


CREATE TABLE BASKET_HAS_BOOK (
    user_id INT NOT NULL REFERENCES USERS(user_id),
    book_id INT NOT NULL REFERENCES BOOKS(book_id),
    quantity INT NOT NULL,
    PRIMARY KEY(user_id, book_id)
);

-- Genre table
CREATE TABLE GENRES (
	genre_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR(250) NOT NULL,
    PRIMARY KEY (genre_id)
);

-- Bridge table to allow many-to-many relationship between books and genres
CREATE TABLE BOOK_HAS_GENRE (
    genre_id INT NOT NULL REFERENCES GENRES(genre_id),
	book_id INT NOT NULL REFERENCES BOOKS(book_id),
    PRIMARY KEY (genre_id, book_id)
); 

-- Author table
CREATE TABLE AUTHORS (
	author_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	name VARCHAR(50) NOT NULL,
    PRIMARY KEY (author_id)
);

-- Bridge table to allow many-to-many relationship between books and authors
CREATE TABLE BOOK_HAS_AUTHOR (
	author_id INT NOT NULL REFERENCES AUTHORS(author_id),
	book_id INT NOT NULL REFERENCES BOOKS(book_id),
    PRIMARY KEY (author_id, book_id)
);

-- insert into BOOKS (title, edition, sales_price, publish_year) VALUES 
-- ('Ultimate Cricket Superstars', '1st', 6.50, 2022, ?),
-- ('Crickets Most Legendary International Matches', '1st', 7.99, 2023, ?),
-- ('Fatty Batter: How cricket saved my life (then ruined it)', '1st', 12.09, 2008, ?),
-- ('The Cricket Quiz Book: The quintessential quizzing challenge for cricket lovers with over 500 questions', '1st', 4.99, 2022, ?),
-- ('Pushing the Boundaries: Cricket in the Eighties', '1st', 20.00, 2018, ?);

-- insert into USERS (first_name, last_name, email_address, password, admin, recent_book_id, basket_total) VALUES ('Matt', 'Robinson', 'up2022742@port.ac.uk' 'password', True, 1, 0.0);
-- insert into GENRES (name) VALUES ('Sport');
-- insert into BOOK_HAS_GENRE (book_id, genre_id) VALUES (1, 1), (2, 1), (3, 1), (4, 1), (5, 1);
-- insert into AUTHORS (name) VALUES ('Tanya Aldred'),('Matt Oldfield'),('Derek Pringle'),('Clarity Media'),('Michael Simkins'),('Lunar Press');
-- insert into BOOK_HAS_AUTHOR (book_id,author_id) VALUES (5, 1),(5, 2),(4, 3),(3, 4),(2,5),(1,6);
-- insert into CARDS (user_id, name, expiration_month, expiration_year, number) VALUES (1, 'Matthew Robinson', 6, 2023, '111132123123');
-- insert into ADDRESSES(user_id,street,city,state,postcode,country) VALUES (1,'Street','London','Greater London','LO11AP','United Kingdom');
-- insert into ORDERS(total,status,user_id,card_id,address_id,ordered) VALUES (6.50, 'Ordered', 1, 1, 1, CURRENT_TIMESTAMP);
-- insert into ORDER_HAS_BOOK(order_id,book_id,quantity,item_cost_on_purchase) VALUES (1,1,1,6.50);
-- insert into BASKET_HAS_BOOK (user_id, book_id, quantity) VALUES (1, 1, 1), (1, 3, 3);

-- insert into ORDER_HAS_BOOK(order_id,book_id,quantity,item_cost_on_purchase) VALUES (1,1,1,6.50);
-- insert into ORDER_HAS_BOOK(order_id,book_id,quantity,item_cost_on_purchase) VALUES (1,3,3,12.09);

-- insert into ORDERS(total,status,user_id,card_id,address_id,ordered) VALUES (42.77, 'Ordered', 1, 1, 1, CURRENT_TIMESTAMP);
-- insert into ORDER_HAS_BOOK(order_id,book_id,quantity,item_cost_on_purchase) VALUES (2,1,1,6.50);
-- insert into ORDER_HAS_BOOK(order_id,book_id,quantity,item_cost_on_purchase) VALUES (2,3,3,12.09);