create database bnbshop;

use bnbshop;

/*
select * from customers;
select * from admins; only 1 admin allowed
select * from books;
select * from burgers;
*/

/*
dear admin,
	
    run these queries with different values so that your shop is prosporous.

INSERT INTO burgers VALUES ('Beef', 3, 400);
INSERT INTO books VALUES ('Hunger Games', 5, 900);
*/

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