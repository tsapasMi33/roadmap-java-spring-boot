CREATE TABLE artists
(
    id        BIGINT AUTO_INCREMENT                  NOT NULL,
    firstname VARCHAR(60) COLLATE utf8mb4_unicode_ci NOT NULL,
    lastname  VARCHAR(60) COLLATE utf8mb4_unicode_ci NOT NULL,
    CONSTRAINT pk_artists PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
