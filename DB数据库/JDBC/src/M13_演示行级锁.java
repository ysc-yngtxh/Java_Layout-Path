import util.DBUtil;

import java.sql.*;

/**
 * @author 游家纨绔
 */
/*
  1、在K11悲观锁和乐观锁的概念中加上断点在提交事务(conn.commit)语句上,然后debug运行
  2、debug运行后再到L12演示行级锁中运行，会发现本应输出的值1没有出现。
 */
public class M13_演示行级锁 {
    public static void main(String[] args) {

        Connection conn = null;
        PreparedStatement ps = null;

        try {
            conn = DBUtil.getConnection();

            conn.setAutoCommit(false);
            String sql = "update t_student set age=? where name='wangzhuxian'";
            ps = conn.prepareStatement(sql);
            ps.setString(1, "36");
            int count = ps.executeUpdate();
            System.out.println(count);

            conn.commit();
        } catch (SQLException e) {
            if (conn == null) {
                try {
                    conn.rollback();
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, ps, null);
        }
    }
}
