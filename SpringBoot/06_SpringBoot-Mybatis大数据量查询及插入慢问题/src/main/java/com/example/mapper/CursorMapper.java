package com.example.mapper;

import com.example.domain.Student;
import org.apache.ibatis.annotations.Options;
import org.apache.ibatis.annotations.ResultType;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.cursor.Cursor;
import org.apache.ibatis.mapping.ResultSetType;
import org.apache.ibatis.session.ResultHandler;

/**
 * @Author 游家纨绔
 * @Description TODO
 * @Date 2025-04-11 09:06:30
 */
public interface CursorMapper {

	// TODO 1、流式查询：主要是用来处理大量数据，通过分批次拉取数据，避免一次性加载到内存中导致内存溢出。
	//           在当前应用查询数据的过程中，与远程数据库一直保持连接(建立长连接)，不断地去数据库拉取数据，
	//           查询成功后不是返回一个集合而是返回一个迭代器，利用服务端游标，每次从迭代器读取指定数量的数据加载到 JVM 内存（多次获取）
	//           处理完上一次查询的数据后才会再从数据库中读取下一次数据到内存，避免一次性取出的数据量过大，导致我们的系统发生内存溢出(OOM)的错误。
	//           流式查询在提交事务并关闭SqlSession后，与数据库之间的连接将断开，停止数据拉取
	//           需要注意的是使用这种方式，需要自己手动维护SqlSession和事务的提交。
	//      2、核心配置：
	//         ①、MyBatis 层：resultSetType=FORWARD_ONLY + fetchSize。
	//         ②、数据库层：驱动参数（如 MySQL 数据库的连接url添加 useCursorFetch=true）。
	//         ③、事务层：@Transactional
	//         ④、保持连接。
	//      3、流式查询的实现方式：
	//         ①、游标查询：
	//            Cursor（游标）是 MyBatis 提供的一个流式查询接口，它允许你以“逐行遍历”的方式处理大型结果集，而不是一次性将全部数据加载到内存中。
	//            可以把Cursor看作是一个迭代器，它会在后台与数据库保持连接，并在需要时从数据库中获取数据。
	//         ②、处理结果集ResultHandler：
	//            通过在mapper接口中定义方法，参数包含ResultHandler，然后在处理结果时，MyBatis会为每一条记录调用ResultHandler的handleResult方法。
	//      4、应用场景（适合大数据量操作）：
	//                 数据迁移
	//                 数据导出
	//                 批量处理数据
	//      5、流式查询的过程当中，因为数据处理结束之前都需要一次次从数据库当中取数据的，数据库连接是要保持打开状态的

	// 流式查询的第一种写法：指定Mapper方法的返回值为 Cursor 类型，且Cursor迭代会进行多次查询，每次查询的数据量为一个。因此又叫做游标查询
	@ResultType(Student.class)
	@Select("SELECT * FROM `db_student`")
	// FORWARD_ONLY：结果集的游标只能向下滚动     fetchSize：表示要从数据库中每次检索的行数
	@Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = 1000)
	// 以上@Options根据自己需要设置，也可以不设置。不设置的话，Cursor迭代会进行多次查询，每次查询的数据量为一个。
	Cursor<Student> streamingQuery1();


	@ResultType(Student.class)
	@Select("SELECT * FROM `db_student`")
	// fetchSize = Integer.MIN_VALUE 是一种特殊的设定值。
	// 表示让 JDBC 驱动程序基于自身的实现和数据库服务器的能力自动选择一个合适的 fetchSize 值，就不需要我们手动指定
	@Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
	Cursor<Student> streamingQuery2();

	/** streamingQuery2() 效率以及性能是优于 streamingQuery1() ，
	 *  因为指定了Cursor每次从数据库中读取的数据量，减少了迭代次数
	 */


	// 流式查询的第二种写法：方法入参为 ResultHandler<?> 类型，
	//                    一定要加上@ResultType，以便在ResultHandler的实现类中handler方法里用作入参类型
	@Select("SELECT * FROM db_student union all SELECT * FROM db_student" )
	@ResultType(Student.class)
	@Options(resultSetType = ResultSetType.FORWARD_ONLY, fetchSize = Integer.MIN_VALUE)
	void selectStudentOnResultHandler(ResultHandler<Student> resultHandler);
}
