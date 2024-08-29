DROP DATABASE IF EXISTS MapDB;
CREATE DATABASE IF NOT EXISTS MapDB;

CREATE TABLE MapDB.exampleTab (
    X1 float,
    X2 float,
    X3 float
);

INSERT INTO MapDB.exampleTab VALUES(1,2,0);
INSERT INTO MapDB.exampleTab VALUES(0,1,-1);
INSERT INTO MapDB.exampleTab VALUES(1,3,5);
INSERT INTO MapDB.exampleTab VALUES(1,3,4);
INSERT INTO MapDB.exampleTab VALUES(2,2,0);

DROP USER IF EXISTS 'MapUser'@'localhost';
CREATE USER IF NOT EXISTS 'MapUser'@'localhost' IDENTIFIED BY 'map';

GRANT SELECT ON MapDB.* TO 'MapUser'@'localhost';