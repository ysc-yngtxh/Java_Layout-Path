# DDD电商消息系统编码实践（五）

本篇文章我们将在项目中添加一些查询接口，来展示在DDD的项目中查询逻辑的编写。

## 需求概览

1. 增加一个查询未读数量的接口
2. 增加一个查询往来消息的接口

## CQRS架构

CQRS（Cammand-Query Responsibility
Segregation），CQRS是将紧缩（Stringent）对象（或者组件）设计原则和命令-查询分离（CQS）应用在架构模式中的结果。

### 命令-查询分离（CQS）

1. 如果一个方法修改了对象的状态，该方法便是一个命令（Command），它不应该返回数据。
2. 如果一个方法返回了数据，该方法便是一个查询（Query），此时它不应该通过直接的或间接的手段修改对象的状态。

### 运用CQRS

如下图所示，在DDD中根据CQS的特性拆分为写模型与读模型，写模型包括验证、领域逻辑以及持久化的工作，读模型仅负责查询数据生成DTO对象。

![cqrs-basic](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/command-and-query-responsibility-segregation-cqrs-basic.png)

> 图片来自于：https://docs.microsoft.com/en-us/azure/architecture/patterns/cqrs

此种模式咋一看起来，与常见的应用开发没有太多差别，无非是写完了以后在读，但实际上因为将读写模型进行了抽象，即使存储在一起，我们完全可以对写与读采用不同的数据结
构，关于这一点我们接着往下看。

![cqrs-separate-stores](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/command-and-query-responsibility-segregation-cqrs-separate-stores.png)

> 图片来自于：https://docs.microsoft.com/en-us/azure/architecture/patterns/cqrs

此图的差别在于写存储与读存储分开了,如果你联想到了数据库读写分离的架构，我刚开始的时候也是这样。但虽然存储分开了，却没有定义数据同步的方式，是通过binlog，还是
领域事件，还是其它别的比如实时或离线计算，并没有明确的定义。

所以CQRS的本质是读写模型的分离，并独立的对其进行优化，这个优化包括模型的优化以及存储的优化。

关于这一点，举个例子。

在电商平台中，店铺与商品通常是两个不同的服务，且拥有不同的数据库，按照DDD的语义，就是两个不同的聚合或者限界上下文，然而电商搜索系统通常需要对店铺与商品同时进行
检索操作，在这个场景中，对店铺与商品的更新可以是看做写模型，索引可以看做读写模型数据同步的方式，而对搜索引擎的查询可以看做读模型。

