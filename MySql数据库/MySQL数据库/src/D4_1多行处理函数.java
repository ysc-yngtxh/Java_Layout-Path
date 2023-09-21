/**
 * @author 游家纨绔
 * @dateTime 2023-09-19 13:12
 * @apiNote TODO
 */
/*
一、聚合函数(也叫做：多行处理函数:就是综合多行数据，然后再输出)
        count  计数
        sum    求和
        avg    平均值
        max    最大值
        min    最小值

        记住：所有的分组函数都是对“某一组”数据进行操作的

        例：找出工资总和
           select sum(sal) from emp;
        找出最高工资
           select max(sal) from emp;
        找出最低工资
           select min(sal) from emp;
        找出平均工资
           select avg(sal) from emp;
        找出总人数
           select count(*) from emp;       -- 不是统计某个字段中数据的个数，而是统计总记录条数（和某个字段无关）
           select count(ename) from emp;   -- 表示统计comm字段中不为null的数据总数量

        分组函数自动忽略null
           select sum(comm) from emp where is not null;
           -- 这么写意思是：不是null的津贴(comm)之和。但是不需要后面的where语法，因为分组函数自动忽略null

        例：计算每个员工的年薪
           select ename,(sal+comm)*12 as yearsal from emp;
        注意：因为不是每个员工都有津贴(comm)的，有的员工津贴(comm)为null。但是所有的数据库这样规定，只要有null参与的运算结果一定是null
             但是如果计算出来的员工年薪为null的话，人家早跑了，所以，怎么办呢？--------——————链接到单行处理函数

        例：找出工资高于平均工资的员工
           select ename,sal from emp where sal > avg(sal);
        思考以上的错误信息：无效的使用了分组函数
        原因：SQL语句当中有一个语法规则，分组函数不可直接使用在where字句当中，why？--------——————链接到group by和 having

        TODO SQL中的count(1)、count(*) 与 count(列名) 到底有什么区别？
        count(列名)：只包括列名那一列，在统计结果的时候，会忽略列值为空
                    这里的空不是指空字符串或者0，而是表示null的计数，即某个字段值为NULL时，不统计。
        count(*)：包括了所有的列，相当于行数，在统计结果的时候，不会忽略为NULL的值。
        count(1)：包括了忽略所有列，在统计结果的时候，不会忽略为NULL的值。
                  这个 1 代表什么呢？最开始我以为表示列的下标(1表示第一列)，后来查阅资料发现并不是。
                  这里的 1 是指把每一行的数据用 1 表示，假如我有十行数据，那么我就有十个1，用1代表数据行，最后再统计有多少个 1 就行。
                  ⚠️：这里的 1 只是作为一个常量值，你也可以是count(2)、count(3)、count(4)

                      小曹这个时候就会问：为什么要用 1(常量值) 替代数据行呢？我直接统计原数据行多少条不就行了吗？
                  count(*)原理就是统计原数据行一共有多少行，但是会更耗费资源，因为它们需要从表中取出实际的字段值进行计数。
                  而当我们只关心表的行数而不需要具体的字段值时，可以使用count(1)来获取表的行数。

        综上所述：我是不是只关心表的行数而不需要具体的字段值时，就可以无脑使用 count(1)了呢？
                 当然是不可以，上述情况我们只考虑了在没有索引的情况下，统计条数的使用 count(1) 的性能高
                 但是在有索引情况下，我们统计大数据量的数据时 通过索引统计 比 遍历统计 性能更优
                 因为我们的索引数据结构是 B+Tree 类型，可以通过我的叶子节点深度跟我的节点个数就能知道count值
                 而mysql 中的对 count(*)是进行过优化的，他会先在表中进行根据索引统计，如果没有索引就遍历统计
                 并且，count(*)是官方推荐写法，建议使用！！！


        性能对比：
           列名为主键  -- count(列名)会比count(1)快，因为根据主键索引(聚集索引)查询条数
           列名不为主键 -- count(1)会比count(列名)快
           如果表多个列并且没有主键，则 count（1） 的执行效率优于 count（*）
           如果有主键，则 select count（主键）的执行效率是最优的

二、group by 和 having

        group by : 按照某个字段或者某些字段进行分组。比如一张表里的工作类型，有重复的工作类型，也有不重复的工作类型。先进行分组，重复的为一组。
        having   : having是对分组之后的数据进行再次过滤,需要注意的是只能操作分组数据

        注意：分组函数一般都会和group by联合使用，这也是为什么他被称为分组函数的原因
        并且任何一个分组函数都是在group by语句执行结束之后才会执行的。
        当一条SQL语句没有group by的话，整张表的数据会自成一组。

        执行顺序：
            select      5
            ...
            from        1
            ...
            where       2
            ...
            group by    3
            ...
            having      4
            ...
            order by    6
            ...

        找出工资高于平均工资的员工
           select ename,sal from emp where sal > avg(sal);
        TODO 注意：这样写是错误的。因为avg(sal)是属于分组函数，但是在执行where的时候，并没有执行分组语句group by.

        第一步：select avg(sal) from emp;  // from后面其实有group by，只是缺省了。
        第二步：select ename,sal from emp where sal > 表给的实际数据;
        合成一步：select ename,sal from emp where sal > (select avg(sal) from emp); // 小括号优先级高，所以先执行小括号里的


        案例：找出每个工作岗位的最高薪资
           select ename,job,max(sal) from emp group by job;  // 语法错误
        注意：以上在MySQL当中，查询结果是有的，但是结果没有意义，因为ename是随机的。在Oracle数据库当中会报错。语法错误。
        Oracle的语法规则比MySQL语法规则严谨
        记住一条规则：当一条语句中有group by的话，select后面只能跟分组函数和参与分组的字段


        案例：找出每个部门的不同工作岗位的最高薪资
           select deptno,job,max(sal) from emp group by deptno,job;
        +-----------+-------------+-----------+
        | deptno    | job         | sal       |
        +-----------+-------------+-----------|
        | 10        | CLERK       | 800.00    |
        +-----------+-------------+-----------+

        +-----------+-------------+-----------+
        | 20        | CLERK       | 5612.00   |
        +-----------+-------------+-----------|
        | 20        | PRESIDENT   | 5624.00   |
        +-----------+-------------+-----------+
        | 20        | MANAGER     | 3513.00   |

        +-----------+-------------+-----------|
        | 30        | CLERK       | 4612.00   |
        +-----------+-------------+-----------|
        | 30        | PRESIDENT   | 6852.00   |
        +-----------+-------------+-----------+

        案例：找出每个部门的最高薪资，要求显示薪资大于2900的数据
        第一步：找出每个部门的最高薪资    select dephno,max(sal) from emp group by dephno;
        第二步：找出薪资大于2900的数据   select dephno,max(sal) from emp group by dephno having max(sal) > 2900;  //这种方式效率低
        select dephno,max(sal) from emp where sal > 2900 group by dephno;   //效率较高，建议能够使用where过滤的尽量使用where

        案例：找出每个部门的平均薪资 ，并显示薪资大于2000的数据
        第一步：找出每个部门的平均薪资    select dephno,avg(sal) from emp group by dephno;
        第二步：薪资大于2000的数据       select dephno,avg(sal) from emp group by dephno having avg(sal) > 2000;
                                     // avg(sal)是分组函数不能写进where语句，所以只能使用having

三、with rollup
        GROUP BY中使用WITH ROLLUP：
        使用WITH ROLLUP关键字之后，在所有查询出的分组记录之后增加一条记录，该记录计算查询出的所有记录的总和，即统计记录数量。

        select subject,avg(total_amount),sum(order_no) FROM t_order GROUP BY subject WITH ROLLUP
        +-----------+-------------------+---------------+
        | subject   | avg(total_amount) | sum(order_no) |
        +-----------+-------------------+---------------+
        | 许致远     | 526               | 3513.00       |
        +-----------+-------------------+---------------|
        | 龙震南     | 663               | 4612.00       |
        +-----------+-------------------+---------------|
        | 朱杰宏     | 408               | 6852.00       |
        +-----------+-------------------+---------------+
        | 董晓明     | 867               | 4565.00       |
        +-----------+-------------------+---------------+
        | null      | 2464              | 19542.00      |  // 可以看到这多出来的一行便是使用WITH ROLLUP关键字统计的结果
        +-----------+-------------------+---------------+

        注意： 当使用ROLLUP时，不能同时使用ORDER BY子句进行结果排序，即ROLLUP和ORDER BY是互相排斥 的。

四、其他的聚合函数(注意：以下函数可以在group by中使用)
   1、GROUP_CONCAT()	将组中的字符串连接返回一个字符串
      [1]、select GROUP_CONCAT(name) from t_order
           -- 默认连接字符','  结果为  许致远,龙震南,朱杰宏,董晓明,许致远
      [2]、select GROUP_CONCAT( CONCAT_WS('_', name, id) ) from t_order
           -- 使用单行处理函数进行字符串拼接后再将组中字符串连接  结果为  许致远_1,龙震南_2,朱杰宏_3,董晓明_4,许致远_5
      [3]、select GROUP_CONCAT(DISTINCT name) from t_order
           -- 去除组内重复数据  结果为  许致远,龙震南,朱杰宏,董晓明
      [4]、select GROUP_CONCAT(DISTINCT name ORDER BY name DESC) from t_order
           -- 去除组内重复数据并倒序排序  结果为  龙震南,许致远,董晓明,朱杰宏
      [5]、select GROUP_CONCAT(DISTINCT name ORDER BY name DESC SEPARATOR ';') from t_order
           -- 去除组内重复数据并倒序排序而且使用';'作为连接字符  结果为  龙震南;许致远;董晓明;朱杰宏

   2、JSON_ARRAYAGG(expression)	用于将查询结果中的多个行合并为一个JSON数组。
      [1]、select JSON_ARRAYAGG( province ) from t_order;
           -- 查询省份结果为: ["北京", "北京", "太原", "河南省", "湖北省", "湖北省"]
      [2]、select JSON_ARRAYAGG( province ) from t_order group by province;
           -- 查询去重省份结果为：["北京"]
                               ["太原"]
                               ["河南省"]
                               ["湖北省"]

   3、JSON_OBJECTAGG(key, value)	用于创建一个JSON对象，其中包含指定的键值对。
      [1]、select JSON_OBJECTAGG(id, name) from t_order;
           -- 查询结果为：{"1": "许致远", "2": "龙震南", "3": "朱杰宏"}
      [2]、select JSON_OBJECTAGG(id, title) from tb_sku group by id
           -- 查询结果为：{"1": "许致远"}
                        {"2": "龙震南"}
                        {"3": "朱杰宏"}
*/
class D4_1多行处理函数 {}