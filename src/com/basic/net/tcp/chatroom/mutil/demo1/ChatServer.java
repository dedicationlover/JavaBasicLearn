package com.basic.net.tcp.chatroom.mutil.demo1;

import com.basic.net.tcp.chatroom.mutil.demo0.CloseUtil;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

/**
 * 创建服务器
 */
public class ChatServer {
    private List<MyChannel> all = new ArrayList<MyChannel>();

    public static void main(String[] args) throws IOException {
        new ChatServer().start();
    }

    public void start() throws IOException {
        ServerSocket server = new ServerSocket(9999);
        while (true) {
            Socket socket = server.accept();
            MyChannel myChannel = new MyChannel(socket);
            all.add(myChannel);   // 统一管理所有道路
            new Thread(myChannel).start();   // 一条道路
        }
    }

    private class MyChannel implements Runnable {
        private DataInputStream dis;
        private DataOutputStream dos;
        private boolean isRunning = true;

        /**
         * 一个客户端一条道路
         * 1、输入流
         * 2、输出流
         * 3、接收数据
         * 4、发送数据
         * @author Administrator
         *
         */
        public MyChannel(Socket socket) {
            try {
                dos = new DataOutputStream(socket.getOutputStream());
                dis = new DataInputStream(socket.getInputStream());
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos, dis);
                all.remove(this);
            }
        }

        /**
         * 读取数据
         * @return
         */
        private String receive() {
            String message = "";
            try {
                message = dis.readUTF();
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dis);
                all.remove(this);
            }
            return message;
        }

        private void send(String message) {
            if (message == null && message.equals("")) {
                return ;
            }
            try {
                dos.writeUTF(message);
            } catch (IOException e) {
                isRunning = false;
                CloseUtil.closeAll(dos);
                all.remove(this);
            }
        }

        private void sendOthers() {
            String message = receive();
            // 遍历容器
            for (MyChannel other : all) {
                if (other == this) {
                    continue;
                }
                // 发送给其他客户端
                other.send(message);
            }
        }

        @Override
        public void run() {
            while (isRunning) {
                sendOthers();
            }
        }
    }
}
