package com.youshicheng.普通;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
/**
 * 生产者
 * @author 游家纨绔
 */
public class Publish {

    private final static String QUEUE_NAME="simple_queue";

    public static void main(String[] args) {

        ConnectionFactory factory = new ConnectionFactory();//定义连接工厂
        Connection connection = null;
        Channel channel = null;

        try{
            //获取到连接
            connection = factory.newConnection();
            //从连接中创建通道，使用通道才能完成消息相关的操作
            channel = connection.createChannel();

            //消息内容
            String message = "游诗成";
            //向指定的队列中发送消息
            channel.basicPublish("",QUEUE_NAME,null,message.getBytes("utf-8"));
                /*
                   队列中的参数说明：
                      参数1：为交换机名称 这里为空字符串表示不使用交换机
                      参数2：为队列名或key,当指定了交换机名称以后这个值就是key
                      参数3：为死信属性信息 通常空即可
                      参数4：为具体的消息数据的字节数组
                */
        } catch(Exception e){
            e.printStackTrace();
        } finally {
            //关闭通道和链接
            if (channel != null) {
                try{
                    channel.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try{
                    connection.close();
                } catch(Exception e){
                    e.printStackTrace();
                }
            }
        }
    }
    /**
     * 可以这么去理解MQ:
     *       相当于生产者更新了一篇文章，但是消费者暂时不需要。那你这篇文章可以缓存，等待消费者需要的时候再用以消费。
     */
}
