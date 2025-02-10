package com.example.service;

import com.alibaba.fastjson.JSON;
import com.baomidou.mybatisplus.core.toolkit.Wrappers;
import com.example.entity.Credit;
import com.example.entity.User;
import com.example.pojo.UserCharge;
import com.example.mapper.MQTxLogMapper;
import com.example.mapper.UserMapper;
import com.example.entity.MQTxLog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author 游家纨绔
 * @dateTime 2025-02-08 21:00
 * @apiNote TODO
 */
@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private MQTxLogMapper mqTxLogMapper;

    /**
     * 用户增加余额+事务日志
     */
    @Transactional(rollbackFor = Exception.class)
    public void addBalance(UserCharge userCharge, String transactionId) {
        // 1. 增加余额
        userMapper.update(Wrappers.<User>lambdaUpdate()
                .eq(User::getId, userCharge.getUserId())
                .setSql("balance = balance - " + userCharge.getChargeAmount().intValue())
        );

        // 2. 写入mq事务日志
        saveMQTransactionLog(transactionId, userCharge);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveMQTransactionLog(String transactionId, UserCharge userCharge) {
        MQTxLog mqTxLog = MQTxLog.builder()
                .transactionId(transactionId)
                .log(JSON.toJSONString(userCharge))
                .build();
        mqTxLogMapper.insert(mqTxLog);
    }
}

