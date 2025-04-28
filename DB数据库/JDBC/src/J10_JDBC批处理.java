import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;

/**
 * @author 游家纨绔
 * @dateTime 2024-03-15 20:08
 * @apiNote TODO
 */
public class J10_JDBC批处理 {
    public static void main(String[] args) {
        long startTime = System.currentTimeMillis();
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC"
                                             , "root"
                                             , "131474");
            // 3、获取预编译数据库操作对象
            String sql = "insert into t_student(no,name,age) values(?,?,?)";
            ps = conn.prepareStatement(sql);
            for (int i = 0; i < 100000; i++) {
                ps.setInt(1, 10+i);
                ps.setString(2, "大狼狗"+i);
                ps.setInt(3, i);
                ps.addBatch();
            }
            // 4、执行SQL语句
            int[] count = ps.executeBatch();
            // 5、处理查询结果集
            long stopTime = System.currentTimeMillis();
            System.err.printf("批处理插入%d条数据，耗时：%dms%n", count.length, (stopTime-startTime));
            System.out.println("批处理数据量：" + count.length);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            if (ps != null) {
                try {
                    ps.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }

            }
        }
    }
}
