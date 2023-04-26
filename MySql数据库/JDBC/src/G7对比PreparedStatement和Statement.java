import java.sql.*;
import java.util.Scanner;

/*
1、对比一下Statement和preparedStatement?
    -Statement存在SQL注入问题，preparedStatement解决了SQL注入问题
    -Statement是编译一次执行一次。preparedStatement是编译一次，可执行N次。preparedStatement效率较高一些
    -preparedStatement会在编译阶段做类型的安全检查

    综上所述：preparedStatement使用较多。只有极少数的情况下需要使用Statement

2、什么情况下必须使用Statement呢？
    业务方面要求必须支持sql注入的时候。
    Statement支持SQL注入，凡是业务方面要求是需要进行SQL语句拼接的，必须使用Statement

    看一下代码程序，可以发现要进行拼接字符串到SQL语句中，而preparedStatement却无法做到，
    因为传值过程中会自动加上单引号，但是加上了单引号又不符合SQL语法。
    所以这个时候就需要使用到Statement
 */
/**
 * @author 游家纨绔
 */
public class G7对比PreparedStatement和Statement {
    public static void main(String[] args) {
        //用户在控制台输入desc就是降序，输入asc就是升序
        Scanner s = new Scanner(System.in);
        System.out.println("输入desc或asc。desc就是降序,输入asc就是升序");
        System.out.println("请输入:");
        String keyword = s.nextLine();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");
            stmt = conn.createStatement();
            String sql = "select name,age from t_student order by age " + keyword;
            rs = stmt.executeQuery(sql);
            while(rs.next()){
                System.out.println(rs.getString("name") +"\t"+ rs.getString("age"));
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}
