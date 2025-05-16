# DDD电商消息系统编码实践（二）

上篇文章中，我们通过一个最基本的创建消息需求，整体的展示了DDD的架构分层，各个模块的分工与配合。
在这篇文章中，我们将通过对创建消息增加一些业务规则的处理来看下在DDD中如何实现。

## 需求概览

1. 未读消息功能，判断消息接收方是否读取了消息。 (
   注：消息已读未读是消息系统一个比较核心的功能，这里只是处理发送时的一个未读状态检查)
2. 违禁词功能，当消息内容包含违禁词时，消息发送失败，提示发送方原因。
3. 安全验证功能，当消息内容包含不安全的信息时，消息发送失败，提示发送方原因。
4. 黑名单功能，当接收方将发送方加入黑名单时，消息发送失败，提示发送方原因。

> 正式开始需求前，我们先了解一些概念

## 规约模式

### 什么是规约模式？

网上对规约模式的介绍很多，个人觉得规约模式核心是对业务规则的封装与组合，而规约模式的实现完全可以灵活操作。


> 维基百科对于规约模式的介绍

> In computer programming, the specification pattern is a particular software
> design pattern, whereby business rules can be recombined by chaining the
> business rules together using boolean logic. The pattern is frequently used in
> the context of domain-driven design.

> 个人理解,非翻译

1. 规约模式是一个用于处理业务规则特定的设计模式。
2. 规约模式通过将单个的业务规则进行封装，在通过boolean的方式(and,or,not)
   进行结合为更丰富的业务规则。
3. 规约模式通常被用于DDD中。

### 什么时候使用规约模式？

> 以下是笔者的个人总结

1. 当代码中有重复的业务验证规则，可以使用规约模式进行抽象，提高其复用性。
2. 业务验证过于繁琐代码臃肿，可以使用规约模式进行抽象，避免单类代码膨胀，提高其可读性。
3. 依赖于外部SDK或者服务时，可以使用规约模式进行抽象，避免单类对外依赖太重，提高其扩展性。

### 规约模式的基础实现

![规约模式-uml](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/specification-pattern-uml.png)

> 规约模式基础实现包括5个类,本文直接将 https://github.com/citerus/dddsample-core
> 中的基础类拷贝到项目中，不做重复实现。

* Specification
* AbstractSpecification
* NotSpecification
* OrSpecification
* AndSpecification

通过这些类，可以将业务规则单元通过OR、AND、NOT的方式进行重新组合，形成更丰富的业务规则，通过调用
isSatisfiedBy 方法，来判断是否满足业务规则。

```
/**
* Check if {@code t} is satisfied by the specification.
*
* @param t Object to test.
* @return {@code true} if {@code t} satisfies the specification.
*/
boolean isSatisfiedBy(T t);
```

>
具体的使用在此就不赘述了，点击源码一看即明。https://gitee.com/izhengyin/ddd-message/tree/master/src/main/java/com/izhengyin/dddmessage/domain/shared/specification

### 未读消息功能实现

1. 我们在message聚合下创建一个 specification 目录 ，这个目录用于放置我们的业务规则单元类，新建
   ReadSpecification 类，在此类中实现未读消息判断的业务逻辑。

>
消息发送中判断接收方是否读取了消息，可以通过socket维护一个是否在对话的状态,也就是说当你正在打开聊天窗口和别人对话时，此时收到推送消息，那么就是已读的（具体你的眼睛有没有看屏幕，这个不会去判断）。

```
@Component
public class ReadSpecification extends AbstractSpecification<Message> {
    /**
     * 返回 true 代表消息已读 , false 代表消息未读
     */
    @Override
    public boolean isSatisfiedBy(Message message) {
        //mock正在对话
        boolean isTalking = message.getReceiver().getUserId() % 3 == 0;
        if(isTalking){
            return true;
        }
        return false;
    }
}
```

2. 在Message实体中，添加一个方法用于处理未读消息状态

```
/**
 * 根据 {@link ReadSpecification} 处理消息未读状态
 * @param ReadSpecification
 * @return
 */
public Message handleReadStatusBy(ReadSpecification readSpecification){
    this.read = false;
    if(readSpecification.isSatisfiedBy(this)){
        this.read = true;
    }
    return this;
}
```

3. 在 MessageDomainServiceImpl 中调用，handleReadStatusBy 方法，处理未读消息逻辑

```
message.handleReadStatusBy(readSpecification);
```

### 违禁词、安全、黑名单实现

违禁词、安全、黑名单在业务场景上的处理上可以有两种不一样的方式

1. 简单粗暴、只有不符合任何一个的验证规则，消息告知用户发送失败，不区分为什么失败
2. 为消息增加一个标识，明确的标识出为什么消息发送失败，比如是触发了黑名单规则，提示用户被对方拉黑了

接下来分别看下两种不同是规约实现

假设我们有了违禁词、安全、黑名单的规约类，分别是

