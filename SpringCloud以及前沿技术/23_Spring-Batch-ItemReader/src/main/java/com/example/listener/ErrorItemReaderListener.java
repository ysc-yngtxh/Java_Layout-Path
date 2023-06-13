package com.example.listener;

import com.example.dto.User1;
import org.springframework.batch.core.ItemReadListener;

import java.awt.event.ItemListener;

/**
 * @author 游家纨绔
 * @dateTime 2023-06-12 09:44
 * @apiNote TODO 自定义读取异常监听器
 */
public class ErrorItemReaderListener implements ItemReadListener<User1> {
    @Override
    public void beforeRead() {
        System.err.println("读取文件前的监听器被执行");
    }

    @Override
    public void afterRead(User1 item) {
        System.err.println("读取文件中的监听器被执行"+item);
    }

    @Override
    public void onReadError(Exception ex) {
        ItemReadListener.super.onReadError(ex);
    }
}
