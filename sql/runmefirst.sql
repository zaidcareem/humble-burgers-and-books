create database bnbshop;
use bnbshop;
show tables;
select * from customers;
select * from books;

SELECT EXISTS(SELECT * FROM books WHERE Title = 'knotting');

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