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
brandName VARCHAR(100),
productName VARCHAR (100),
resinID INT (10),
material VARCHAR(75),
disposal VARCHAR(30),
ecoComment VARCHAR(500),
ecoScore VARCHAR(15)
);

SELECT * FROM objects;
