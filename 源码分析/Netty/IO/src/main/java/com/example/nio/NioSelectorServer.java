package com.example.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import java.util.Iterator;
import java.util.Set;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-14 上午9:53
 * @apiNote TODO
 */
public class NioSelectorServer {
    public static void main(String[] args) throws IOException {
        // 创建一个ServerSocketChannel并绑定到指定的端口
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.bind(new InetSocketAddress(9999));
        // 设置为非阻塞模式
        serverSocketChannel.configureBlocking(false);
        // 打开Selector处理Channel，
        Selector selector = Selector.open();
        // 将ServerSocketChannel注册到Selector上，并监听OP_ACCEPT事件
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        System.out.println("服务器已启动，等待客户端连接...");

        // TODO 很明显在这个while循环中，不会出现将没有收发事件的SocketChannel进行遍历，并且在遍历处理完逻辑之后进行删除
        while (true) {
            // 阻塞等待需要处理的事件发生。这个事件收发可能是 [连接、发送消息、读取消息]
            selector.select();

            // 获取Selector中注册的全部事件的 SelectionKey实例
            Set<SelectionKey> selectedKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectedKeys.iterator();
            // 遍历SelectionKey实例对事件进行处理。因为需要在循环后删除元素，所以这里需要使用迭代器遍历
            while (iterator.hasNext()) {
                SelectionKey key = iterator.next();
                if (key.isAcceptable()) {
                    // 处理连接请求事件。accept()接受客户端的连接请求，并返回一个SocketChannel实例
                    SocketChannel socketChannel = serverSocketChannel.accept();
                    // 设置为非阻塞
                    socketChannel.configureBlocking(false);
                    // 表示这个连接只注册读事件，如果想要给客户端发送消息可以注册写事件
                    socketChannel.register(selector, SelectionKey.OP_READ);
                    System.out.println("客户端连接成功！！！");
                } else if (key.isReadable()) {
                    // 走到这里说明客户端已经连接过了
                    SocketChannel socketChannel = (SocketChannel) key.channel();
                    System.out.println("socketChannel的IP地址" + socketChannel.getRemoteAddress());
                    // 分配缓存区容量
                    ByteBuffer buffer = ByteBuffer.allocate(128);
                    int readLet = socketChannel.read(buffer);
                    if (readLet > 0) {
                        // 读取数据
                        String output = new String(buffer.array()).trim();
                        Socket socket = socketChannel.socket();
                        // 客户端 域名+端口号
                        String clientInfo = socket.getInetAddress() + ":" + socket.getPort();
                        System.out.println(MessageFormat.format("来自客户端 {0} , 消息：{1}", clientInfo, output));
                        System.out.print("socket的IP地址: " + socket.getRemoteSocketAddress());
                    } else if(readLet == -1) {
                        System.out.println("客户端断开连接");
                        socketChannel.close();
                    }
                }
                iterator.remove();
            }
        }
    }
}
