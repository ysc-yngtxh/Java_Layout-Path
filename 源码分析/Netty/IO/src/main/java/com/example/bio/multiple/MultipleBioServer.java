package com.example.bio.multiple;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-13 下午6:09
 * @apiNote TODO BIO 【Block I/O】阻塞同步IO
 */
public class MultipleBioServer {
    public static void main(String[] args) {
        byte[] buffer = new byte[1024];
        try {
            ServerSocket serverSocket = new ServerSocket(8080);
            System.out.println("服务器已启动并监听8080端口");
            while (true) {
                // 阻塞1：等待连接时阻塞。即accept()表示接受客户端连接，没有客户端连接时，会进行阻塞。
                Socket socket = serverSocket.accept();
                // 使用线程的方式去执行阻塞方法
                new Thread(() -> {
                    System.out.println("服务器已接收到连接请求...");
                    int read = 0;
                    try {
                        // 阻塞2：等待数据时阻塞。即没有数据时，会进行阻塞
                        read = socket.getInputStream().read(buffer);
                    } catch (IOException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                    String content = new String(buffer, 0, read);
                    System.out.println("接收到的数据:" + content);
                }).start();
            }
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}