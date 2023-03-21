--liquibase formatted sql
--changeset vlpr:1
CREATE TABLE IF NOT EXISTS task
(
    id          BIGINT AUTO_INCREMENT PRIMARY KEY,
    description varchar(100) NOT NULL,
    status      int          NOT NULL
);