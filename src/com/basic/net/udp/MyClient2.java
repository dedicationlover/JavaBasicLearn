package com.basic.net.udp;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;
/**
 * 客户端
 * 1、创建客户端+端口
 * 2、准备数据  double -> 字节数组   字节数组输出流
 * 3、数据打包（发送的服务器地址+端口）
 * 4、发送数据
 * 5、释放资源
 */
public class MyClient2 {
    public static void main(String[] args) throws IOException {
        DatagramSocket socket = new DatagramSocket(2048); // 同一台电脑上，不能和服务器一样
        double num = 3.1415926;
        byte[] data = convert(num);
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        socket.send(packet);
        socket.close();
    }

    /**
     * double  --->  字节数组
     * @param num
     * @return
     */
    private static byte[] convert(double num) throws IOException {
        byte[] data = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        DataOutputStream dos = new DataOutputStream(bos);
        dos.writeDouble(num);
        dos.flush();
        data = bos.toByteArray();
        dos.close();
        return data;
    }
}
