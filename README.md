# cricket-shelf
Enterprise Web Architectures Requirements

# Create local glassfish server
1. Services
2. Servers 
3. Add Server
4. GlassFish Server Open Source Edition 4.1.1 
5. Next
6. Browse > C:\glassfish4\glassfish\domains\domain1
7. Next 
8. Finish
9. Start server
# Create local database
1. Services
2. Databases
3. Java DB
4. Create Database
5. Copy paste the following

| Property | Value |
| --- | --- |
Database Name | cricketstore
User Name | root
Password | password
6. OK
# Create JDBC Connection Pool
1. Navigate to http://localhost:4848 
2. Resources 
3. JDBC 
4. JDBC Connection Pools 
5. New... 
6. Pool Name: cricketstore 
7. Resource Type: javax.sql.DataSource 
8. Database Driver Vendor: Derby 
9. Next > Scroll to additional properties

Copy and paste the following properties:
| Property | Value |
| --- | --- |
URL | jdbc:derby://localhost:1527/cricketstore
User | root
Password | password
ServerName | localhost
PortNumber | 1527
DatabaseName | cricketstore

10. Finish
11. Ping to check...

<!-- Session cookies are considered strictly necessary cookies. Hence, per most data regulations, such as the GDPR, websites do not need to gain users' consent to set them on their devices.25 Oct 2022 -->

<!-- CREATE TABLE USERS (
	user_id INT NOT NULL GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),
	first_name VARCHAR(25) NOT NULL,
	last_name VARCHAR(25) NOT NULL,
	email_address VARCHAR(250) NOT NULL,
    password VARCHAR(250) NOT NULL,
	admin BOOLEAN NOT NULL,
	recent_book_id INT REFERENCES BOOKS(book_id),
    basket_total DOUBLE NOT NULL,
	PRIMARY KEY (user_id)
); -->