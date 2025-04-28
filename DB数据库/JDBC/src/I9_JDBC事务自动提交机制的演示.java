import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 * @author 游家纨绔
 */
/* JDBC事务机制：
 *      1、JDBC中的事务是自动提交的，什么是自动提交？
 *           只要执行任意一条DML语句，则自动提交一次。这是JDBC默认的事务行为
 *           但是在实际的业务当中，通常都是N条DML语句共同联合才能完成的，必须保证他们这些DML语句在同一事物中同时成功或者同时失败
 *      2、以下程序先来验证一下JDBC的事务是否是自动提交机制
 *           测试结果：JDBC中只要执行任意一条DML语句，就提交一次。
 *           重点三行代码：
 *                      conn.setAutoCommit(false);  开启事务
 *                      conn.commit();              提交事务
 *                      conn.rollback();            回滚事务
 * SQL脚本：
 *        drop table if exists t_act;
 *        create table t_act(
 *            act_no bigint,
 *            balance double(7, 2)      注意：7表示有效数字的个数，2表示小数位的个数
 *        );
 *        insert into t_act(act_no, balance) values(111, 20000);
 *        insert into t_act(act_no, balance) values(222, 0);
 *        commit;
 *        select * from t_act;
 */
public class I9_JDBC事务自动提交机制的演示 {
    public static void main(String[] args) {
        Connection conn = null;
        PreparedStatement ps = null;
        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/db_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            // 将自动提交机制修改为手动修改
            conn.setAutoCommit(false);
            // 3、获取预编译数据库操作对象
            String sql = "update t_act set balance=? where act_no=?";
            ps = conn.prepareStatement(sql);
            ps.setDouble(1, 10000);
            ps.setInt(2, 111);
            // 4_1、第一次执行SQL语句
            int count = ps.executeUpdate();
            // 4_2、第二次执行SQL语句
            ps.setDouble(1, 10000);
            ps.setInt(2, 222);
            count += ps.executeUpdate();
            // 5、处理查询结果集
            System.out.println(count == 2 ? "转账成功" : "转账失败");

            // 程序能够走够这里说明以上程序没有异常，事务结束，手动提交数据
            conn.commit();

        } catch (Exception e) {
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
            // 6、释放资源
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
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
