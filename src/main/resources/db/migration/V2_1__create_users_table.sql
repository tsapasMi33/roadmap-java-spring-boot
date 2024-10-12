CREATE TABLE users
(
    id        BIGINT AUTO_INCREMENT                              NOT NULL,
    login     VARCHAR(60) COLLATE utf8mb4_unicode_ci             NOT NULL,
    password  VARCHAR(255) COLLATE utf8mb4_unicode_ci            NOT NULL,
    firstname VARCHAR(60) COLLATE utf8mb4_unicode_ci             NOT NULL,
    lastname  VARCHAR(60) COLLATE utf8mb4_unicode_ci             NOT NULL,
    email     VARCHAR(255) COLLATE utf8mb4_unicode_ci            NOT NULL,
    language  VARCHAR(2) COLLATE utf8mb4_unicode_ci              NOT NULL,
    `role`    ENUM ('admin','member') COLLATE utf8mb4_unicode_ci NOT NULL,
    created_at datetime                                          NOT NULL,
    CONSTRAINT pk_users PRIMARY KEY (id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_unicode_ci;
