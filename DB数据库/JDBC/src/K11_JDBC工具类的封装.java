import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import util.DBUtil;

/**
 * 这个程序与两个任务：
 * 第一：测试DBUtil是否好用
 * 第二：模糊查询怎么写？
 */
public class K11_JDBC工具类的封装 {
	public static void main(String[] args) {
		Connection conn = null;
		PreparedStatement ps = null;
		ResultSet rs = null;
		try {
			// 获取连接
			conn = DBUtil.getConnection();

			// 获取预编译的数据库操作对象
			String sql = "select no,name,age,height from t_student where name like ?";
			ps = conn.prepareStatement(sql);
			ps.setString(1, "%游%");
			rs = ps.executeQuery();

			while (rs.next()) {
				System.out.println("t_student{no=" + rs.getString("no") +
						                   ", name=" + rs.getString("name") +
						                   ", age=" + rs.getString("age") +
						                   ", height=" + rs.getString("height") +
						                   "}"
				);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 释放资源
			DBUtil.close(conn, ps, rs);
		}
	}
}
