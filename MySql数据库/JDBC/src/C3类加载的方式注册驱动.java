import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/*
public class Driver extends com.mysql.cj.jdbc.Driver {
    public Driver() throws SQLException {
        super();
    }

    static {
        System.err.println("Loading class `com.mysql.jdbc.Driver'. This is deprecated. The new driver class is `com.mysql.cj.jdbc.Driver'. "
                + "The driver is automatically registered via the SPI and manual loading of the driver class is generally unnecessary.");
    }
}

以上代码是jdbc中的com.mysql.jdbc.Driver类.我们可以发现，这个类继承了com.mysql.cj.jdbc.Driver类，且有一个静态代码块。
而在学习javase反射机制的时候我们就了解到。
      如果你只希望一个类的静态代码块执行，其他代码一律不执行
       你可以使用：
           Class.forName("完整类名");
       这个方法的执行会导致类加载，类加载时，静态代码块执行

       所以，我们在第一步注册驱动的时候还有一种写法：即Class.forName("完整类名");
       这种方法也是我们最常用的方法
 */
/**
 * @author 游家纨绔
 */
public class C3类加载的方式注册驱动 {
    public static void main(String[] args) {

        Connection conn = null;
        Statement stmt = null;
        try{
            //1、注册驱动的第二种写法：类加载方式
              //为什么这种方式常用？因为参数是一个字符串，字符串可以写到xxx.properties文件中
              //以下方法不需要接收返回值，因为我们只想用他的类加载动作
            Class.forName("com.mysql.cj.jdbc.Driver");

            //2、获取连接
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/bjpowernode?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC","root","131474");

            //3、获取数据库操作对象
            stmt = conn.createStatement();

            //4、执行SQL
            String sql = "insert into t_student(no,name,age) values(10,'shenfang','28')";
            int count = stmt.executeUpdate(sql);
            System.out.println(count == 1 ? "新增成功" : "新增失败");

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally{
            //6、释放资源
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
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }

    }
}
