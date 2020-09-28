package com.basic.net.tcp.chatroom.mutil.demo0;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据的线程
 */
public class Send implements Runnable {
    // 控制台输入流
    private BufferedReader console;
    // 管道输出流
    private DataOutputStream dos;
    // 控制线程
    private boolean isRunning = true;  // 判断是否正常初始化

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket) {
        this();
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(dos, console);
            e.printStackTrace();
        }
    }

    //从控制台接收数据
    private String getMsgFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 1、从控制台接收数据
     * 2、发送数据
     */
    public void send() {
        String message = getMsgFromConsole();
        if (message != null && !message.equals("")) {
            try {
                dos.writeUTF(message);
                dos.flush();
            } catch (IOException e) {
                // 消息发不出去说明线程没用 ---> 关闭线程
                isRunning = false;
                CloseUtil.closeAll(dos, console);
            }
        }
    }

    @Override
    public void run() {
        // 线程体
        while (isRunning) {
            send();
        }
    }
}
