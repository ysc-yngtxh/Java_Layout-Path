package com.example.controller;

import com.example.annotation.GetVal;
import com.example.component.MySpELBean;
import com.example.pojo.User;
import java.util.Locale;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-10 09:58
 * @apiNote TODO 解析对象
 */
@RestController("objectsSpELController")
public class ObjectsSpELController {

    @Autowired
    private MySpELBean mySpELBean;

    // TODO 使用自定义的解析器上下文ParserContext进行解析表达式。通过解析出的对象获取属性，并将属性值转为大写
    @RequestMapping("/objects1")
    @GetVal(value = "@{#user.name.toUpperCase()}")
    public String objects1(@RequestBody User user){
        return "SpEL表达式结果：" + user.getName().toUpperCase(Locale.ROOT);
    }

    // TODO 通过解析出的对象获取指定属性的下标值(address)
    @RequestMapping("/objects2")
    @GetVal(value = "@{#user.address[3] + '湖北武汉'}")
    public String objects2(@RequestBody User user){
        return "SpEL表达式结果：" + user.getAddress().get(3) + "湖北武汉";
    }

    // TODO 通过解析出的对象获取指定下标值属性，并将嵌套的userInfo对象获取birthday属性，
    //      间接通过birthday属性的类型LocalDatetime，获取 year 属性值，最后通过数学运算符+2000.
    @RequestMapping("/objects3")
    @GetVal(value = "@{#user.userInfos[1].birthday.year + 2000}")
    public String objects3(@RequestBody User user){
        return "SpEL表达式结果：" + (user.getUserInfos().get(1).getBirthday().getYear() + 2000);
    }

    // TODO 使用基本类型时要小心，因为它们会立即被装箱为包装器类型。
    //      1 instanceof T(int) 会计算为false，而 1 instanceof T(Integer) 会计算为true。
    @RequestMapping("/objects4")
    @GetVal(value = "@{#user.userInfos[0].age instanceof T(Integer)}")
    public String objects4(@RequestBody User user){
        return "SpEL表达式结果：" + (user.getUserInfos().get(0).getAge() instanceof Integer);
    }

    // TODO 正则表达式的匹配操作符
    @RequestMapping("/objects5")
    @GetVal(value = "@{#user.userInfos[0].height matches '^\\d+.\\d{2}$'}")
    public String objects5(@RequestBody User user){
        return "SpEL表达式结果：" + Pattern.matches("^\\d+.\\d{2}$", user.getUserInfos().get(0).getHeight().toString());
    }

    // TODO 安全导航操作符用于避免NullPointerException
    //      当 userInfoList 属性为 null，那么再获取 sex 子属性就会出现空指针报错。
    //      因此使用安全导航操作符可以避免报错，直接返回值为 null
    @RequestMapping("/objects6")
    @GetVal(value = "@{#user.userInfoList?.sex}")
    public String objects6(@RequestBody User user){
        return "SpEL表达式结果：" + null;
    }

    // TODO 在表达式中引用 Bean。在 Spring Security 中有使用到，通过权限控制访问
    @RequestMapping("/objects7")
    @GetVal(value = "@mySpELBean.hasAuthorization(#user.country)", enable = "false")
    public String objects7(@RequestBody User user){
        return "SpEL表达式结果：" + mySpELBean.hasAuthorization(user.getCountry());
    }
}
