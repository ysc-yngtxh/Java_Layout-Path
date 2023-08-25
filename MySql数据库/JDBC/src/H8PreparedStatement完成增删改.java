import java.sql.*;

/**
 * PreparedStatement完成insert  delete  update
 * @author 游家纨绔
 */

public class H8PreparedStatement完成增删改 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            String sql = " insert into t_student(name,age) values(?,?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "liuyu");
            ps.setString(2, "21");
            int count = ps.executeUpdate();
            System.out.println(count);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
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
