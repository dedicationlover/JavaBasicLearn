package com.basic.net.tcp.chatroom.mutil.demo0;

import java.io.IOException;
import java.net.Socket;

/**
 * 创建客户端：发送数据 + 接收数据 （独立，两条道）
 * 写出数据：输出流
 * 读取数据：输入流
 */
public class ChatClient {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 9999);
        // 控制台输入流
        new Thread(new Send(client)).start();
        new Thread(new Receive(client)).start();
    }
}
