package com.example.listener;

import com.example.util.DBUtil;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class OneListener implements ServletContextListener {

	public OneListener() {
		super();
	}

	// 在Tomcat启动时，预先创建20个Connection，在userDao.add方法执行时，将事先创建好connection交给add方法
	@Override
	public void contextInitialized(ServletContextEvent sce) {
		DBUtil util = new DBUtil();
		Map<Connection, Boolean> map = new HashMap<>();
		for (int i = 1; i <= 20; i++) {
			Connection conn = util.getCon();
			System.out.println("在Http服务器启动时，创建Connection" + conn);
			map.put(conn, true); // true表示这个通道处于空闲状态，false表示通道正在被使用
		}
		// 为了在Http服务器运行期间，一直都可以使用20个Connection,将connection保存到全局作用域对象
		ServletContext application = sce.getServletContext();
		application.setAttribute("key1", map);
	}// 局部变量map出方法就被销毁

	// 在Http服务器关闭时刻，将全局作用域对象20个Connection销毁
	@Override
	public void contextDestroyed(ServletContextEvent sce) {
		ServletContext application = sce.getServletContext();
		Map map = (Map) application.getAttribute("key1");
		Iterator it = map.keySet().iterator();
		while (it.hasNext()) {
			Connection conn = (Connection) it.next();
			if (conn != null) {
				System.out.println("兄弟们，我" + conn + "先行一步，20年后老子又是一条好汉");
			}
		}
	}

}
