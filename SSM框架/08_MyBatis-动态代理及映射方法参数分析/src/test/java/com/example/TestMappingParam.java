package com.example;

import com.example.dao.MappingParamDao;
import com.example.domain.Student;
import com.example.util.MyBatisUtils;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;

import java.util.List;

public class TestMappingParam {

    @Test
    public void SelectStudents(){
        /*
         * 使用mybatis的动态及代理机制，使用SqlSession.getMapper(dao接口)
         * getMapper能获取dao接口对于的实现类对象
         */
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);
        /**
         * 可以发现这里打印出的 dao=com.sun.proxy.$Proxy6：即为jdk的动态代理
         * System.out.println("dao=" + dao.getClass().getName());
         */
        // StudentDao类是接口，无法实例化，我们却能通过“接口对象dao”调用方法，很明显这里是jdk动态代理的对象执行数据库的操作
        List<Student> students = dao.selectStudents();
        for (Student stu : students) {
            System.out.println("学生 = " + stu);
        }
    }

    // 单个参数可以直接通过 #{参数名}、#{_parameter} 传递参数值
    // MyBatis 可以直接将单个参数作为 SQL 语句的参数，无需额外的映射。
    @Test
    public void SelectStudentsSingleParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);

        List<Student> students = dao.selectStudentsSingleParam("敏敏");
        for (Student stu : students) {
            System.out.println("学生 = " + stu);
        }
    }

    // 多个参数，Mybatis是没办法自己判断哪个参数值与Sql占位符相对应，不做额外处理执行查询会报错。
    // 问题原因：在 Java 8 之前，方法的参数名在编译后会丢失，字节码中只保留参数的类型信息，而不保留参数的实际名称。
    //         这是因为 Java 编译器默认不会将参数名写入 .class 文件中，主要是为了减少字节码的大小和提高性能。
    //         从 Java 8 开始，Java 引入了 -parameters 编译选项，允许将方法的参数名保留在编译后的字节码中。
    //         这样，通过反射就可以获取到方法的实际参数名，而不是默认的 arg0、arg1 这样的占位符名称。
    // 解决方案：可以在编译时加上 -parameters 参数来保留方法参数名。
    //         方法一、在IDEA中设置：File -> Settings -> Build, Execution, Deployment
    //                                 -> Compiler -> Java Compiler
    //                                 -> Additional command line parameters: -parameters
    //         方法二、在pom.xml中设置：
    //                <build>
    //                    <plugins>
    //                        <plugin>
    //                            <groupId>org.apache.maven.plugins</groupId>
    //                            <artifactId>maven-compiler-plugin</artifactId>
    //                            <configuration>
    //                                <compilerArgs>
    //                                    <arg>-parameters</arg>
    //                                </compilerArgs>
    //                            </configuration>
    //                        </plugin>
    //                    </plugins>
    //                </build>
    @Test
    public void selectStudentsParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);

        // Mybatis中通过反射获取到方法的参数名称：method.getParameters()
        // jdk1.8之前：通过反射获取到的参数名为[arg0、arg1]、[param1、param2]
        // jdk1.8之后：可以在编译时添加 -parameters 参数来保留方法参数名。
        //            这样通过反射获取到的参数名为参数的变量名，即：name、age。。。
        List<Student> students = dao.selectStudentsPropertyParam("敏敏", 22);
        for (Student stu : students) {
            System.out.println("学生 = " + stu);
        }
    }

    // 多个参数，通过参数名为 [arg0、arg1]、[param1、param2] 传递参数值
    @Test
    public void selectStudentsReflect(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);
        // 这种写法可读性不高，不推荐使用
        List<Student> students = dao.selectStudentsReflect("敏敏", 22);
        for (Student stu : students) {
            System.out.println("学生 = " + stu);
        }
    }

    // 多个参数，通过注解 @Param 指定映射
    @Test
    public void selectStudentsAnnotationParam(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);
        // 所以，基于上述存在的问题：当查询中存在多个参数值时，需要使用注解 @Param 进行指定映射
        List<Student> students = dao.selectStudentsAnnotationParam("敏敏", 22);
        for (Student stu : students) {
            System.out.println("学生 = " + stu);
        }
    }

    @Test
    public void InsertStudent(){
        SqlSession sqlSession = MyBatisUtils.getSqlSession();
        MappingParamDao dao = sqlSession.getMapper(MappingParamDao.class);
        Student student = new Student();
        student.setId(1007);
        student.setName("诸葛亮");
        student.setEmail("zhugeliang@163.com");
        student.setAge(35);
        int nums = dao.insertStudent(student);
        sqlSession.commit();
        System.out.println("添加对象的数量：" + nums);
    }
}
