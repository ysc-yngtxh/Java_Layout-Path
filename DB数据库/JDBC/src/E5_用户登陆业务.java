import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

/**
 * @author 游家纨绔
 */
/*
实现功能：
       1、需求：
            模拟用户登录功能的实现。
       2、业务描述：
            程序运行的时候，提供一个输入的入口，可以让用户输入用户名和密码
            用户输入用户名和密码之后，提交信息，Java程序收集到用户信息
            Java程序连接数据库验证用户名和密码是否合法
            合法：显示登陆成功
            不合法：显示登陆失败
       3、数据的准备：
             在实际开发中，表的设计会使用专业的建模工具，我们这里安装一个建模工具：PowerDesigner

当前程序存在的问题：
       用户名：root
       密码：root' or '1'='1
       登陆成功
       当用户这么输入，在jdbc第四过程中执行SQL语句：select * from t_student where name='root' and password='root' or '1'='1';
       SQL语句 name='root' and password='root' or '1'='1' 表示1=1成立则不需要看 name='root' and password='root' 是否成立
       这种现象被称为SQL注入(安全隐患)。黑客经常使用
导致SQL注入的根本原因是什么？
       用户输入的信息中含有SQL语句的关键字，并且这些关键字参与SQL语句的编译过程，导致SQL语句的原意被扭曲，进而达到SQL注入。
解决SQL注入问题
       只要用户提供的信息不参与SQL语句的编译过程，问题就解决了
       即使用户提供的信息中含有SQL语句的关键字，但是没有参与编译，不起作用
       要想用户信息不参与SQL语句的编译，那么必须使用 java.sql.PreparedStatement
       PreparedStatement 接口继承了java.sql.Statement
       PreparedStatement 是属于预编译的数据库操作对象
       PreparedStatement 的原理是：预先对SQL语句的框架进行编译，然后再给SQL语句传"值"
*/
public class E5_用户登陆业务 {
    public static void main(String[] args) {
        // 初始化界面
        Map<String, String> userLoginInfo = initUI();
        // 验证用户名和密码
        boolean loginSuccess = login(userLoginInfo);
        // 最后输出结果
        System.out.println(loginSuccess ? "登陆成功" : "登陆失败");
    }

    /**
     * 用户登陆
     *
     * @param userLoginInfo 用户登录信息
     * @return false表示失败，true表示成功
     */
    private static boolean login(Map<String, String> userLoginInfo) {

        // 打标记
        boolean loginSuccess = false;
        // JDBC代码
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1、注册驱动
            Class.forName("com.mysql.cj.jdbc.Driver");
            // 2、获取驱动
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC", "root", "131474");
            // 3、获取数据库操作对象
            stmt = conn.createStatement();
            // 4、执行SQL
            String sql = "select * from t_student where name='" + userLoginInfo.get("loginName") + "' and age= '" + userLoginInfo.get("loginPwd") + "'";
            rs = stmt.executeQuery(sql);
            // 5、处理查询结果集
            if (rs.next()) {
                // 登陆成功
                loginSuccess = true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6、释放资源
            if (rs != null) {
                try {
                    rs.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (stmt != null) {
                try {
                    stmt.close();
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
        return loginSuccess;
    }


    /**
     * 初始化界面
     *
     * @return loginName用户名
     * loginPwd密码
     */
    private static Map<String, String> initUI() {
        Scanner s = new Scanner(System.in);
        System.out.println("用户名：");
        String loginName = s.nextLine();

        System.out.println("密码：");
        String loginPwd = s.nextLine();

        Map<String, String> userLoginInfo = new HashMap<>(10);
        userLoginInfo.put("loginName", loginName);
        userLoginInfo.put("loginPwd", loginPwd);

        return userLoginInfo;
    }
}
