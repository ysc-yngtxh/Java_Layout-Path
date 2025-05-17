package com.example.bio.single;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import lombok.SneakyThrows;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-13 下午6:10:00
 * @apiNote TODO BIO 【Block I/O】阻塞同步IO
 */
public class SingleBioServer {

	@SneakyThrows
	public static void main(String[] args) {
		ServerSocket serverSocket = new ServerSocket(9000);
		while (true) {
			System.out.println("服务器已启动并监听9000端口");
			// 阻塞1：等待连接时阻塞。即accept()表示接受客户端连接，没有客户端连接时，会进行阻塞。
			Socket clientSocket = serverSocket.accept();
			System.out.println("有客户端连接了...");
			handler(clientSocket);
		}
	}

	private static void handler(Socket clientSocket) throws IOException {
		byte[] bytes = new byte[1024];
		System.out.println("准备read...");
		// 阻塞2：等待数据时阻塞。即没有数据时，会进行阻塞
		int read = clientSocket.getInputStream().read(bytes);
		System.out.println("read完毕...");
		if (read != -1) {
			System.out.println("接收到客户端数据：" + new String(bytes, 0, read) + "\n");
		}

		// clientSocket.getOutputStream().write("Hello Client".getBytes());
		// clientSocket.getOutputStream().flush();
	}

}
