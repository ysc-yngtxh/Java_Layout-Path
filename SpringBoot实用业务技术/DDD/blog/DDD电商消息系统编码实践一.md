# DDD电商消息系统编码实践（一）

前面的三篇文章我们对DDD的一些基本知识做了介绍，从本文开始我们正式的进入实际的项目编码环节，本系列文章我们将围绕电商消息系统的需求一点点展开，每个章节我们完成一个核心的需求，同时为了体现DDD如何处理需求变化的过程，我们采用需求迭代的方式，而不是假设需求已经是不可变的。


> 如果你对DDD没有了解，强烈建议你先看下前面的文章；附上文章地址。

* [DDD概览](https://gitee.com/izhengyin/ddd-message/blob/master/blog/DDD%E6%A6%82%E8%A7%88.md)
* [DDD推荐的架构模式](https://gitee.com/izhengyin/ddd-message/blob/master/blog/DDD%E6%8E%A8%E8%8D%90%E7%9A%84%E6%9E%B6%E6%9E%84%E6%A8%A1%E5%BC%8F.md)
* [DDD充血模型编码实践](https://gitee.com/izhengyin/ddd-message/blob/master/blog/DDD%E5%85%85%E8%A1%80%E6%A8%A1%E5%9E%8B%E7%BC%96%E7%A0%81%E5%AE%9E%E8%B7%B5.md)

## 需求概览

> 本文我们将实现一个最基本的创建消息并将消息推送到不同终端的需求，通过本文你能够看到一个DDD项目的工程结构，以及各层之间如何配合。

![发送消息流程](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/message/send-message-flow.png)


在这个需求中，你可以把发送端理解为淘宝店主，接收端就是你的设备，你可能通过旺旺，也可能通过APP通知收到店主给你发的消息。

## 依据六边形架构来建立工程结构

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── MessageApplication.java
    │       │           ├── application
    │       │           ├── domain
    │       │           ├── infrastructure
    │       │           └── interfaces
```

* application 应用层
* domain 领域层
* interfaces 接口层
* infrastructure 基础层

还记得六边形的图吗? 我放在这里给大家回忆一下


![六边形架构](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/liubianxing.png)

## 领域层

> 源码 https://gitee.com/izhengyin/ddd-message/tree/part1/src/main/java/com/izhengyin/dddmessage/domain

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── domain
    │       │           │   ├── aggregate
    │       │           │   ├── service
    │       │           │   └── shared
    │       │           │       ├── Entity.java
    │       │           │       ├── ValueObject.java
    │       │           │       ├── constant
    │       │           │       ├── event
    │       │           │       └── facade
```

* aggregate 存放领域聚合，一个聚合中包括（实体、值对象、仓储抽象等）
* service 存放领域服务，领域服务不是必须的，领域服务可以帮助处理一些实体不便处理的事情，比如需要多个实体之间的配合来完成的事情，或者作为实体工厂来创建实体对象，在我们的示例中，[MessageDomainService](https://gitee.com/izhengyin/ddd-message/blob/part1/src/main/java/com/izhengyin/dddmessage/domain/service/impl/MessageDomainServiceImpl.java)就可以看做Message实体的工厂类。
* shared 领域之间共享的工具、常量，接口等，可以根据应用自行的扩展
    * Entity.java 实体接口声明
    * ValueObject.java 值对象接口声明
    * constant 常量、枚举类等
    * event 事件相应的接口封装
    * facade 外部服务门口接口抽象，实现由基础层实现



### 领域聚合划分

根据我们的需求中，我们可以划分为两个聚合，一个是消息聚合、一个是通知聚合，从业务上来讲它们有明显的边界，它们之间通过事件来传递数据。同时随着业务的演化，当两者变得越来越复杂，或者出于性能与外部复用性的考虑，我们可能将它们演化为两个不同的微服务，而这时通过聚合的边界，我们很方便就可以将它们彼此独立。

#### message聚合

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── domain
    │       │           │   ├── aggregate
    │       │           │   │   ├── message
    │       │           │   │   │   ├── MessageIdGenerator.java
    │       │           │   │   │   ├── entity
    │       │           │   │   │   │   ├── Message.java
    │       │           │   │   │   │   └── valueobject
    │       │           │   │   │   │       └── User.java
    │       │           │   │   │   ├── event
    │       │           │   │   │   │   └── MessageCreatedEvent.java
    │       │           │   │   │   └── repository
    │       │           │   │   │       └── MessageRepository.java
```

* MessageIdGenerator 消息ID生成抽象接口,由基础层实现
* entity（实体包）
    * Message.java 消息实体
    * valueobject （值对象包）
        * User.java 用户值对象
    * event (事件包)
        * MessageCreatedEvent.java 消息已创建事件
    * repository (仓储包)
        * MessageRepository.java 消息存储接口抽象

#### notice聚合

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── domain
    │       │           │   ├── aggregate
    │       │           │   │   ├── notice
    │       │           │   │   │   ├── AppMessagePublisher.java
    │       │           │   │   │   ├── SocketMessagePublisher.java
    │       │           │   │   │   ├── entity
    │       │           │   │   │   │   ├── Notice.java
    │       │           │   │   │   │   └── valueobject
    │       │           │   │   │   │       ├── AppMessage.java
    │       │           │   │   │   │       └── SocketMessage.java
    │       │           │   │   │   └── repository
    │       │           │   │   │       └── NoticeRepository.java
```

* AppMessagePublisher 移动端消息发布接口抽象
* SocketMessagePublisher 站内Socket消息发布接口抽象
* entity（实体包）
    * Notice.java 通知实体
    * valueobject （值对象包）
        * AppMessage.java 移动端消息值对象
        * SocketMessage.java  站内消息值对象
    * repository (仓储包)
        * NoticeRepository.java 通知存储接口抽象

## 应用层

> 源码 https://gitee.com/izhengyin/ddd-message/tree/part1/src/main/java/com/izhengyin/dddmessage/application

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── application
    │       │           │   ├── MessageCommandService.java
    │       │           │   ├── MessagePublisher.java
    │       │           │   ├── NoticeCommandService.java
    │       │           │   ├── impl
    │       │           │   │   ├── MessageServiceImpl.java
    │       │           │   │   └── NoticeServiceImpl.java
```

在应用层我们使用简单版本的CQRS(查询与命令分离的设计方式),这里的命令可以理解为更新，目前我们只抽象出Command接口，CQRS是为了解决相同的数据模型用于查询和更新，在这种情况模型会变得复杂（想象一下在一个传统应用下的Service层提供的getByXXX方法），完整的CQRS实现是比较复杂的，本文旨在用简单的实现体现CQRS的思想。

> 更多CQRS介绍，请阅读参考文章 : https://docs.microsoft.com/en-us/azure/architecture/patterns/cqrs

* MessageCommandService.java 创建消息命令接口
* NoticeCommandService.java 创建通知命令接口
* impl
    * MessageServiceImpl.java 实现 MessageCommandService 接口
    * NoticeServiceImpl.java 实现 NoticeCommandService 接口
* MessagePublisher 实现了在领域层通知聚合中定义的AppMessagePublisher ，SocketMessagePublisher 接口，此处考虑到可以通过应用接口直接发生通知的灵活性。


## 领域层与应用层的交互

写了这么多的文件描述，大家看起来可能会有点抽象了，那么在介绍基础层与接口层之前，我们先将领域层与应用层串联一下，加深对以上代码的理解。


### 创建一条新消息的流程

创建新消息由接口层，调用 MessageCommandService:createMessage 接口,MessageCommandService的实现调用MessageDomainService领域服务创建完成消息，最后发送消息已创建的事件 MessageCreatedEvent。

![创建一条新消息的流程](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/message/MessageCommandService_createMessage.png)

### 消息通知的流程

NoticeCommandService的实现监听MessageCreatedEvent的事件，创建通知实体，通过实体创建通知消息，分别调用AppMessagePublisher,SocketMessagePublisher，调用通知接口完成通知。

![消息通知的流程](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/message/NoticeCommandService_createNotice.png)


## 基础层

> 源码 : https://gitee.com/izhengyin/ddd-message/tree/part1/src/main/java/com/izhengyin/dddmessage/infrastructure

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           ├── infrastructure
    │       │           │   ├── InProcessMessageIdGenerator.java
    │       │           │   ├── client
    │       │           │   │   ├── ApnsServiceFacadeClient.java
    │       │           │   │   ├── HuaweiServiceFacadeClient.java
    │       │           │   │   ├── ImSocketServiceFacadeClient.java
    │       │           │   │   └── UserServiceFacadeClient.java
    │       │           │   ├── package-info.java
    │       │           │   ├── persistence
    │       │           │   │   ├── InMemoryMessageRepository.java
    │       │           │   │   ├── InMemoryNoticeRepository.java
    │       │           │   │   └── package-info.java
    │       │           │   └── utils
    │       │           │       └── SnowflakeIdGenerator.java
```

在我们目前的需求中基础层还是比较简单的，这来源于我们的克制，我们并不求一上来文章就像你展示一个完整的消息系统，因为我们的重点是DDD实践，而不是消息系统实践。

言归正传，在我们的基础层中基本都是对抽象接口的实现，这也是DDD推崇的DIP原则。

```
DIP:高层模块不应该依赖于底层模块，二者都应该依赖于抽象。抽象不应该依赖于细节，细节应该依赖于抽象。
```
* InProcessMessageIdGenerator.java 这个类是对，消息实体唯一ID生成器的实现
* client(外部调用的客户端)
    * ApnsServiceFacadeClient.java 模拟调用APNS服务接口
    * HuaweiServiceFacadeClient.java 模拟调用华为推送服务接口
    * ImSocketServiceFacadeClient.java 模拟调用ImSocketService推送接口
    * UserServiceFacadeClient.java 模拟调用用户服务接口，查询用户的信息
* persistence(持久层)
    * InMemoryMessageRepository.java 基于内存的消息仓储实现
    * InMemoryNoticeRepository.java 基于内存的通知仓储实现
* utils（工具包）
    * SnowflakeIdGenerator.java SnowflakeId生成器

## 接口层

> 源码 : https://gitee.com/izhengyin/ddd-message/tree/part1/src/main/java/com/izhengyin/dddmessage/interfaces

接口层在六边形架构中担任着适配不同输入 接口的角色，通过适配器将来着不同接口的输入进行转换，在交由应用层处理。然而接口的输入可能是有很大差别的，比如MQ的输入是一个有MQ所封装的Message对象，WEB的输入是由我们自定义的一个Command或者Query,如果将不同的适配器直接对接应用层，当应用层发生改变时，就会导致所有适配器的变更，显然是不合理的。
因此我在接口层，引入一个facede门面，来封装应用层可能的变化，对适配器提供统一的入口。

```
└── src
    ├── main
    │   └── java
    │       ├── com
    │       │   └── izhengyin
    │       │       └── dddmessage
    │       │           └── interfaces
    │       │               ├── command
    │       │               │   └── SendMessageCommand.java
    │       │               ├── facade
    │       │               │   ├── MessageServiceFacade.java
    │       │               │   ├── impl
    │       │               │   │   └── MessageServiceFacadeImpl.java
    │       │               │   └── package-info.java
    │       │               └── web
    │       │                   ├── Response.java
    │       │                   ├── RestControllerAdvice.java
    │       │                   └── controller
    │       │                       └── MessageController.java
```


* command (存放命令类的包)
    * SendMessageCommand.java 发送消息命令
* facade(适配应用层门面)
    * MessageServiceFacade.java 消息服务门面接口
    * impl
        * MessageServiceFacadeImpl.java 消息服务门面接口实现
* web (web接口适配）
    *  Response.java web接口Response包装类
    *  RestControllerAdvice.java AOP统一异常处理类
    *  controller
        *  MessageController.java 消息web接口

## 测试


### 单元测试

DDD单元测试可以按照工程接口编写相应的单元测试类，因为DDD每层直接都比较独立，通过接口、适配器方式进行交互，很方便的可以通过Mock实现来完成单元测试，本文中我们只编写了消息实体的单元测试，做一个示例。
限于篇幅就不将源码贴出来了，读者可以通过文末源码地址，去查看源码。

```
└── src
    └── test
        └── java
            └── com
                └── izhengyin
                    └── dddmessage
                        └── test
                            ├── domain
                            │   ├── aggregate
                            │   │   ├── message
                            │   │   │   └── MessageTest.java
```


### 集成测试

我们使用Idea,http接口测试插件编写,api-request.http文件,进行测试，读者可以根据源码在自己的电脑上完成。

```
#发消息
POST http://localhost:8080/message
Content-Type: application/json; charset=UTF-8

{"sender":1,"receiver": 2,"content": "xxx"}
```

### 发送消息完整的流程 （图大，新窗口查看）

![ 发送消息](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/message/MessageController_sendMessage.png)


### 消息通知完整的流程（图大，新窗口查看）

![消息通知](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/message/NoticeCommandService_createNotice_full.png)