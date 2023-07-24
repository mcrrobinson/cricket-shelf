# cricket-shelf

University of Portsmouth Java Programming Coursework.

# Requirements
[Read More](Requirements_Document.pdf)

# Setup

## Create local glassfish server
1. Services
2. Servers 
3. Add Server
4. GlassFish Server Open Source Edition 4.1.1 
5. Next
6. Browse > C:\glassfish4\glassfish\domains\domain1
7. Next 
8. Finish
9. Start server
## Create local database
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
7. Create the database by executing the code in `webpages/internal/db/setup.sql`
## Create JDBC Connection Pool
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
## Create JDBC Resource
1. Click on JDBC Resources
2. New...
3. JNDI Name: jdbc/cricketbooksbean
4. Pool Name: cricketstore
5. OK
