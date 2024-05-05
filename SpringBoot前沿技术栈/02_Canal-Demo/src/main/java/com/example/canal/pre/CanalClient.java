package com.example.canal.pre;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.otter.canal.client.CanalConnector;
import com.alibaba.otter.canal.client.CanalConnectors;
import com.alibaba.otter.canal.protocol.CanalEntry;
import com.google.protobuf.ByteString;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;
import com.alibaba.otter.canal.protocol.Message;

import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.TimeUnit;


/**
 * @author 游家纨绔
 */
@Component
public class CanalClient implements InitializingBean {
    // InitializingBean接口为bean提供了初始化方法的方式，它只包括afterPropertiesSet方法，
    // 凡是继承该接口的类，在初始化bean的时候会执行该方法。

    @Override
    public void afterPropertiesSet() throws Exception {
        // TODO 获取连接
        CanalConnector canalConnector = CanalConnectors.newSingleConnector(
                new InetSocketAddress("127.0.0.1", 11111)
                , "example", "", "");

        while (true) {

            // TODO 连接
            canalConnector.connect();

            // TODO 订阅数据库
            canalConnector.subscribe("springdb.*");

            // TODO 获取数据
            Message message = canalConnector.get(100);

            // TODO 获取Entry集合
            List<CanalEntry.Entry> entries = message.getEntries();

            // TODO 判断集合是否为空，如果为空，则等待一会儿继续拉取数据
            if (entries.isEmpty()) {
                System.out.println("当次抓取没有数据。休息一会儿。。。。");
                TimeUnit.SECONDS.sleep(10);
            } else {
                // TODO 遍历entries，单条解析
                for (CanalEntry.Entry entry : entries) {
                    // 1、获取表名
                    String tableName = entry.getHeader().getTableName();
                    // 2、获取类型
                    CanalEntry.EntryType entryType = entry.getEntryType();
                    // 3、获取序列化后的数据
                    ByteString storeValue = entry.getStoreValue();
                    // 4、判断当前entryType类型是否为ROWDATA
                    if (CanalEntry.EntryType.ROWDATA.equals(entryType)) {
                        // 5、反序列化数据
                        CanalEntry.RowChange rowChange = CanalEntry.RowChange.parseFrom(storeValue);
                        // 6、获取当前事件的操作类型
                        CanalEntry.EventType eventType = rowChange.getEventType();
                        // 7、获取数据集
                        List<CanalEntry.RowData> rowDatasList = rowChange.getRowDatasList();
                        // 8、遍历rowDataList。并打印数据集
                        for (CanalEntry.RowData rowData : rowDatasList) {
                            JSONObject beforeData = new JSONObject();
                            List<CanalEntry.Column> beforeColumnsList = rowData.getBeforeColumnsList();
                            for (CanalEntry.Column column : beforeColumnsList) {
                                beforeData.put(column.getName(), column.getValue());
                            }

                            JSONObject afterData = new JSONObject();
                            List<CanalEntry.Column> afterColumnsList = rowData.getAfterColumnsList();
                            for (CanalEntry.Column column : afterColumnsList) {
                                beforeData.put(column.getName(), column.getValue());
                            }

                            //数据打印
                            System.out.println("Table:" + tableName +
                                    ",EventType:" + eventType +
                                    ",Before:" + beforeData +
                                    ",After:" + afterData);
                        }
                    } else {
                        System.out.println("当前操作类型为：" + entryType);
                    }
                }
            }
        }
    }
}