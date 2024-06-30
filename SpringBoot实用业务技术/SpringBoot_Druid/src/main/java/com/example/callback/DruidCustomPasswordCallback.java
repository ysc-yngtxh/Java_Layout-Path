package com.example.callback;

import com.alibaba.druid.util.DruidPasswordCallback;
import java.util.Properties;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;

@Slf4j
public class DruidCustomPasswordCallback extends DruidPasswordCallback {

    @Override
    public void setProperties(Properties properties) {
        super.setProperties(properties);
        // 获取配置文件中的已经加密的密码（spring.datasource.druid.connect-properties.password）
        String pwd = (String)properties.get("password");
        if (StringUtils.hasText(pwd)) {
            try {
                // 这里的代码是将密码进行解密，并设置
                String password = "131474";
                setPassword(password.toCharArray());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}