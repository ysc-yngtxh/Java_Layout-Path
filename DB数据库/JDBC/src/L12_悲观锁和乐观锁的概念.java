import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

/**
 * @author 游家纨绔
 */
/* 悲观锁也叫行级锁：select后面添加for update
 * 悲观锁：事务必须排队执行。数据锁住了，不允许并发
 * 乐观锁：支持并发，事务也不需要排队，只不过需要一个版本号
 *
 * 这个程序开启一个事务，这个事务专门进行查询，并且使用行级锁/悲观锁，锁住相关的记录
 * 配合M13演示行级锁一起使用，方便理解
 */
public class L12_悲观锁和乐观锁的概念 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = DBUtil.getConnection();

            // 开启事务
            conn.setAutoCommit(false);

            String sql = "select no,name,age from t_student where age=? for update";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "18");

            rs = ps.executeQuery();
            while (rs.next()) {
                System.out.println("t_student{no=" + rs.getString("no") +
                                           ", name=" + rs.getString("name") +
                                           ", age=" + rs.getString("age") +
                                           ", height=" + rs.getString("height") +
                                           "}");
            }

            // 提交事务
            conn.commit();
        } catch (SQLException e) {
            // 回滚事务
            if (conn != null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, rs);
        }
    }
}
