/**
 * @author 游家纨绔
 * @dateTime 2023-09-19 13:12
 * @apiNote TODO
 */
/* 一、聚合函数(也叫做：多行处理函数:就是综合多行数据，然后再输出)
 *     CREATE TABLE `t_emp`(
 *         `id`       int          NOT NULL AUTO_INCREMENT COMMENT '主键Id',
 *         `emp_no`   bigint       DEFAULT NULL COMMENT '员工编号',
 *         `ename`    varchar(255) DEFAULT NULL COMMENT '员工姓名',
 *         `mgr`      bigint       DEFAULT NULL COMMENT '领导编号',
 *         `sal`      double(10,2) DEFAULT NULL COMMENT '工资',
 *         `comm`     double(10,2) DEFAULT NULL COMMENT '津贴',
 *         `dept_no`  bigint       DEFAULT NULL COMMENT '部门编号',
 *         `job`      varchar(255) DEFAULT NULL COMMENT '工作岗位',
 *         `province` varchar(255) DEFAULT NULL COMMENT '省份',
 *         PRIMARY KEY (`id`)
 *     ) ENGINE=InnoDB AUTO_INCREMENT=1 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='员工信息表';
 *
 *     常用的分组函数：
 *         count()  计数
 *         sum()    求和
 *         avg()    平均值
 *         max()    最大值
 *         min()    最小值
 *         记住：所有的分组函数都是对"某一组"数据进行操作的
 *
 *     示例一：找出工资总和: SELECT SUM(sal) FROM t_emp;
 *            找出最高工资: SELECT MAX(sal) FROM t_emp;
 *            找出最低工资: SELECT MIN(sal) FROM t_emp;
 *            找出平均工资: SELECT AVG(sal) FROM t_emp;
 *            找出总人数:   SELECT COUNT(*) FROM t_emp;      -- 不是统计某个字段中数据的个数，而是统计总记录条数（和某个字段无关）
 *                        SELECT COUNT(ename) FROM t_emp;  -- 表示统计comm字段中不为 null 的数据总数量
 *
 *            分组函数自动忽略null
 *                SELECT SUM(comm) FROM t_emp WHERE comm IS NOT NULL;
 *                -- 这么写意思是：不是null的津贴(comm)之和。但是不需要后面的where语法，因为分组函数自动忽略null
 *
 *     示例二：计算每个员工的年薪
 *            SELECT ename,(sal+comm)*12 AS yearSal FROM t_emp;
 *            TODO 注意：因为不是每个员工都有津贴(comm)的，有的员工津贴(comm)为null，但是只要有 null 参与的运算结果一定是 null。
 *                      这样规定的运算其实仔细想想就明白：null 表示的是不确定值，一个不确定值与任意值运算结果都会是不确定值 null。
 *                      但是如果计算出来的员工年薪为null的话，人家早跑了。所以，怎么办呢？--------- 链接到章节 单行处理函数(ifnull或case)
 *
 *     示例三：找出工资高于平均工资的员工
 *            SELECT ename,sal FROM t_emp WHERE sal > AVG(sal);
 *            TODO 思考以上的错误信息：无效的使用了分组函数
 *                 原因：SQL语句当中有一个语法规则，分组函数不可直接使用在where字句当中，why？------ 链接到下面 group by 和 having
 *
 *     TODO SQL中的 count(1)、count(*) 与 count(列名) 到底有什么区别？
 *     count(列名)：统计指定列名的所有行数，不包括 NULL 值行
 *     count(*)：包括了所有的列，相当于行数，在统计结果的时候，不会忽略为NULL行的值。
 *     count(1)：包括了忽略所有列，在统计结果的时候，不会忽略为NULL行的值。
 *               这个 1 代表什么呢？最开始我以为表示列的下标(1表示第一列)，后来查阅资料发现并不是。
 *               这里的 1 是指把每一行的数据用 1 表示，假如我有十行数据，那么我就有十个1，用1代表数据行，最后再统计有多少个 1 就行。
 *               ⚠️：这里的 1 只是作为一个常量值，你也可以是count(2)、count(3)、count(4)
 *
 *                   小曹这个时候就会问：为什么要用 1(常量值) 替代数据行呢？我直接统计原数据行多少条不就行了吗？
 *               count(*)原理就是统计原数据行一共有多少行，但是会更耗费资源，因为它们需要从表中取出实际的字段值进行计数。
 *               而当我们只关心表的行数而不需要具体的字段值时，可以使用count(1)来获取表的行数。
 *
 *     综上所述：我是不是只关心表的行数而不需要具体的字段值时，就可以无脑使用 count(1)了呢？
 *              当然是不可以，上述情况我们只考虑了在没有索引的情况下，统计条数的使用 count(1) 的性能高
 *              但是在有索引情况下，我们统计大数据量的数据时 通过索引统计 比 遍历统计 性能更优
 *              因为我们的索引数据结构是 B+Tree 类型，可以通过我的叶子节点深度跟我的节点个数就能知道count值
 *              而mysql数据库中对 count(*) 是进行过优化的，他会先在表中进行根据索引统计，如果没有索引就遍历统计
 *              并且，count(*)是官方推荐写法，建议使用！！！
 *
 *     性能对比：
 *        列名为主键  -- count(列名)会比count(1)快，因为根据主键索引(聚集索引)查询条数
 *        列名不为主键 -- count(1)会比count(列名)快
 *        如果表多个列并且没有主键，则 count（1） 的执行效率优于 count（*）
 *        如果有主键，则 select count（主键）的执行效率是最优的
 * ---------------------------------------------------------------------------------------------------------------
 * 二、group by 和 having
 *       group by: 按照某个字段或者某些字段进行分组。比如一张表里的工作类型，有重复的工作类型，也有不重复的工作类型。先进行分组，重复的为一组。
 *       having  : having是对分组之后的数据进行再次过滤,需要注意的是只能操作分组数据
 *
 *       注意：分组函数一般都会和 group by 联合使用，这也是为什么他被称为 分组函数 的原因
 *            并且任何一个 分组函数 都是在 group by语句 执行结束之后才会执行的。
 *            当一条SQL语句没有 group by 的话，整张表的数据会自成一组。
 *
 *            在Having子句中，拿聚合函数跟具体的数据做比较是没有问题的，
 *            但是，拿聚合函数跟某个字段作条件判断是不行的，这个必须用表连接才能实现；
 *            例如：SELECT dept_no,AVG(sal) FROM t_emp GROUP BY dept_no HAVING sql > AVG(sal); 语法上是不行的
 *
 *
 *       执行顺序：
 *              SELECT      5
 *                 ...
 *              FROM        1
 *                 ...
 *              WHERE       2
 *                 ...
 *              GROUP BY    3
 *                 ...
 *              HAVING      4
 *                 ...
 *              ORDER BY    6
 *                 ...
 *
 *       找出工资高于平均工资的员工
 *           SELECT ename,sal FROM t_emp WHERE sal > AVG(sal);
 *           TODO 注意：这种写法是错误的。因为 AVG(sal) 是属于分组函数，但是按照上述SQL的执行顺序来说，
 *                     执行 WHERE 语句的时候还没有执行过分组语句group by。因此 WHERE 中的 AVG(sal) 分组函数是无法得到结果的。
 *
 *           第一步：SELECT AVG(sal) FROM t_emp;  // FROM 后面其实有group by sal，只是缺省了。
 *           第二步：SELECT ename,sal FROM t_emp WHERE sal > 表给的实际数据;
 *           合成一步：SELECT ename,sal FROM t_emp WHERE sal > (SELECT AVG(sal) FROM t_emp); // 小括号优先级高，所以先执行小括号里的
 *
 *
 *       案例一：找出每个工作岗位的最高薪资
 *              SELECT ename,job,MAX(sal) FROM t_emp GROUP BY job;  // 语法错误
 *              注意：以上在MySQL8以及Oracle数据库中，语法错误。
 *                   记住一条规则：当一条语句中有group by的话，select后面只能跟分组函数和参与分组的字段
 *
 *       案例二：找出每个部门的不同工作岗位的最高薪资
 *              SELECT dept_no,job,MAX(sal) FROM t_emp GROUP BY dept_no,job;
 *              +-----------+-------------+-----------+
 *              | dept_no   | job         | sal       |
 *              +-----------+-------------+-----------|
 *              | 10        | CLERK       | 800.00    |
 *              +-----------+-------------+-----------+
 *
 *              +-----------+-------------+-----------+
 *              | 20        | CLERK       | 5612.00   |
 *              +-----------+-------------+-----------|
 *              | 20        | PRESIDENT   | 5624.00   |
 *              +-----------+-------------+-----------+
 *              | 20        | MANAGER     | 3513.00   |
 *
 *              +-----------+-------------+-----------|
 *              | 30        | CLERK       | 4612.00   |
 *              +-----------+-------------+-----------|
 *              | 30        | PRESIDENT   | 6852.00   |
 *              +-----------+-------------+-----------+
 *
 *       案例三：找出每个部门的最高薪资，要求显示薪资大于2900的数据
 *             方式一：这种方式查询效率低
 *                   第一步: 找出每个部门的最高薪资 SELECT dept_no,MAX(sal) FROM t_emp GROUP BY dept_no;
 *                   第二步: 找出薪资大于2900的数据 SELECT dept_no,MAX(sal) FROM t_emp GROUP BY dept_no HAVING MAX(sal)>2900;
 *             方式二：这种方式查询效率较高（能够使用 WHERE 语句过滤的，推荐使用 WHERE 语句）
 *                    SELECT dept_no,MAX(sal) FROM t_emp WHERE sal > 2900 GROUP BY dept_no;
 *
 *       案例四：找出每个部门的平均薪资，并显示平均薪资大于2000的数据
 *              第一步：找出每个部门的平均薪资  SELECT dept_no,AVG(sal) FROM t_emp GROUP BY dept_no;
 *              第二步：薪资大于2000的数据     SELECT dept_no,AVG(sal) FROM t_emp GROUP BY dept_no HAVING AVG(sal) > 2000;
 *                                         // AVG(sal)是分组函数不能写进where语句，所以只能使用having
 * ---------------------------------------------------------------------------------------------------------------
 * 三、with rollup
 *       GROUP BY 中使用 WITH ROLLUP：
 *       使用 WITH ROLLUP 关键字之后，在所有查询出的分组记录之后增加一条记录，该记录计算查询出的所有记录的总和，即统计记录数量。
 *
 *       SELECT ename,AVG(sal),SUM(comm) FROM t_emp GROUP BY ename WITH ROLLUP
 *       +-----------+-------------------+---------------+
 *       | ename     | avg(total_amount) | sum(order_no) |
 *       +-----------+-------------------+---------------+
 *       | 许致远     | 526               | 3513.00       |
 *       +-----------+-------------------+---------------|
 *       | 龙震南     | 663               | 4612.00       |
 *       +-----------+-------------------+---------------|
 *       | 朱杰宏     | 408               | 6852.00       |
 *       +-----------+-------------------+---------------+
 *       | 董晓明     | 867               | 4565.00       |
 *       +-----------+-------------------+---------------+
 *       | null      | 2464              | 19542.00      |  // 可以看到这多出来的一行便是使用WITH ROLLUP关键字统计的结果
 *       +-----------+-------------------+---------------+
 *
 *       注意： 当使用ROLLUP时，不能同时使用ORDER BY子句进行结果排序，即ROLLUP和ORDER BY是互相排斥 的。
 * ---------------------------------------------------------------------------------------------------------------
 * 四、其他的聚合函数(注意：以下函数可以在group by中使用)
 *     1、GROUP_CONCAT() 将组中的字符串连接返回一个字符串
 *        [1]、SELECT GROUP_CONCAT(ename) FROM t_emp
 *             -- 默认连接字符','  结果为 [许致远,龙震南,朱杰宏,董晓明,许致远]
 *        [2]、SELECT GROUP_CONCAT( CONCAT_WS('_', ename, id) ) FROM t_emp
 *             -- 使用单行处理函数进行字符串拼接后再将组中字符串连接  结果为 [许致远_1,龙震南_2,朱杰宏_3,董晓明_4,许致远_5]
 *        [3]、SELECT GROUP_CONCAT(DISTINCT ename) FROM t_emp
 *             -- 去除组内重复数据  结果为 [许致远,龙震南,朱杰宏,董晓明]
 *        [4]、SELECT GROUP_CONCAT(DISTINCT ename ORDER BY ename DESC) FROM t_emp
 *             -- 去除组内重复数据并倒序排序  结果为 [龙震南,许致远,董晓明,朱杰宏]
 *        [5]、SELECT GROUP_CONCAT(DISTINCT ename ORDER BY ename DESC SEPARATOR ';') FROM t_emp
 *             -- 去除组内重复数据并倒序排序而且使用';'作为连接字符  结果为 [龙震南;许致远;董晓明;朱杰宏]
 *
 *     2、JSON_ARRAYAGG(expression)	用于将查询结果中的多个行合并为一个JSON数组。
 *        [1]、SELECT JSON_ARRAYAGG( province ) FROM t_emp;
 *             -- 查询省份结果为: ["北京", "北京", "太原", "河南省", "湖北省", "湖北省"]
 *        [2]、SELECT JSON_ARRAYAGG( province ) FROM t_emp GROUP BY province;
 *             -- 查询去重省份结果为：["北京", "北京"]
 *                                 ["太原"]
 *                                 ["河南省"]
 *                                 ["湖北省", "湖北省"]
 *
 *     3、JSON_OBJECTAGG(key, value)	用于创建一个JSON对象，其中包含指定的键值对。
 *        [1]、SELECT JSON_OBJECTAGG(id, ename) FROM t_emp;
 *             -- 查询结果为：{"1": "许致远", "2": "龙震南", "3": "朱杰宏"}
 *        [2]、SELECT JSON_OBJECTAGG(id, ename) FROM t_emp GROUP BY id
 *             -- 查询结果为：{"1": "许致远"}
 *                          {"2": "龙震南"}
 *                          {"3": "朱杰宏"}
*/
class D4_1多行处理函数 {}
