package com.basic.net.tcp.demo1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

/**
 * 1、创建客户端：指定服务器+端口  此时就在连接  (客户端的端口由系统自动分配)
 * 2、接收数据 + 发送数据
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 1、创建客户端：指定服务器+端口   Socket(String host, int port)
        Socket socket = new Socket("localhost", 8888);
        // 2、读取数据
        BufferedReader br = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        String echo = br.readLine();
        // 逐行读取，但是服务器没有行号传过来，方法为阻塞方法
        System.out.println(echo);
    }
}
