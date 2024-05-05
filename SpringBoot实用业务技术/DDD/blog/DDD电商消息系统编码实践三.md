# DDD电商消息系统编码实践（三）

上篇文章中，我们通过对消息的一些业务规则处理，了解了规约模式在DDD中的应用 在这篇文章中，我们将一起来看下，对已存在实体进行操作时如何实现。

## 需求概览

增加消息撤回功能，允许5分钟内的消息撤回

## 需求实现

### 1. 增加实体属性，满足撤回需求

> 撤回可以理解为发送方接收方，都删除了，同时消息除了撤回功能，通常还允许单边删除，因此我们增加4个属性来表示，发送方删除与接收方删除。

```
    private boolean senderDeleted;
    private boolean receiverDeleted;
    private Date senderDeleteTime;
    private Date receiverDeleteTime;
```

### 2. 修改实体,增加撤回方法

```
    /**
     * 撤回消息
     * @return
     */
    public void recall(){
        Date deletedTime = new Date();
        this.senderDeleted = true;
        this.senderDeleteTime = deletedTime;
        this.receiverDeleted = true;
        this.receiverDeleteTime = deletedTime;
        this.status = MessageStatus.RECALL;
    }
```

### 3. 定义业务规约 (只允许撤销，5分钟内的消息)

```
@Component
public class RecallSpecification extends AbstractSpecification<Message> {
    /**
     * @param message
     * @return
     */
    @Override
    public boolean isSatisfiedBy(Message message) {
        //已经撤回了，不能二次撤回
        if(message.isSenderDeleted() && message.isReceiverDeleted()){
            return false;
        }
        //只允许撤销，5分钟内的消息
        Date fiveMinutesAgo = new Date(System.currentTimeMillis() - 60 * 5 * 1000);
        boolean isAllow = message.getSendTime().after(fiveMinutesAgo);
        if(isAllow){
            return true;
        }
        return false;
    }
}
```

### 4. 定义操作的接口

> MessageDomainService

```
    /**
     * 撤销消息
     * @param message
     */
    Result<Void> recall(Message message);

```

> MessageCommandService

```
    /**
     * 撤回消息
     * @param userId
     * @param messageId
     */
    Result<Void> recallMessage(int userId , long messageId);

```

> MessageServiceFacade

```
    /**
     * 撤回消息
     *
     * @param userId 当前会话用户Id
     * @param messageId 消息ID
     */
    Result<Void> recallMessage(int userId , long messageId);
```

需要特别说明的是，通常在实际业务场景中需要通过返回的ERROR类别、错误信息来告知用户失败的原因，然而在CQRS中对于Command并不推荐使用返回值，command方法返回值应该定义为void , 那么如何传递错误的信息呢。

* 第一种做法是作为特定的异常抛出，然而通过异常来控制业务的分支并不是编码规范（阿里巴巴JAVA开发指南）所推荐的。

* 第二种做法是，在方法签名中增加一个参数来传递，比如
```
void recallMessage(int userId , long messageId , Result executeResult);
```
* 第三种做法是，在Command增加一个属性，比如
```
@Data
public class RecallCommand {
    private int userId;
    private long messageId;
    Result<Void> executeResult;
}
```

笔者觉得都不是很好，最终选择采用一个Result类的返回值来传递中间结果。

```
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Result<T> {
    private Error error;
    private T data;

    public boolean isSuccess(){
        return error == null;
    }

    public static <T> Result<T> success(){
        return new Result<>();
    }

    public static <T> Result<T> create(T data){
        return new Result<>(null,data);
    }

    public static <T> Result<T> create(Error error){
        return new Result<>(error,null);
    }

    public static <T> Result<T> create(Error error,T data){
        return new Result<>(error,data);
    }
}
```

### 5. 实现接口

> MessageDomainServiceImpl

领域层，通过规约进行验证，并最终调用实体的recall方法

```
    @Override
    public Result<Void> recall(Message message) {
        if(recallSpecification.isSatisfiedBy(message)){
            message.recall();
            return Result.success();
        }
        return Result.create(Error.NOT_ALLOW_RECALL);
    }
```


> MessageCommandServiceImpl (MessageService)

在应用层，对空实体与越权进行校验，在成功后重新存储实体，并发送事件消息

```
    @Override
    public Result<Void> recallMessage(int userId, long messageId) {
        Message message = this.messageRepository.find(messageId);
        if (message == null){
            return Result.create(Error.ENTITY_NOT_FUND);
        }
        //不是发送者不能撤销消息
        boolean isSender = Optional.ofNullable(message.getSender()).filter(v -> v.getUserId() == userId).isPresent();
        if (!isSender){
            return Result.create(Error.NOT_OPERATION_PERMISSION);
        }
        //调用领域服务撤销消息
        Result<Void> result = this.messageDomainService.recall(message);
        if(result.isSuccess()){
            this.messageRepository.save(message);
            this.domainEventPublisher.emit(new MessageRecalledEvent(message));
        }
        return result;
    }
```

> MessageServiceFacadeImpl

直接调用应用层，无额外实现

```
   @Override
    public Result<Void> recallMessage(int userId, long messageId) {
        return this.messageCommandService.recallMessage(userId,messageId);
    }
```

### 6. 接口层增加接口

调用 facade 接口，将返回的 Result 对象包装为 Response 返回给客户端

```
 /**
     * 撤回消息
     *
     * @param messageId
     * @param request
     * @return
     */
    @PostMapping("/message/{messageId}/recall")
    public Response<Void> recallMessage(@PathVariable long messageId , HttpServletRequest request){
        int userId = Integer.parseInt(request.getHeader("mock-user-id"));
        Result<Void> result = this.messageServiceFacade.recallMessage(userId,messageId);
        if (result.isSuccess()){
            return Response.ok(result.getData());
        }
        return Response.failed(result.getError().getMsg());
    }
```


## 测试

```
###
#撤回消息
POST http://localhost:8080/message/1/recall
Content-Type: application/json; charset=UTF-8
mock-user-id: 1

```

```
public class RecallSpecificationTest {
    @Autowired
    private RecallSpecification recallSpecification;
    @Test
    public void isSatisfiedByTest(){
        Message message1 = new Message(
                1L,
                new User(1,"",""),
                new User(2,"",""),
                "xxx",
                new Date()
        );
        Assertions.assertTrue(recallSpecification.isSatisfiedBy(message1));
        Message message2 = new Message(
                1L,
                new User(1,"",""),
                new User(2,"",""),
                "xxx",
                new Date(System.currentTimeMillis() - 60 * 6 * 1000)
        );
        Assertions.assertFalse(recallSpecification.isSatisfiedBy(message2));
    }
}
```