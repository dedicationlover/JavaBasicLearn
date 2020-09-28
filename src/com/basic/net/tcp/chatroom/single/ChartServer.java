package com.basic.net.tcp.chatroom.single;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 创建服务器
 */
public class ChartServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(9999);
        Socket socket = serverSocket.accept();
        // 读取数据
        // 输入流
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String data = dis.readUTF();
        // 写出数据
        // 输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("server ---> " + data);
        dos.flush();
    }
}
