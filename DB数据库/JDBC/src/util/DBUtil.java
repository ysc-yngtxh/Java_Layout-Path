package util;

import java.sql.*;

/**
 * JDBC工具类，简化JDBC编程
 */
public class DBUtil {
    /**
     * 工具类中的的构造方法都是私有的
     * 因为工具类当中的方法都是静态的，不需要new对象，直接采用类名调用。
     */
    public DBUtil() {}

    /**
     * 静态代码块在类加载时执行，并且只执行一次
     */
    static {
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库连接对象
     * @return  连接对象
     * @throws SQLException
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/db_database?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");
        /* 为什么这里不选择try...catch而是上抛异常呢？
         *  因为这里只是一个JDBC的工具类，是会被外界需要时调用的.所以，在外界需要连接对象调用这个方法的时候，会采取捕捉(try...catch)的方式。
         *  因此这里选择 try..catch 不合适，而选用上抛异常的话会更加合理。
         */
    }

    /**
     * 关闭资源
     * @param conn 连接对象
     * @param ps 数据库操作对象
     * @param rs 结果集
     */
    public static void close(Connection conn, Statement ps,ResultSet rs) {
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
