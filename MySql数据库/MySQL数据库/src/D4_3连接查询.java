/*
连接查询
1、什么是连接查询？
           在实际开发中，大部分的情况下都不是从单表中查询数据，一般都是多张表联合查询去除最终的结果。
           在实际开发中，一般一个业务都会对应多张表，比如：学生和班级，起码两张表。

     stuno   stuname  classno       classname
     -----------------------------------------------
     1         zs        1       湖北省武汉市江夏区
     2         ls        1       湖北省武汉市江夏区
     学生和班级信息存储到一张表中，结果就像上面一样，数据会存在大量的重复，导致数据的冗(rong三声)余。

2、连接查询的分类
           根据语法出现的年代来划分的，包括：
       SQL92(一些老的DBA可能还在使用这种语法。DBA:DataBase Administrator,数据管理员)
       SQL99(比较新的语法)

           根据表的连接方式来划分，包括：
               内连接：
                   等值连接
                   非等值连接
                   自连接
               外连接：
                   左外连接(左连接)
                   右外连接(右连接)
               全连接：(这个很少用不做了解)

3、在表的连接查询方面有一种现象被称为：笛卡尔积现象。(笛卡尔乘积现象)
           案例：找出每一个员工的部门名称，要求显示员工名和部门名
           emp表
           +-----------+-------------+
           | ename     | dephno      |
           +-----------+-------------+
           | SMITH     | 20          |
           | ALLEN     | 30          |
           | WARD      | 20          |
           | JONES     | 10          |
           +-----------+-------------+
           dept表
           +-----------+-------------+-----------+
           | dephno    | dname       | loc       |
           +-----------+-------------+-----------|
           | 10        | ACCOUNTING  | NEW YORK  |
           | 20        | RESEARCH    | DALLAS    |
           | 30        | SALES       | CHICAGO   |
           | 40        | OPERATIONS  | BOSTON    |
           +-----------+-------------+-----------|

           select e.ename,d.dname from emp e,dept d;
                      表的别名的好处：第一：执行效率高(避免在两张表中查询到相同字段数据时，无法取出正确的需要的数据)。
                                                  第二：可读性好
                      笛卡尔积现象：当这两张表进行连接查询的时候，没有任何条件进行限制，最终的查询结果条数是两张表记录条数的乘积(4 X 4)
           +-----------+-------------+
           | ename     | dname       |
           +-----------+-------------+
           | SMITH     | ACCOUNTING  |
           | SMITH     | RESEARCH    |
           | SMITH     | SALES       |
           | SMITH     | OPERATIONS  |

           | ALLEN     | ACCOUNTING  |
           | ALLEN     | RESEARCH    |
           | ALLEN     | SALES       |
           | ALLEN     | OPERATIONS  |

           | WARD      | ACCOUNTING  |
           | WARD      | RESEARCH    |
           | WARD      | SALES       |
           | WARD      | OPERATIONS  |

           | JONES     | ACCOUNTING  |
           | JONES     | RESEARCH    |
           | JONES     | SALES       |
           | JONES     | OPERATIONS  |
           +-----------+-------------+

                      怎么避免笛卡尔积现象？当然是加条件进行过滤
                             思考：避免了笛卡尔积现象，会减少记录的匹配次数吗？
                                        不会，次数还是16次，只不过显示的是有效记录。
                      案例：找出每一个员工的部门名称，要求显示员工名和部门名
                      select
                         e.ename,d.dname
                      from
                         emp e,dept d
                      where
                         e.dephno = d.dephno;   // sql92语法，以后不用，作为了解
                      +-----------+-------------+
                      | ename     | dname       |
                      +-----------+-------------+
                      | SMITH     | RESEARCH    |

                      | ALLEN     | SALES       |

                      | WARD      | RESEARCH    |

                      | JONES     | ACCOUNTING  |
                      +-----------+-------------+

4、内连接
       等值连接  最大特点是：条件是等量关系。

           案例：查询每个员工的部门名称，要求显示员工名和部门名 。
           SQL92：(太老了，不常用)
                  select
                     e.ename,d.dname
                  from
                     emp e,dept d
                  where
                     e.deptno = d.dephno;

           SQL99：(常用)                       语法：
                  select                           select
                     e.ename,d.dname                  ...
                  from                             from
                     emp e                            A
                  join                             inner join   // inner可以省略的，带着inner目的是可读性好一些
                     dept d                           B
                  on                               on
                     e.deptno = d.dephno;             连接条件
                                                   where
                                                      ...

           SQL99语法结构更清晰一些：表的连接条件和后来的where条件分离了

       非等值连接  最大的特点是：连接条件中的关系是非等量关系
               案例：找出每个员工的工资等级，要求显示员工名、工资、工资等级
           emp表
           +-----------+-------------+
           | ename     | sal         |
           +-----------+-------------+
           | SMITH     | 800.00      |
           | ALLEN     | 3000.00     |
           | WARD      | 2005.00     |
           | JONES     | 1080.00     |
           +-----------+-------------+
           salgrade表
           +-----------+-------------+-----------+
           | grade     | losal       | hisal     |     // 工资等级     员工最低金额    员工最高金额
           +-----------+-------------+-----------|
           | 1         | 700         | 1200      |
           | 2         | 1201        | 1400      |
           | 3         | 1401        | 2000      |
           | 4         | 2001        | 3000      |
           +-----------+-------------+-----------|

           select
              e.ename,e.sal,s.grade
           from
              emp e
           inner join
              salgrade s
           on
              e.sal between s.losal and s.hisal;


        自连接   最大特点是： 一张表看作两张表
               案例：找出每个员工的上级领导，要求显示员工名和对应的领导名
           emp表
           +-----------+-------------+-----------+
           | empno     | ename       | mgr       |      // 员工编号       员工名       员工的上级领导编号
           +-----------+-------------+-----------|
           | 7839      | KING        | NULL      |
           | 4563      | CLARK       | 7263      |
           | 4135      | FORD        | 4563      |
           | 7263      | WARD        | 7839      |
           +-----------+-------------+-----------|
           select
              A.ename,B.ename
           from
              emp A
           inner join
              emp B
           on
              A.mgr=B.empno;      // 这里无法查询出KING的上级领导，因为KING是大老板，没有上级领导，所以只有三组数据



5、外连接
            什么是外连接，和内连接有什么区别？
            内连接：
                       假设A和B表进行连接，使用内连接的话，凡是A表和B表能够匹配上的记录查询出来，这就是内连接。
            AB两张表没有主副之分，两张表是平等的

            外连接：
                       假设A和B表进行连接，使用外连接的话，AB两张表中有一张表示主表，一张表是副表，主要查询主表中的数据，
                       捎带着查询副表，当副表中的数据没有和主表中的数据匹配上，副表自动模拟出null与之匹配。

      左外连接(左连接)：表示左边的这张表是主表、
      右外连接(右连接)：表示右边的这张表是主表、
      左连接有右连接的写法，右连接也会有对应的左连接的写法

      案例：找出每个员工的上级领导
           emp表
           +-----------+-------------+-----------+
           | empno     | ename       | mgr       |      // 员工编号       员工名       员工的上级领导编号
           +-----------+-------------+-----------|
           | 7839      | KING        | NULL      |
           | 4563      | CLARK       | 7263      |
           | 4135      | FORD        | 4563      |
           | 7263      | WARD        | 7839      |
           +-----------+-------------+-----------|
           select
              A.ename '员工表',B.ename '领导表'
           from
              emp A
           left outer join        // outer可以省略，写上去可读性更高
              emp B
           on
              A.mgr = B.empno;    // 加上left语句，就是将emp A表作为主表。所以KING也可以显示出来，emp B表作为副表，没有数据默认null


      案例：找出哪个部门没有员工
           emp表
           +-----------+-------------+
           | ename     | dephno      |
           +-----------+-------------+
           | SMITH     | 20          |
           | ALLEN     | 30          |
           | WARD      | 20          |
           | JONES     | 10          |
           +-----------+-------------+
           dept表
           +-----------+-------------+-----------+
           | dephno    | dname       | loc       |
           +-----------+-------------+-----------|
           | 10        | ACCOUNTING  | NEW YORK  |
           | 20        | RESEARCH    | DALLAS    |
           | 30        | SALES       | CHICAGO   |
           | 40        | OPERATIONS  | BOSTON    |
           +-----------+-------------+-----------|
           select
              d.dept
           from
              emp e
           ringt outer join
              dept d
           on
              e.dephno = d.dephno
           where
              e.name is null;
 */

/**
 * @author 游家纨绔
 */
public class D4_3连接查询 {
}
