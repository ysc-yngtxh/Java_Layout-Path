/**
 * @author 游家纨绔
 * @dateTime 2024-05-07 07:50:00
 * @apiNote TODO
 */
/* 自定义函数
 *    [1]、语法
 *         create function 函数名([参数列表]) returns 数据类型
 *         begin
 *             SQL语句;
 *             return 值;
 *         end;
 *
 *    [2]、示例：自定义函数 NEXTVAL()
 *         CREATE DEFINER=`root`@`localhost` FUNCTION `NEXTVAL`() RETURNS BIGINT(20) DETERMINISTIC
 *         BEGIN
 *             DECLARE current BIGINT;               -- 声明bigint 类型的变量
 *             SELECT last_insert_id() INTO current; -- 获取插入数据的id，并将其赋值给变量 current
 *             RETURN current;                       -- 返回变量 current 的值
 *         END;
 *
 *         语法解析：
 *             DEFINER=`root`@`localhost`：指定函数的创建者 【`用户`@`域名`】
 *                   指定存储过程、函数、触发器等数据库对象的所有者以及这些对象执行时所具有的权限级别。
 *                   如果不显式指定 DEFINER，MySQL 会默认使用当前用户的权限作为定义者的权限。
 *             RETURNS BIGINT(20)：指定函数的返回类型
 *                   自定义函数必须要有返回值
 *             DETERMINISTIC：指定函数是否为确定性函数
 *                  确定性函数是指对于相同的输入参数，总是返回相同的结果。
 *                  在binlog启用后，创建的函数需要声明类型，因为binlog在主从复制需要知道这个函数创建语句是什么类型，否则同步数据会有不一致现象。
 *
 *    [3]、示例：自定义函数 get_student_scores_by_id(sid INT, cid INT)
 *         -- 删除已经存在的函数 get_student_scores_by_id
 *         DROP FUNCTION IF EXISTS get_student_scores_by_id;
 *         -- 创建自定义函数 get_student_scores_by_id
 *         DELIMITER $$
 *         CREATE DEFINER = CURRENT_USER FUNCTION get_student_scores_by_id(sid INT, cid INT)
 *         RETURNS VARCHAR(300)
 *         DETERMINISTIC
 *         BEGIN
 *             DECLARE score INT;
 *             -- 尝试从scores表中获取学生的成绩
 *             SELECT sc.score INTO score
 *             FROM scores AS sc
 *             WHERE sc.sid = sid AND sc.cid = cid;
 *             -- 根据成绩判断是否及格
 *             IF score >= 60 THEN
 *             	RETURN '及格';
 *             ELSEIF score > 0 AND score < 60 THEN
 *             	RETURN '不及格';
 *             ELSE
 *             	RETURN '找不到该学生或该学生没有选课！';
 *             END IF;
 *         END$$
 *         DELIMITER ;
 *         -- 调用函数
 *         SELECT get_student_scores_by_id(1, 2);
 *
 *         语法解析：
 *            DELIMITER $$ --> 将语句结束符从 ; 改为 $$
 *            END$$        --> 原本使用 ; 结束语句。现在使用语句结束符 $$ 来结束函数定义
 *            DELIMITER ;  --> 将语句结束符重新改回 ;
 *            注意：在执行更改语句结束符的命令后，后续只能在 MySql 命令行提示符中使用定义的$$，
 *                 第三方工具(Navicat...)使用更改后的语句结束符$$会报错
 *                 DELIMITER 该语法仅作了解，不怎么使用。
 */
public class T20_自定义函数 {}
