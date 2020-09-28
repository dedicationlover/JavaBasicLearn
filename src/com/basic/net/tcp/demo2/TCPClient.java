package com.basic.net.tcp.demo2;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * 1、创建客户端：指定服务器+端口  此时就在连接  (客户端的端口由系统自动分配)
 * 2、接收数据 + 发送数据
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 1、创建客户端：指定服务器+端口   Socket(String host, int port)
        Socket socket = new Socket("localhost", 8888);
        /*
        // 设置连接超时
        // 创建一个无连接的Socket
        Socket s = new Socket();
        // 让该Socket连接到远程服务器，如果经过10秒还没有连接到，则认为连接超时。
        s.connect(new InetSocketAddress("localhost", 8888), 10000);
        */
        // 2、读取数据
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String echo = dis.readUTF();
        System.out.println(echo);
    }
}
