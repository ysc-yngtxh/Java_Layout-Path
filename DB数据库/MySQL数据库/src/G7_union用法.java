/**
 * @author 游家纨绔
 */
/* union（可以将查询结果集相加）
 *    案例：找出和工作岗位是 SALESMAN 和 MANAGER 的员工
 *         第一种：SELECT ename,job FROM t_emp WHERE job = 'SALESMAN' OR job = 'MANAGER';
 *
 *         第二种：SELECT ename,job FROM t_emp WHERE job IN('SALESMAN','MANAGER');
 *
 *         第三种：SELECT ename,job FROM t_emp WHERE job = 'SALESMAN';
 *                UNION
 *                SELECT ename,job FROM t_emp WHERE job = 'MANAGER';
 *
 *         这里你可能会觉得第三种方法union操作比前两种复杂麻烦。但是如果两张不相干的的表中数据拼接在一起显示呢？
 *             SELECT ename FROM t_emp
 *             UNION
 *             SELECT d_name FROM t_dept; // 虽然这个语句的意义不大，但是它却能通过union将两张不相干的表连接并将其查询的数据拼接在一起
 *             注意：通过union连接的表在select字段上的数量要一致
 *
 *    两次面试被问到：union 和 union all 的区别
 *         union会将查询结果集进行去重处理，去除重复行是基于所有选择的列数据都相同的情况。
 *         而union all不会被去除。
 */
public class G7_union用法 {}
