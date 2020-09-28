package com.basic.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetSocketAddress;

/**
 * 客户端
 * 1、创建客户端 + 端口
 * 2、准备数据
 * 3、数据打包（发送的服务器地址 + 端口)
 * 4、发送数据
 * 5、释放资源
 * @author Administrator
 *
 */
public class MyClient {

    public static void main(String[] args) throws IOException {
        // 1、创建客户端 + 端口
        DatagramSocket client = new DatagramSocket(2048); // 因为目前在同一电脑上，所以不能和服务器一样
        // 2、准备数据
        String msg = "父母在不远游！";
        byte[] data = msg.getBytes();
        // 3、数据打包（发送的服务器地址 + 端口)
        // DatagramPacket(byte[] buf, int length, SocketAddress address)
        DatagramPacket packet = new DatagramPacket(data, data.length, new InetSocketAddress("localhost", 8888));
        // 4、发送数据
        client.send(packet);
        // 5、释放资源
        client.close();
    } // end main

} // end MyClient
