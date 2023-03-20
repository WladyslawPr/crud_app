CREATE TABLE Task
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) NOT NULL,
    status      int          NOT NULL
);