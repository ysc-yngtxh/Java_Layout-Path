CREATE TABLE `student`(
    `id`    int NOT NULL AUTO_INCREMENT,
    `name`  varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `email` varchar(255) CHARACTER SET utf8mb4 DEFAULT NULL,
    `age`   int DEFAULT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB AUTO_INCREMENT = 17 DEFAULT CHARSET = utf8mb4 COLLATE = utf8mb4_0900_ai_ci;