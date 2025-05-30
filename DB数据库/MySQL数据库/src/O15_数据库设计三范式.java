/**
 * @author 游家纨绔
 */
/* 数据库设计三范式
 *     1、什么是设计范式？
 *           数据库三范式（Normal Forms）是关系型数据库设计的基础理论，用于减少数据冗余和提高数据一致性。
 *     ---------------------------------------------------------------------------------------------------------------
 *     2、三范式有哪些？
 *           第一范式（1NF）：原子性
 *               定义：每个字段都是不可再分的原子值
 *               要求：每列都是不可分割的最小数据单元
 *                    每行数据有唯一标识（主键）
 *                    没有重复的列
 *               ❌ 不符合1NF的设计：
 *                   订单表(order_id, 产品列表)
 *                   产品列表："手机, 电脑, 平板"
 *               ✅ 符合1NF的设计：
 *                   订单表(order_id)
 *                   订单产品表(id, order_id, product_name)
 *
 *           第二范式（2NF）：完全依赖
 *               定义：在1NF基础上，非主键字段必须完全依赖于主键（不能存在部分依赖）
 *               要求：表必须符合1NF
 *                    所有非主键列必须完全依赖于整个主键（针对复合主键的情况）
 *                    需要将部分依赖的列拆分到新表
 *               ❌ 不符合2NF的设计（学号和课程ID为复合主键）：
 *                   选课表(学号, 课程ID, 学生姓名, 课程名称, 成绩)
 *                   存在问题：学生姓名只依赖学号，课程名称只依赖课程ID，所以存在部分依赖
 *               ✅ 符合2NF的设计：
 *                   学生表(学号, 学生姓名)
 *                   课程表(课程ID, 课程名称)
 *                   课程成绩表(学号, 课程ID, 成绩)
 *
 *           第三范式（3NF）：直接依赖
 *               定义：在2NF基础上，非主键字段之间不能存在传递依赖
 *               要求：表必须符合2NF
 *                    非主键列必须直接依赖于主键，不能依赖于其他非主键列
 *                    需要消除传递依赖
 *               ❌ 不符合3NF的设计：
 *                   学生表(学号, 姓名, 学院ID, 学院名称, 学院地址)
 *                   存在问题：学院名称和学院地址依赖于学院ID，而学院ID又依赖于学号，所以存在传递依赖
 *               ✅ 符合3NF的设计：
 *                   学生表(学号, 姓名, 学院ID)
 *                   学院表(学院ID, 学院名称, 学院地址)
 *     ----------------------------------------------------------------------------------------------------
 *     3、三范式总结
 *           范式	  要求	           解决的问题
 *           1NF	字段原子性	    消除重复列和可分割字段
 *           2NF	完全依赖主键	       消除部分依赖
 *           3NF	无传递依赖	       消除间接依赖
 */
public class O15_数据库设计三范式 {}
