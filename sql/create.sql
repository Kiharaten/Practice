-- create tables sql of 'DVD rental management system' in JV32 summer sutudy
-- source ~/Desktop/JV32_summerstudy/sql/create.sql;
USE sys;

DROP DATABASE practice09;

CREATE DATABASE practice09;

USE practice09;


-- create tables
CREATE TABLE status (
    id       INT(1) NOT NULL,
    name     VARCHAR(10)
);
ALTER TABLE status ADD INDEX id_index(id);

CREATE TABLE gender (
    id      INT(1) NOT NULL,
    name    VARCHAR(10)
);
ALTER TABLE gender ADD INDEX id_index(id);

CREATE TABLE actor (
    id        INT(255) NOT NULL,
    name      VARCHAR(100),
    age       INT(2),
    gender_id    INT(1),
    FOREIGN KEY (gender_id) REFERENCES practice09.gender(id)
);


CREATE TABLE genre (
    id        INT(255) NOT NULL,
    name      VARCHAR(100)
);


CREATE TABLE disk (
    id         INT(255) NOT NULL,
    title     VARCHAR(100),
    genre     INT(2),
    actor     VARCHAR(255)
);
ALTER TABLE disk ADD INDEX id_index(id);


CREATE TABLE friend (
    id       INT(255) NOT NULL,
    name     VARCHAR(100),
    mail     VARCHAR(100)
);
ALTER TABLE friend ADD INDEX id_index(id);


CREATE TABLE rental (
    id     INT(255) NOT NULL,
    number   INT(255) NOT NULL,
    date     DATETIME,
    disk_id         INT(255) NOT NULL,
    friend_id       INT(255) NOT NULL,
    status_id       INT(1) NOT NULL,
    FOREIGN KEY (disk_id) REFERENCES practice09.disk(id),
    FOREIGN KEY (friend_id) REFERENCES practice09.friend(id),
    FOREIGN KEY (status_id) REFERENCES practice09.status(id)
);