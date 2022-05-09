CREATE DATABASE IF NOT EXISTS design DEFAULT CHARACTER SET utf8 ;

CREATE Table IF NOT EXISTS user(
    id INT NOT NULL AUTO_INCREMENT,
    pub_key VARCHAR(45) NOT NULL,
    expires VARCHAR(45) NOT NULL,
    PRIMARY KEY (idu)
);

CREATE TABLE IF NOT EXISTS certify (
    certificate VARCHAR (100) NOT NULL UNIQUE PRIMARY KEY,
    md5_key VARCHAR(100) NOT NULL,
    hash_val varchar(100) NOT NULL,
    expires DATE NOT NULL,
    user_idu INT,
    FOREIGN KEY (user_idu) REFERENCES user(idu)
);CREATE DATABASE IF NOT EXISTS design DEFAULT CHARACTER SET utf8 ;

  CREATE Table IF NOT EXISTS user(
      idu INT NOT NULL AUTO_INCREMENT,
      surname VARCHAR(45) NOT NULL,
      name VARCHAR(45) NOT NULL,
      PRIMARY KEY (idu)
  );

  CREATE TABLE IF NOT EXISTS Certificate (
      idc VARCHAR (100) NOT NULL UNIQUE PRIMARY KEY,
      md5_key VARCHAR(100) NOT NULL,
      hash_val varchar(100) NOT NULL,
      expires DATE NOT NULL,
      user_idu INT,
      FOREIGN KEY (user_idu) REFERENCES user(idu)
  );