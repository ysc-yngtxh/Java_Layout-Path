package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author 游家纨绔
 * @Controller：创建处理器对象，对象放在springMvc容器中 位置：在类的上面，和Spring中讲的@Service、@Component
 */
@Controller
public class MyController {

    /** @RequestMapping：请求映射，作用是把一个请求地址和一个方法绑定在一起（一个请求指定一个方法处理）
     *             属性：1、value是一个String，表示请求的uri地址的(some.do)
     *                     value的值必须是唯一的，不能重复。在使用时，推荐地址以 “/” 开头
     *             位置：1、在方法的上面，常用的
     *                  2、在类的上面
     * 说明：使用 @RequestMapping 修饰的方法叫做处理器方法或者控制器方法。
     *      使用 @RequestMapping 修饰的方法可以处理请求的，类似Servlet中的doGet()、doPost()
     * 返回值：ModelAndView 表示本次请求的处理结果
     *        Model: 数据，请求处理完成后，要显示给用户的数据
     *        View: 视图 ，比如jsp等等
     */
    @RequestMapping(value= {"/test/some.do", "/test/first.do"}) // 里面的value是一个数组结构
    public ModelAndView doSome() {
        // 处理some.do请求了。相当于servlet调用处理完成了
        ModelAndView mv = new ModelAndView();
        // 添加数据，框架在请求的最后把数据放入到request作用域
        // request.setAttribute("msg", "欢迎使用SpringMvc做web开发");
        mv.addObject("msg", "欢迎使用 SpringMVC 做Web开发");
        mv.addObject("fun", "执行的是 doSome() 方法");

		// 当没有声明视图解析器时，需要指定视图的完整路径。例如：框架对视图执行的forward操作
		// request.getRequestDispatcher("/show.jsp").forward(...)
		// mv.setViewName("/WEB-INF/view/show.jsp");

		// 当配置了视图解析器后，可以使用逻辑名称(文件名)，指定视图。框架会使用【视图解析器的前缀+逻辑名称+后缀】组成完成路径
		mv.setViewName("show");

		// 返回mv
		return mv;
	}

	@RequestMapping(value = {"/test/other.do", "/test/second.do"})
	public ModelAndView doOther() {
		ModelAndView mv = new ModelAndView();
		mv.addObject("msg", "欢迎使用 SpringMVC 做web开发");
		mv.addObject("fun", "执行的是 doOther() 方法");
		mv.setViewName("other");
		return mv;
	}
}
