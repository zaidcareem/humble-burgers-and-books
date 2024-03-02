create database bnbshop;
use bnbshop;
show tables;
select * from customers;
select * from books;
drop table burgers, books;
Update customers SET CumulativeExpense = CumulativeExpense + 500 WHERE username = 'Zaid';
insert into customers values ('zaid', '123', 0);
create table books (
    Title varchar(50) not null,
    InStock int,
    Price float,
    primary key (Title)
    );
SELECT EXISTS(SELECT * FROM books WHERE Title = 'kntting');



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