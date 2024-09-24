package com.example.aop;

import com.example.annotation.Master;
import com.example.annotation.Slave;
import com.example.datasource_holder.DataSourceContextHolder;
import com.example.constant.DataSourceType;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class DataSourceAspect {

    @Before("@annotation(master)")
    public void setMasterDataSource(JoinPoint joinPoint, Master master) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.MASTER);
    }

    @Before("@annotation(slave)")
    public void setSlaveDataSource(JoinPoint joinPoint, Slave slave) {
        DataSourceContextHolder.setDataSourceType(DataSourceType.SLAVE);
    }

    @After("@annotation(master) || @annotation(slave)")
    public void clearDataSourceType(JoinPoint joinPoint, Master master, Slave slave) {
        DataSourceContextHolder.clearDataSourceType();
    }
}
