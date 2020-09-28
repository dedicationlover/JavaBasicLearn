package com.basic.net.tcp.demo2;

import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 使用DataInputStream和DataOutputStream
 *
 * 1、创建服务器，指定端口
 * 2、接收客户端的连接
 * 3、发送数据 + 接收数据
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 1、创建服务器，指定端口
        ServerSocket serverSocket = new ServerSocket(8888);
        // 2、接收客户端的连接  阻塞式（没接收到，不会往下执行）
        Socket socket = serverSocket.accept();
        System.out.println("一个客户端建立连接");
        // 3、发送数据
        String msg = "欢迎使用！";
        // 获取输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF(msg);
        dos.flush();
    }
}