* StopWordSpecification
* SafetySpecification
* BlockedSpecification

> 具体实现，下面会介绍,此处暂且跳过

1. 通过组合方式合并规约

```
    Specification<Message> specification = new AndSpecification<Message>(blockedSpecification,safetySpecification)
            .and(stopWordSpecification);

    Message message = messageDomainService.createMessage(1L, MessageCategory.CHAT,1,2,"你好");
    Assertions.assertTrue(specification.isSatisfiedBy(message));
    //触发违禁词
    message = messageDomainService.createMessage(1L,MessageCategory.CHAT,1,2,"你好,线下交易");
    Assertions.assertFalse(specification.isSatisfiedBy(message));
    //触发安全词
    message = messageDomainService.createMessage(1L,MessageCategory.CHAT,1,2,"支付宝点击付款码，截个图。");
    Assertions.assertFalse(specification.isSatisfiedBy(message));
    //触发黑名单
    message = messageDomainService.createMessage(1L,MessageCategory.CHAT,100,1,"你好");
    Assertions.assertFalse(specification.isSatisfiedBy(message));
```

这种方式可以满足第一种简单粗暴的处理方式，但因为外部并不能够去知道具体的细节，因此不能满足第二种精细化的需求。

2. 自定义一个可交互的规约 （注意：这是笔者自行探索的方式）

这种方式在不满足规约时，可以自定义一个handle进行处理,具体实现见下文

```
        InteractiveSpecification<Message, MessageStatus> specification = new AndInteractiveSpecification<>(blockedSpecification,safetySpecification,stopWordSpecification);
        Message message = messageDomainService.createMessage(1L,MessageCategory.CHAT,1,2,"你好");
        Assertions.assertTrue(specification.isSatisfiedBy(message));
        //触发违禁词
        message = messageDomainService.createMessage(1L,MessageCategory.CHAT,1,2,"你好,线下交易");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.HAS_STOP_WORD,status));
        //触发安全词
        message = messageDomainService.createMessage(1L,MessageCategory.CHAT,1,2,"你好,支付宝");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.UN_SAFE,status));
        //触发黑名单
        message = messageDomainService.createMessage(1L,MessageCategory.CHAT,100,1,"你好");
        Assertions.assertFalse(specification.isSatisfiedBy(message));
        specification.notSatisfiedHandleBy(message,status -> Assertions.assertEquals(MessageStatus.IN_BLOCKED_LIST,status));

```

#### 实现违禁词、安全、黑名单的精细化交互需求

1. 增加一个消息状态属性，标识消息的特定场景信息

```
@AllArgsConstructor
@Getter
public enum MessageStatus implements ValueObject<MessageStatus> {
    /**
     * 正常 ，发送方接受方均可见
     */
    NORMAL((byte)0),
    /**
     * 在接受方的黑名单中 , 发送方可见,接收方不可见
     */
    IN_BLOCKED_LIST((byte)1),
    /**
     * 消息中包含违禁词 , 发送方可见,接收方不可见
     */
    HAS_STOP_WORD ((byte)3),
    /**
     * 消息不安全 , 发送方可见,接收方不可见
     */
    UN_SAFE ((byte)4);
    /**
     * 消息状态编码
     */
    private final byte code;
    @Override
    public boolean sameValueAs(MessageStatus other) {
        return other != null && other.getCode() == this.code;
    }
}

```

InteractiveSpecification 定义如下,新增一个handle处理交互场景

```
/**
 * 可交互的 specification
 *     扩展自 {@link Specification} , 新增 notSatisfiedHandleBy 当处理的结果不令人满意时调用，handle 处理
 * @author zhengyin
 * Created on 2021/7/5
 */
public interface InteractiveSpecification<T,R> extends Specification<T>{
    /**
     * 当验证结果不满意时，调用 handle 进行处理
     * @param t
     * @param handle
     */
    void notSatisfiedHandleBy(T t , Consumer<R> handle);
}
```

### 通过 InteractiveSpecification 来组合违禁词、安全、黑名单规约实现

![InteractiveSpecification](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/interactive-specification-pattern-uml.png)

1. 违禁词、安全、黑名单规约实现

* 违禁词 StopWordSpecification 实现

```
@Component
public class StopWordSpecification extends AbstractSpecification<Message> implements InteractiveSpecification<Message, MessageStatus> {
    @Override
    public void notSatisfiedHandleBy(Message message, Consumer<MessageStatus> handle) {
        if (isSatisfiedBy(message)) {
            return;
        }
        handle.accept(MessageStatus.HAS_STOP_WORD);
    }
    @Override
    public boolean isSatisfiedBy(Message message) {
        String content = message.getContent();
        //todo 通过系统违禁词列表，匹配消息内容是否包含违禁词
        if(content.contains("线下交易")){
            return false;
        }
        return true;
    }
}
```

* 安全 SafetySpecification 实现

