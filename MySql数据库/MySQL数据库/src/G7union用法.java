/**
 * @author 游家纨绔
 */ /*
union（可以将查询结果集相加）

   案例：找出和工作岗位是SALESMAN和MANAGER的员工
         第一种：select ename,job from emp where job = 'SALESMAN' or job = 'MANAGER';

         第二种：select ename,job from emp where job in('SALESMAN','MANAGER');

         第三种：select ename,job from emp where job = 'SALESMAN';
                union
                select ename,job from emp where job = 'MANAGER';

             这里你可能会觉得第三种方法union操作比前两种复杂麻烦。但是如果两张不相干的的表中数据拼接在一起显示呢？
             select ename from emp
             union
             select dname fron dept; // 虽然这个语句的意义不大，但是它却能通过union将两张不相干的表连接并将其查询的数据拼接在一起

             注意：通过union连接的表在select字段上的数量要一致

   两次面试被问到：union 和 union all 的区别
        union会将查询结果集进行去重处理，就是重复的字段会被去除掉。而union all不会被去除
 */
public class G7union用法 {
}
