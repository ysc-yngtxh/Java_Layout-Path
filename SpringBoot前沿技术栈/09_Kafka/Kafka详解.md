
## Mac电脑M2芯片使用 homebrew 安装 Kafka
<figure>
  <h3>

    Apache Kafka 3.9.x 版本之前是整合了Zookeeper使用的，即开启 Kafka 服务同时也需要开启 Zookeeper 服务.
    而 3.9.x 版本开始，Kafka 引入了 KRaft 模式（Kafka Raft Metadata Mode）。
    标志着 Kafka 逐步摆脱对 Apache ZooKeeper的依赖。且KRaft 模式与 ZooKeeper 模式数据不兼。
    从Kafka 4.0 起，ZooKeeper 将彻底移除，所有版本将完全基于 KRaft 模式运行。

  ## Kafka 3.9.x 版本 使用 KRaft 模式

  ### 1. 生成集群唯一 ID
  <figure>

  ```
  # 在目录 /opt/homebrew/Cellar/kafka/3.9.0/libexec/bin 下执行
  sh kafka-storage.sh random-uuid
  ```
  </figure>

### 2. 修改配置文件
  <figure>

  注意: 使用 homebrew 安装的 Kafka 版本，配置文件在 `/opt/homebrew/etc/kafka/server.properties` 目录下
  ```
  broker.id=0
  # 启用 KRaft 模式
  process.roles=broker,controller
  # 设置集群 ID（替换为你的生成的 ID）
  cluster.id=tM3-P24CQlGgg85tMKQKXA
  # 控制器配置
  controller.quorum.voters=0@localhost:9093
  controller.listener.names=CONTROLLER
  # 监听地址
  listeners=PLAINTEXT://localhost:9092,CONTROLLER://localhost:9093
  advertised.listeners=PLAINTEXT://localhost:9092
  listener.security.protocol.map=PLAINTEXT:PLAINTEXT,CONTROLLER:PLAINTEXT
  # 数据存储目录
  log.dirs=/opt/homebrew/Cellar/kafka/3.9.0/libexec/logs
  ```
  </figure>

### 3. 初始化存储目录
  <figure>

  ```
  # 在目录 /opt/homebrew/Cellar/kafka/3.9.0/libexec/bin 下执行
  sh kafka-storage.sh format -t 【生成集群的唯一ID】 -c /opt/homebrew/etc/kafka/server.properties
  ```
  </figure>

### 4. 启动 Kafka
  <figure>

  ```   
  brew services start kafka
  ```
  </figure>
  </h3>
</figure>

## Kafka介绍
参考教程（https://www.liangjunfeng.cn/docs/kafka/advance/producer）
Apache Kafka是一个分布式流处理平台。它最初由LinkedIn开发，后来成为Apache软件基金会的一部分，并在开源社区中得到了广泛应用。Kafka也是一个消息服务器，它的特点一是快，二是有巨大的吞吐量.

 Kafka的核心概念包括Producer、Consumer、Broker、Topic、Partition和Offset。

    Producer：生产者，负责将数据发送到Kafka集群。
    Consumer：消费者，从Kafka集群中读取数据。
    Broker：Kafka服务器实例，Kafka集群通常由多个Broker组成。
    Topic：主题，数据按主题进行分类。
    Partition：分区，每个主题可以有多个分区，用于实现并行处理和提高吞吐量。可等同于 RocketMQ 中的 Queue 队列
    Offset：偏移量，每个消息在其分区中的唯一标识。
使用场景
    日志收集：集中收集系统日志和应用日志，通过Kafka传输到大数据处理系统。
    消息队列：作为高吞吐量、低延迟的消息队列系统。
    数据流处理：实时处理数据流，用于实时分析、监控和处理。
    事件源架构：将所有的变更事件存储在Kafka中，实现事件溯源和回放。
    流数据管道：构建数据管道，连接数据源和数据存储系统。


Kafka如何支持十万甚至百万的并发呢？答案是分区。Kafka的一个Topic可以有一个至多个Partition，并且可以分布到多台机器上。
Kafka的分区是有序的，Kafka会为每个Partition分配一个唯一的ID。
Producer在发送消息时，会根据某种规则将消息发送到不同的Partition中。
Consumer在消费消息时，也会根据Partition ID来读取消息。

Kafka只保证在一个Partition内部，消息是有序的，但是，存在多个Partition的情况下，Producer发送的3个消息会依次发送到Partition-1、Partition-2和Partition-3，Consumer从3个Partition接收的消息并不一定是Producer发送的顺序，因此，多个Partition只能保证接收消息大概率按发送时间有序，并不能保证完全按Producer发送的顺序。这一点在使用Kafka作为消息服务器时要特别注意，对发送顺序有严格要求的Topic只能有一个Partition。

Kafka的另一个特点是消息发送和接收都尽量使用批处理，一次处理几十甚至上百条消息，比一次一条效率要高很多。

最后要注意的是消息的持久性。Kafka总是将消息写入Partition对应的文件，消息保存多久取决于服务器的配置，可以按照时间删除（默认3天），也可以按照文件大小删除，因此，只要Consumer
在离线期内的消息还没有被删除，再次上线仍然可以接收到完整的消息流。这一功能实际上是客户端自己实现的，客户端会存储它接收到的最后一个消息的offsetId，再次上线后按上次的offsetId查询。offsetId是Kafka标识某个Partition的每一条消息的递增整数，客户端通常将它存储在ZooKeeper中。

                ┌ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┐
                 Topic
                │                   │
                    ┌───────────┐        ┌──────────┐
                │┌─▶│Partition-1│──┐│┌──▶│Consumer-1│
                 │  └───────────┘  │ │   └──────────┘
    ┌────────┐  ││  ┌───────────┐  │││   ┌──────────┐
    │Producer│───┼─▶│Partition-2│──┼─┼──▶│Consumer-2│
    └────────┘  ││  └───────────┘  │││   └──────────┘
                 │  ┌───────────┐  │ │   ┌──────────┐
                │└─▶│Partition-3│──┘│└──▶│Consumer-3│
                    └───────────┘        └──────────┘
                └ ─ ─ ─ ─ ─ ─ ─ ─ ─ ┘

可靠性配置
acks 是跟可靠性相关的生产者重要配置，它控制发送的消息的持久性。 允许以下设置：

acks=0 如果设置为零，则生产者根本不会等待来自服务器的任何确认。 该记录将立即添加到套接字缓冲区并被视为已发送。 在这种情况下不能保证服务器已经收到记录，并且重试配置不会生效（因为客户端通常不会知道任何失败）。 如果你要的是吞吐量，对于部分消息的丢失能接受，可以考虑这样设置。比如web服务的访问日志。

acks=1 这意味着Leader节点会将记录写入其本地日志，但会在不等待所有追随者的完全确认的情况下做出响应。 在这种情况下，如果Leader节点在确认记录后但在追随者复制它之前立即失败，那么记录将丢失。

acks=all 这意味着Leader节点将等待完整的同步副本集来确认记录。 这保证了只要至少一个同步副本保持活动状态，记录就不会丢失。 这是最有力的保证。

默认情况下：acks=all, 也就是会采用最可靠的配置。这一节写了这么多，其实就是告诉大家，acks这个参数你啥也不干就是最可靠的。
