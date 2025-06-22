/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-20 22:50:00
 */
/* 一、窗口函数（Window Functions）
 *        窗口函数（Window Functions）是MySQL8.0版本引入的一项强大功能。它允许在查询中对结果集上执行计算，而无需将数据分组到多个输出行中。
 *     通常窗口函数（Window Functions）是与 OVER() 子句一起使用，以指定数据窗口，即窗口函数将要在其上执行计算的行集。
 *        简单来说，窗口函数的作用类似于在查询中对数据进行分组，每个分组即是一个窗口，这和使用聚合函数时的 group by 分组类似。
 *     但与聚合函数不同的地方是：不同的是，Group By分组操作会把分组的结果聚合成一条记录，而窗口函数会对每一条数据进行计算，每一行返回一个结果
 *
 *        窗口函数能够实现诸如排名、累计和移动平均等操作，非常适合数据分析任务。
 *        判断一个函数是不是窗口函数只需要盯着是否有 over 关键字即可。
 *
 *     1、语法格式：
 *               SELECT
 *                  窗口函数([参数]) OVER(
 *                     [PARTITION BY <分组列：`字段1`, `字段2`, ...>]
 *                     [ORDER BY <排序列：`字段3`, `字段4` ASC/DESC>]
 *                     [窗口框架：ROWS BETWEEN 开始行 AND 结束行]
 *                  ) [AS `别名`]
 *               FROM `表名`;
 *        其中，`<窗口函数>`可以是RANK()、DENSE_RANK()、ROW_NUMBER()等函数，
 *        `PARTITION BY`用于将 结果集 分区，`ORDER BY`用于指定排序列，`<窗口框架>`用于定义窗口范围。
 *
 *       需要注意：
 *          SQL标准规定：
 *             当有 ORDER BY 时，默认窗口框架是 RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW，       框架范围：动态扩展(分区开始->当前行)
 *             当无 ORDER BY 时，默认窗口框架是 ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING，框架范围：固定(整个分区)
 *           设计逻辑：
 *              有排序时，假设您可能想计算"到目前为止"的累计值
 *              无排序时，假设您想查看整个分区的值
 *
 *     2、MySQL8支持以下几类窗口函数：
 *        序号函数：用于为窗口内的每一行生成一个序号。                   例如 ROW_NUMBER()、RANK()、DENSE_RANK() 等。
 *        分布函数：用于计算窗口内的每一行在整个分区中的相对位置。         例如 PERCENT_RANK()、CUME_DIST()、NTILE() 等。
 *        偏移（前后取值）函数：用于获取窗口内的当前行的前后某一行的值。    例如 LAG()、LEAD() 等。
 *        头尾函数：用于获取窗口内的第一行或最后一行的值。                例如 FIRST_VALUE()、LAST_VALUE()、NTH_VALUE() 等。
 *        聚合函数窗口化：用于计算窗口内的某个字段的聚合值。              例如 SUM()、AVG()、MIN()、MAX() 等。
 *
 *     3、窗口函数示例
 *        ①、序号函数
 *           ROW_NUMBER(): 计算每行的序号，序号是连续的。【每一个分区内序号重新计算】
 *           <1>、RANK(): 计算每行的排名，如果有相同值会跳过排名。
 *               【如：当 province='东莞'，sal='54929.11' 值相同的行数据，排名是相同。对于下一行不相同的行数据排名是不连续，会跳过重复的行数值】
 *           <2>、DENSE_RANK(): 类似RANK()，但是不跳过排名。
 *               【如：当 province='东莞'，sal='54929.11' 值相同的行数据，排名是相同。对于下一行不相同的行数据排名是连续】
 *           示例：SELECT id, ename, sal, province,
 *                       ROW_NUMBER() OVER(PARTITION BY `province` ORDER BY `sal` DESC) as row_number_sal,
 *                       RANK() OVER(PARTITION BY `province` ORDER BY `sal` DESC) as rank_sal,
 *                       DENSE_RANK() OVER(PARTITION BY `province` ORDER BY `sal` DESC) as dense_rank_sal
 *                FROM t_emp;
 *           +----+----------------+----------+-----------+----------------+----------+----------------+
 *           | id | ename          | sal      | province  | row_number_sal | rank_sal | dense_rank_sal |
 *           +----+----------------+----------+-----------+----------------+----------+----------------+
 *           |  4 | JONES          |  2850.00 | 上海市     |              1 |        1 |              1 |
 *           |  3 | WARD           |  1250.00 | 上海市     |              2 |        2 |              2 |
 *           | 10 | Saito Takuya   | 59816.50 | 东莞       |              1 |        1 |              1 |
 *           |  7 | Inoue Momoka   | 54929.11 | 东莞       |              2 |        2 |              2 |
 *           |  8 | Cheng Chi Ming | 54929.11 | 东莞       |              3 |        2 |              2 |
 *           |  9 | Tiffany Stone  | 28986.97 | 东莞       |              4 |        4 |              3 |
 *           |  1 | SMITH          |   800.00 | 北京市     |              1 |        1 |              1 |
 *           |  6 | Gong Jiehong   | 44757.87 | 深圳       |              1 |        1 |              1 |
 *           |  5 | Yau Wai Yee    | 31047.97 | 深圳       |              2 |        2 |              2 |
 *           |  2 | ALLEN          |  1600.00 | 深圳       |              3 |        3 |              3 |
 *           +----+----------------+----------+-----------+----------------+----------+----------------+
 *        ②、分布函数
 *           <1>、PERCENT_RANK(): 计算当前行在分区中的百分比排名。计算公式：(当前行的 RANK() - 1) / (分区总行数 - 1)
 *               【如：当 province='东莞'，第一行数据：(1-1)/(4-1)=0；第二行数据：(2-1)/(4-1)=0.3~；
 *                                       第三行数据：第三行数据排名为 2，所以(2-1)/(4-1)=0.3~；第四行数据：(4-1)/(4-1)=0
 *                   该窗口函数适于用途：分析薪资在部门内的相对位置、快速识别前10%、后20%的学生、确定金牌/银牌销售（如排名前15%为金牌）】
 *           <2>、CUME_DIST(): 计算当前行在分区中的累积分布。计算公式：(≤当前行值的行数) / (分区总行数)
 *               【如：当 province='东莞'，第一行数据：由于脚本中是倒序，应该查询的是≥59816.50值的行数，只有一条，所以 1/4=0.25；
 *                                       第二行数据：查询的是≥54929.11值的行数，存在三条，所以 3/4=0.75；第三行数据：3/4=0.75；第四行数据：4/4=1
 *                    这种窗口函数特别适合用来做统计结果分析，结合当前例子: ≥59816.50薪资的比例占到 25%，≥54929.11薪资比例占到 75%......】
 *           <3>、NTILE(): 将结果集分成n个桶，并为每行分配桶编号。
 *               【将所有员工根据省份分组，按薪水（sal）从高到低切分为3组，并获得员工自己所在组的序号。
 *                 如：当 province='东莞'，分区中存在四条数据，但还是只会是切分 3组】
 *           示例：SELECT id, ename, sal, province,
 *                       PERCENT_RANK() OVER(PARTITION BY `province` ORDER BY `sal` DESC) as percent_rank_sal,
 *                       CUME_DIST() OVER(PARTITION BY `province` ORDER BY `sal` DESC) as cume_dist_sal,
 *                       NTILE(3) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as ntile_sal
 *                FROM t_emp;
 *           +----+----------------+----------+-----------+--------------------+--------------------+-----------+
 *           | id | ename          | sal      | province  | percent_rank_sal   | cume_dist_sal      | ntile_sal |
 *           +----+----------------+----------+-----------+--------------------+--------------------+-----------+
 *           |  4 | JONES          |  2850.00 | 上海市     |                  0 |                0.5 |         1 |
 *           |  3 | WARD           |  1250.00 | 上海市     |                  1 |                  1 |         2 |
 *           | 10 | Saito Takuya   | 59816.50 | 东莞       |                  0 |               0.25 |         1 |
 *           |  7 | Inoue Momoka   | 54929.11 | 东莞       | 0.3333333333333333 |               0.75 |         1 |
 *           |  8 | Cheng Chi Ming | 54929.11 | 东莞       | 0.3333333333333333 |               0.75 |         2 |
 *           |  9 | Tiffany Stone  | 28986.97 | 东莞       |                  1 |                  1 |         3 |
 *           |  1 | SMITH          |   800.00 | 北京市     |                  0 |                  1 |         1 |
 *           |  6 | Gong Jiehong   | 44757.87 | 深圳       |                  0 | 0.3333333333333333 |         1 |
 *           |  5 | Yau Wai Yee    | 31047.97 | 深圳       |                0.5 | 0.6666666666666666 |         2 |
 *           |  2 | ALLEN          |  1600.00 | 深圳       |                  1 |                  1 |         3 |
 *           +----+----------------+----------+-----------+--------------------+--------------------+-----------+
 *        ③、偏移（前后取值）函数
 *           <1>、LAG(): 返回当前行之前的值。
 *               【如：当 province='东莞'，返回的是LAG()中指定的往前偏移量行数的值
 *                    第一行数据：由于是第一行数据，往前的偏移量已经到头了，所以值为 NULL；
 *                    第二行数据：这里示例的是第一行数据；第三行数据：这里示例的是第二行数据；第四行数据：这里示例的是第三行数据】
 *           <2>、LEAD(): 返回当前行之后的值。
 *               【如：当 province='东莞'，返回的是LAG()中指定的往后偏移量行数的值
 *                    第一行数据：这里示例的是第一行数据；第二行数据：这里示例的是第三行数据；第三行数据：这里示例的是第四行数据；
 *                    第四行数据：由于是第四行数据，往后的偏移量已经到头了，所以值为 NULL】
 *           示例：SELECT id, ename, sal, comm, job, province,
 *                       LAG(sal,1) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as lag_sal,
 *                       LEAD(sal,1) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as lead_sal
 *                FROM t_emp;
 *           +----+----------------+----------+--------+-----------------+-----------+----------+----------+
 *           | id | ename          | sal      | comm   | job             | province  | lag_sal  | lead_sal |
 *           +----+----------------+----------+--------+-----------------+-----------+----------+----------+
 *           |  4 | JONES          |  2850.00 |   NULL | 产品质量部        | 上海市    |     NULL |  1250.00 |
 *           |  3 | WARD           |  1250.00 | 846.75 | 产品质量部        | 上海市    |  2850.00 |     NULL |
 *           | 10 | Saito Takuya   | 59816.50 | 597.47 | Engineering     | 东莞      |     NULL | 54929.11 |
 *           |  7 | Inoue Momoka   | 54929.11 | 338.78 | Research        | 东莞      | 59816.50 | 54929.11 |
 *           |  8 | Cheng Chi Ming | 54929.11 | 892.20 | Marketing       | 东莞      | 54929.11 | 28986.97 |
 *           |  9 | Tiffany Stone  | 28986.97 | 579.85 | 产品质量部        | 东莞      | 54929.11 |     NULL |
 *           |  1 | SMITH          |   800.00 |  13.32 | 信息技术部        | 北京市    |     NULL |     NULL |
 *           |  6 | Gong Jiehong   | 44757.87 | 345.68 | Human resource  | 深圳      |     NULL | 31047.97 |
 *           |  5 | Yau Wai Yee    | 31047.97 | 266.68 | Purchasing      | 深圳      | 44757.87 |  1600.00 |
 *           |  2 | ALLEN          |  1600.00 | 122.44 | 行政管理部        | 深圳      | 31047.97 |     NULL |
 *           +----+----------------+----------+--------+-----------------+-----------+----------+----------+
 *        ④、头尾函数
 *           <1>、FIRST_VALUE(): 返回窗口内的第一行的值。
 *               【如：当 province='东莞'，分区值全都是分区的第一行的数据值】
 *           <2>、LAST_VALUE(): 返回窗口内的最后一行的值。
 *               【注意⚠️：SQL规定：无 ORDER BY 时，默认窗口框架是 ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING，
 *                                [框架范围]是整个分区，所以这里取得的最后一行数据值即为整个分区下的最后一行数据值
 *                        SQL规定：当有 ORDER BY 时，默认窗口框架是 RANGE BETWEEN UNBOUNDED PRECEDING AND CURRENT ROW，
 *                                [框架范围]是分区开始截止到当前行，所以这里取得的最后一行数据值实际上就是当前行的数据值.
 *                        解决方案：为避免返回值为当前行值，可以通过设置窗口框架来实现。
 *                                设置为：ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING，即[框架范围]为整个分区】
 *           <3>、NTH_VALUE(): 返回窗口内第n行的值。
 *               【如：当 province='东莞'，分区值全都是NTH_VALUE()中指定第n行的数据值】
 *           示例：SELECT id, ename, sal, province,
 *                       FIRST_VALUE(sal) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as first_sal,
 *                       LAST_VALUE(sal) OVER(PARTITION BY `province`) as last_sal1,
 *                       LAST_VALUE(sal) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as last_sal2,
 *                       LAST_VALUE(sal) OVER(PARTITION BY `province` ORDER BY `sal` DESC ROWS BETWEEN UNBOUNDED PRECEDING AND UNBOUNDED FOLLOWING) as last_sal3,
 *                       NTH_VALUE(sal,2) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as nth_sal
 *                FROM t_emp;
 *           +----+----------------+----------+-----------+-----------+-----------+-----------+-----------+----------+
 *           | id | ename          | sal      | province  | first_sal | last_sal1 | last_sal2 | last_sal3 | nth_sal  |
 *           +----+----------------+----------+-----------+-----------+-----------+-----------+-----------+----------+
 *           |  4 | JONES          |  2850.00 | 上海市     |   2850.00 |   1250.00 |   2850.00 |   1250.00 |     NULL |
 *           |  3 | WARD           |  1250.00 | 上海市     |   2850.00 |   1250.00 |   1250.00 |   1250.00 |  1250.00 |
 *           | 10 | Saito Takuya   | 59816.50 | 东莞       |  59816.50 |  28986.97 |  59816.50 |  28986.97 |     NULL |
 *           |  7 | Inoue Momoka   | 54929.11 | 东莞       |  59816.50 |  28986.97 |  54929.11 |  28986.97 | 54929.11 |
 *           |  8 | Cheng Chi Ming | 54929.11 | 东莞       |  59816.50 |  28986.97 |  54929.11 |  28986.97 | 54929.11 |
 *           |  9 | Tiffany Stone  | 28986.97 | 东莞       |  59816.50 |  28986.97 |  28986.97 |  28986.97 | 54929.11 |
 *           |  1 | SMITH          |   800.00 | 北京市     |    800.00 |    800.00 |    800.00 |    800.00 |     NULL |
 *           |  6 | Gong Jiehong   | 44757.87 | 深圳       |  44757.87 |   1600.00 |  44757.87 |   1600.00 |     NULL |
 *           |  5 | Yau Wai Yee    | 31047.97 | 深圳       |  44757.87 |   1600.00 |  31047.97 |   1600.00 | 31047.97 |
 *           |  2 | ALLEN          |  1600.00 | 深圳       |  44757.87 |   1600.00 |   1600.00 |   1600.00 | 31047.97 |
 *           +----+----------------+----------+-----------+-----------+-----------+-----------+-----------+----------+
 *        ⑤、聚合函数窗口化
 *           <1>、SUM(): 计算窗口内某个字段的总和。  <2>、AVG(): 计算窗口内某个字段的平均值。
 *           <3>、MIN(): 计算窗口内某个字段的最小值。<4>、MAX(): 计算窗口内某个字段的最大值。 <5>、COUNT(): 计算窗口内某个字段的行数。
 *           示例：SELECT id, sal, province,
 *                       SUM(sal) OVER(PARTITION BY `province`) as sum_sal,
 *                       AVG(sal) OVER(PARTITION BY `province`) as avg_sal,
 *                       MIN(sal) OVER(PARTITION BY `province`) as min_sal,
 *                       MAX(sal) OVER(PARTITION BY `province`) as max_sal,
 *                       COUNT(sal) OVER(PARTITION BY `province` ORDER BY `sal` DESC) as count_sal
 *                FROM t_emp;
 *           +----+----------+-----------+-----------+--------------+----------+----------+-----------+
 *           | id | sal      | province  | sum_sal   | avg_sal      | min_sal  | max_sal  | count_sal |
 *           +----+----------+-----------+-----------+--------------+----------+----------+-----------+
 *           |  3 |  1250.00 | 上海市     |   4100.00 |  2050.000000 |  1250.00 |  2850.00 |         2 |
 *           |  4 |  2850.00 | 上海市     |   4100.00 |  2050.000000 |  1250.00 |  2850.00 |         2 |
 *           |  7 | 54929.11 | 东莞       | 198661.69 | 49665.422500 | 28986.97 | 59816.50 |         4 |
 *           |  8 | 54929.11 | 东莞       | 198661.69 | 49665.422500 | 28986.97 | 59816.50 |         4 |
 *           |  9 | 28986.97 | 东莞       | 198661.69 | 49665.422500 | 28986.97 | 59816.50 |         4 |
 *           | 10 | 59816.50 | 东莞       | 198661.69 | 49665.422500 | 28986.97 | 59816.50 |         4 |
 *           |  1 |   800.00 | 北京市     |    800.00 |   800.000000 |   800.00 |   800.00 |         1 |
 *           |  2 |  1600.00 | 深圳       |  77405.84 | 25801.946667 |  1600.00 | 44757.87 |         3 |
 *           |  5 | 31047.97 | 深圳       |  77405.84 | 25801.946667 |  1600.00 | 44757.87 |         3 |
 *           |  6 | 44757.87 | 深圳       |  77405.84 | 25801.946667 |  1600.00 | 44757.87 |         3 |
 *           +----+----------+-----------+-----------+--------------+----------+----------+-----------+
 *        ⑥、综上：有无 ORDER BY 时，窗口函数的行为会有所不同。主要会影响头尾函数中的 LAST_VALUE() 和聚合函数窗口化系列函数。
 *
 *     4、命名窗口
 *        当一个窗口被多次引用的时候，在每个 over 后面都写一遍定义就显得有些繁琐了，此场景可以通过命名窗口优化：一次定义，多次引用。
 *        定义语法：window [wind_name] as ()，括号内的部分就是原 over 子句后的窗口定义，在用 over 关键字调用窗口时，直接引用窗口名 wind_name 即可：
 *        示例1：SELECT id, ename, sal, province,
 * 					   SUM(sal) over w sum_sal     -- 通过名称 w 引用窗口
 *              FROM t_emp
 *              window w as (partition by wind);   -- 命名窗口定义;
 *        示例2：SELECT id, ename, sal, province,
 * 					   SUM(sal) over(w ORDER BY sal DESC) sum_sal     -- 通过名称 w 引用窗口
 *              FROM t_emp
 *              window w as (partition by wind);   -- 命名窗口定义;
 *
 *     5、窗口函数框架
 *        ①、框架的定义语法：由 frame_units（框架单位）和 frame_extent（框架范围）两子句组成。
 *        ②、框架单位（frame_units 子句）可以有 2 种选择：
 *               rows：通过起始行和结束行来划定框架的范围，边界是明确的一行。具体参考示例 1、2。
 *               range：通过具有相同值的行来划定框架的范围，边界是一个范围，具有相同值的行作为一个整体看待。具体参考示例 3、4。
 *        ③、框架范围（frame_extent 子句）也有两种定义方式：
 *               只定义起始点（frame_start），而终止点（frame_end）默认就会是当前行。
 *               通过 between frame_start and frame_end 子句，同时定义起始点（frame_start）和终止点 （frame_end）。
 *        ④、框架范围的起始点（frame_start）和终止点（frame_end）可以是以下几种：
 * 		         current row：当框架单位是 rows 时，即当前行。当框架单位是 range 时，包含当前行和当前行相同的行（一个范围）。
 * 		         unbound preceding：窗口内第 1 行。
 * 		         unbound following：窗口内最后 1 行。
 * 		         expr preceding：当框架单位是 rows 时，边界是当前行的前 expr 行。
 *                               当框架单位是 range 时，边界是值和 "当前行的值 - expr" 相等的行，
 *                                                   如果当前行的值是 null，那边界就是和当前行相等的行。
 *               expr following：当框架单位是 rows 时，边界是当前行的后 expr 行。
 *                               当框架单位是 range 时，边界是和 "当前行的值 + expr" 相等的行，
 *                                                   如果当前行的值是 null，那边界就是和当前行相等的行。
 *        ⑤、示例 1：SELECT id, ename, sal, province,
 *                          LAST_VALUE(sal*10) OVER(PARTITION BY province ORDER BY sal rows unbounded preceding) AS last_sal
 *                  FROM t_emp;
 *                  框架的定义是 rows unbouned preceding。
 *                  框架单位是 rows（行），框架范围是 unbounded preceding（组内第 1 行）。
 *                  这里采用了仅定义起始点的方式，框架的终止点默认就是当前行（current row），
 *                  定义等同于：rows between unbound preceding and current row
 *           示例 2：SELECT id, ename, sal, province,
 *                         SUM(sal*10) OVER(PARTITION BY province ORDER BY sal rows 1 preceding) AS sum_sal1,
 *                         SUM(sal*10) OVER(PARTITION BY province ORDER BY sal rows between 1 preceding and current row) AS sum_sal2
 *                  FROM t_emp;
 *                  第一个框架的定义是 rows 1 preceding，框架单位是 rows（行），
 *                  第一个框架范围是 1 preceding（当框架单位为 rows 时，1 preceding 代表当前行的前 1 行）.
 *                  第一个框架采用了仅定义起始点的方式，框架的终止点默认就是当前行。
 *                  第二个框架采用了 between 1 preceding and current row 的方式，显式指定了框架的起始和结束范围，效果是相同的。
 *           示例 3：SELECT id, ename, sal, province,
 *                         SUM(sal) OVER(PARTITION BY province ORDER BY sal range 1 preceding) AS sum_sal,
 *                         FIRST_VALUE(sal) OVER(PARTITION BY province ORDER BY sal range 1 preceding) AS first_sal,
 *                         LAST_VALUE(sal) OVER(PARTITION BY province ORDER BY sal range 1 preceding) AS last_sal
 *                  FROM t_emp;
 *                  +----+----------------+----------+-----------+-----------+-----------+----------+
 *                  | id | ename          | sal      | province  | sum_sal   | first_sal | last_sal |
 *                  +----+----------------+----------+-----------+-----------+-----------+----------+
 *                  |  3 | WARD           |  1250.00 | 上海市     |   1250.00 |   1250.00 |  1250.00 |
 *                  |  4 | JONES          |  2850.00 | 上海市     |   2850.00 |   2850.00 |  2850.00 |
 *                  |  9 | Tiffany Stone  | 28986.97 | 东莞       |  28986.97 |  28986.97 | 28986.97 |
 *                  |  7 | Inoue Momoka   | 54929.11 | 东莞       | 109858.22 |  54929.11 | 54929.11 |
 *                  |  8 | Cheng Chi Ming | 54929.11 | 东莞       | 109858.22 |  54929.11 | 54929.11 |
 *                  | 10 | Saito Takuya   | 59816.50 | 东莞       |  59816.50 |  59816.50 | 59816.50 |
 *                  |  1 | SMITH          |   800.00 | 北京市     |    800.00 |    800.00 |   800.00 |
 *                  |  2 | ALLEN          |  1600.00 | 深圳       |   1600.00 |   1600.00 |  1600.00 |
 *                  |  5 | Yau Wai Yee    | 31047.97 | 深圳       |  31047.97 |  31047.97 | 31047.97 |
 *                  |  6 | Gong Jiehong   | 44757.87 | 深圳       |  44757.87 |  44757.87 | 44757.87 |
 *                  +----+----------------+----------+-----------+-----------+-----------+----------+
 *                  框架定义为 range 1 preceding，等价于 range between 1 preceding and current row。
 *                  当框架单位为 range 时，这里的 1 preceding 不再是前 1 行的意思，而是 当前窗口函数【如：sum(sal)】中的sal值 - 1"。
 *                  而 range between 1 preceding and current row 代表值的范围落在区间 [当前sal值-1，sal值] 内所有行。
 *                  示例说明：在 province=‘东莞’ 情况下，
 *                     第一行 sal 值为 28986.97，因此框架包含值在 [28986.97-1, 28986.97] 范围内的所有行，
 *                                             即 只有第一行（28986.97）在此范围内，所以 sum 求和结果为 28986.97。
 * 				       第二行 sal 值为 54929.11，因此框架包含值在 [54929.11-1, 54929.11] 范围内的所有行，
 *                                             即 第二（54929.11）和第三（54929.11）两行，sum 求和结果为 54929.11+54929.11=109858.22。
 * 				       第三行 sal 值为 54929.11，因此框架包含值在 [54929.11-1, 54929.11] 范围内的所有行，
 *                                             即 第二（54929.11）和第三（54929.11）两行，sum 求和结果为 54929.11+54929.11=109858.22。
 * 				       第四行 sal 值为 59816.50，因此框架包含值在 [59816.50-1, 59816.50] 范围内的所有行，
 *                                             即 第四行（59816.50），sum 求和结果为 59816.50。
 *           示例 4：SELECT id, ename, sal, province,
 *                         SUM(sal) OVER(PARTITION BY province ORDER BY sal range 100 preceding) AS sum_sal,
 *                         FIRST_VALUE(sal) OVER(PARTITION BY province ORDER BY sal range 100 preceding) AS first_sal,
 *                         LAST_VALUE(sal) OVER(PARTITION BY province ORDER BY sal range 100 preceding) AS last_sal
 *                  FROM t_emp;
 *                  示例说明：在 province=‘东莞’ 情况下，
 *                     第一行 sal 值为 28986.97，因此框架包含值在 [28986.97-100, 28986.97] 范围内的所有行，
 *                                             即 只有第一行（28986.97）在此范围内，所以 sum 求和结果为 28986.97。
 * 				       第二行 sal 值为 54929.11，因此框架包含值在 [54929.11-100, 54929.11] 范围内的所有行，
 *                                             即 第二（54929.11）和第三（54929.11）两行，sum 求和结果为 54929.11+54929.11=109858.22。
 * 				       第三行 sal 值为 54929.11，因此框架包含值在 [54929.11-100, 54929.11] 范围内的所有行，
 *                                             即 第二（54929.11）和第三（54929.11）两行，sum 求和结果为 54929.11+54929.11=109858.22。
 * 				       第四行 sal 值为 59816.50，因此框架包含值在 [59816.50-100, 59816.50] 范围内的所有行，
 *                                             即 第四行（59816.50），sum 求和结果为 59816.50。
 *
 *     6、LeetCode 窗口函数题目
 *        ①、178. 分数排名 [https://leetcode.cn/problems/rank-scores/description/]
 *        ②、184. 最高工资 [https://leetcode.cn/problems/department-highest-salary/description/]
 *        ③、185. 前三工资 [https://leetcode.cn/problems/department-top-three-salaries/description/]
 */
class D4_3窗口函数 {}
