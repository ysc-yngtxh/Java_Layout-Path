package com.cn.leyou.service;

import com.cn.leyou.mapper.UserMapper;
import com.cn.leyou.pojo.User;
import com.cn.leyou.enums.ExceptionEnum;
import com.cn.leyou.exception.LyException;
import com.cn.leyou.utils.NumberUtils;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

import javax.validation.Valid;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Service
public class UserService {

    @Autowired
    private UserMapper userMapper;


    public Boolean checkData(String data, Integer type) {
        User user = new User();
        //判断数据类型
        switch(type){
            case 1:
                user.setUsername(data);
                break;
            case 2:
                user.setPhone(data);
                break;
            default:
                throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        return userMapper.selectCount(user) == 0;
    }

    @Autowired
    private AmqpTemplate amqpTemplate;
    @Autowired
    private StringRedisTemplate redisTemplate;

    private static final String KEY_PREFIX = "user:verify:phone";

    public void sendCode(String phone) {
        //生成key
        String key = KEY_PREFIX + phone;
        //生成验证码
        String code = NumberUtils.generateCode(6);  //这个是工具类的方法，可以点进去验证
        Map<String,String> msg = new HashMap<>();
        msg.put("phone",phone);
        msg.put("code",code);
        //发送验证码
        amqpTemplate.convertAndSend("ly.sms.exchange","sms.verify.code",msg);
        //这里的参数严谨点应该是放在配置文件中的，因为参数不是一成不变的

        //保存验证码
        redisTemplate.opsForValue().set(key,code,5, TimeUnit.MINUTES);

    }

    public void register(@Valid User user, String code) {
        //从redis取出验证码
        String cacheCode =redisTemplate.opsForValue().get(KEY_PREFIX + user.getPhone());
        //校验验证码
        if(!StringUtils.equals(code,cacheCode)){
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }

        //生成盐
        String salt = "sdbi2wr7qwfb8qwe";
        user.setSalt(salt);
        //对密码加密
        user.setPassword(DigestUtils.md5Hex(DigestUtils.md5Hex(user.getPassword()) + salt));
        //写入数据库
        user.setCreated(new Date());
        userMapper.insert(user);
    }

    public User queryUserByUsername(String username, String password) {

        //查询用户
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);
        User user1 = userMapper.selectOne(user);
        //校验
        if(user1==null){
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        //校验密码
        if(!StringUtils.equals(user1.getPassword(),
                DigestUtils.md5Hex(DigestUtils.md5Hex(password) + user1.getSalt())
                               )
          ){
            throw new LyException(ExceptionEnum.INVALID_FILE_TYPE);
        }
        return user1;
    }
}
