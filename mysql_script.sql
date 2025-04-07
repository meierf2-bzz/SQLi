drop database if exists sqli_demo;

CREATE DATABASE if not exists sqli_demo;
USE sqli_demo;
CREATE TABLE if not exists users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50),
    password VARCHAR(50)
);
INSERT INTO users (username, password) VALUES ('admin', 'password123');

select * from users;

