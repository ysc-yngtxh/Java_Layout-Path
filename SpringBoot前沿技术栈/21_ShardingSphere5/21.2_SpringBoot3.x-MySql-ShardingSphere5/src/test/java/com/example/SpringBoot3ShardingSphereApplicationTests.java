package com.example;

import com.example.entity.EceLookupClassify;
import com.example.entity.EceUser;
import com.example.mapper.EceLookupClassifyMapper;
import com.example.mapper.EceUserMapper;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringBoot3ShardingSphereApplicationTests {

    @Autowired
    private EceUserMapper eceUserMapper;

    @Autowired
    private EceLookupClassifyMapper eceLookupClassifyMapper;


    @Test
    void contextLoads1() {
        System.out.println(eceUserMapper.queryById(76));
    }

    @Test
    void contextLoads2() throws ParseException {
        String data = "2024-09-16 10:00:00";
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date parseData = format.parse(data);
        eceUserMapper.insert(
                EceUser.builder()
                        .eceId(123456)
                        .userCode("ECE-RPT")
                        .userName("李四")
                        .birthday(parseData)
                        .age(22)
                        .sex("女")
                        .address("湖北武汉洪山区")
                        .build()
        );
    }

    /**
     * 在 ShardingSphere 中，广播表是一种特殊的表类型，它存在于每一个分片数据源中，并且在这所有的数据库中拥有完全相同的表结构和数据。
     * 这意味着，当数据发生变化时，这个变化会被同步到所有的分片中，从而保持数据的一致性。
     *
     * 广播表的设计目的是为了处理那些数据量不大但是却需要频繁与其他大表进行关联查询的小表。
     * 这类表通常包含了一些基本的参考数据，例如字典表、状态码映射表、省份信息等。
     * 由于这类表的数据量较小，而且通常用于连接查询，因此不适合进行分片处理。
     * 通过使用广播表，可以避免因为多次查询不同分片中的相同小表而导致的额外开销。
     *
     * 广播表的特点：
     * 数据一致性：所有的插入、更新或删除操作都会被实时地应用到每个分片中，保证了数据的一致性。
     * 查询效率：由于广播表在每个分片中都有完整的副本，所以查询操作不需要跨分片进行，这简化了查询逻辑并且提高了查询速度。
     * 配置简单：在 ShardingSphere 中，可以通过简单的配置来声明哪些表是广播表，从而不需要为这些表设置复杂的分片策略。
     *
     * 应用场景：
     * 当需要与大量的数据表进行频繁的关联查询时，可以考虑将一些小而重要的参考表配置成广播表。
     * 在需要全局唯一数据的情况下，比如配置表、用户权限表等，可以使用广播表来确保数据的一致性和易用性。
     * 总之，广播表是为了优化那些数据量不大但是需要频繁参与联合查询的小表而设计的一种机制。
     * 通过这种方式，可以在分布式环境中实现数据的高效管理和查询。
     */
    @Test
    void contextLoads3() {
        System.out.println(eceLookupClassifyMapper.update(
                EceLookupClassify.builder()
                        .lookupId(1L)
                        .updateBy("游家纨绔1")
                        .build()
        ));
    }
}

