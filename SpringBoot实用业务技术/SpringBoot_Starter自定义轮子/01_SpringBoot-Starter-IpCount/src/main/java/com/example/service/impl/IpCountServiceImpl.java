package com.example.service.impl;

import com.example.config.IpAutoConfiguration;
import com.example.enums.DisplayModeEnum;
import com.example.service.IpCountService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-20 19:25
 * @apiNote TODO
 */
public class IpCountServiceImpl implements IpCountService {

    @Autowired
    private HttpServletRequest request;
    @Autowired
    private IpAutoConfiguration ipAutoConfiguration;

    private final Map<String, Integer> map = new HashMap<>();

    public void IpCount() {
        // 获得客户端的ip地址
        String remoteAddr = request.getRemoteAddr();
        // 访问次数
        Integer i = map.get(remoteAddr);
        int i1 = Objects.isNull(i) ? 1 : ++i;
        // 更新
        map.put(remoteAddr, i1);
        // 输出
        map.forEach((key, value) -> {
            if (ipAutoConfiguration.getDisplay().equals(DisplayModeEnum.SIMPLE.getMode())) {
                System.out.println("id: " + key + "\tcount: " + value);
            } else {
                System.out.println("\t\t\tip访问监控");
                System.out.println("+----------ip----------+----count----+");
                System.out.println("|      " + key + "       |      " + value + "      |");
                System.out.println("+----------------------+-------------+\n");
            }
        });
    }
}
