package com.example.mapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * @Component: 创建对象的，等同于<bean>的功能
 *        属性：value 就是对象的名称，也就是bean的id值。value的值是唯一的，创建的对象在整个spring容器中就一个
 *        位置：：在类的上面
 *
 * @Component(value="myStudent")  等同于
 *        <bean id="myStudent" class="com.example.bao.Student"/>
 *
 *    spring中和@Component功能一致，创建对象的注解还有：
 *        1、@Repository(用在持久层的上面)：放在dao的实现类上面，表示创建dao对象，dao对象是能访问数据库的
 *        2、@service(用在业务层的上面)：放在service的实现类上面，创建service对象，service对象是做业务处理，可以有事务等功能的。
 *        3、@Controller(用在控制器的上面)：放在控制器(处理器)类的上面，创建控制器对象的，控制器对象，能够接受用户提交的参数，显示请求的处理结果
 *        以上三个注解的使用语法和@Component一样的。都能创建对象，但是这三个注解还有额外的功能。
 *        @Repository, @service, @Controller 是给项目的对象分层的。当你不确定类的功能时，可以使用@Component
 */

// 也可以省略value：@Component("myStudent")
/*
   还有不指定对象名称:@Component
   由spring提供默认名称：类名的首字母小写
   即在MyApp中 Student service = (Student) ac.getBean("student");
   getBean()里对象名称就是类Student的spring默认名称 student
*/
@Component(value="myStudent")
public class Student {
    /**
     * @Value:简单类型的属性赋值
     *     属性：value是String类型的，表示简单类型的属性值
     *     位置：1、在属性定义的上面，无需set方法，推荐使用
     *          2、在set方法的上面
     *     注意：加上属性赋值前提是要有 @Component("myStudent")。在获取到对象后才能进行赋值
     */
    @Value("游诗成")   // 或者加在set方法上
    private String name;
    @Value("2020915") // 或者加在set方法上
    private int age;

    /**
     * 引用类型(spring框架提供)
     * @Autowired：spring框架提供的注解，实现引用类型的赋值。使用的是自动注入原理，支持byName,byType
     * @Autowired：默认使用的是byType自动注入。
     *      属性：required 是一个boolean类型的的，默认是true
     *           required=true：表示引用类型赋值失败，程序报错，并终止执行
     *           required=false：引用类型如果赋值失败，程序正常执行，引用类型是null
     *           常用的也是默认的required=true，便于调试
     *
     *      位置：1、在属性定义的上面，无需set方法，推荐使用
     *
     * @Autowired注解的工作原理如下：
     *      1、Spring框架会在你的应用启动时，自动扫描并解析所有的Bean。
     *      2、当Spring框架找到一个带有@Autowired注解的变量时，它会查找一个与这个变量类型匹配的Bean。
     *      3、如果Spring框架找到了一个匹配的Bean，那么它就会将这个Bean注入到这个变量中。
     *      4、如果Spring框架没有找到一个匹配的Bean，那么它就会抛出一个错误。
     *      5、最后通过反射进行相应属性赋值或者方法调用（反射是不需要setter方法或者构造方法就可以进行属性赋值的）
     */
    @Autowired
    // @Autowired(required = false)
    @Qualifier("mySchool") // 本来默认使用的是byType自动注入，加上这条语句就成了byName自动注入
    private School school;



    /**
     * 引用类型(jdk提供的注解)
     * @Resource：来自jdk中的注解，spring框架提供了对这个注解的功能支持，可以使用他给引用类型赋值
     *            使用的也是自动注入原理，支持byName,byType，默认是byName
     *    位置：1、在属性定义的上面，无需set方法.
     */
    @Resource // 默认使用byName：先使用byName自动注入，如果byName赋值失败，再使用byType
    // @Resource(name="myTeacher") // 这种写法表示只使用byName方式，需要增加一个属性name,name的值是bean的id(名称)
    private Teacher teacher;

    public Student() {
        System.out.println("Student的无参构造");
    }

    // @Value("游诗成")
    public void setName(String name) {
        this.name = name;
    }

    // @Value("2020915")
    public void setAge(int age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Student{" +
                "name='" + name + '\'' +
                ", age=" + age +"\n"+
                ", school=" + school +"\n"+
                ", teacher=" + teacher +
                '}';
    }
}
