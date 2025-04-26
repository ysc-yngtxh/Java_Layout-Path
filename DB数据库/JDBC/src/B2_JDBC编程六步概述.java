import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * @author 游家纨绔
 */
public class B2_JDBC编程六步概述 {
    public static void main(String[] args) {
        Connection conn = null;
        Statement stmt = null;
        try {
            // 1、注册驱动
            Driver driver = new com.mysql.cj.jdbc.Driver();   // 多态，父类型引用指向子类型对象
            DriverManager.registerDriver(driver);
            // 合并写法：DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());

            // 2、获取连接
            /*
             * url:统一资源定位符（网络中某个资源的绝对路径）
             * jdbc:mysql://localhost:3306/db_databash?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC 这就是URL
             * URL包括哪几部分?
             *    jdbc:mysql:   通信协议
             *    localhost：   IP地址
             *    3306:         MySQL数据库端口号
             *    db_databash： 具体的数据库实例名
             *    ?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC    这就是MySQL驱动8的新特性，加入了时区
             *
             * 说明：localhost和127.0.0.1都是本机IP地址
             */
            String url = "jdbc:mysql://localhost:3306/db_databash?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
            String user = "root";
            String password = "131474";
            conn = DriverManager.getConnection(url, user, password);
            System.out.println("数据库连接对象 = " + conn);
            // 合并写法：conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");

            // 3、获取数据库操作对象
            stmt = conn.createStatement();

            // 4、执行SQL
            String sql = "insert into t_student(no, name, age) values(9, '游家纨绔', '28')";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "保存成功" : "保存失败");

            // 5、处理查询结果集

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源。为了保证资源一定释放，在finally语句块中关闭资源，并且要遵循从小到大依次关闭
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (SQLException e) {
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
