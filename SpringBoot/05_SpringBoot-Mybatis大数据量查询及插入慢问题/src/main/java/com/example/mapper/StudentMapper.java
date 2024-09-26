package com.example.mapper;

import com.example.domain.Student;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

import java.util.Map;

public interface StudentMapper {

    // 返回一个key为name值，value为Map的Map集合；相当于Map<String, Map>
    @MapKey("name")
    Map<String, Student> selectByPrimaryKeyMap(Integer id);

    /** 区别于 selectByPrimaryKeyMap 查询语句，即xml文件中返回值不同就会导致取value都能取到，
     * selectByPrimaryKeyMap 查询语句结果的value值无法通过Student实体类get方法获取详细属性值,除非强转成Map类型,通过get属性的方式获取
     * selectByPrimaryKeyStudent 查询语句结果的value值是可以通过Student实体类get方法获取详细属性值：比如 getName()
     * <p>
     * 简单思考一下就知道：
     *   selectByPrimaryKeyMap返回类型是Map，所以语句结果的value值只能强转成Map类型,通过get("xxx")方式获取
     *   selectByPrimaryKeyStudent返回类型是Student，语句结果的value值可以通过Student实体类get方法获取详细属性值
     * 这里的爆红不影响项目启动，只是我们安装的箭头插件提示*/
    @MapKey("name") // 返回一个key为name值，value为Student对象的Map集合；相当于Map<String, Student>
    Map<String, Student> selectByPrimaryKeyStudent(Integer id);


    // TODO 1、流式查询：指的是当前应用与数据库建立长连接，查询成功后不是返回一个集合而是返回一个迭代器，
    //                 利用服务端游标，每次从迭代器读取一条加载到 JVM 内存（多次获取，一次一行）
    //                 处理完上一条查询的数据后才会再从数据库中读取下一条数据到内存，避免一次性取出的数据过大
    //                 导致我们的系统发生内存溢出(OOM)的错误。流式查询的好处是能够降低内存使用。
    //      2、应用场景(适合大数据量操作)：
    //                 数据迁移
    //                 数据导出
    //                 批量处理数据
    //      3、流式查询的过程当中，因为数据处理结束之前都需要一次次从数据库当中取数据的，数据库连接是要保持打开状态的
    //  ⚠️：实现流式查询需要在配置文件中的数据库连接的url后添加：useCursorFetch=true

    // 流式查询的第一种写法：指定Mapper方法的返回值为 Cursor 类型，这个查询方法就会被 MyBatis 定义为一个流式查询。
    //                   且Cursor迭代会进行多次查询，每次查询的数据量为一个。因此又叫做游标查询
    @ResultType(Student.class)
    @Select("SELECT * FROM `student`")
    // FORWARD_ONLY：结果集的游标只能向下滚动     fetchSize：表示要从数据库中每次检索的行数
    // @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 1000)
    // 以上@Options根据自己需要设置，也可以不设置。不设置的话，Cursor迭代会进行多次查询，每次查询的数据量为一个。
    Cursor<Student> streamingQuery1();

    @ResultType(Student.class)
    @Select("SELECT * FROM `student`")
    // fetchSize = Integer.MIN_VALUE 是一种特殊的设定值。
    // 表示让 JDBC 驱动程序基于自身的实现和数据库服务器的能力自动选择一个合适的 fetchSize 值，就不需要我们手动指定
    @Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
    Cursor<Student> streamingQuery2();

    /**
     * streamingQuery2() 效率以及性能是优于 streamingQuery1() ，
     * 因为指定了Cursor每次从数据库中读取的数据量，减少了迭代次数
     */



    // 流式查询的第二种写法：方法入参为 ResultHandler<?> 类型，
    //                    一定要加上@ResultType，以便在ResultHandler的实现类中handler方法里用作入参类型
    // 这种写法只会对数据库查询一次，将查询结果集保存到JDBC驱动程序的缓冲区(缓存，即内存)中，
    // 并且对于查询结果集中的每一行数据，都会调用handleResult方法去进行处理。
    // JDBC驱动程序是在JVM上运行的，因此它使用的是JVM的内存。所以当查询出来的结果集过大，还是会对JVM发生内存溢出（OOM）
    @Select("SELECT * FROM student union all " +
            "SELECT * FROM student" )
    @ResultType(Student.class)
    void selectStudent(ResultHandler<Student> resultHandler);
}

