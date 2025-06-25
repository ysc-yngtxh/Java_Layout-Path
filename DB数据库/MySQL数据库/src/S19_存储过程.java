/**
 * @author 游家纨绔
 * @dateTime 2023-10-07 10:30:00
 * @apiNote TODO 存储过程详解
 */
/* 存储过程
 *    [1]、有时候仅凭 SQL语句可能达不到想要的数据操作目的，有可能需要写一些方法体，通过循环判断等操作最终达到目的。
 *         那么在数据库里实现这种方法体就需要存储过程了，存储过程简单来说，就是为以后的使用而保存的一条或多条 MySQL 语句的集合。
 *         可将其视为批处理文件，虽然他们的作用不仅限于批处理。
 *
 *    [2]、高级示例
 *         • 写一个函数，对一个库里所有的表全部添加一个“status”状态字段
 *         • 删除存储过程
 *           -- 删除之前存在的存储过程
 *           drop procedure if exists proc_init_status;
 *         • 创建存储过程
 *           -- 1、创建名为 proc_init_status 的存储过程
 *           create procedure proc_init_status()
 *           -- 开始存储过程
 *           BEGIN
 *               -- 自定义变量，控制游标循环变量
 *               declare end_flag int default 0;
 *               -- 自定义变量my_name
 *               declare my_name varchar(100);
 *               -- 定义游标并输入结果集
 *               declare album_cursor cursor for
 *                   -- 该sql为查询custom_database库中满足custom_form_表前缀，且不存在status字段的所有表名
 *                   SELECT distinct table_name
 *                   FROM information_schema.columns
 *                   WHERE table_schema = 'custom_database'
 *                         AND table_name LIKE 'custom_form_%'
 *                         AND table_name NOT IN(SELECT table_name
 *                                               FROM information_schema.columns
 *                                               WHERE table_schema = 'custom_database'
 *                                                     AND table_name LIKE 'custom_form_%'
 *                                                     AND column_name = 'status'
 *                                              );
 *               -- 绑定控制变量到游标,游标循环结束自动转1
 *               declare continue handler for not found set end_flag=1;
 *               --  打开游标
 *               open album_cursor;
 *               -- 遍历游标
 *               repeat
 *               -- 获取当前游标指针记录，取出值赋给自定义的变量my_name
 *               fetch album_cursor into my_name;
 *               -- 利用取到的值进行数据库的操作
 *               set @stmt = concat("ALTER TABLE ", my_name, " ADD COLUMN `status` varchar(20) DEFAULT NULL COMMENT '状态'");
 *               -- 定义预处理语句
 *               prepare stmt from @stmt;
 *               -- 执行预处理语句
 *               execute stmt;
 *               -- 输出sql
 *               select @stmt;
 *               -- 删除(释放)定义
 *               deallocate prepare stmt;
 *               -- 根据 end_flag 判断是否结束
 *               until end_flag end repeat;
 *               -- 关闭游标
 *               close album_cursor;
 *           -- 结束
 *           end;
 *         • 调用存储过程
 *           -- 2、调用存储过程
 *           CALL proc_init_status();
 *
 *      注意：这里有一个坑，我也是排查了好久才发现：变量的定义不要和你的select的列的键同名！不然，fetch into 会失败！！！
 *           我之前定义的变量DECLARE table_name VARCHAR(100); 和SELECT DISTINCT table_name同名，都叫table_name ，
 *           导致我一度怀疑我整个存储过程写的有问题，排查两天才发现，特此记录。如果你也遇到fetch into不生效问题，也请长个记性！！
 *
 *
 *    [3]、存储过程用法
 *         (1)、简单示例
 *              create procedure myPro1(in a int, in b int, out sum int)
 *              begin
 *                  set sum = a+b;
 *              end;
 *              -- 语法解析：
 *                 create procedure 用来创建过程；myPro1 用来定义过程名称；
 *                 (in a int, in b int, out sum int) 表示过程的参数，
 *                 其中 in 表示输入参数，默认可以不写；out 表示输出参数。类似于 Java 定义方法时的形参和返回值；
 *                 begin 与end 表示过程主体的开始和结束，相当于 Java 定义方法的一对大括号；
 *                 call用来调用过程。
 *
 *         (2)、存储过程的参数
 *              MySQL 存储过程的参数用在存储过程的定义，共有三种参数类型：
 *                 IN 输入参数：表示调用者向过程传入值（传入值可以是字面量或变量）；
 *                 OUT 输出参数：表示过程向调用者传出值(可以返回多个值)（传出值只能是变量）；
 *                 INOUT输入输出参数：既表示调用者向过程传入值，又表示过程向调用者传出值（值只能是变量）。
 *
 *         (3)、存储过程的变量。
 *              变量定义：declare variable_name [,variable_name...] datatype [default value];
 *                    • declare：用于声明变量的关键字
 *                    • variable_name：表示变量名称；
 *                    • [,variable_name...]：表示可定义多个变量
 *                    • datatype：为 MySQL 的数据类型；
 *                    • default：用于声明默认值;
 *                    • 例如：declare name varchar(20) default 'mac';
 *              变量赋值：SET 变量名 = 表达式值 [,variable_name = expression ...]
 *                      如上面例子的：SET sum = a+b;
 *
 *         (4)、流程控制语句
 *              if 条件语句
 *                 -- 创建过程
 *                 create procedure myPro2(in num int)
 *                 begin
 *                     if num<0 then -- 条件开始
 *                       select '负数';
 *                     elseif num=0 then  -- elseif要连写，中间不需要空格
 *                       select '不是正数也不是负数';
 *                     else
 *                       select '正数';
 *                     end if; -- 条件结束
 *                 end;
 *                 -- 调用过程
 *                 call myPro2(-1);
 *
 *
 *              case 条件语句
 *                   写法一：
 *                        -- 创建过程
 *                        create procedure myPro3(in num int)
 *                        begin
 *                            case -- 条件开始
 *                            when num<0 then select '负数';
 *                            when num=0 then select '不是正数也不是负数';
 *                            else select '正数';
 *                            end case; -- 条件结束
 *                        end;
 *                        -- 调用过程
 *                        call myPro3(1);
 *                   写法二：
 *                        -- 创建过程
 *                        create procedure myPro4(in num int)
 *                        begin
 *                            case num -- 条件开始
 *                            when 1 then select '数值是 1';
 *                            when 2 then select '数值是 2';
 *                            else select '不是 1 也不是 2';
 *                            end case; -- 条件结束
 *                        end;
 *                        -- 调用过程
 *                        call myPro4(3);
 *                   两种 case 语法都可以实现条件判断，但第一种适合范围值判断，而第二种适合确定值判断。
 *
 *
 *              while 循环语句
 *                    -- 创建过程
 *                    create procedure myPro5(out sum int)
 *                    begin
 *                        declare num int default 0;
 *                        set sum = 0;
 *                        while num<10 do -- 循环开始
 *                        set num = num+1;
 *                        set sum = sum+num;
 *                        end while; -- 循环结束
 *                    end;
 *                    -- 调用过程
 *                    call myPro5(@sum);
 *                    -- 查询变量值
 *                    select @sum;
 *
 *
 *              repeat 循环语句
 *                repeat语句的用法和 java中的 do…while 语句类似，都是先执行循环操作，再判断条件。
 *                区别是 repeat表达式值为 false 时才执行循环操作，直到表达式值为 true 停止。
 *                     -- 创建过程
 *                     create procedure myPro6(out sum int)
 *                     begin
 *                         declare num int default 0;
 *                         set sum = 0;
 *                         repeat -- repeat表示循环开始
 *                         set num = num+1;
 *                         set sum = sum+num;
 *                         until num>=10 -- until表示当满足 num>=10 循环结束
 *                         end repeat; -- 结束循环
 *                     end;
 *                     -- 调用过程
 *                     call myPro6(@sum);
 *                     -- 查询变量值
 *                     select @sum;
 *
 *
 *              loop 循环语句
 *                循环语句，用来重复执行某些语句。执行过程中可使用 leave语句或 iterate 跳出循环，也可以嵌套 IF等判断语句。
 *                • leave语句效果相当于 java 中的 break，用来终止循环；
 *                • iterate语句效果相当于 java 中的 continue，用来结束本次循环操作，进入下一次循环。
 *                   -- 创建过程
 *                   create procedure myPro7(out sum int)
 *                   begin
 *                       declare num int default 0;
 *                       set sum = 0;
 *                       loop_sum:loop -- 循环开始，loop_sum 相当于给循环贴个标签(可自定义名称)，方便多重循环时灵活操作。
 *                       set num = num+1;
 *                       set sum = sum+num;
 *                       if num>=10 then
 *                         leave loop_sum;
 *                       end if;
 *                       end loop loop_sum; -- 循环结束
 *                   end;
 *                   -- 调用过程
 *                   call myPro7(@sum);
 *                   -- 查询变量值
 *                   select @sum;
 *
 *         (5)、存储过程的管理
 *              显示存储过程：
 *                  SHOW PROCEDURE STATUS;
 *              显示特定数据库的存储过程：
 *                  SHOW PROCEDURE status where db = 'schooldb';
 *              显示特定模式的存储过程，要求显示名称中包含'my'的存储过程：
 *                  SHOW PROCEDURE status where name like '%my%';
 *              显示存储过程'myPro1'的源码：
 *                  SHOW CREATE PROCEDURE myPro1;
 *              删除存储过程'myPro1'：
 *                  drop PROCEDURE myPro1;
 */
public class S19_存储过程 {}
