/**
 * @author 游家纨绔
 */
/*
存储引擎
  1、什么是存储引擎？
        存储引擎这个名字只有在MySQL中存在。(Oracle中有对应的机制，但是不叫作存储引擎。Oracle中没有特殊的名字，就是“表的存储方式”)
     MySQL支持很多存储引擎，每一个存储引擎都对应一种不同的存储方式。每一个存储引擎都有自己的优缺点，需要在合适的时机选择合适的存储引擎
     (mysql默认使用的存储引擎是InnoDB方式。默认采用的字符集是UTF8)

  2、查看当前MySQL支持的存储引擎
       show engines \G;

       mysql5.5.36版本支持的存储引擎有9个

  3、常见的存储引擎
              第一个: MyISAM
             Engine: MyISAM
            Support: YES
            Comment: MyISAM storage engine
       Transactions: NO
                 XA: NO
          Savepoint: NO

         MyISAM这种存储引擎不支持事务。
         MyISAM是MySQL最常用的存储引擎，但是这种存储引擎不是默认的。
         MyISAM采用三个文件组织一张表：
              xxx.frm(存储格式的文件)
              xxx.MYD(存储表中数据的文件)
              XXX.MYI(存储表中索引的文件)
                   优点：可被压缩，节省存储空间，并且可以转换为只读表，提高检索效率。
                   缺点：不支持事务
  ---------------------------------------------------------------------------------------------------
             第二个: InnoDB
            Engine: InnoDB
           Support: DEFAULT
           Comment: Supports transactions, row-level locking, and foreign keys
      Transactions: YES
                XA: YES
         Savepoint: YES

        表的结构存储在xxx.frm文件中
        数据存储在tablespace这样的表空间中(逻辑概念)，无法被压缩，无法转换成只读
        这种InnoDB存储引擎在MySQL数据库崩溃之后提供自动恢复机制。
   InnoDB支持级联删除和级联更新：(外键约束中删除（更新）父表中的一组数据，相对应的子表中约束的数据也会被删除（更新）)

        优点：支持事务、行级锁、外键等。这种引擎数据的安全存储得到保障。
  ---------------------------------------------------------------------------------------------------
             第三个: MEMORY
            Engine: MEMORY
           Support: YES
           Comment: Hash based, stored in memory, useful for temporary tables
      Transactions: NO
                XA: NO
         Savepoint: NO
         优点：因为所有数据和索引都是存储在内存当中的，查询速度快
         缺点：不支持事务。因为所有数据和索引都是存储在内存当中的，所以数据容易丢失（比如说断电数据全没了）。

 */
public class K11存储引擎 {
}
