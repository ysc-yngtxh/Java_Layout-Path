package com.example.mapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @Component: 创建对象的，等同于<bean>的功能
 *        属性：value 就是对象的名称，也就是bean的id值。value的值是唯一的，创建的对象在整个spring容器中就一个
 *        位置：：在类的上面
 *
 * @Component(value="mySchool")  等同于
 *        <bean id="mySchool" class="com.example.bao.Student"/>
 *        也可以省略配置注解中的value属性值：@Component("mySchool")
 *
 * Spring中和@Component功能一致，创建对象的注解还有：
 *     1、@Repository(用在持久层的上面)：放在dao的实现类上面，表示创建dao对象，dao对象是能访问数据库的
 *     2、@Service   (用在业务层的上面)：放在service的实现类上面，创建service对象，service对象是做业务处理，可以有事务等功能的。
 *     3、@Controller(用在控制器的上面)：放在controller(控制器)类的上面，创建控制器对象的，控制器对象，能够接受用户提交的参数，显示请求的处理结果
 *     以上三个注解的子注解中都存在@Component，所以注解功能是包含@Component的，都能创建对象。但是这三个注解还有额外的功能。
 *     @Repository, @service, @Controller 是给项目的对象分层的。当你不确定类的功能时，可以使用@Component
 *
 * Spring生成的Bean名称规则：
 *     1、优先取注解中 name 指定的名字 -- @Component(value="mySchool")
 *     2、如果注解中没有指定value属性值的话，默认情况下是类名首字母转小写。
 *        即在MyApp中：School service = (School) ac.getBean("school");
 *        通过容器的 getBean() 方法，可以获取指定的Bean名称实例对象。
 *     3、特殊情况：注解中没有指定value属性值，且类名是连续两个首字母大写的话，类名即为Bean名称，不会做其他处理。
 *        比如：存在SChool类，那么 SChool school = (SChool) ac.getBean("SChool");
 */
@Component("mySchool")
public class School {
    /**
     * @Value：简单类型的属性赋值
     *     属性：value是String类型的，表示简单类型的属性值
     *     位置：1、在属性定义的上面，无需set方法，推荐使用
     *          2、在set方法的上面
     *     注意：加上属性赋值前提是要有 @Component("myStudent")。在获取到对象后才能进行赋值
     */
    @Value("游家纨绔")       // 或者加在set方法上
    private String name;
    @Value("陕西西安雁塔区") // 或者加在set方法上
    private String address;

    /**
     * 引用类型(Spring框架提供)
     * @Autowired：spring框架提供的注解，实现引用类型的赋值。使用的是自动注入原理，支持byName、byType
     * @Autowired：默认使用的是byType自动注入。
     *      属性：required 是一个boolean类型的的，默认是true
     *           required=true： 表示引用类型赋值失败，程序报错，并终止执行
     *           required=false：引用类型如果赋值失败，程序正常执行，引用类型是null
     *      位置：在属性定义的上面，无需set方法，推荐使用
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
    @Qualifier("myStudent") // 本来默认使用的是byType自动注入，加上这条语句就成了byName自动注入
    private Student student;



    /**
     * 引用类型(Jdk提供的注解)
     * @Resource：来自Jdk中的注解，Spring框架提供了对这个注解的功能支持，可以使用他给引用类型赋值
     *            使用的也是自动注入原理，支持byName、byType，默认是byName
     *      位置：在属性定义的上面，无需set方法.
     */
    @Resource // 默认使用byName：先使用byName自动注入，如果byName赋值失败，再使用byType
    // @Resource(name="myTeacher") // 这种写法表示只使用byName方式，需要增加一个属性name，name的值是bean的id(名称)
    private Teacher teacher;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "School{" +
                "name='" + name + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