![search-cqrs](https://gitee.com/izhengyin/ddd-message/raw/master/blog/images/search-cqrs.png)

总结一下，在CQRS中强调的是读写模型的分离，而并没有限定具体的存储方式，因此常见的CQRS运用可以有下面的几种情况。

1. 在一个进程上同时实现读写两个模型，并共享一个存储
2. 在一个进程上同时实现读写两个模型，但存储分离
3. 读写模型分开在不同的进程实现，且存储分离

而存储分离的方式，可以是同为一种媒介，比如mysql主从，也可以是不同媒介比如mysql +
ES。

## 需求实现

在CQRS的架构中，查询不涉及对领域对象状态的修改，因此可以直接在应用服务对应仓储实现，不用经过领域层，因此本篇内容不会涉及领域层的修改。

#### 1. 定义接口

> 仓储 MessageRepository 新增接口

```
 /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    int getUnreadMessageTotal(int userId);

    /**
     * 获取往来消息列表
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    List<Message> getContactMessageList(int userId, int contactId, int size);
```

> 应用层 MessageQueryService 接口

```
public interface MessageQueryService {
    /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    int getUnreadMessageTotal(int userId);

    /**
     * 获取往来消息列表
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    List<Message> getContactMessageList(int userId,int contactId,int size);
}
```

> 接口层 MessageServiceFacade 新增接口

```
    /**
     * 获取未读消息的数量
     * @param userId
     * @return
     */
    int getUnreadMessageTotal(int userId);

    /**
     * 获取往来消息列表
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    List<MessageDTO> getContactMessageList(int userId, int contactId, int size);

```

### 接口实现

> 仓储实现

```
    /**
     * 判断接受者是当前用户，且消息未读，最后统计总数
     * @param userId
     * @return
     */
    @Override
    public int getUnreadMessageTotal(int userId) {
        return (int) messageRepositories.values()
                .stream()
                .filter(v -> v.getStatus().sameValueAs(MessageStatus.NORMAL))
                .filter(v -> v.getReceiver().getUserId() == userId)
                .filter(v -> !v.isRead())
                .count();
    }

    /**
     * 往来消息
     *      发送者等于当前用户时，检查发送者是否删除
     *      接受者等于当前用户时，检查接受者是否删除
     * @param userId
     * @param contactId
     * @param size
     * @return
     */
    @Override
    public List<Message> getContactMessageList(int userId, int contactId, int size) {
        return messageRepositories.values()
                .stream()
                .filter(v -> v.getStatus().sameValueAs(MessageStatus.NORMAL))
                .filter(v ->
                    (v.getSender().getUserId() == userId && v.getReceiver().getUserId() == contactId && !v.isSenderDeleted())
                    ||
                    (v.getReceiver().getUserId() == userId &&  v.getSender().getUserId() == contactId && !v.isReceiverDeleted())
                )
                .collect(Collectors.toList());
    }
```

> 应用层 MessageQueryService 实现

没有逻辑，直接调用仓储完成数据读取

```

    @Override
    public int getUnreadMessageTotal(int userId) {
        return messageRepository.getUnreadMessageTotal(userId);
    }

    @Override
    public List<Message> getContactMessageList(int userId, int contactId, int size) {
        return messageRepository.getContactMessageList(userId,contactId,size);
    }
```

> 接口层 MessageServiceFacade 实现

* getUnreadMessageTotal 直接调用应用层接口返回数据
* getContactMessageList 调用应用层接口，在将实体转换为DTO，实体不能直接返回给客户端

```
 @Override
    public int getUnreadMessageTotal(int userId) {
        return this.messageQueryService.getUnreadMessageTotal(userId);
    }

    @Override
    public List<MessageDTO> getContactMessageList(int userId, int contactId, int size) {
        return this.messageQueryService.getContactMessageList(userId,contactId,size)
                .stream()
                .map(MessageDTOAssembler::toDTO)
                .collect(Collectors.toList());
    }
```

> 实体转换为DTO MessageDTOAssembler

```
   /**
     * 消息实体转换为 {@link MessageDTO}
     * @param message 消息实体
     * @return 消息实体转换后的 MessageDTO
     */
    public static MessageDTO toDTO(final Message message) {
        MessageDTO dto = new MessageDTO();
        dto.setMessageId(message.getMessageId());
        dto.setCatId(message.getCategory().getCode());
        dto.setSender(message.getSender().getUserId());
        dto.setSenderNickname(message.getSender().getNickname());
        dto.setSenderPhoto(message.getSender().getPhoto());
        dto.setReceiver(message.getReceiver().getUserId());
        dto.setReceiverNickname(message.getReceiver().getNickname());
        dto.setReceiverPhoto(message.getReceiver().getPhoto());
        dto.setContentTpl(message.getContent().getTpl());
        dto.setContent(message.getContent().getContent());
        dto.setRead(message.isRead());
        dto.setSendTime(message.getSendTime());
        return dto;
    }
```

### 接口层新增路由

```
    @GetMapping("/unreadMessageTotal")
    public Response<Integer> getUnreadMessageTotal(HttpServletRequest request){
        int userId = Integer.parseInt(request.getHeader("mock-user-id"));
        return Response.ok(messageServiceFacade.getUnreadMessageTotal(userId));
    }

    @GetMapping("/contact/{contactId}/messages")
    public Response<List<MessageDTO>> getContactMessageList(@PathVariable int contactId , Integer size, HttpServletRequest request){
        int userId = Integer.parseInt(request.getHeader("mock-user-id"));
        List<MessageDTO> data = messageServiceFacade.getContactMessageList(userId,contactId, Optional.ofNullable(size).orElse(10));
        return Response.ok(data);
    }
```

## 测试

### 应用层测试

```

@SpringBootTest
public class MessageServiceTest {

    @Autowired
    private MessageCommandService messageCommandService;

    @Autowired
    private MessageQueryService messageQueryService;

    @Test
    public void test(){
        messageCommandService.createMessage(new SendMessageCommand(1,MessageCategory.CHAT.getCode(),1,3,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(2,MessageCategory.CHAT.getCode(),1,4,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(3,MessageCategory.CHAT.getCode(),1,5,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(4,MessageCategory.CHAT.getCode(),2,3,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(5,MessageCategory.CHAT.getCode(),2,4,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(6,MessageCategory.CHAT.getCode(),2,5,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(7,MessageCategory.CHAT.getCode(),1,3,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(8,MessageCategory.CHAT.getCode(),1,8,"xxx "));
        messageCommandService.createMessage(new SendMessageCommand(9,MessageCategory.CHAT.getCode(),1,9,"xxx "));
        //receiver % 3 == 0 已读
        Assertions.assertEquals(0,messageQueryService.getUnreadMessageTotal(3));
        Assertions.assertEquals(2,messageQueryService.getUnreadMessageTotal(4));
        Assertions.assertEquals(2,messageQueryService.getContactMessageList(1,3,10).size());
        //测回一条消息
        messageCommandService.recallMessage(1,1);
        Assertions.assertEquals(1,messageQueryService.getContactMessageList(1,3,10).size());
        //测回一条消息
        messageCommandService.recallMessage(1,2);
        Assertions.assertEquals(1,messageQueryService.getUnreadMessageTotal(4));
    }


}

```

### 接口测试

```


###
#未读消息
GET http://localhost:8080/unreadMessageTotal
Content-Type: application/json; charset=UTF-8
mock-user-id: 2

###
#往来消息消息
GET http://localhost:8080/contact/2/messages
Content-Type: application/json; charset=UTF-8
mock-user-id: 1
```

参考：

https://docs.microsoft.com/en-us/azure/architecture/patterns/cqrs

https://insights.thoughtworks.cn/backend-development-cqrs/
