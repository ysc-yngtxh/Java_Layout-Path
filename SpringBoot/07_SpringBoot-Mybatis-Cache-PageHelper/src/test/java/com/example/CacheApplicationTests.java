package com.example;

import com.example.mapper.FindLevelOneMapper;
import com.example.mapper.FindLevelTwoMapper;
import com.example.mapper.UpdateMapper;
import com.example.pojo.Student;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootTest
class CacheApplicationTests {

    @Autowired
    private FindLevelOneMapper findLevelOneMapper;
    @Autowired
    private FindLevelTwoMapper findLevelTwoMapper;
    @Autowired
    private UpdateMapper updateMapper;


    // TODO MyBatis 一级缓存
    //      1、默认状态：它由 MyBatis 自动管理，开发者无法手动关闭。
    //      2、核心机制：它是 SqlSession（数据库会话）级别的缓存。
    //                 在同一个 SqlSession 内，执行相同的 SQL 查询时，第一次会查数据库并存入缓存，后续查询直接返回缓存结果。
    //      3、生命周期：与 SqlSession 绑定，会话结束（关闭/提交/回滚）缓存即被清空。
    //      4、失效场景：执行 INSERT、UPDATE、DELETE 等增删改操作会自动清空缓存；
    //                 查询条件、SQL 语句或参数不同也不会命中缓存。
    @Test
    @Transactional // 通过 @Transactional 可以保证在同一个 SqlSession（同一个会话中）内执行，避免一级缓存失效
    void testFirstLevelCache() {
        // 第一次查询：从数据库获取，并放入一级缓存
        System.err.println("===== 第一次查询 =====");
        Student student1 = findLevelOneMapper.selectByPrimaryKey(1);
        System.out.println(student1);

        // 第二次查询：直接命中一级缓存，不会查询数据库
        System.err.println("===== 第二次查询（命中一级缓存）=====");
        Student student2 = findLevelOneMapper.selectByPrimaryKey(1);
        System.out.println(student2);

        // 执行更新操作：会清空当前 SqlSession 的一级缓存
        System.err.println("===== 执行更新操作 =====");
        updateMapper.updateByPrimaryKeySelective(Student.builder().id(1).email("ziyilu9@gmail.com").build());

        // 第三次查询：缓存已被清空，会重新查询数据库
        System.err.println("===== 第三次查询（一级缓存已清空，查库） =====");
        Student student3 = findLevelOneMapper.selectByPrimaryKey(1);
        System.out.println(student3);
    }



    // TODO MyBatis 二级缓存（二级缓存优先级高于一级缓存，即先查二级，再查一级，最后查库）
    //      1、默认状态：需手动开启（全局开关 + Mapper局部配置）。
    //                 ① 全局开启：mybatis.configuration.cache-enabled=true
    //                 ② Mapper 局部开启：XML 中添加 <cache/> 或 Mapper接口添加 @CacheNamespace
    //                                   如果同时使用 XML 和注解配置缓存，必须二选一，避免命名空间冲突。
    //                 ③ 实体类必须实现 Serializable（默认序列化机制需要）。
    //      2、核心机制：同一个 Mapper 中，相同 SQL 和参数第一次查询后缓存结果，后续不同会话的相同查询直接命中缓存，避免查库。
    //      3、生命周期：从 MyBatis 初始化开始，直到应用关闭或缓存被主动清空。
    //      4、失效场景
    //            • 同 Mapper 下的增删改操作会自动清空该命名空间的所有缓存。
    //            • 达到 flushInterval（自动刷新时间）后缓存清空。
    //            • 缓存对象数量达到 size 上限后，按 eviction 策略淘汰。
    //      二级缓存是可以跨会话的，即无需在同一个 SqlSession 中才会生效，同一个 Mapper 的多个 SqlSession 可以共享缓存数据。
    @Test
    public void testSecondLevelCache() {
        // 1. 第一次查询（从数据库取，并放入二级缓存）
        System.err.println("===== 第一次查询 =====");
        Student student1 = findLevelTwoMapper.selectByPrimaryKey(1);
        System.out.println(student1);

        // 2. 第二次查询（应该命中二级缓存，不执行 SQL）
        System.err.println("===== 第二次查询（应命中二级缓存） =====");
        Student student2 = findLevelTwoMapper.selectByPrimaryKey(1);
        System.out.println("学生2: " + student2);

        // 3. 执行 同Mapper 下的更新操作（会清空当前 Mapper 的二级缓存）
        //    由于 Mybatis 的二级缓存是基于 Mapper 的，执行不同的 Mapper 下的更新操作是不会清空二级缓存
        System.err.println("===== 执行更新操作 =====");
        findLevelTwoMapper.updateByPrimaryKeySelective(Student.builder().id(1).email("ziyilu9@gmail.com").build());

        // 4. 第三次查询（缓存已清空，需重新查询数据库）
        System.err.println("===== 第三次查询（一级缓存已清空，查库） =====");
        Student student3 = findLevelTwoMapper.selectByPrimaryKey(1);
        System.out.println(student3);
    }



    // TODO MyBatis 二级缓存。
    //      使用注解 @CacheNamespace 实现（只针对注解类型 Sql 才有效 -- @Select, @Update 等）
    @Test
    public void testSecondLevelAnnotationCache() throws InterruptedException {
        // 1. 第一次查询（从数据库取，并放入二级缓存）
        System.err.println("===== 第一次查询 =====");
        Student student1 = findLevelOneMapper.selectByPrimaryKeyOnAnnotation(1);
        System.out.println(student1);

        TimeUnit.MILLISECONDS.sleep(70000); // 等待 200 毫秒，确保第一次查询完成并缓存结果

        // 2. 第二次查询（应该命中二级缓存，不执行 SQL）
        System.err.println("===== 第二次查询（应命中二级缓存） =====");
        Student student2 = findLevelOneMapper.selectByPrimaryKeyOnAnnotation(1);
        System.out.println("学生2: " + student2);

        // 3. 执行 同Mapper 下的更新操作（会清空当前 Mapper 的二级缓存）
        //    由于 Mybatis 的二级缓存是基于 Mapper 的，执行不同的 Mapper 下的更新操作是不会清空二级缓存
        System.err.println("===== 执行更新操作 =====");
        findLevelOneMapper.updateByPrimaryKeySelective(Student.builder().id(1).email("ziyilu9@gmail.com").build());

        // 4. 第三次查询（缓存已清空，需重新查询数据库）
        System.err.println("===== 第三次查询（一级缓存已清空，查库） =====");
        Student student3 = findLevelOneMapper.selectByPrimaryKeyOnAnnotation(1);
        System.out.println(student3);
    }

}
