CREATE TABLE IF NOT EXISTS task
(
    id          INT NOT NULL AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) NOT NULL,
    status      INT NOT NULL
);