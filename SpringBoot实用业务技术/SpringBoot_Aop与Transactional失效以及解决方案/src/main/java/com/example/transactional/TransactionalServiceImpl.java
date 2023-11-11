package com.example.transactional;

import lombok.Getter;
import org.springframework.aop.framework.AopContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2023-11-11 14:48
 * @apiNote TODO
 */
@Service
public class TransactionalServiceImpl implements TransactionalService {

    public volatile int YSC = 0;

    @Override
    @Transactional
    public void saveUser() {
        YSC++;
        throw new RuntimeException("故意抛异常，制造回滚");
    }

    @Override
    @Transactional
    public void saveSigUser() {
        System.out.println("执行saveSigUser()方法...");
        try {
            YSC++;
        } catch (Exception e) {
            System.out.println(YSC);
        }
        this.saveUser();
    }

    @Override
    public void saveAllUser() {
        System.out.println("执行saveAllUser()方法...");
        YSC++;
        TransactionalService currentProxy = (TransactionalService) AopContext.currentProxy();
        currentProxy.saveUser();
    }
}
