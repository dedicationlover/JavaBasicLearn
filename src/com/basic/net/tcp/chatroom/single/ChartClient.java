package com.basic.net.tcp.chatroom.single;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

/**
 *  创建客户端：发送数据 + 接收数据 （独立，两条道）
 *  写出数据：输出流
 *  读取数据：输入流
 */
public class ChartClient {
    public static void main(String[] args) throws IOException {
        Socket socket = new Socket("localhost", 9999);
        // 输出流
        DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
        dos.writeUTF("first");
        dos.flush();
        // 输入流
        DataInputStream dis = new DataInputStream(socket.getInputStream());
        String data = dis.readUTF();
        System.out.println(data);
    }
}
