package com.example.single;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import lombok.SneakyThrows;

public class Reactor implements Runnable {

	public final Selector selector;
	public final ServerSocketChannel serverSocket;

	public Reactor(int port) throws IOException {
		selector = Selector.open();
		serverSocket = ServerSocketChannel.open();
		serverSocket.socket().bind(new InetSocketAddress(port));
		serverSocket.configureBlocking(false);
		SelectionKey sk = serverSocket.register(selector, SelectionKey.OP_ACCEPT);
		sk.attach(new Acceptor());
	}

	public class Acceptor implements Runnable {
		@SneakyThrows
		public void run() {
			SocketChannel c = serverSocket.accept();
			if (c != null) {
				new Handler(selector, c);
			}
		}
	}

	@SneakyThrows
	public void run() {
		while (!Thread.interrupted()) {
			selector.select();
			Set<SelectionKey> selected = selector.selectedKeys();
			Iterator<SelectionKey> it = selected.iterator();
			while (it.hasNext()) {
				Runnable r = (Runnable) (it.next().attachment());
				if (r != null) {
					r.run();
				}
			}
			selected.clear();
		}
	}

}
