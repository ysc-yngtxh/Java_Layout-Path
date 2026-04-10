# Kibana 详解

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

**方式三：Systemd 服务（生产环境）**
```ini
# /etc/systemd/system/kibana.service
[Unit]
Description=Kibana
Wants=network-online.target elasticsearch.service
After=network-online.target elasticsearch.service

[Service]
User=kibana
Group=kibana
ExecStart=/opt/kibana/bin/kibana
Restart=on-failure
RestartSec=5
LimitNOFILE=65536

[Install]
WantedBy=multi-user.target
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

## 三、配置文件详解

Kibana 主配置文件位于 `config/kibana.yml`

### 3.1 服务器配置

```yaml
# 监听端口
server.port: 5601

# 绑定地址（localhost 仅本地访问，0.0.0.0 允许外部访问）
server.host: "localhost"

# 服务器名称（用于重定向 URL）
server.name: "my-kibana-server"

# 公共基准 URL
server.basePath: ""

# 重写 URI 前缀
server.rewriteBasePath: true
```

### 3.2 中文语言支持

```yaml
# 设置界面语言为中文
i18n.locale: "zh-CN"

# 时区设置
server.timezone: "Asia/Shanghai"
```

### 3.3 Elasticsearch 连接配置

```yaml
# Elasticsearch 地址（支持多节点）
elasticsearch.hosts: ["http://localhost:9200", "http://192.168.1.100:9200"]

# 认证账号（建议使用 kibana_system 专用账号）
elasticsearch.username: "kibana_system"
elasticsearch.password: "你的密码"

# HTTPS 配置
elasticsearch.hosts: ["https://localhost:9200"]

# SSL 证书配置
elasticsearch.ssl.certificateAuthorities: ["/path/to/ca.crt"]

# SSL 验证模式
# certificate（默认，严格验证）、none（不验证）、warning（警告但不阻止）
elasticsearch.ssl.verificationMode: certificate

# 自定义客户端证书（双向认证）
elasticsearch.ssl.certificate: "/path/to/cert.pem"
elasticsearch.ssl.key: "/path/to/key.pem"
```

### 3.4 加密密钥配置（8.x+）

从 Kibana 8.x 开始，以下功能必须配置加密密钥：

| 功能 | 配置项 | 用途 |
|------|--------|------|
| 加密保存对象 | `xpack.encryptedSavedObjects.encryptionKey` | Alert、DataStream 等 |
| 报表生成 | `xpack.reporting.encryptionKey` | PDF 报表加密 |
| 安全加密 | `xpack.security.encryptionKey` | 敏感数据加密 |

**生成密钥命令：**
```bash
# 生成单个密钥
./bin/kibana-encryption-keys generate

# 输出示例
xpack.encryptedSavedObjects.encryptionKey: 9f2f164bcb3589eea3716bae3c3a67672afd1a9c0a4a01751fdc6bcb3b212b0e
```

**配置到 kibana.yml：**
```yaml
xpack.encryptedSavedObjects.encryptionKey: "9f2f164bcb3589eea3716bae3c3a67672afd1a9c0a4a01751fdc6bcb3b212b0e"
xpack.reporting.encryptionKey: "284a15ee9345b541d01d9165ed3dfde6fef1b18c155cb5f08b56bd73c16d8450"
xpack.security.encryptionKey: "afa1064cdc743feb132e2ba355872410cb7f5d983f7e1ef7eceb0985fe4dd77f"
```

> ⚠️ **重要提示**：
> - 密钥长度为 32 字节（64 个十六进制字符）
> - 生产环境请使用随机生成的密钥
> - 密钥丢失将无法解密已保存的数据
> - 集群环境中所有节点需使用相同密钥

### 3.5 禁用遥测服务

Kibana 默认会向 Elastic 遥测服务发送匿名使用统计。国内网络环境可能遇到 300ms 超时报错：

```yaml
# 完全禁用遥测
telemetry.optIn: false
telemetry.allowChangingOptInStatus: false
```

### 3.6 其他常用配置

```yaml
# 日志配置
logging.dest: stdout
logging.level: "info"
logging.appenders: file
logging.appenders.file.filename: logs/kibana.log

# 内存配置（JVM 堆外内存）
javaOptions: "-Xmx512m"

# 请求大小限制（字节）
server.maxPayloadBytes: 10485760

# 压缩响应
responseCompression.enabled: true

# 会话超时（分钟）
sessionTimeout: 720

# 列表项数量限制
listMaxItems: 5000
```

### 3.7 禁用 Agentless 功能

Kibana 8.x 引入的 Agentless 功能无需安装 Agent 即可采集云服务商数据，但要求有效 SSL 证书：

```yaml
# 开发环境禁用（避免自签名证书问题）
xpack.fleet.agentless.enabled: false
```

---

## 四、完整配置示例

### 4.1 开发环境配置

```yaml
# ======================== Kibana 开发配置 =========================

server.port: 5601
server.host: "0.0.0.0"
server.name: "dev-kibana"
server.timezone: "Asia/Shanghai"

# 语言
i18n.locale: "zh-CN"

# Elasticsearch 连接
elasticsearch.hosts: ["http://localhost:9200"]
elasticsearch.username: "elastic"
elasticsearch.password: "elastic"
elasticsearch.ssl.verificationMode: none

