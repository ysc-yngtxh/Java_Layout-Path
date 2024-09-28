import java.sql.*;

/**
 * PreparedStatement完成insert  delete  update
 * @author 游家纨绔
 */
public class H8_PreparedStatement完成增删改 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            // 3、获取预编译数据库操作对象
            String sql = "insert into t_student(name, age) values(?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "liuyu");
            ps.setString(2, "21");
            // 4、执行SQL语句
            int count = ps.executeUpdate();
            // 5、处理查询结果集
            System.out.println(count);
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
