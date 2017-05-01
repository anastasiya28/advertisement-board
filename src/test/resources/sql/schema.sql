  DROP TABLE comment;
  DROP TABLE advertisement;

  CREATE TABLE
  IF NOT EXISTS
  advertisement (
     id INT UNSIGNED NOT NULL AUTO_INCREMENT
     PRIMARY KEY,
     name VARCHAR(1000) NOT NULL,
     createOn DATETIME,
     description VARCHAR(10000) NOT NULL
  );

  CREATE TABLE
  IF NOT EXISTS
  comment (
    id INT UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    text VARCHAR(5000) NOT NULL,
    advertisementId INT UNSIGNED NOT NULL,
    FOREIGN KEY (advertisementId) REFERENCES advertisement(id)
  );







