package com.example.bio.single;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-13 下午6:00:00
 * @apiNote TODO
 */
public class SingleBioClient {

	public static void main(String[] args) {
		try {
			// 连接服务端
			Socket socket = new Socket("127.0.0.1", 9000);
			String message;
			Scanner sc = new Scanner(System.in);
			message = sc.next();
			// 向服务端发送消息
			socket.getOutputStream().write(message.getBytes());
			socket.close();
			sc.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
