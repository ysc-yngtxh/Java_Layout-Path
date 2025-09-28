/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-06-29 15:15:00
 */
/* 什么是 WITH 子句
 *   1、定义
 *      WITH 子句是 MySQL 中的一种 SQL 结构，又称为 Common Table Expression (CTE)。
 *      它在不影响原有 SQL 语句的情况下，允许开发人员临时创建一个内存中的结果集，然后对其进行操作。
 *
 *   2、用途
 *      WITH 子句的主要用途是创建一个暂时的结果集，这个结果集在后续的查询中可以多次使用。
 *      WITH 子句主要用于解决查询复杂度高的问题，因为它可以将多次需要的计算结果集存储下来，以便后续的查询可以直接使用。
 *      它还可以帮助我们更好地组织复杂的 SQL 查询，使得代码更加清晰易读。
 *
 *   3、语法
 *      WITH
 *      cte_name1 (column_name1, column_name2, ...) AS (
 *        SELECT column_name1, column_name2, ...
 *        FROM table_name
 *        WHERE condition
 *      ),
 *      cte_name2 (column_name1, column_name2, ...) AS (
 *        SELECT column_name1, column_name2, ...
 *        FROM table_name
 *        WHERE condition
 *      )
 *      SELECT column_name1, column_name2, ...
 *      FROM cte_name1
 *      JOIN cte_name2
 *      WHERE condition;

 *      ①、其中，cte_name 是 WITH 子句的名称；
 *      ②、column_name1、column_name2 等是结果集的列名；
 *      ③、SELECT 子句定义了该结果集的内容；
 *      ④、condition 是查询的过滤条件。
 *
 *   4、示例
 *      SQL 语法规定：WITH 子句必须位于语句开头（定义 CTE），不能嵌套在 IN 或其他子句中。
 *      例如：
 *          错误写法：SELECT *
 *                   FROM table
 *                   WHERE col IN (
 *                       WITH cte AS (SELECT id FROM other_table)
 *                       SELECT id FROM cte  -- 不允许在 IN 内部定义 CTE
 *                   );
 *          正确写法：WITH cte AS (SELECT id FROM other_table)
 *                   SELECT *
 *                   FROM table
 *                   WHERE col IN (SELECT id FROM cte);
 *               
 *
 */
public class W23_WITH语法 {}
