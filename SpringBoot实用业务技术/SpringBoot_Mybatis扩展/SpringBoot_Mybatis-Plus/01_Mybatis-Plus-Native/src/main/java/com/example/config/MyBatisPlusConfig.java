package com.example.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.handler.TenantLineHandler;
import com.baomidou.mybatisplus.extension.plugins.inner.BlockAttackInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.TenantLineInnerInterceptor;
import com.example.handler.MyTenantLineHandler;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisPlusConfig {

	/**
	 * 注册分页插件
	 */
	@Bean
	public MybatisPlusInterceptor mybatisPlusInterceptor() {

		MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

		// 添加租户插件
		// 如果用了分页插件注意先 add TenantLineInnerInterceptor 再 add PaginationInnerInterceptor
		TenantLineInnerInterceptor tenantLineInnerInterceptor = new TenantLineInnerInterceptor();
		TenantLineHandler myTenantLineHandler = new MyTenantLineHandler();
		tenantLineInnerInterceptor.setTenantLineHandler(myTenantLineHandler);
		interceptor.addInnerInterceptor(tenantLineInnerInterceptor);

		// 添加分页插件
		PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor();
		// 设置请求的页面大于最大页后操作，true调回到首页，false继续请求。默认false
		pageInterceptor.setOverflow(false);
		// 单页分页条数限制，默认无限制
		pageInterceptor.setMaxLimit(500L);
		// 设置数据库类型
		pageInterceptor.setDbType(DbType.MYSQL);
		interceptor.addInnerInterceptor(pageInterceptor);

		// 添加乐观锁
		interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
		// 防全表更新与删除插件
		interceptor.addInnerInterceptor(new BlockAttackInnerInterceptor());
		return interceptor;
	}
}
