package com.basic.net.tcp.chatroom.mutil.demo0;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 */
public class ChatServer {
    public static void main(String[] args) throws IOException {
        ServerSocket server = new ServerSocket(9999);
        Socket socket = server.accept();
        // 读取数据
        // 输入流
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        // 只能接收一个客户端
        while (true) {
            String data = dis.readUTF();
            dos.writeUTF("server ---> " + data);
            dos.flush();
        }
    }
}
