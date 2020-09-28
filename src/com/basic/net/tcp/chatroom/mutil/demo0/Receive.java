package com.basic.net.tcp.chatroom.mutil.demo0;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

/**
 * 接收数据线程
 */
public class Receive implements Runnable {
    // 输入流
    private DataInputStream dis;
    // 线程标识
    private boolean isRunning = true;

    public Receive() {
    }

    public Receive(Socket socket) {
        try {
            dis = new DataInputStream(socket.getInputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
            e.printStackTrace();
        }
    }

    public String receive() {
        String message = "";
        try {
            message = dis.readUTF();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dis);
            e.printStackTrace();
        }
        return message;
    }

    @Override
    public void run() {
        // 线程体
        while (true) {
            System.out.println(receive());
        }
    }
}
