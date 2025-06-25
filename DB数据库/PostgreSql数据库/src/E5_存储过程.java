/**
 * @author 游家纨绔
 * @dateTime 2024-12-17 15:40:00
 * @apiNote TODO
 */
/* 一、触发器Trigger，是由事件触发的一种存储过程。
 *     1、在PostgreSQL数据库中，触发器（Trigger）是一种特殊类型的数据库对象，它可以在特定的数据库事件发生时自动执行预定义的操作。
 *        这些事件可以是插入（INSERT）、更新（UPDATE）或删除（DELETE）表中的数据行。
 *        触发器通常用于强制复杂的业务规则、审计更改、同步复制数据到其他表或文件、以及提供复杂的引用完整性等。
 *
 *        触发器Trigger简单示例：更新表时自动设置 updated_at 字段为当前时间戳。
 *            ①、创建一个返回触发器类型的函数
 *                CREATE OR REPLACE FUNCTION trigger_set_updated_at()
 *                RETURNS TRIGGER AS $$
 *                BEGIN
 *                   NEW.updated_at = NOW();
 *                   RETURN NEW;
 *                END;
 *                $$ LANGUAGE plpgsql;
 *
 *                CREATE OR REPLACE FUNCTION：这个命令用来创建一个新的函数，或者替换一个已有的同名函数。
 *                trigger_set_updated_at()：这是函数的名字。
 *                RETURNS TRIGGER：指明这是一个返回触发器类型的函数。触发器函数必须声明为返回 TRIGGER 类型。
 *                AS $$ ... $$：这里的双美元符号（也可以使用其他字符）包围的是函数体，也就是 PL/pgSQL 语言编写的代码块。
 *                BEGIN ... END;：定义了 PL/pgSQL 块，其中包含实际执行的逻辑。
 *                NEW.updated_at = NOW();：这行代码将新行（即更新后即将插入数据库的那一行）的 updated_at 字段设置为当前时间戳。
 *                RETURN NEW;：告诉 PostgreSQL 使用修改后的行（这里是指带有新的 updated_at 时间戳的行）进行更新操作。
 *                LANGUAGE plpgsql;：指定函数体使用的编程语言，在这里是 PL/pgSQL，它是 PostgreSQL 的过程语言。
 *            ②、创建触发器
 *                CREATE TRIGGER set_users_updated_at
 *                BEFORE UPDATE ON users
 *                FOR EACH ROW
 *                EXECUTE FUNCTION trigger_set_updated_at();
 *
 *                CREATE TRIGGER：这是创建触发器的命令。
 *                set_users_updated_at：触发器的名字。
 *                BEFORE UPDATE：指定触发器应该在 UPDATE 操作之前被激活。
 *                ON users：指出触发器关联的表名，这里是 users 表。
 *                FOR EACH ROW：表明这个触发器将在每一行被更新时单独触发，而不是整个操作完成后触发一次。
 *                EXECUTE FUNCTION trigger_set_updated_at();：当触发条件满足时，要执行的函数名。
 *
 *     2、触发器的类型
 *        ①、触发时机
 *            BEFORE触发器：在检查约束和执行实际的数据更改之前触发。
 *            AFTER触发器：在数据更改之后触发。
 *            INSTEAD OF触发器：用于视图，替代标准的数据库操作。当尝试在视图上执行INSERT、UPDATE或DELETE操作时，INSTEAD OF触发器会执行预定义的替代操作。
 *        ②、作用范围
 *            行级触发器（Row-Level Trigger）：对DML语句影响的每一行触发一次。这意味着如果一条DML语句影响了多行数据，触发器函数将为每一行分别执行一次。
 *            语句级触发器（Statement-Level Trigger）：对每个DML语句触发一次，不考虑影响的行数。即使一条DML语句影响了多行数据，触发器函数也只会执行一次。
 *        ③、触发事件
 *            INSERT：向表中插入行时触发。
 *            UPDATE：更新表中行时触发。
 *            DELETE：从表中删除行时触发。
 *            TRUNCATE：截断表时触发。
 *            DDL事件：从PostgreSQL 9.3版本开始，还支持事件触发器（Event Trigger），
 *                    用于处理DDL（数据定义语言）事件，如CREATE、ALTER和DROP操作。
 *        ④、触发器函数的一些特殊变量
 *            NEW：数据类型是RECORD。该变量为行级触发器中的INSERT/UPDATE操作保持新数据行。
 *                 在语句级别的触发器以及DELETE操作，这个变量是null。
 *            OLD：数据类型是RECORD。该变量为行级触发器中的UPDATE/DELETE操作保持新数据行。
 *                 在语句级别的触发器以及INSERT操作，这个变量是null。
 *
 *     3、触发器的创建
 *        ①、创建时指定的事件（insert，update，delete，truncate表），当对表进行相关操作时，会触发表的Trigger。
 *            案例：学生信息表，学生分数表，在删除学生信息的同时，自动删除学生的分数。
 *                 create table student (        create table score (
 *                     id int,                       id int,
 *                     name varchar(32)              student_id int,
 *                 );                                math_score numeric,
 *                                                   english_score numeric,
 *                                                   chinese_score numeric
 *                                               );
 *                 INSERT INTO student VALUES(1, '张三'), (2, '李四');
 *                 INSERT INTO score   VALUES(1, 1, 66, 66, 66), (2, 2, 55, 55, 55);
 *
 *        ②、构建一个删除学生分数的触发器函数
 *            create function trigger_function_delete_student_score()
 *            returns trigger as $$
 *            begin
 *                delete from score where student_id = old.id;
 *                return old;
 *            end;
 *            $$ language plpgsql;
 *
 *        ③、编写触发器，指定在删除某一行学生信息时，触发当前触发器，执行触发器函数
 *            create trigger trigger_student
 *            after
 *            delete
 *            on student
 *            for each row
 *            execute function trigger_function_delete_student_score();
 */
public class E5_存储过程 {}
