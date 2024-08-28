package com.example.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * nacos配置中，修改sjzl-loadjars.yml
 *
 * @author lijianyu
 * @date 2023/04/19 17:59
 **/
@Component
public class NacosConfigUtil {

    private static Logger logger = LoggerFactory.getLogger(NacosConfigUtil.class);

    @Autowired
    private NacosConfig nacosConfig;

    private String dataId = "sjzl-loadjars.yml";

    @Value("${spring.cloud.nacos.config.group}")
    private String group;

    /**
     * 从nacos配置文件中，添加初始化jar包配置
     *
     * @param jarName 要移除的jar包名
     * @throws Exception
     */
    public void addJarName(String jarName) throws Exception {
        ConfigService configService = nacosConfig.configService();
        String content = configService.getConfig(dataId, group, 5000);
        // 修改配置文件内容
        YAMLMapper yamlMapper = new YAMLMapper();
        ObjectMapper jsonMapper = new ObjectMapper();
        Object yamlObject = yamlMapper.readValue(content, Object.class);

        String jsonString = jsonMapper.writeValueAsString(yamlObject);
        JSONObject jsonObject = JSONObject.parseObject(jsonString);
        List<String> loadjars;
        if (jsonObject.containsKey("loadjars")) {
            loadjars = (List<String>) jsonObject.get("loadjars");
        } else {
            loadjars = new ArrayList<>();
        }
        if (!loadjars.contains(jarName)) {
            loadjars.add(jarName);
        }
        jsonObject.put("loadjars", loadjars);

        Object yaml = yamlMapper.readValue(jsonMapper.writeValueAsString(jsonObject), Object.class);
        String newYamlString = yamlMapper.writeValueAsString(yaml);
        boolean b = configService.publishConfig(dataId, group, newYamlString);

        if (b) {
            logger.info("nacos配置更新成功");
        } else {
            logger.info("nacos配置更新失败");
        }
    }
}
