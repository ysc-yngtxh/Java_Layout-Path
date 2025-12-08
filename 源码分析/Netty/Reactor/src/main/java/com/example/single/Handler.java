package com.example.single;

import java.io.IOException;
import java.net.Socket;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.text.MessageFormat;
import lombok.SneakyThrows;

public class Handler implements Runnable {

	public final SocketChannel socketChannel;
	public final SelectionKey selectionKey;
	public ByteBuffer input = ByteBuffer.allocate(1024);
	public ByteBuffer output = ByteBuffer.allocate(1024);
	public static final int READING = 0, SENDING = 1;
	public int state = READING;

	public Handler(Selector sel, SocketChannel sc) throws IOException {
		socketChannel = sc;
		socketChannel.configureBlocking(false);
		selectionKey = socketChannel.register(sel, 0);
		selectionKey.attach(this);
		selectionKey.interestOps(SelectionKey.OP_READ);
		sel.wakeup();
	}

	@SneakyThrows
	@Override
	public void run() {
		if (state == READING) read();
		else if (state == SENDING) send();
	}

	public void read() throws IOException {
		int read = socketChannel.read(input);  // 不阻塞
		if (inputIsComplete()) {
			process(read);
			state = SENDING;
			selectionKey.interestOps(SelectionKey.OP_WRITE);
		}
	}

	public void send() throws IOException {
		int write = socketChannel.write(output); // 不阻塞
		if (outputIsComplete()) {
			selectionKey.cancel();
		}
	}

	public boolean inputIsComplete() {
		return true;
	}

	public boolean outputIsComplete() {
		return true;
	}

	@SneakyThrows
	public void process(int readLet) {
		if (readLet > 0) {
			// 读取数据
			String output = new String(input.array()).trim();
			Socket socket = socketChannel.socket();
			// 客户端 域名+端口号
			String clientInfo = socket.getInetAddress() + ":" + socket.getPort();
			System.out.println(MessageFormat.format("来自客户端 {0} , 消息：{1}", clientInfo, output));
			System.out.print("socket的IP地址: " + socket.getRemoteSocketAddress());
		} else if (readLet == -1) {
			System.out.println("客户端断开连接");
			socketChannel.close();
		}
	}
}
