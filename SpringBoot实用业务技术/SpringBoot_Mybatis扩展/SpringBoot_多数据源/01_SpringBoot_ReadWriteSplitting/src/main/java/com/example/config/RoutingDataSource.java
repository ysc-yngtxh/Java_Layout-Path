package com.example.config;

import com.example.datasource_holder.DataSourceContextHolder;
import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

public class RoutingDataSource extends AbstractRoutingDataSource {

    /**
     * SpringBoot提供了 AbstractRoutingDataSource 根据用户定义的规则选择当前的数据源，
     * 这样我们可以在执行查询之前，设置使用的数据源。实现可动态路由的数据源，在每次数据库查询操作前执行。
     * 它的抽象方法 determineCurrentLookupKey() 决定使用哪个数据源。
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDataSourceType();
    }
}