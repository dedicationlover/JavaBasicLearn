package com.basic.net.tcp.chatroom.mutil.demo2;

import com.basic.net.tcp.chatroom.mutil.demo0.CloseUtil;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 发送数据线程
 */
public class Send implements Runnable {

    // 控制台输入流
    private BufferedReader console;
    // 管道输出流
    private DataOutputStream dos;
    // 控制线程
    private boolean isRunning = true;
    // username
    private String username;

    public Send() {
        console = new BufferedReader(new InputStreamReader(System.in));
    }

    public Send(Socket socket, String username) {
        this();
        try {
            dos = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(console, dos);
        }
        this.username = username;
        send(this.username);

    }

    // 从控制台接收数据
    private String getMsgFromConsole() {
        try {
            return console.readLine();
        } catch (IOException e) {
            isRunning = false;
            CloseUtil.closeAll(console, dos);
        }
        return "";
    }

    /**
     * 1、从控制台接收数据
     * 2、发送数据
     * @param message
     */
    public void send(String message) {
        if (message != null && !message.equals("")) {
            try {
                dos.writeUTF(message);
                dos.flush();
            } catch (IOException e) {
                // 消息发不出去说明线程没用 --> 关闭线程
                isRunning = false;
                CloseUtil.closeAll(dos, console);
            }
        }
    }

    @Override
    public void run() {
        while (isRunning) {
            send(getMsgFromConsole());
        }
    }
}