```
@Component
public class SafetySpecification extends AbstractSpecification<Message> implements InteractiveSpecification<Message, MessageStatus> {
    @Override
    public void notSatisfiedHandleBy(Message message, Consumer<MessageStatus> handle) {
        if (isSatisfiedBy(message)) {
            return;
        }
        handle.accept(MessageStatus.UN_SAFE);
    }
    @Override
    public boolean isSatisfiedBy(Message message) {
        String content = message.getContent();
        //todo 通过消息风控系统，检查消息是否安全，比如检查是否包含银行卡，支付宝，转账等敏感词。
        if(content.contains("支付宝")){
            return false;
        }
        return true;
    }
}
```

* 黑名单 BlockedSpecification 实现

```
@Component
public class BlockedSpecification extends AbstractSpecification<Message> implements InteractiveSpecification<Message, MessageStatus> {
    private static final Map<Integer,Integer> mockBlockedList;
    static {
        mockBlockedList = new HashMap<>();
        mockBlockedList.put(1,100);
        mockBlockedList.put(2,200);
        mockBlockedList.put(3,300);
    }
    @Override
    public void notSatisfiedHandleBy(Message message, Consumer<MessageStatus> handle) {
        if(isSatisfiedBy(message)){
            return;
        }
        handle.accept(MessageStatus.IN_BLOCKED_LIST);
    }
    @Override
    public boolean isSatisfiedBy(Message message) {
        User sender = message.getSender();
        User receiver = message.getReceiver();
        //todo 查询数据库或者调用接口，检查发送者是否在接受者的黑名单中
        boolean inBlockedList = Optional.ofNullable(mockBlockedList.get(receiver.getUserId()))
                .filter(v -> v.equals(sender.getUserId()))
                .isPresent();
        if(inBlockedList){
            return false;
        }
        return true;
    }
}
```

2. 消息状态规约实现 MessageStatusSpecification (关键)

> 通过组合违禁词、安全、黑名单完成消息状态的业务验证逻辑

```
@Component
public class MessageStatusSpecification extends AndInteractiveSpecification<Message, MessageStatus> {
    public MessageStatusSpecification(StopWordSpecification stopWordSpecification,BlockedSpecification blockedSpecification,SafetySpecification safetySpecification){
        super(stopWordSpecification,blockedSpecification,safetySpecification);
    }
}
```

3. 消息实体 handleStatusBy 实现

```
    /**
     * 根据 specifications 来处理消息的状态
     *      比如，发送者权限处理,违禁词处理等
     * @return
     */
    public Message handleStatusBy(MessageStatusSpecification specification){
        if(MessageStatus.NORMAL.equals(this.status)){
            return this;
        }
        this.status = MessageStatus.NORMAL;
        specification.notSatisfiedHandleBy(this , messageStatus -> this.status = messageStatus);
        return this;
    }
```

4. 在 MessageDomainServiceImpl 中调用，handleStatusBy 方法，处理消息状态逻辑

```
message.handleStatusBy(messageStatusSpecification);
```

### 测试

```
 @Test
    public void createMessage(){
        Message message = messageDomainService.createMessage(1L,100,1,"xx");
        Assert.assertEquals(MessageStatus.IN_BLOCKED_LIST,message.getStatus());

        message = messageDomainService.createMessage(1L,1,2,"xxx");
        Assert.assertEquals(MessageStatus.NORMAL,message.getStatus());

        message = messageDomainService.createMessage(1L,1,2,"线下交易");
        Assert.assertEquals(MessageStatus.HAS_STOP_WORD,message.getStatus());

        message = messageDomainService.createMessage(1L,1,2,"支付宝");
        Assert.assertEquals(MessageStatus.UN_SAFE,message.getStatus());

        message = messageDomainService.createMessage(1L,1,3,"xxx");
        Assert.assertTrue(message.isRead());

        message = messageDomainService.createMessage(1L,1,2,"xxx");
        Assert.assertFalse(message.isRead());
    }
```

### 写到最后

规约模式似乎还有不同的用法，

比如在《领域驱动设计-复杂软件的设计之道》书中提到，"
基于Specification的查询是将Repository通用化的好办法"

比如在wiki中还有这样一段描述: As a consequence of performing runtime composition
of high-level business/domain logic, the Specification pattern is a convenient
tool for converting ad-hoc user search criteria into low level logic to be
processed by repositories；
作为执行高级业务/域逻辑的运行时组合的结果，规范模式是一种方便的工具，用于将临时用户搜索条件转换为存储库处理的低级逻辑。

比如在 Spring JPA 中有类似于 Specification
Query的查询方式，https://spring.io/blog/2011/04/26/advanced-spring-data-jpa-specifications-and-querydsl/

似乎规约被用于了与资源库、和数据之间打交道，被用于一种数据检索的方式,关于这一点，笔者目前没有想到具体的应用场景。
