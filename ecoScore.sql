/*
	Name:		Wyatt Kirschner
    Date: 		10/29/20
    Student ID:	200407722
    Notes:		This is for the database, the JavaFX application will attempt to support CRUD

*/

DROP DATABASE ecoScore; 
CREATE DATABASE ecoScore;
USE ecoScore;
CREATE TABLE objects
(id INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
brandName VARCHAR(50),
productName VARCHAR (50),
resinID BIT (1),
material VARCHAR(75),
disposal VARCHAR(15),
ecoComment VARCHAR(255),
ecoScore VARCHAR(15)
);

SELECT * FROM objects;