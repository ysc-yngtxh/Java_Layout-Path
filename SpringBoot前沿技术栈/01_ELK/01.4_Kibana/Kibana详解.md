## 一、简介

### 1.1 什么是 Kibana

Kibana 是 Elastic Stack（原 ELK Stack）的可视化组件，是一个开源的数据分析可视化平台，与 Elasticsearch 深度集成。

### 1.2 核心功能

| 功能模块 | 描述 |
|---------|------|
| **Discover** | 实时搜索和浏览日志数据，支持复杂查询 |
| **Visualize** | 创建多种图表类型（柱状图、折线图、饼图、热力图等） |
| **Dashboard** | 组合多个可视化组件，形成综合性监控面板 |
| **Canvas** | 像素级精准设计的富媒体仪表盘 |
| **Maps** | 地理空间数据可视化 |
| **Reports** | 生成 PDF 格式的报表 |
| **Alerting** | 配置告警规则，触发通知 |
| **Fleet** | 统一管理中心和代理 |

---

## 二、快速开始

### 2.1 安装方式

**方式一：压缩包部署**

```bash
# 下载解压
wget https://artifacts.elastic.co/downloads/kibana/kibana-8.11.0-linux-x86_64.tar.gz
tar -zxvf kibana-8.11.0-linux-x86_64.tar.gz
cd kibana-8.11.0-linux-x86_64

# 启动
./bin/kibana
```

**方式二：Docker 部署**

```bash
docker run -d \
  --name kibana \
  --publish 5601:5601 \
  --link elasticsearch:elasticsearch \
  docker.elastic.co/kibana/kibana:8.11.0
```


### 2.2 启动与访问

默认访问地址：`http://localhost:5601/`

```bash
# Linux/Mac
./bin/kibana

# Windows
bin\kibana

# 后台运行（Linux）
nohup ./bin/kibana &
```

访问效果如下：

![Kibana 主界面](src/main/resources/static/img.png)

![索引模式配置](src/main/resources/static/img_1.png)

![可视化创建](src/main/resources/static/img_2.png)

![仪表盘示例](src/main/resources/static/img_3.png)

---

在启动 Kibana 后，Kibana 会自动生成连接 Elasticsearch 的配置在 `Kibana.yml` 文件中，如下：
```yaml
# Elasticsearch 地址（支持多节点、Http、Https配置）
elasticsearch.hosts: ["http://localhost:9200", "https://192.168.1.100:9200"]

# 认证账号（建议使用 kibana_system 专用账号）
elasticsearch.username: "kibana_system"
elasticsearch.password: "你的密码"

# SSL 证书配置
elasticsearch.ssl.certificateAuthorities: ["/path/to/ca.crt"]

# SSL 验证模式
# certificate（默认，严格验证）、none（不验证）、warning（警告但不阻止）
elasticsearch.ssl.verificationMode: certificate

# 自定义客户端证书（双向认证）
elasticsearch.ssl.certificate: "/path/to/cert.pem"
elasticsearch.ssl.key: "/path/to/key.pem"
```

## 三、配置文件详解（根据需要进行配置）

Kibana 主配置文件位于 `config/kibana.yml`

```yaml
# ======================== Kibana 开发配置 =========================

server.port: 5601
server.host: "0.0.0.0"
server.name: "dev-kibana"
# 时区设置
server.timezone: "Asia/Shanghai"
# 语言
i18n.locale: "zh-CN"

# 从 Kibana 8.x 版本开始，必须配置密钥来支持加密功能的所有插件，包括 Fleet、Alerting、Actions、PDF 报表、敏感数据 等。
# 生成密钥命令：
  ./bin/kibana-encryption-keys generate
# 生成的加密密钥示例（将生成的加密密钥复制到 kibana.yml 文件中）
xpack.encryptedSavedObjects.encryptionKey: "9f2f164bcb3589eea3716bae3c3a67672afd1a9c0a4a01751fdc6bcb3b212b0e"
xpack.reporting.encryptionKey: "284a15ee9345b541d01d9165ed3dfde6fef1b18c155cb5f08b56bd73c16d8450"
xpack.security.encryptionKey: "afa1064cdc743feb132e2ba355872410cb7f5d983f7e1ef7eceb0985fe4dd77f"

# Kibana 默认会向 Elastic 遥测服务发送匿名使用统计。国内网络环境可能遇到 300ms 超时报错。所以在配置上禁用遥测，避免不必要的报错
telemetry.optIn: false
telemetry.allowChangingOptInStatus: false

# Kibana 8.x 引入的 Agentless 功能无需安装任何软件，就能直接把云服务（比如 AWS、Azure、GCP）的数据接入 Elastic，但要求Kibana 提供有效 SSL 证书。
# 开发环境直接禁用Agentless（避免自签名证书问题）
xpack.fleet.agentless.enabled: false

# 调试日志
logging.level: "debug"
```


## 四、核心功能使用

### 4.1 Discover 数据探索

- 支持 Lucene 查询语法和 KQL（Kibana Query Language）
- 可配置时间范围、命中数显示
- 支持字段过滤和高亮显示

### 4.2 Visualize 可视化

支持的图表类型：

- 柱状图、条形图
- 折线图、区域图
- 饼图、环形图
- 直方图
- 数据表
- 垂直计量仪、水平计量仪
- 聚合表格、Venny 图

### 4.3 Dashboard 仪表盘

- 拖拽布局调整
- 全局时间筛选器
- 行级权限控制
- 嵌入 iframe 支持

---
