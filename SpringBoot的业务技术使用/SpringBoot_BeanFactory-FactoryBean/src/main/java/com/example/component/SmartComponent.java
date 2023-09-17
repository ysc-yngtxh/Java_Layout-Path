package com.example.component;

import com.example.service.TestService;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;

@Component
@Primary
public class SmartComponent implements TestService, ApplicationContextAware {

    private ApplicationContext applicationContext;
	
    private TestService autoSelect() {
        return (TestService) applicationContext.getBean("serviceFactory");
    }
   
    @Override
    public void doService() {
        autoSelect().doService();
    }

     @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
