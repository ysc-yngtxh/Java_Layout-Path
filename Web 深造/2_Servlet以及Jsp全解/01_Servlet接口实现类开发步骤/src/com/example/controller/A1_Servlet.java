package com.example.controller;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * idea快捷功能：
 * Run --> Edit Configurations --> Tomcat server --> server --> on "Update" action 和 on frame deactivation都选上Update classes and resources
 * (表示的意思是动态资源和静态资源更新后会立即生效，不用再进行重启Tomcat。直接在浏览器界面进行刷新，可以实现资源的更新)
 *
 * 抽象类作用：降低接口实现类对接口实现过程难度，将接口中不需要使用抽象方法交给抽象类进行完成，
 *           这样接口实现类只需要对接口需要方法进行重写
 * Servlet接口：
 *             init
 *             getServletConfig
 *             getServletInfo
 *             destory------------------四个方法对于Servlet接口实现类没用
 *             Service()-------有用
 *
 * Tomcat根据Servlet规范调用servlet接口实现类规则：
 *   1、Tomcat有权创建servlet接口实现类实例对象    Servlet com.example.controller.A1_Servlet = new com.example.controller.A1_Servlet();
 *   2、Tomcat根据实例对象调用Service方法处理当前请求   com.example.controller.A1_Servlet.service()
 *
 *   com.example.controller.A1_Servlet---------->(abstract)HttpServlet---------->(abstract)GenericServlet------------>servlet接口
 *                                    (extends)                        (extends)       init              (implements)
 *                                                                                     getServletConfig
 *                                                                                     getServletInfo
 *                                                                                     destory
 */
public class A1_Servlet extends HttpServlet {

	public A1_Servlet() {
		System.out.println("A1Servlet类被创建实例对象");
	}

	// 浏览器中有七种请求方式，CTRL+O可以看到，选择get和post方式进行重写
	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("A1_Servlet类针对浏览器发送Get请求方式处理");
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) {
		System.out.println("A1_Servlet类针对浏览器发送Post请求方式处理");
	}

}
