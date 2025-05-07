package com.example.aop;

import com.example.annotation.GetVal;
import com.example.registry.ApplicationContextRegister;
import java.util.Random;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.ApplicationContext;
import org.springframework.context.expression.BeanFactoryResolver;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.ParserContext;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

/**
 * @author 游家纨绔
 * @dateTime 2024-08-07 22:00
 * @apiNote TODO
 */
@Aspect
@Component
public class AspectAop {

    @Pointcut(value = "@annotation(com.example.annotation.GetVal)")
    public void pointCut() {}

    @Before(value = "pointCut()")
    public void before(JoinPoint joinPoint) throws NoSuchMethodException {
        // 获取注解上的 value 值
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        GetVal annotation = signature.getMethod().getAnnotation(GetVal.class);
        String annotationValue = annotation.value();
        // 获取方法参数名称
        String[] parameterNames = signature.getParameterNames();
        // 获取方法参数值
        Object[] args = joinPoint.getArgs();

        // 解析表达式
        parserSpEL(annotation, annotationValue, parameterNames, args);
    }

    private void parserSpEL(GetVal annotation, String annotationValue, String[] parameterNames, Object[] args) throws NoSuchMethodException {
        // 1、构造 解析器
        ExpressionParser parser = new SpelExpressionParser();
        // 获取 @GetVal 注解的属性 type 的默认值
        String defaultType = String.valueOf(GetVal.class.getDeclaredMethod("enable").getDefaultValue());
        Expression expression;
        if (annotation.enable().equalsIgnoreCase(defaultType)) {
            // 随机取[0, 100]数值
            Random random = new Random();
            int nextInt = random.nextInt(101);
            // 2、构造 表达式解析上下文
            //    SpEL或者是其他的EL语言时，会碰到两种使用场景。一种是纯表达式，一种是文本夹杂表达式（也称之为“模板”）。
            //    例如，表达式一：value = #user.name； 表达式二：value = 用户所在城市是#{#user.city}。
            //    表达式一：纯SpEL达式，直接解析返回一个结果。
            //    表达式二：文本中夹杂着SpEL表达式，只有其中的“#{user.city}”才是SpEL语法，在默认解析器下必定无法解析。
            ParserContext parserContext;
            // 以下是解析器上下文的两种写法：
            // 第一种：当随机数值 <= 50 时，选择接口 ParserContext 的匿名类的方式，定义解析上下文
            // 第二种：当随机数值 > 50 时，选择 ParserContext 的实现类 TemplateParserContext的方式，定义解析上下文
            if (nextInt <= 50) {
                // 这里不是将 接口ParserContext 实例化，而是创建接口的匿名内部类来实现间接实例化
                parserContext = new ParserContext() {
                    // 对应着表达式是否是template表达式
                    @Override
                    public boolean isTemplate() {
                        return true;
                    }
                    // 对应着表达式前缀
                    @Override
                    public String getExpressionPrefix() {
                        return "@{";
                    }
                    // 对应着表达式后缀
                    @Override
                    public String getExpressionSuffix() {
                        return "}";
                    }
                };
            } else {
                // 设置解析器上下文中表达式的前缀和后缀
                parserContext = new TemplateParserContext("@{", "}");
            }
            // 3、解析表达式，传入解析器上下文
            expression = parser.parseExpression(annotationValue, parserContext);
        } else {
            // 3、解析表达式，没有传入解析器上下文，即使用默认的解析器上下文
            //    默认的解析器上下文是没有前后缀的，即直接解析纯表达式。
            expression = parser.parseExpression(annotationValue);
        }
        // 4、设置计算上下文（SpEL解析器默认不知道这个表达式是取哪个对象的值，如果不指定解析对象，就无法进行解析。）
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (int i = 0; i < parameterNames.length; i++) {
            // 解析表达式后，对应的对象进行赋值
            context.setVariable(parameterNames[i], args[i]);
        }
        // 5、SpEL表达式可能需要访问Spring容器中的Bean。例如：@GetVal("#{@myService.doSomething()}")
        //    默认情况下，SpEL表达式并不知道如何从Spring容器中查找Bean，因此需要通过BeanResolver来让SpEL表达式能够访问Spring容器中的Bean。
        ApplicationContext applicationContext = ApplicationContextRegister.getApplicationContext();
        // 解析 Bean：需要注入一个BeanResolver来解析bean引用，此处注入 BeanFactoryResolver
        context.setBeanResolver(new BeanFactoryResolver(applicationContext));
        // 6、获取解析结果
        System.out.println("SpEL表达式的值：" + expression.getValue(context));
    }
}
