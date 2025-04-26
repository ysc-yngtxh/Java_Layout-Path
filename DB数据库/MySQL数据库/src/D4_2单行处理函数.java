/**
 * @author 游家纨绔
 * @dateTime 2023-09-19 17:58
 * @apiNote TODO
 */
/* 一、单行处理函数（这里可以简单的理解为函数只处理单行字段，以此分类；那么后面就是分组函数或者叫聚集函数，意味处理多行字段，但是输出都是一行字段）
 *
 *     CREATE TABLE `tb_order`(
 *        `id` bigint unsigned NOT NULL AUTO_INCREMENT COMMENT '订单id',
 *        `subject` varchar(256) DEFAULT NULL COMMENT '订单标题',
 *        `order_no` varchar(50) DEFAULT NULL COMMENT '商户订单编号',
 *        `total_amount` double DEFAULT NULL COMMENT '订单金额',
 *        `status` varchar(50) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '订单状态(WAIT_BUYER_PAY: 交易创建; TRADE_SUCCESS: 支付成功; TRADE_FINISHED: 交易完结; TRADE_CLOSED: 交易关闭)',
 *        `create_time` datetime DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
 *        `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
 *        `pay_type` varchar(10) CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci DEFAULT NULL COMMENT '支付方式(支付宝; 微信; 银联)',
 *        PRIMARY KEY (`id`)
 *     ) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='订单表';
 *
 *   Ⅰ、字符函数
 *      1、length 获取参数值的字节个数（每个英文一个字节；中文需要看编码，utf8编码一个中文3个字节）
 *         SELECT LENGTH(name) FROM tb_order;  -- 比如 name='游家纨绔' ，那么返回数据为 12; name='yjwk' ，那么返回数据为 4
 *
 *      2、concat 拼接字符串
 *         [1]、SELECT CONCAT(name, alias) FROM tb_order;
 *              -- 比如 name='游家纨绔'，alias='小游'，返回结果为 ‘游家纨绔小游’
 *         [2]、SELECT CONCAT_WS("_", name, alias, status) FROM tb_order;
 *              -- 比如 name='游家纨绔‘，alias='小游'，status='PLEASE_PAY' 返回结果为 ‘游家纨绔_小游_PLEASE_PAY’
 *
 *      3.upper、lower 将字符变为大写/小写
 *         [1]、SELECT LOWER(name) FROM tb_order;  -- 比如 name='YouShiCheng' 返回结果为 ‘youshicheng’
 *         [2]、SELECT UPPER(name) FROM tb_order;  -- 比如 name='YouShiCheng' 返回结果为 ‘YOUSHICHENG’
 *
 *      4.substr、substring 从下标pos开始往后截取字符输出，索引从1开始（两种写法都可）
 *         [1]、SELECT SUBSTR(name, 1, 2) FROM tb_order;     -- 比如 name='齐天大圣孙悟空' 返回结果为 ‘齐天’
 *         [2]、SELECT SUBSTRING(name, 1, 2) FROM tb_order;  -- 比如 name='齐天大圣孙悟空' 返回结果为 ‘齐天’
 *
 *      5.instr 查询子串在主串中第一次出现的下标,找不到返回0
 *         SELECT INSTR(name, '孙') FROM tb_order;
 *         -- 比如 name='齐天大圣孙悟空‘ 返回结果为  5 ; 如果name='齐天大圣悟空‘ 返回结果为  0
 *
 *      6.trim 去掉前后空格/或指定字符
 *         [1]、SELECT TRIM(name) FROM tb_order;  -- 去除前后空白
 *         [2]、SELECT LTRIM(name) FROM tb_order; -- 去除前导空白
 *         [3]、SELECT RTRIM(name) FROM tb_order; -- 去除尾随空白
 *         [4]、SELECT TRIM(BOTH 'a' FROM name) FROM tb_order  -- 去除前导和尾随的'a'字符【默认写法 TRIM('a' from subject)】
 *         [5]、SELECT TRIM(LEADING 'a' FROM name) FROM tb_order     -- 去除前导的'a'字符
 *         [6]、SELECT TRIM(TRAILING 'a' FROM name) FROM tb_order    -- 去除尾随的'a'字符
 *         [7]、SELECT TRIM(BOTH 'a' from TRIM(name)) FROM tb_order  -- 去除前后空白后，再去除前后的'a'字符
 *
 *      7.lpad 用指定的字符实现左填充指定长度
 *         [1]、SELECT LPAD(subject, 2, "*") FROM tb_order
 *              -- 如果 subject=‘齐天大圣孙悟空’ 结果为： 齐天
 *         [2]、SELECT LPAD(subject, 10, "*") FROM tb_order
 *              -- 如果 subject=‘齐天大圣孙悟空’ 结果为： ***齐天大圣孙悟空
 *
 *      8.rpad 用指定的字符实现右填充指定长度
 *         [1]、SELECT RPAD(subject, 2, "*") FROM tb_order
 *              -- 如果 subject=‘齐天大圣孙悟空’ 结果为： 齐天
 *         [2]、SELECT RPAD(subject, 10, "*") FROM tb_order
 *              -- 如果 subject=‘齐天大圣孙悟空’ 结果为： 齐天大圣孙悟空***
 *
 *      9.replace 替换
 *        [1]、SELECT REPLACE(subject, '操', "*") FROM tb_order
 *             -- 如果 subject=‘中学生在操场上做早操’ 结果为： 中学生在*场上做早*
 *        [2]、UPDATE tb_order SET subject = REPLACE(subject, '曹', '*' ) WHERE id=1;
 *             -- 如果 subject='曹操'  结果id=1的数据中： subject='曹*'
 * ---------------------------------------------------------------------------------------------------------------
 *   Ⅱ、数学函数
 *      1.round 四舍五入
 *           SELECT ROUND(price) FROM tb_order;     -- 如果 price=123.556 结果为：124
 *           SELECT ROUND(price, 2) FROM tb_order;  -- 如果 price=123.556 结果为：123.56
 *
 *      2.ceil 向上取整，返回 >= 该参数的最小整数（向上记住其实就是找比它大的整数就好）
 *           SELECT CEIL(price) FROM tb_order;  -- 如果 price=123.009 结果为：124; 如果 price=-1.009 结果为：-1
 *
 *      3.floor 向下取整，返回 <= 该参数的最大整数
 *           SELECT FLOOR(price) FROM tb_order; -- 如果 price=123.009 结果为：123; 如果 price=-1.009 结果为：-2
 *
 *      4.truncate 截断（其实就是截断小数点后面的几位，和round不同，简单的保留）
 *           SELECT TRUNCATE(price, 2) FROM tb_order;  -- 如果 price=123.556 结果为：124.55  区别于四舍五入，纯粹的截取
 *
 *      5.mod取余
 *           SELECT MOD(price, 3) FROM tb_order;  -- 如果 price=10 结果为：1
 * ---------------------------------------------------------------------------------------------------------------
 *   Ⅲ、日期函数
 *      1.now 返回当前系统日期+时间
 *           SELECT NOW();   -- 2023-09-20 14:00:00
 *
 *      2.curdate 返回当前系统日期，不包括时间
 *           SELECT CURDATE();   -- 2023-09-20
 *
 *      3.curtime 返回当前时间，不包含日期
 *           SELECT CURTIME();   -- 14:00:00
 *
 *      4.year month monthname day hour minute second可以获取时间指定的部分，年、月、日、小时、分钟、秒
 *           SELECT YEAR( NOW() );       -- 返回年：2023
 *           SELECT MONTH( NOW() );      -- 返回月：9
 *           SELECT MONTHNAME( NOW() );  -- 返回月份的英文名称：September
 *           SELECT DAY( NOW() );        -- 返回日：20
 *           SELECT HOUR( NOW() );       -- 返回小时：14
 *           SELECT MINUTE( NOW() );     -- 返回分钟：30
 *           SELECT SECOND( NOW() );     -- 返回秒：28
 *
 *      5.str_to_date 将日期格式的字符转换成指定格式的日期
 *           SELECT STR_TO_DATE('09-20-2023', '%m-%d-%Y');     -- 返回 2023-09-20
 *
 *      6.date_format 将日期转换成字符
 *           SELECT DATE_FORMAT('2023/09/20', '%Y年%m月%d日');  -- 返回 2023年09月20日
 *
 *      7. 时间间隔加减
 *           -- date_add 加
 *           select date_add('2025-1-9', interval 1 day) dayTime,
 *                  date_add('2025-1-9', interval 1 month) monthTime,
 *                  date_add('2025-1-9', interval 1 year) yearTime;
 *           -- date_add 减
 *           select date_sub('2025-1-9', interval 1 day) dayTime,
 *                  date_sub('2025-1-9', interval 1 month) monthTime,
 *                  date_sub('2025-1-9', interval 1 year) yearTime;
 *
 *      8. 日期时间减法
 *           -- datediff 日期减
 *           select datediff('2025-1-9', '2025-1-5') intervfalDay;
 *           -- timediff 时间减
 *           select timediff('12:00:00', '11:10:00') intervfalTime;
 * ---------------------------------------------------------------------------------------------------------------
 *   Ⅳ、其它函数
 *      1.if(condition, expr_if_true, expr_if_false) 函数
 *           -- 当condition条件表达式为true，则返回expr_if_true，否则返回expr_if_false
 *           SELECT IF(del_flag=0, '显示', '删除') FROM tb_order
 *
 *      2.ifnull(expr1, expr2) 函数
 *           -- 当expr1 为 NULL，IFNULL() 函数返回 expr1，否则返回 expr2
 *           SELECT IFNULL(subject, order_no) FROM tb_order
 *
 *      3.nullif(expr1, expr2) 函数
 *           -- 当expr1 = expr2，NULLIF() 函数返回 NULL，否则返回 expr1
 *           SELECT NULLIF(0, 0) FROM tb_order
 *
 *      4.case 函数
 *        [1]、写法一：(CASE)举例 order_no, (WHEN)当其值为 0 时, (THEN)输出'Monday'  ...  (ELSE)否则输出'Error', (END)结束
 *             SELECT
 *                  CASE order_no
 *                      WHEN 0 THEN 'Monday'
 *                      WHEN 1 THEN 'Tuesday'
 *                      WHEN 2 THEN 'Wednesday'
 *                      ELSE 'Error'
 *                  END AS `orderName`
 *              FROM
 *                  tb_order;
 *
 *        [2]、写法二：(CASE)举例, (WHEN)当 order_no = 0 时, (THEN)输出'Monday'  ...  (ELSE)否则输出'Error', (END)结束
 *             SELECT
 *                  CASE
 *                      WHEN order_no = 0 THEN 'Monday'
 *                      WHEN order_no = 1 THEN 'Tuesday'
 *                      WHEN order_no = 2 THEN 'Wednesday'
 *                      ELSE 'Error'
 *                  END AS `orderName`
 *              FROM
 *                  tb_order;
 */
public class D4_2单行处理函数 {}
