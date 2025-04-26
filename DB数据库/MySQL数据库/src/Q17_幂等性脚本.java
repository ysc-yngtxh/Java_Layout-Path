/**
 * @author 游家纨绔
 * @dateTime 2023-09-23 17:50
 * @apiNote TODO
 */
/* 脚本幂等性:
 *     1、概述：在数据库中，幂等性是指执行相同操作多次所产生的结果与执行一次的结果相同。
 *             对于SQL脚本，幂等性意味着无论脚本执行多少次，数据库的状态都会保持一致。
 * ---------------------------------------------------------------------------------------------------------------
 *     2、举例：我有一个.sql文件，如果这个脚本文件中的脚本不是幂等性的，我直接执行这个脚本文件，一旦其中有一条语句报错，
 *             那在这条语句后其他脚本就无法执行，且还无法定位出错语句在哪儿，哪些脚本是执行过的哪些脚本没有执行，对开发人员造成维护困难。
 *             但是如果将脚本改为幂等性的，那么执行中SQL语句就不会出现报错的情况，继而全部脚本可以得到顺利执行
 * ---------------------------------------------------------------------------------------------------------------
 *     3、CRUD数据(update语句天生就支持幂等性)
 *        [1]、获取数据库表下一条插入数据最大值 Id
 *             select max(id+1)... from progress
 *        [2]、使用 insert into 方式插入主键冲突或者数据重复会直接报错，如果我们一次性执行很多脚本
 *             使用"INSERT IGNORE"语句可以确保在插入数据时，如果发生重复键或其他违反唯一性约束的情况，不会报错并终止插入操作，而是忽略这些冲突的数据继续插入。
 *             这样可以避免由于数据冲突导致的插入错误，并继续插入不冲突的数据。
 *             insert ignore into progress(...) values(...)
 *        [3]、在删除数据时，同样使用WHERE子句来匹配特定的条件，确保只有符合条件的数据会被删除。这样可以避免多次执行删除操作所带来的影响。
 *             delete from progress where no=7
 * ---------------------------------------------------------------------------------------------------------------
 *     4、表备份
 *        [1]、如果数据库中不存在名为"progress_20230925"的表，就创建一个新的表，并且该新表的结构与现有的名为"progress"的表完全相同。
 *             这可以方便地基于已有表的结构创建新的表，而无需手动输入表的各个字段和数据类型。
 *             如果数据库中已经存在名为"progress_20230925"的表，则不会执行任何操作，保证不会重复创建相同的表结构。
 *
 *             create table if not exists progress_20230925 like progress;（只复制progress的结构，不带有原表数据）
 *             区别于表的复制：create table progress_20230925 AS SELECT * FROM progress;（复制progress的结构及数据）
 *
 *        [2]、将"progress"表中的所有数据插入到"progress_20230925"表中。
 *             使用 INSERT IGNORE INTO 语句可以确保在插入数据时，如果重复键或其他违反唯一性约束，不会报错并终止插入操作，而是忽略这些冲突的数据继续插入。
 *             这样可以避免由于数据冲突导致的插入错误，并继续插入不冲突的数据。
 *
 *             insert ignore into progress_20230925 select * from progress
 * ---------------------------------------------------------------------------------------------------------------
 *     5、添加字段【set关键字表示赋值】
 *        SET @schema = 'caoyumin';    -- 表所在的数据库模式为caoyumin
 *        SET @table = 'brand';        -- 要操作的表名为brand
 *        SET @col = 'age';            -- 要创建的字段名称为age
 *        SET @sql = (
 *            SELECT
 *                CASE
 *                    WHEN (SELECT count(1) FROM information_schema.COLUMNS WHERE table_schema = @schema AND table_name = @table AND column_name = @col) = 0
 *        			   THEN
 *                        CONCAT('ALTER TABLE ', @TABLE, ' ADD COLUMN ', @col, ' VARCHAR(200) DEFAULT NULL COMMENT "工作内容" AFTER remark;')
 *                    ELSE
 *                        'SELECT 1'
 *                END
 *            );
 *        prepare stmt from @sql;    -- 使用变量@sql的值来准备一个动态SQL语句，并将其分配给stmt
 *        execute stmt;              -- 执行准备好的动态SQL语句
 *        deallocate prepare stmt;   -- 释放准备好的语句
 *
 *        这段SQL代码的目的是在一个名为caoyumin的数据库中的brand表添加一个名为age的列。这个列的数据类型是varchar(200)，默认值为null，并且有一个注释为工作内容。
 *        如果不存在，则添加该列。添加列的语句是使用alter table ... 语句来执行的，意味着在remark列之后添加一个名为 age 的200个字符长度的varchar类型列。
 *        如果列已经存在，则执行 select 1 并返回结果为数字1。
 * ---------------------------------------------------------------------------------------------------------------
 *     6、添加索引
 *        set @schema = 'caoyumin';        -- 表所在的数据库模式为caoyumin
 *        set @table = 'brand';            -- 要操作的表名为brand
 *        set @index = 'idx_unique_code';  -- 要创建的索引名称为idx_unique_code
 *        set @sql = (
 *            SELECT
 *                CASE
 *                    WHEN (SELECT COUNT(1) FROM information_schema.statistics WHERE table_schema = @schema AND table_name = @table AND index_name = @index) = 0
 *        			   THEN
 *                        CONCAT('ALTER TABLE ', @table, ' ADD UNIQUE INDEX ', @index, ' (`brand_name`, `delete_flag`) COMMENT "删除标识、集合编码联合唯一索引" USING BTREE;')
 *                    ELSE
 *                        'select "索引已存在"'
 *                END
 *            );
 *        prepare stmt from @sql;
 *        execute stmt;
 *        deallocate prepare stmt;
 *
 *        整个脚本的作用是检查指定表中是否已经存在一个名为 idx_unique_code 的唯一索引，
 *        如果不存在，则创建该索引。创建索引的语句会将 brand_name 和 delete_flag 这两列作为联合唯一索引，并使用B树索引类型。创建索引语句的注释为'删除标识、集合编码联合唯一索引'。
 *        如果已经存在，则输出 '索引已存在' 作为结果。
 *
 *
 *        还有IF函数写法：if(condition, expr_if_true, expr_if_false)
 *                       -- 当condition条件表达式为true，则返回expr_if_true，否则返回expr_if_false
 *        set @sql = (
 *            SELECT
 *                IF(
 *                    (SELECT COUNT(1) FROM information_schema.statistics WHERE table_schema = @schema AND table_name = @table AND index_name = @index) = 0
 *                    , CONCAT('ALTER TABLE ', @table, ' ADD UNIQUE INDEX ', @index, ' (`brand_name`, `delete_flag`) COMMENT "删除标识、集合编码联合唯一索引" USING BTREE;')
 *                    , 'select "索引已存在"'
 *                  )
 *            );
 *
 */
public class Q17_幂等性脚本 {}
