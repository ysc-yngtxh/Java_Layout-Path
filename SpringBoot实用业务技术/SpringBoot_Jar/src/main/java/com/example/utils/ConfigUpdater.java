package com.example.utils;

import org.springframework.stereotype.Component;
import org.yaml.snakeyaml.DumperOptions;
import org.yaml.snakeyaml.Yaml;

import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.Map;

/**
 * @author 游家纨绔
 * @dateTime 2024-02-22 15:57
 * @apiNote TODO 用于动态修改bootstrap.yml配置文件
 */
@Component
public class ConfigUpdater {
    public void updateLoadJars(List<String> jarNames) throws IOException {
        // 读取bootstrap.yml
        Yaml yaml = new Yaml();
        InputStream inputStream = new FileInputStream(new File("src/main/resources/bootstrap.yml"));
        Map<String, Object> obj = yaml.load(inputStream);
        inputStream.close();

        obj.put("loadjars", jarNames);

        // 修改
        FileWriter writer = new FileWriter(new File("src/main/resources/bootstrap.yml"));
        DumperOptions options = new DumperOptions();
        options.setDefaultFlowStyle(DumperOptions.FlowStyle.BLOCK);
        options.setPrettyFlow(true);
        Yaml yamlWriter = new Yaml(options);
        yamlWriter.dump(obj, writer);
    }
}
