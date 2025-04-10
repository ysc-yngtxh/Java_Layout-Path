import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ResourceBundle;

/**
 * @author 游家纨绔
 */
/*
 * 1、将连接数据库的所有信息配置到配置文件
 * 2、处理查询结果集
 *      // int executeUpdate( insert/delete/update )
 *      // ResultSet executeQuery( select )
 */
public class D4_从属性资源文件中读取连接数据库信息 {
    public static void main(String[] args) {

        // 使用反射机制中的资源绑定器绑定属性配置文件
        // 死记：配置文件必须在类路径(src)下，否则找不到
        ResourceBundle bundle = ResourceBundle.getBundle("resourceJdbc");
        String driver = bundle.getString("driver");
        String url = bundle.getString("url");
        String user = bundle.getString("user");
        String password = bundle.getString("password");

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、注册驱动
            Class.forName(driver);
            // 2、获取驱动
            conn = DriverManager.getConnection(url, user, password);
            // 3、获取数据库对象
            stmt = conn.createStatement();
            // 4、执行SQL
            String sql = "select no,name,age from t_student";
            rs = stmt.executeQuery(sql);    // 专门执行SQL语句的方法

            // int executeUpdate(insert/delete/update)
            // ResultSet executeQuery(select)

            // 5、处理查询结果集
            /*
             * Boolean flag = rs.next();  // rs.next()方法相当于一个动作，从表中一行一行的取数据，如果取到数据，返回结果true
             * System.out.println(rs.next());
             */
            while (rs.next()) {
                /*
                 * // getString()特点：不管数据库中的数据类型是什么，都以String的形式取出
                 * String no = rs.getString(1);      // JDBC中所有的下标都是从1开始的，不是从0开始
                 * String name = rs.getString(2);    // 后面括号里数字1，2，3代表的是表里的列
                 * String age = rs.getString(3);     // 很明显，上面的方法会更健壮，推荐上面的写法
                 * System.out.println(no + "," + name + "," + (age+1));
                 */
                // 取数据。除了可以以String类型取出之外，还可以以特定的类型取出，但是特定类型需要和数据库类型一致。
                int no = rs.getInt("no");
                String name = rs.getString("name");
                double age = rs.getDouble("age");
                System.out.println(no + "," + name + "," + (age + 1));
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (rs != null) {
                try {
                    rs.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
                } catch (Exception e) {
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
