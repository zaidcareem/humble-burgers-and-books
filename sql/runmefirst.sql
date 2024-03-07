create database bnbshop;

use bnbshop;

create table books (
    Title varchar(50) not null,
    InStock int,
    Price float,
    primary key (Title)
    );

create table burgers (
    Type varchar(50) not null,
    InStock int,
    Price float,
    primary key (type)
    );
   
create table admins (
	Username varchar(50) not null,
	Password varchar(50) not null,
	primary key (Username)
	);

create table Customers (
	Username varchar(50) not null,
	Password varchar(50) not null,
    CumulativeExpense float,
	primary key (Username)
	);


-- table checking queries

	select * from customers;
	select * from admins;
	select * from books;
	select * from burgers;

-- set up admin credentials, set credentials of your choice
	
	SET @admin_username = 'EnterAdminUsername of your choice';
	SET @admin_password = 'EnterAdminPassword of your choice';

-- add the admin into the database (admins table) using those variables

	INSERT INTO admins VALUES (@admin_username, @admin_password);
    
/*
	Now run the below trigger so that the admins table will only have one record at all times
	(you can still edit the only record that exists on the admins table)
*/

-- Create a trigger to enforce only one row in the admins table
DELIMITER //

CREATE TRIGGER enforce_single_admin_row
BEFORE INSERT ON admins
FOR EACH ROW
BEGIN
    DECLARE num_rows INT;

    -- Count the number of existing rows in the admins table
    SELECT COUNT(*) INTO num_rows FROM admins;

    -- If there's already a row in the table, prevent insertion of new rows
    IF num_rows > 0 THEN
        SIGNAL SQLSTATE '45000' SET MESSAGE_TEXT = 'Only one row is allowed in the admins table';
    END IF;
END;
//

DELIMITER ;

/*
dear admin,
    run these queries below (even with values of your own choice) so that your shop is prosporous.
*/

	INSERT INTO burgers VALUES ('Beef', 3, 400);
	INSERT INTO books VALUES ('Hunger Games', 5, 900);
