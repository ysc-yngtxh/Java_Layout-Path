package com.example.multiple;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import lombok.SneakyThrows;

// 在基础实现上增加线程池处理
public class MultiThreadHandler implements Runnable {

    private static final int POOL_SIZE = Runtime.getRuntime().availableProcessors();
    private static ExecutorService pool = Executors.newFixedThreadPool(POOL_SIZE);

    final SocketChannel socket;
    final SelectionKey sk;
    ByteBuffer input = ByteBuffer.allocate(1024);
    ByteBuffer output = ByteBuffer.allocate(1024);
    static final int READING = 0, SENDING = 1;
    int state = READING;

    public MultiThreadHandler(Selector sel, SocketChannel c) throws IOException {
        socket = c;
        c.configureBlocking(false);
        sk = socket.register(sel, 0);
        sk.attach(this);
        sk.interestOps(SelectionKey.OP_READ);
        sel.wakeup();
    }

    boolean inputIsComplete() { /* ... */ return true; }

    boolean outputIsComplete() { /* ... */ return true;	}

    void process() { /* ... */ }


    // ... 其他代码与Handler类似
    void read() throws IOException {
        socket.read(input);
        if (inputIsComplete()) {
            process();
            state = SENDING;
            sk.interestOps(SelectionKey.OP_WRITE);
        }
    }

    void send() throws IOException {
        socket.write(output);
        if (outputIsComplete()) {
            sk.cancel();
        }
    }

    @SneakyThrows
    @Override
    public void run() {
        if (state == READING) pool.execute(new ReadTask());
        else if (state == SENDING) send();
    }
    
    class ReadTask implements Runnable {
        public void run() {
            try {
                read();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
