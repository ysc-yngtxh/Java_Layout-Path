# DDD电商消息系统编码实践（四）

在电商的场景下，消息是多种多样的，比如新订单的消息，卖家让你确认收货地址的消息，买家付款了的消息等，同时它们的展示形式也是千变万化的。本篇文章我们将模拟此功能，来看下在我们项目中如何实现。

## 需求概览

1. 增加消息分类，用于区分不同业务的消息
2. 增加消息卡片，用于适应不同消息的展示形态

## 需求实现

> 注：这里只把一些主要流程列出，详细调整请看源码

#### 1. 添加消息分类属性，区分不同的业务消息

```

@AllArgsConstructor
@Getter
public enum MessageCategory implements ValueObject<MessageCategory> {
    /**
     * 聊天消息
     */
    CHAT(0),
    /**
     * 新订单
     */
    CREATED_ORDER(1),
    /**
     * 买家已付款
     */
    PAID(2);
    private final int code;
    /**
     * 通过分类编码获取消息分类
     * @param code
     * @return
     */
    public static MessageCategory getByCode(int code){
        for (MessageCategory category : MessageCategory.values()){
            if(category.getCode() == code){
                return category;
            }
        }
        return null;
    }

    @Override
    public boolean sameValueAs(MessageCategory other) {
        return other != null && other.getCode() == this.code;
    }
}
```

#### 2. 新增消息内容值对象,并且将创建过程的业务规则内聚

* 使用 tpl 模板ID，区分不同的消息展示形态
* 将消息内容格式与模板ID进行对应

```
@Data
public class Content {
    private int tpl;
    private String content;

    private final static Map<MessageCategory,Integer> TPL_MAPS;
    static {
        TPL_MAPS = new HashMap<>();
        TPL_MAPS.put(MessageCategory.CREATED_ORDER,Tpl.CREATED_ORDER);
        TPL_MAPS.put(MessageCategory.PAID,Tpl.PAID);
    }

    public Content(MessageCategory category , String content){
        if(MessageCategory.CHAT.sameValueAs(category)){
            constructByParse(content);
        }else{
            constructByCategory(category,content);
        }
    }

    /**
     * 通过分析content完成创建
     * @param content
     */
    private void constructByParse(String content){
        //这里仅是简单判断，实际场景下根据业务需求处理，比如是否需要考虑支持在一个内容中匹配多个链接的情况
        boolean isLink = StringUtils.substringMatch(content,0,"https://") || StringUtils.substringMatch(content,0,"http://");
        if(isLink){
            this.tpl = Tpl.LINK;
            Map<String,String> contentMap = new HashMap<>(2);
            contentMap.put("link",content);
            //todo ... 通过接口或者网页抓取
            contentMap.put("title","title");
            contentMap.put("describe","describe");
            this.content = JSON.toJSONString(contentMap);
        }else{
            this.tpl = Tpl.TEXT;
            this.content = content;
        }
    }

    /**
     * 通过分类创建消息内容
     *      系统分类通常情况下，1个分类就是一个模板，比如创建订单、已付款等
     * @param category
     * @param content
     */
    private void constructByCategory(MessageCategory category , String content){
        this.tpl = TPL_MAPS.get(category);
        this.content = content;
    }
    /**
     * 模板ID
     */
    public interface Tpl{
        int TEXT = 0;
        int LINK = 1;
        int CREATED_ORDER = 2;
        int PAID = 3;
    }
}
```

#### 3. 调整实体类引用的content类型

```
    - private final String content;
    + private final Content content;
```

#### 4. 相应调整应用层、领域层

* 调整应用层createMessage方法

```
  @Override
    public void createMessage(long messageId, int catId, int sender, int receiver, String content) {
        ...
        MessageCategory category = MessageCategory.getByCode(catId);
        Message message = this.messageDomainService.createMessage(messageId, category,sender, receiver, content);
        ....
    }
```

* 调整领域层createMessage方法

```
   @Override
    public Message createMessage(long messageId, MessageCategory category , 
        ...
        Content content = new Content(category,sourceContent);
        Message message = new Message(messageId,category,sender, receiver, content, new Date());
        ...
    }
```

## 测试

```
    @Test
    public void test(){
        Content content = new Content(MessageCategory.CHAT,"xxx");
        Assertions.assertEquals(Content.Tpl.TEXT,content.getTpl());

        content = new Content(MessageCategory.CHAT,"http://www.baidu.com");
        Assertions.assertEquals(Content.Tpl.LINK,content.getTpl());

        content = new Content(MessageCategory.CREATED_ORDER,"{\"orderId\":1}");
        Assertions.assertEquals(Content.Tpl.CREATED_ORDER,content.getTpl());

        content = new Content(MessageCategory.PAID,"{\"orderId\":1}");
        Assertions.assertEquals(Content.Tpl.PAID,content.getTpl());
    }
```
