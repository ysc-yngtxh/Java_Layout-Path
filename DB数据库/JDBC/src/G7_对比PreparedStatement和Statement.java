import java.sql.*;
import java.util.Scanner;

/**
 * @author 游家纨绔
 */
/*
 * 1、对比一下Statement和preparedStatement?
 *     -- Statement 存在SQL注入问题，preparedStatement 解决了SQL注入问题
 *     -- Statement 是编译一次执行一次。preparedStatement是编译一次，可执行N次。preparedStatement效率较高一些
 *     -- preparedStatement 会在编译阶段做类型的安全检查
 *
 *     综上所述：preparedStatement使用较多。只有极少数的情况下需要使用Statement
 *
 * 2、什么情况下必须使用Statement呢？
 *     业务方面要求必须支持sql注入的时候。
 *     Statement支持SQL注入，凡是业务方面要求是需要进行SQL语句拼接的，必须使用Statement
 *
 *     看一下代码程序，可以发现要进行拼接字符串到SQL语句中，而preparedStatement却无法做到，
 *     因为传值过程中会自动加上单引号，但是加上了单引号又不符合SQL语法。
 *     所以这个时候就需要使用到Statement…
 */
public class G7_对比PreparedStatement和Statement {
    public static void main(String[] args) {
        // 用户在控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("输入desc或asc。desc就是降序,输入asc就是升序");
        System.out.println("请输入:");
        String keyword = s.nextLine();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            // 3、获取预编译数据库操作对象
            stmt = conn.createStatement();
            // 4、执行SQL语句
            String sql = "select name,age from t_student order by age " + keyword;
            rs = stmt.executeQuery(sql);
            // 5、处理查询结果集
            while (rs.next()) {
                System.out.println(rs.getString("name") + "\t" + rs.getString("age"));
            }
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
