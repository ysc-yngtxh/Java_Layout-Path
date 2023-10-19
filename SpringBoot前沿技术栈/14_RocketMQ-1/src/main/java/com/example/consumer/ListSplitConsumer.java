package com.example.consumer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class ListSplitConsumer implements Iterator<List<Message>> {
    private static final String NAME_SERVER_ADDR = "localhost:9876";
    private final int SIZE_LIMIT = 1024 * 1024 * 4;  // 4MB
    private final List<Message> messages;
    private int currIndex;

    public ListSplitConsumer(List<Message> messages) {
        this.messages = messages;
    }

    @Override
    public boolean hasNext() {
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        int startIndex = getStartIndex();
        int nextIndex = startIndex;
        int totalSize = 0;
        for (; nextIndex < messages.size(); nextIndex++) {
            Message message = messages.get(nextIndex);
            int tmpSize = calcMessageSize(message);
            if (tmpSize + totalSize > SIZE_LIMIT) {
                break;
            } else {
                totalSize += tmpSize;
            }
        }
        List<Message> subList = messages.subList(startIndex, nextIndex);
        currIndex = nextIndex;
        return subList;
    }

    private int getStartIndex() {
        Message currMessage = messages.get(currIndex);
        int tmpSize = calcMessageSize(currMessage);
        while (tmpSize > SIZE_LIMIT) {
            currIndex += 1;
            Message message = messages.get(currIndex);
            tmpSize = calcMessageSize(message);
        }
        return currIndex;
    }

    private int calcMessageSize(Message message) {
        int tmpSize = message.getTopic().length() + message.getBody().length;
        Map<String, String> properties = message.getProperties();
        for (Map.Entry<String, String> entry : properties.entrySet()) {
            tmpSize += entry.getKey().length() + entry.getValue().length();
        }
        tmpSize = tmpSize + 20; // 增加⽇志的开销20字节
        return tmpSize;
    }

    public static void main(String[] args) {
        // 把大的消息分裂成若干个小的消息
        ListSplitConsumer splitter = new ListSplitConsumer(messages);
        while (splitter.hasNext()) {
            try {
                List<Message> listItem = splitter.next();
                // 实例化消息生产者 -- 生产组(please_rename_unique_group_name)
                DefaultMQProducer producer = new DefaultMQProducer("please_rename_unique_group_name");
                // 设置NameServer的地址
                producer.setNamesrvAddr(NAME_SERVER_ADDR);
                // 启动Producer实例
                producer.start();
                // 发送消息
                producer.send(listItem);
            } catch (Exception e) {
                e.printStackTrace();
                // 处理error
            }
        }
    }
}