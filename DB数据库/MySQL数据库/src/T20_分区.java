/**
 * @author 游家纨绔
 * @dateTime 2024-09-21 21:09
 * @apiNote TODO
 */
/*
分区

--创建数据库
CREATE DATABASE partitioning DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci;
--创建分区表，主键：int类型
CREATE TABLE tbl_users (
	`uuid`         INT PRIMARY KEY,
	`customerId`   VARCHAR(200),
	`pwd`          VARCHAR(20),
	`showName`     VARCHAR(100),
	`trueName`     VARCHAR(100),
	`registerTime` VARCHAR(100)
) ENGINE = InnoDB AUTO_INCREMENT = 1 DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_0900_ai_ci ROW_FORMAT = DYNAMIC COMMENT '字典类别表'
  PARTITION BY RANGE (uuid) (
		PARTITION p0 VALUES LESS THAN (5),
		PARTITION p1 VALUES LESS THAN (10),
		PARTITION p2 VALUES LESS THAN (15),
		PARTITION p3 VALUES LESS THAN MAXVALUE
  );
--插入数据
INSERT INTO tbl_users VALUES
(1,"只为你","123456","qzp","quezhipeng","20200808"),
(6,"人生","123456","人生","无名","20200808"),
(12,"无须终有","123456","无须终有","无声","20200808"),
(100,"坚持","123456","胜利","坚持","20200808");

 */
public class T20_分区 {}
