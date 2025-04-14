LogStash

配置环境变量：LS_JAVA_HOME

启动命令：
1、logstash -e 'input { stdin {} } output { stdout {} }'
   -e                    表示 “executor”，执行
   input { stdin {} }    从标准输入（键盘）读取数据。
   output { stdout {} }  将数据输出到标准输出（控制台）。
2、logstash -e 'input { generator { count => 1 message => "哈啰" } } output { stdout {} }'
   generator  生成器，生成数据。
   count      生成数据的次数。
   message    生成的数据。
3、logstash -f ./logstash.conf
   -f：指定配置文件。
   logstash.conf：需要自己提供的配置文件，指定输入、过滤、输出。


logstash-codec-json_lines 是 Logstash 的一个编解码器插件，主要用于处理 每行一个独立 JSON 对象 的数据格式。
安装命令：logstash-plugin install logstash-codec-json_lines
使用示例：
```
input {
  stdin {
    message => "{'name':'张三','age':18}"
    codec => json_lines  # 指定编解码器，将JSON对象解析为字段
  }
}
output {
  stdout {
    codec => json_lines # 指定编解码器，将字段解析为JSON字符串
  }
}
```
