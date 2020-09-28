package com.basic.net.tcp.demo;

import java.io.IOException;
import java.net.Socket;

/**
 * 1、创建客户端：指定服务器+端口  此时就在连接（客户端的端口由系统自动分配）
 */
public class TCPClient {
    public static void main(String[] args) throws IOException {
        // 1、创建客户端：指定服务器+端口
        Socket socket = new Socket("localhost", 8888);
    }
}
