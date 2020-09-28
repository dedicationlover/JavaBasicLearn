package com.basic.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务器
 * 1、创建服务端 + 端口
 * 2、准备接收容器
 * 3、容器封装成包 DatagramPacket
 * 4、接收数据
 * 5、分析数据（数据在Packet里）
 * 6、释放资源
 * @author Administrator
 *
 */
public class MyServer {
    public static void main(String[] args) throws IOException {
        // 1.创建服务器+端口
        DatagramSocket server = new DatagramSocket(8888);  // 异常：端口可能被占用
        // 2、准备接收容器
        byte[] container = new byte[1024];
        // 3、容器封装成包 DatagramPacket
        DatagramPacket packet = new DatagramPacket(container, container.length);
        // 4、接收数据
        server.receive(packet); // IOException
        // 5、分析数据（数据在Packet里）
        byte[] data = packet.getData();
        int length = packet.getLength();
        System.out.println(new String(data, 0, length));
        // 6、释放资源
        server.close();

    }
}
