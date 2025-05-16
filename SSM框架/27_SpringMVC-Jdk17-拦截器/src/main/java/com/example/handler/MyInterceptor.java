package com.example.handler;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @dep 拦截器类：拦截用户的请求
 */
public class MyInterceptor implements HandlerInterceptor {

    long btime = 0;
    /**
     * preHandle叫做预处理方法
     * 参数：
     *     Object handler：被拦截的控制器对象
     *     返回值Boolean
     *            true
     *            false
     * 特点：
     *     1、方法在控制器方法（StudentController类的doSome()方法）之前先执行的，用户的请求首先到达此方法
     *     2、在这个方法中可以获取请求的信息，验证请求是否符合要求。
     *        可以验证用户是否登录，验证用户是否有权限访问某个连接地址(url)。
     *          如果验证失败，可以截断请求，请求不能被处理。
     *          如果验证成功，可以放行请求，此时控制器方法才能执行。
     */
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        btime = System.currentTimeMillis();
        System.out.println("拦截器的MyInterceptor的preHandle()");
        return true;
        // 当返回值为true，表示验证成功，放行请求，执行后续程序
        // 当返回值为false，拦截请求，给浏览器一个反馈
        // request.getRequestDispatcher("/tip.jsp").forward(request,response);
        // return false;
    }

    /**
     * postHandle:后处理方法
     * 参数：
     *     Object handler:被拦截的处理器对象StudentController
     *     ModelAndView mv:处理器方法的返回值
     * 特点：
     *     1、在处理器方法之后执行的(StudentController.dosome())
     *     2、能够获取到处理器方法的返回值ModelAndView,可以修改ModelAndView中的数据和视图，可以影响到最后的执行结果
     *     3、主要是对原来的执行结果做二次修正
     */
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView mv) {
        System.out.println("拦截器的MyInterceptor的postHandle()");
        // 对原来的 doSome() 方法执行结果进行补充
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        if(mv != null) {
            mv.addObject("myDate", LocalDateTime.now().format(dtf));
            mv.setViewName("other");
        }
    }

    /**
     * afterCompletion:最后执行的方法
     * 参数：：
     *      Object handler:被拦截器的处理器对象
     *      Exception ex:程序中发生的异常
     * 特点：
     *     1、在请求处理完成后执行的。框架中规定是当你的视图处理完成后，对视图执行了forward。就认为请求处理完成
     *     2、一般做资源回收工作的，程序请求过程中创建了一些对象，在这里可以删除，把占用的内存回收
     */
    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) {

		System.out.println("拦截器的MyInterceptor的afterCompletion()");

		long etime = System.currentTimeMillis();
		System.out.println("计算从preHandler到请求处理完成的时间：" + (etime - btime));
	}
}
