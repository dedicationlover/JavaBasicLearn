package com.basic.net.udp;

import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;

/**
 * 服务器
 * 1、创建服务端+端口
 * 2、准备接收容器
 * 3、容器封装成包DatagramPacket
 * 4、接收数据
 * 5、分析数据（数据在Packet里）   字节数组 --> double
 * 6、释放资源
 */
public class MyServer2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(8888);
        byte[] container = new byte[1024];
        DatagramPacket packet = new DatagramPacket(container, container.length);
        socket.receive(packet);
        byte[] data = packet.getData();
        double num = convert(packet.getData());
        System.out.println(num);
        int len = packet.getLength();
        System.out.println(new String(data, 0, len));
    }

    /**
     * 字节数组 ----> double
     * @param data
     * @return
     * @throws IOException
     */
    public static double convert(byte[] data) throws IOException {
        DataInputStream dis = new DataInputStream(new ByteArrayInputStream(data));
        double num = dis.readDouble();
        dis.close();
        return num;
    }
}