# 加密密钥
xpack.encryptedSavedObjects.encryptionKey: "devkey1234567890abcdef1234567890abcdef1234567890abcdef12345678"
xpack.reporting.encryptionKey: "devkey1234567890abcdef1234567890abcdef1234567890abcdef12345678"
xpack.security.encryptionKey: "devkey1234567890abcdef1234567890abcdef1234567890abcdef12345678"

# 禁用遥测
telemetry.optIn: false

# 禁用 Agentless
xpack.fleet.agentless.enabled: false

# 调试日志
logging.level: "debug"
```

### 4.2 生产环境配置

```yaml
# ======================== Kibana 生产配置 =========================

server.port: 5601
server.host: "127.0.0.1"  # 配合 Nginx 反向代理
server.name: "prod-kibana"
server.timezone: "Asia/Shanghai"

# HTTPS 配置
server.ssl.enabled: true
server.ssl.certificate: "/etc/ssl/certs/kibana.crt"
server.ssl.key: "/etc/ssl/private/kibana.key"

i18n.locale: "zh-CN"

# Elasticsearch 集群
elasticsearch.hosts: 
  - "https://es-node-1:9200"
  - "https://es-node-2:9200"
  - "https://es-node-3:9200"
elasticsearch.username: "kibana_system"
elasticsearch.password: "${ELASTIC_PASSWORD}"  # 环境变量
elasticsearch.ssl.certificateAuthorities: ["/etc/ssl/certs/ca.crt"]
elasticsearch.ssl.verificationMode: certificate

# 加密密钥（使用环境变量）
xpack.encryptedSavedObjects.encryptionKey: "${ENCRYPTION_KEY_1}"
xpack.reporting.encryptionKey: "${ENCRYPTION_KEY_2}"
xpack.security.encryptionKey: "${ENCRYPTION_KEY_3}"

# 安全加固
telemetry.optIn: false
security.encryptionKey: "${SECURITY_ENCRYPTION_KEY}"

# 日志
logging.dest: /var/log/kibana
logging.level: "warning"

# 性能优化
server.maxPayloadBytes: 10485760
responseCompression.enabled: true
```

---

## 五、核心功能使用

### 5.1 Discover 数据探索

- 支持 Lucene 查询语法和 KQL（Kibana Query Language）
- 可配置时间范围、命中数显示
- 支持字段过滤和高亮显示

### 5.2 Visualize 可视化

支持的图表类型：
- 柱状图、条形图
- 折线图、区域图
- 饼图、环形图
- 直方图
- 数据表
- 垂直计量仪、水平计量仪
- 聚合表格、Venny 图

### 5.3 Dashboard 仪表盘

- 拖拽布局调整
- 全局时间筛选器
- 行级权限控制
- 嵌入 iframe 支持

---

## 六、常见问题排查

### 6.1 启动失败

**问题：** `Port 5601 in use`
```bash
# 查找占用进程
lsof -i :5601

# 修改配置端口
server.port: 5602
```

**问题：** `Elasticsearch 未就绪`
```bash
# 检查 ES 状态
curl http://localhost:9200/_cluster/health?pretty

# 查看 Kibana 日志
tail -f logs/kibana.log
```

### 6.2 连接 Elasticsearch 失败

**症状：** `No living connections`

**排查步骤：**
```bash
# 1. 测试连通性
telnet localhost 9200

# 2. 验证账号密码
curl -u elastic:password http://localhost:9200

# 3. 检查 CORS 配置
cat config/elasticsearch.yml | grep xpack.security.http
```

### 6.3 索引模式无法创建

**原因：** 对应索引不存在或无数据

**解决：**
```bash
# 查看索引列表
GET _cat/indices?v

# 创建测试索引并写入数据
PUT test-index
POST test-index/_doc/1 { "message": "hello", "@timestamp": "2024-01-01T00:00:00Z" }
```

### 6.4 证书验证错误

**症状：** `SSL handshake failed` 或 `Certificate verification failed`

**解决：**
```yaml
# 方案一：导入 CA 证书
elasticsearch.ssl.certificateAuthorities: ["/path/to/ca.crt"]

# 方案二：开发环境关闭验证
elasticsearch.ssl.verificationMode: none
```

### 6.5 中文乱码

**解决：**
```yaml
# 确保配置了语言和时区
i18n.locale: "zh-CN"
server.timezone: "Asia/Shanghai"
```

---

## 七、最佳实践

### 7.1 安全建议

- 使用 HTTPS 加密传输
- 配置强密码策略
- 定期更新 Kibana 版本
- 最小权限原则配置账号
- 限制 IP 白名单访问

### 7.2 性能优化

- 合理设置 JVM 堆内存
- 启用响应压缩
- 配置适当的 listMaxItems
- 使用专业缓存层

### 7.3 运维建议

- 配置日志轮转
- 设置监控系统告警
- 定期备份 saved objects
- 建立变更审批流程

---

## 八、参考资源

| 资源 | 链接 |
|------|------|
| 官方文档 | https://www.elastic.co/guide/en/kibana/current/index.html |
| 配置参考 | https://www.elastic.co/guide/en/kibana/current/setup-kibana-yml.html |
| KQL 查询语法 | https://www.elastic.co/guide/en/kibana/current/kql.html |
| 中文版本文档 | https://www.elasticsearch.cn/guide/en/kibana/index.html |
| GitHub 源码 | https://github.com/elastic/kibana |