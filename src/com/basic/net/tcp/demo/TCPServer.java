package com.basic.net.tcp.demo;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 1、创建服务器，指定端口 ServerSocket(int port)默认本机为服务器
 * 2、接收客户端的连接
 *
 * 运行后，在浏览器键入：http://localhost:8888，控制台输出“一个客户端建立连接”
 */
public class TCPServer {
    public static void main(String[] args) throws IOException {
        // 1、创建服务器，指定端口
        ServerSocket server = new ServerSocket(8888);
        // 2、接收客户端的连接  阻塞式
        Socket socket = server.accept();
        System.out.println("一个客户端建立连接");
    }
}
