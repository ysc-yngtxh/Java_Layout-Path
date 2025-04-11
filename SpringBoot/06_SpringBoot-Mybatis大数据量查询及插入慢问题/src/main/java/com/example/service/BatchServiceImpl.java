package com.example.service;

import com.example.mapper.BatchMapper;
import com.example.domain.Student;
import com.google.common.collect.Lists;
import org.apache.ibatis.session.ExecutorType;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-16 10:10
 * @apiNote TODO
 */
@Service
public class BatchServiceImpl implements BatchService {

    @Resource
    private SqlSessionFactory sqlSessionFactory;


    // 不使用批处理，分批进行多值插入
    public void insertForeach(List<Student> list) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // foreach 遇到数量大，性能会遭遇瓶颈。建议：表的列数不超过20，遍历次数不超过100个。
        List<List<Student>> partition = Lists.partition(list, 100); // 按每份100个元素分割
        try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
            BatchMapper batchMapper = sqlSession.getMapper(BatchMapper.class);
            // 使用并行流的方式循环执行，且使用了forEachOrdered，表示并行流是顺序执行
            partition.parallelStream().forEachOrdered(batchMapper::insertForeach);
            sqlSession.commit();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }


    // Mybatis的批处理
    public void batchInsert(List<Student> list) {
        // 1、在使用MyBatis进行批处理时，需要设置SqlSession的 ExecutorType 为 BATCH；
        //    还需要在连接字符串上添加‘rewriteBatchedStatements=true’，告诉MyBatis使用批处理模式来执行 SQL 语句。
        // 2、当MyBatis接收到一批 SQL语句时，不会立即发送到数据库执行。相反，MyBatis会将这些 SQL语句拼接到一个缓冲区中
        // 3、这个缓冲区是有大小限制的，当达到一定阈值或者所有 SQL语句都已被缓存时，需要手动进行提交操作，
        //    将缓存的 SQL语句和参数一起发送到数据库执行。
        //    这个过程是通过 JDBC 的 addBatch() 方法实现的，它允许将多个 SQL 语句添加到一个批次中。
        // 4、相比之下，JDBC的批量插入操作更加原生和高效。使用JDBC的 addBatch() 方法可以将多个SQL语句添加到批处理中，
        //    然后使用 executeBatch() 方法一次性执行这些SQL语句。
        //    这种方式没有额外的框架层级的开销。因此，如果对于批量插入操作的性能要求较高，使用JDBC的批量插入方式可能更为适合。
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        // ExecutorType 是 MyBatis 中的一个枚举，它定义了三种执行器类型：
        //   SIMPLE：这是默认的执行器类型。它为每个语句的执行创建一个新的预处理语句。
        //   REUSE：此执行器类型会重用预处理语句。
        //   BATCH：此执行器类型会重复使用语句和批量更新，主要用于执行大量操作，以提高性能。
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            BatchMapper batchMapper = sqlSession.getMapper(BatchMapper.class);
            for (int i = 0; i < list.size(); i++) {
                batchMapper.batchInsert(list.get(i));
                if (i % list.size() == 0 || i == list.size() - 1) {
                    // flushStatements方法的作用就是将前面所有执行过的INSERT、UPDATE、DELETE语句真正刷新到数据库中。
                    // 底层调用了JDBC的statement.executeBatch()方法。
                    // 需要注意的是，调用这个方法后，之前所有执行过的SQL语句都会被提交，而不能回滚。
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }

    // Mybatis的批处理
    public void batchInsertForeach(List<Student> list) {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        List<List<Student>> partition = Lists.partition(list, 10);
        try (SqlSession sqlSession = sqlSessionFactory.openSession(ExecutorType.BATCH)) {
            BatchMapper batchMapper = sqlSession.getMapper(BatchMapper.class);
            for (int i = 0; i < partition.size(); i++) {
                batchMapper.insertForeach(partition.get(i));
                if (i % partition.size() == 0 || i == partition.size() - 1) {
                    // 此方法的作用就是将前面所有执行过的INSERT、UPDATE、DELETE语句真正刷新到数据库中。
                    // 底层调用了JDBC的statement.executeBatch()方法。
                    // 需要注意的是，调用这个方法后，之前所有执行过的SQL语句都会被提交，而不能回滚。
                    sqlSession.flushStatements();
                }
            }
            sqlSession.commit();
        }
        stopWatch.stop();
        System.out.println(stopWatch.getTotalTimeMillis());
    }
}
