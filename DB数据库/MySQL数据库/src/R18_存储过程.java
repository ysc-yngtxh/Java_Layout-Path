/**
 * @author 游家纨绔
 * @dateTime 2023-10-07 10:27
 * @apiNote TODO 存储过程详解
 */
/*
存储过程
   [1]、有时候仅凭 SQL语句可能达不到想要的数据操作目的，有可能需要写一些方法体，通过循环判断等操作最终达到目的。
        那么在数据库里实现这种方法体就需要存储过程了，存储过程简单来说，就是为以后的使用而保存的一条或多条 MySQL 语句的集合。
        可将其视为批处理文件，虽然他们的作用不仅限于批处理。

   [2]、示例
        • 写一个函数，对一个库里所有的表全部添加一个“status”状态字段
        • 删除存储过程
        -- 删除之前存在的存储过程
        drop procedure if exists proc_init_status;
        • 创建存储过程
        -- 1、创建名为 proc_init_status 的存储过程
        create procedure proc_init_status()
        -- 开始存储过程
        BEGIN
        -- 自定义变量，控制游标循环变量
        declare end_flag int default 0;
        -- 自定义变量my_name
        declare my_name varchar(100);
        --  定义游标并输入结果集
        declare album_cursor cursor for
                            -- 该sql为查询dindo_custom库中满足custom_form_表前缀，且不存在status字段的所有表名
                            SELECT distinct table_name FROM
                            information_schema.columns
                            WHERE
                            table_schema = 'dindo_custom'
                            AND table_name LIKE 'custom_form_%'
                            AND table_name NOT IN(SELECT table_name
                                                  FROM information_schema.columns
                                                  WHERE table_schema = 'dindo_custom'
                                                        AND table_name LIKE 'custom_form_%'
                                                        AND column_name = 'status'
                                                 );
        -- 绑定控制变量到游标,游标循环结束自动转1
        declare continue handler for not found set end_flag=1;
        --  打开游标
        open album_curosr;
        -- 遍历游标
        repeat
        -- 获取当前游标指针记录，取出值赋给自定义的变量my_name
        fetch album_curosr into my_name;
        -- 利用取到的值进行数据库的操作
        set @stmt = concat("ALTER TABLE ",my_name ," ADD COLUMN `status` varchar(20) DEFAULT NULL COMMENT '状态'");
        -- 定义预处理语句
        prepare stmt from @stmt;
        -- 执行预处理语句
        execute stmt;
         -- 输出sql
        select @stmt;
        -- 删除(释放)定义
        deallocate prepare stmt;
        -- 根据 end_flag 判断是否结束
        until end_flag end repeat;
        -- 关闭游标
        close album_curosr;
        -- 结束
        end;
注意： 这里有一个坑，我也是排查了好久才发现：变量的定义不要和你的select的列的键同名！不然，fetch into 会失败！！！
我之前定义的变量DECLARE table_name VARCHAR(100);和SELECT DISTINCT table_name同名，都叫table_name ，导致我一度怀疑我整个存储过程写的有问题，排查两天才发现，特此记录。
如果你也遇到fetch into不生效问题，也请长个记性！！！
• 调用存储过程
-- 2、调用
CALL proc_init_status();
存储过程语法解析
• 再来一个简单示例
-- 创建存储过程
create procedure mypro(in a int, in b int, out sum int)
begin
set sum = a+b;
end;
create procedure 用来创建过程；
mypro 用来定义过程名称；
(in a int, in b int, out sum int) 表示过程的参数，其中 in 表示输入参数，out 表示输出参数。类似于 Java 定义方法时的形参和返回值；
begin 与end 表示过程主体的开始和结束，相当于 Java 定义方法的一对大括号；
call用来调用过程，@s 是用来接收过程输出参数的变量
存储过程的参数
MySQL 存储过程的参数用在存储过程的定义，共有三种参数类型：
   IN 输入参数：表示调用者向过程传入值（传入值可以是字面量或变量）；
   OUT 输出参数：表示过程向调用者传出值(可以返回多个值)（传出值只能是变量）；
   INOUT输入输出参数：既表示调用者向过程传入值，又表示过程向调用者传出值（值只能是变量）。
存储过程根据参数可分为四种类别：
   1).没有参数的过程；
   2).只有输入参数的过程；
   3).只有输出参数的过程；
   4).包含输入和输出参数的过程。
变量
MySQL 中的存储过程类似 java 中的方法。
既然如此，在存储过程中也同样可以使用变量。java 中的局部变量作用域是变量所在的方法，而 MySQL 中的局部变量作用域是所在的存储过程。
变量定义
DECLARE variable_name [,variable_name...] datatype [DEFAULT value];
• declare用于声明变量；
• variable_name表示变量名称；
• datatype为 MySQL 的数据类型；
• default用于声明默认值;
• 例如：
declare name varchar(20) default ‘macw’;
变量赋值
SET 变量名 = 表达式值 [,variable_name = expression ...]
如上面例子的：
SET @stmt = CONCAT("ALTER TABLE ",my_name ," ADD COLUMN `status` varchar(20) DEFAULT NULL COMMENT '状态'");
流程控制语句
if 条件语句
IF 语句包含多个条件判断，根据结果为 TRUE、FALSE执行语句，与编程语言中的 if、else if、else 语法类似。
定义存储过程，输入一个整数，使用 if 语句判断是正数还是负数，代码如下所示：
-- 创建过程
create procedure mypro2(in num int)
begin
if num<0 then -- 条件开始
select '负数';
elseif num=0 then
select '不是正数也不是负数';
else
select '正数';
end if;-- 条件结束
end;
-- 调用过程
call mypro2(-1);
运行结果
case 条件语句
case是另一个条件判断的语句，类似于编程语言中的 choose、when语法。MySQL 中的 case语句有两种语法
格式。
定义存储过程，输入一个整数，使用 case 语句判断是正数还是负数，代码如下所示：
-- 创建过程
create procedure mypro3(in num int)
begin
case -- 条件开始
when num<0 then select '负数';
when num=0 then select '不是正数也不是负数';
else select '正数';
end case; -- 条件结束
end;
-- 调用过程
call mypro3(1);
运行结果
定义存储过程，输入一个整数，使用 case 语句判断是 1 还是 2，代码如下所示：
-- 创建过程
create procedure mypro4(in num int)
begin
case num -- 条件开始
when 1 then select '数值是 1';
when 2 then select '数值是 2';
else select '不是 1 也不是 2';
end case; -- 条件结束
end;
-- 调用过程
call mypro4(3);
两种 case 语法都可以实现条件判断，但第一种适合范围值判断，而第二种适合确定值判断。
while 循环语句
while语句的用法和 java中的 while循环类似。
定义存储过程，使用 while 循环输出 1 到 10 的累加和，代码如下所示：
-- 创建过程
create procedure mypro5(out sum int)
begin
declare num int default 0;
set sum = 0;
while num<10 do -- 循环开始
set num = num+1;
set sum = sum+num;
end while; -- 循环结束
end;
-- 调用过程
call mypro5(@sum);
-- 查询变量值
select @sum;
运行结果
repeat 循环语句
repeat语句的用法和 java中的 do…while 语句类似，都是先执行循环操作，再判断条件，区别是 repeat表达
式值为 false时才执行循环操作，直到表达式值为 true停止。
定义存储过程，使用 repeat 循环输出 1 到 10 的累加和，代码如下所示：
-- 创建过程
create procedure mypro6(out sum int)
begin
declare num int default 0;
set sum = 0;
repeat-- 循环开始
set num = num+1;
set sum = sum+num;
until num>=10
end repeat; -- 循环结束
end;
-- 调用过程
call mypro6(@sum);
-- 查询变量值
select @sum;
运行结果
loop 循环语句
循环语句，用来重复执行某些语句。
执行过程中可使用 leave语句或 iterate 跳出循环，也可以嵌套 IF等判断语句。
• leave语句效果相当于 java 中的 break，用来终止循环；
• iterate语句效果相当于 java 中的 continue，用来结束本次循环操作，进入下一次循环。
定义存储过程，使用 loop 循环输出 1 到 10 的累加和，代码如下所示：
-- 创建过程
create procedure mypro7(out sum int)
begin
declare num int default 0;
set sum = 0;
loop_sum:loop-- 循环开始
set num = num+1;
set sum = sum+num;
if num>=10 then
leave loop_sum;
end if;
end loop loop_sum; -- 循环结束
end;
-- 调用过程
call mypro7(@sum);
-- 查询变量值
select @sum;
运行结果
代码中的 loop_sum 相当于给循环贴个标签，方便多重循环时灵活操作。
存储过程的管理
存储过程的管理主要包括：显示过程、显示过程源码、删除过程。
比较简单的方式就是利用 navicat 客户端工具进行管理，鼠标点击操作即可，如下图所示：
显示存储过程
SHOW PROCEDURE STATUS;
显示特定数据库的存储过程
SHOW PROCEDURE status where db = 'schooldb';
显示特定模式的存储过程，要求显示名称中包含“my”的存储过程
SHOW PROCEDURE status where name like '%my%';
显示存储过程“mypro1”的源码
SHOW CREATE PROCEDURE mypro1;
删除存储过程“mypro1”
drop PROCEDURE mypro1;
 */
public class R18_存储过程 {
}
