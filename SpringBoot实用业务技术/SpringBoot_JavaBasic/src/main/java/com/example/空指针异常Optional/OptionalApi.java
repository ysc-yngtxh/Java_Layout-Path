package com.example.空指针异常Optional;

import cn.hutool.log.Log;
import cn.hutool.log.LogFactory;
import com.example.vo.ModelView;
import com.example.vo.Models;
import com.example.vo.User;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.Objects;
import java.util.Optional;

/**
 * @author 游家纨绔
 * @version 1.0
 * @description: TODO
 * @date 2022/11/26 17:18
 */
public class OptionalApi {

    public static final Log log = LogFactory.get(OptionalApi.class);

    @Test
    public void test1() {
        User user = new User();
        Models models = null;
        ModelView modelView = null;
        // 为了避免空指针异常NullPointerException，我们要想获取底层的值就必须要就行判空处理，以至于多层的嵌套if/else(不优雅啊！)
        if (user != null) {
            models = user.getModels();
            if (models != null) {
                modelView = models.getModelView();
                if (modelView != null) {
                    log.debug(modelView.getAddress());
                }  else{
                    log.debug("ModelView为空");
                }
            } else{
                log.debug("Models为空");
            }
        } else {
            log.debug("User为空");
        }
    }

    @Test
    public void test2() {
        // TODO 使用optional来处理空指针(简约)
        User users = new User();
        String stringOptional = Optional.ofNullable(users)
                .map(User::getModels)
                .map(Models::getModelView)
                .map(ModelView::getAddress)
                .orElseGet(() -> "空值");
        // .orElse("空值"); 这里要不为空值的话，最后应该返回的地址address的值
        log.debug(stringOptional);

        // TODO orElse()与orElseGet()的区别(orElseGet的性能高，建议使用)
        User user1 = null;
        // ofNullable()里的值为null,返回orElse中的值。使用orElse()方法时，无论 Optional 对象是否为空，都会执行默认值的计算。
        User one = Optional.ofNullable(user1)
                .orElse(new User(1L, "one", "qwer@163.com", 25));
        // 区别于orElse()方法，使用orElseGet()方法时，只有在 Optional 对象为空时才会执行 Supplier 的计算。比较节约资源提高性能
        User two = Optional.ofNullable(user1)
                .orElseGet(() -> new User(1L, "two", "qwer@163.com", 25));
        log.info(one.toString());
    }

    @Test
    public void test3() {
        // TODO map()与flatMap()方法的区别
        //  对象实例的属性本身就为Optional包装过的类型，那么就要使用flatMap方法
        //  map映射的是返回类型，flatMap映射的是返回类型中Optional包装过的泛型类
        User user3 = new User(null, null, null, 0, null, null
                , Optional.of(new Models(null, Optional.of(new ModelView("湖北武汉洪山区")))));
        String stringOptionalFlatMap = Optional.ofNullable(user3)
                .flatMap(User::getModelsOptional)
                .flatMap(Models::getModelViewOptional)
                .map(ModelView::getAddress)
                .orElse("空值");
        log.debug(stringOptionalFlatMap);

        // TODO 需要注意的点:flatMap中的内容不能直接为null，或者Optional.of(null),而要使用Optional.empty()或者Optional.of(new Models())
        String name = Optional.ofNullable(user3).map(User::getName).orElse("name为空值");
        System.out.println(name);
        User user4 = new User(null, null, null, 0, null, null
                , Optional.of(new Models()));
        Models orElse = Optional.ofNullable(user4)
                .flatMap(User::getModelsOptional)
                .orElse(new Models(new ModelView("大大"), null));
        System.out.println(orElse);
    }

    @Test
    public void test4() {
        // TODO 判断是否有值
        User user2 = null;
        boolean present = Optional.ofNullable(user2).isPresent();
        log.debug(String.valueOf(present));
        // TODO 判断是否有值，如果值存在则执行块。注意：这里是if不是is
        User user5 = new User("ysc", 25);
        Optional.ofNullable(user5).ifPresent(u -> log.debug(u.toString()));

        // TODO 为空返回一个异常
        Optional.ofNullable(user2).orElseThrow(() -> new NullPointerException("从未见过有如此厚颜无耻之人！--叶诗琪"));

        // TODO 使用Optional与Stream来取代if判空逻辑
        Optional.ofNullable(Arrays.asList(user2))
                .orElseGet(() -> Collections.singletonList(new User(null, null, null, 0, null, null, Optional.of(new Models()))))
                .stream().filter(Objects::nonNull).forEach(System.out::println);

    }
}
