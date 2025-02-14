package com.example.utils;

import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.HeadersExchange;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageProperties;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Autowired;

public class RabbitUtil {

    private static final Logger logger = LoggerFactory.getLogger(RabbitUtil.class);

    // TODO RabbitAdmin 的作用是创建、删除、绑定队列、交换机、绑定关系。
    //      注意：RabbitAdmin 需要手动配置注入到 Spring IOC容器中，才能使用
    @Autowired
    private RabbitAdmin rabbitAdmin;

    /**
     * 创建Exchange
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     */
    public void addExchange(String exchangeType, String exchangeName) {
        Exchange exchange = createExchange(exchangeType, exchangeName);
        rabbitAdmin.declareExchange(exchange);
    }

    /**
     * 删除一个Exchange
     * @param exchangeName 交换机名称
     */
    public boolean deleteExchange(String exchangeName) {
        return rabbitAdmin.deleteExchange(exchangeName);
    }

    /**
     * 创建一个指定的Queue
     * @param queueName 队列名称
     */
    public void addQueue(String queueName) {
        Queue queue = createQueue(queueName);
        rabbitAdmin.declareQueue(queue);
    }

    /**
     * 删除一个queue
     * @param queueName 队列名称
     * @return 是否删除成功
     */
    public boolean deleteQueue(String queueName) {
        return rabbitAdmin.deleteQueue(queueName);
    }

    /**
     * 按照筛选条件，删除队列
     * @param queueName 队列名称
     * @param unused 是否被使用
     * @param empty 内容是否为空
     */
    public void deleteQueue(String queueName, boolean unused, boolean empty){
        rabbitAdmin.deleteQueue(queueName, unused, empty);
    }

    /**
     * 清空某个队列中的消息。注意，清空的消息并没有被消费
     * @param queueName 队列名称
     */
    public void purgeQueue(String queueName){
        rabbitAdmin.purgeQueue(queueName, false);
    }

    /**
     * 判断指定的队列是否存在
     * @param queueName 队列名称
     * @return 是否存在
     */
    public boolean existQueue(String queueName){
        return rabbitAdmin.getQueueProperties(queueName) != null;
    }

    /**
     * 绑定一个队列到一个匹配型交换器使用一个routingKey
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @param queueName 队列名称
     * @param routingKey 路由键
     * @param isWhereAll 是否匹配所有
     * @param headers EADERS模式类型设置，其他模式类型传空
     */
    public void addBinding(String exchangeType, String exchangeName, String queueName,
                           String routingKey, boolean isWhereAll, Map<String, Object> headers){
        Binding binding = bindingBuilder(exchangeType, exchangeName, queueName,
                                         routingKey, isWhereAll, headers);
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 声明绑定
     * @param binding 绑定关系
     */
    public void addBinding(Binding binding){
        rabbitAdmin.declareBinding(binding);
    }

    /**
     * 解除交换器与队列的绑定
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @param queueName 队列名称
     * @param routingKey 路由键
     * @param isWhereAll 是否匹配所有
     * @param headers EADERS模式类型设置，其他模式类型传空
     */
    public void removeBinding(String exchangeType, String exchangeName, String queueName, String routingKey, boolean isWhereAll, Map<String, Object> headers){
        Binding binding = bindingBuilder(exchangeType, exchangeName, queueName, routingKey, isWhereAll, headers);
        removeBinding(binding);
    }

    /**
     * 解除交换器与队列的绑定
     * @param binding 绑定关系
     */
    public void removeBinding(Binding binding){
        rabbitAdmin.removeBinding(binding);
    }

    /**
     * 创建一个交换器、队列，并绑定队列
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @param queueName 队列名称
     * @param routingKey 路由键
     * @param isWhereAll 是否匹配所有
     * @param headers EADERS模式类型设置，其他模式类型传空
     */
    public void andExchangeBindingQueue(String exchangeType, String exchangeName, String queueName, String routingKey, boolean isWhereAll, Map<String, Object> headers){
        // 声明交换器
        addExchange(exchangeType, exchangeName);
        // 声明队列
        addQueue(queueName);
        // 声明绑定关系
        addBinding(exchangeType, exchangeName, queueName, routingKey, isWhereAll, headers);
    }


    /**
     * 转换Message对象
     * @param messageType 消息类型
     * @param msg 消息内容
     * @return 返回消息体
     */
    public Message getMessage(String messageType, Object msg){
        MessageProperties messageProperties = new MessageProperties();
        messageProperties.setContentType(messageType);
        return new Message(msg.toString().getBytes(),messageProperties);
    }

    /**
     * 声明交换机
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @return 交换机对象
     */
    private Exchange createExchange(String exchangeType, String exchangeName){
        if(ExchangeType.DIRECT.equals(exchangeType)){
            return new DirectExchange(exchangeName);
        }
        if(ExchangeType.TOPIC.equals(exchangeType)){
            return new TopicExchange(exchangeName);
        }
        if(ExchangeType.HEADERS.equals(exchangeType)){
            return new HeadersExchange(exchangeName);
        }
        if(ExchangeType.FANOUT.equals(exchangeType)){
            return new FanoutExchange(exchangeName);
        }
        return null;
    }

    /**
     * 声明队列
     * @param queueName 队列名称
     * @return 队列对象
     */
    private Queue createQueue(String queueName){
        return new Queue(queueName);
    }

    /**
     * 声明绑定关系
     * @param exchangeType 交换机类型
     * @param exchangeName 交换机名称
     * @param queueName 队列名称
     * @param routingKey 路由键
     * @param isWhereAll 是否匹配所有
     * @param headers EADERS模式类型设置，其他模式类型传空
     * @return 绑定关系
     */
    private Binding bindingBuilder(String exchangeType, String exchangeName, String queueName,
                                   String routingKey, boolean isWhereAll, Map<String, Object> headers){
        if(ExchangeType.DIRECT.equals(exchangeType)) {
            return BindingBuilder.bind(new Queue(queueName)).to(new DirectExchange(exchangeName)).with(routingKey);
        }
        if(ExchangeType.TOPIC.equals(exchangeType)) {
            return BindingBuilder.bind(new Queue(queueName)).to(new TopicExchange(exchangeName)).with(routingKey);
        }
        if(ExchangeType.HEADERS.equals(exchangeType)) {
            if(isWhereAll) {
                return BindingBuilder.bind(new Queue(queueName)).to(new HeadersExchange(exchangeName)).whereAll(headers).match();
            } else {
                return BindingBuilder.bind(new Queue(queueName)).to(new HeadersExchange(exchangeName)).whereAny(headers).match();
            }
        }
        if(ExchangeType.FANOUT.equals(exchangeType)) {
            return BindingBuilder.bind(new Queue(queueName)).to(new FanoutExchange(exchangeName));
        }
        return null;
    }

    /**
     * 交换器类型
     */
    public final static class ExchangeType {
        /**
         * 直连交换机（全文匹配）
         */
        public final static String DIRECT = "DIRECT";
        /**
         * 通配符交换机（两种通配符：*只能匹配一个单词，#可以匹配零个或多个）
         */
        public final static String TOPIC = "TOPIC";
        /**
         * 头交换机（自定义键值对匹配，根据发送消息内容中的headers属性进行匹配）
         */
        public final static String HEADERS = "HEADERS";
        /**
         * 扇形（广播）交换机 （将消息转发到所有与该交互机绑定的队列上）
         */
        public final static String FANOUT = "FANOUT";
    }
}