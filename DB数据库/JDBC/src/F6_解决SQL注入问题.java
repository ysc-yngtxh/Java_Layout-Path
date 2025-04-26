import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 */
/* 联合  E5_用户登陆业务  思考
 * 解决SQL语句注入问题的关键是： 用户提供的信息中即使含有SQL语句的关键字，但是这些关键字并没有参与编译，不起作用
 */
public class F6_解决SQL注入问题 {
    public static void main(String[] args) {
        // 这里不做实现功能，只是简明扼要的分析SQL语句注入问题
        Connection conn = null;
        PreparedStatement ps = null;    // 这里使用PreparedStatement(预编译的数据库操作对象)
        ResultSet rs = null;
        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_databash?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            // 3、获取预编译数据库操作对象
            // SQL语句的框子。其中一个?表示一个占位符，一个"?"将来接收一个‘值’。注意：占位符不能使用单引号括起来
            String sql = "select * from t_student where name=? or height=?";
            // 程序执行到此处，会发送SQL语句框子给DBMS,然后DBMS进行SQL语句的预先编译
            ps = conn.prepareStatement(sql);    // 这是个方法，中间prepare Statement没有d
            // 给占位符 ？传值(第一个问号下标是1，第二个问号下标是2，JDBC中所有下标从1开始)
            ps.setString(1, "游家纨绔");
            ps.setString(2, "1.65' or '1'='1'");  // 因为调用的是setString()方法，所以在传值的时候会自动加上单引号在SQL语句中
            // 4、执行SQL语句
            rs = ps.executeQuery();
            // 5、处理查询结果集
            if (rs.next()) {
                System.out.println("用户登录信息正确");
            } else {
                System.out.println("用户登陆信息有误");
            }
            // JDBC中通过MetaData来获取具体的表的相关信息。可以查询数据库中的有哪些表，表有哪些字段，字段的属性等等。
            // MetaData中通过一系列getXXX函数，将这些信息存放到ResultSet里面，然后返回给用户。
            System.out.println("使用PreparedStatement返回字段个数：" + ps.getMetaData().getColumnCount());
            System.out.println("使用ResultSet返回字段个数：" + rs.getMetaData().getColumnCount());
            System.out.println("获取第一个字段名：" + ps.getMetaData().getColumnName(1));
            System.out.println("获取第一个字段的数据类型：" + ps.getMetaData().getColumnClassName(1));
            System.out.println("获取第一个字段对应的表名：" + ps.getMetaData().getTableName(1));
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (ps != null) {
                try {
                    ps.close();
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
