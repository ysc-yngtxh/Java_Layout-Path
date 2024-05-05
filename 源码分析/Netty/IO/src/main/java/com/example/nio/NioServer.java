package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-13 下午8:24
 * @apiNote TODO NIO【Non Block I/O】非阻塞同步IO
 */
public class NioServer {
    // 保存客户端连接
    public static List<SocketChannel> channelList = new ArrayList<>();

    public static void main(String[] args) {
        ByteBuffer byteBuffer = ByteBuffer.allocate(128);
        try {
            // 创建NIO，为非阻塞设置的类
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            serverSocketChannel.socket().bind(new InetSocketAddress(8080));
            // 设置为非阻塞连接。即没有客户端进行连接时，也不会阻塞。参数为true时，为阻塞连接
            serverSocketChannel.configureBlocking(false);
            while (true) {
                // 非阻塞模式accept方法不会阻塞，否则会阻塞
                // NIO的非阻塞是由操作系统内部实现的，底层调用了Linux内核的accept函数
                SocketChannel socketChannel = serverSocketChannel.accept();

                if (socketChannel == null) {
                    // 表示没人连接
                    System.out.println("正在等待客户端请求连接...");
                    Thread.sleep(3000);
                } else {
                    System.out.println("当前接收到客户端请求连接...");
                    // 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 保存客户端连接到List中
                    channelList.add(socketChannel);
                }

                // TODO 这里会出现一个问题：
                //      就是每一次while循环都会遍历 客户端连接集合channelList ，
                //      但是集合channelList中有的连接并不会总有收发事件[读取、写入等事件]，所以这里遍历就会造成资源的浪费
                //      连接数不多的时候可能不怎么影响，当连接数达到百万级别的时候，这里遍历就会造成资源的浪费，需要优化
                //      优化思路：在 客户端连接集合channelList 中取出有收发事件的 SocketChannel 进行遍历
                for (SocketChannel channel : channelList) {
                    // 非阻塞模式下read方法不会阻塞，否则会阻塞
                    int effective = channel.read(byteBuffer);
                    if (effective > 0) {
                        System.out.println("接收到客户端消息：" + new String(byteBuffer.array()).trim());
                    } else if(effective == -1) {
                        channelList.remove(channel);
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
            }
        } catch (IOException | InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
