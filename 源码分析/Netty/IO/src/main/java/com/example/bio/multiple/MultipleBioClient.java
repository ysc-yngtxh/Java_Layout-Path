package com.example.bio.multiple;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author 游家纨绔
 * @dateTime 2024-04-13 下午6:09
 * @apiNote TODO
 */
public class MultipleBioClient {
    public static void main(String[] args) {
        try{
            Socket socket = new Socket("127.0.0.1", 8080);
            String message;
            Scanner sc = new Scanner(System.in);
            message = sc.next();
            socket.getOutputStream().write(message.getBytes());
            socket.close();
            sc.close();
        } catch(IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}
