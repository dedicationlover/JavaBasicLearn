package com.basic.net.udp;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 使用DatagramSocket实现server/client结构的网络通信程序，
 * 服务器端使用循环1000次来读取DatagramSocket中的数据报，
 * 每当读到内容之后向该数据报的发送者送回一条信息
 */
public class UdpServer {
    // 端口
    public static final int PORT = 30000;
    // 每个数据报的最大大小为4K
    private static final int DATA_LEN = 4096;
    // 定义该服务器使用的DatagramSocket
    private DatagramSocket socket = null;
    // 定义接收网络数据的字节数组
    private byte[] inBuff = new byte[DATA_LEN];
    // 以指定字节数组创建准备接受数据的DatagramPacket对象
    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
    // 定义一个用于发送的DatagramPacket对象
    private DatagramPacket outPacket;
    // 定义一个字符串数组，服务器发送该数组的元素
    String[] books = new String[]{
            "轻量级j2ee企业应用实战",
            "基于J2EE的AJAX宝典",
            "Struts2权威指南",
            "ROR敏捷开发最佳实践"
    };

    public void init() {
        try {
            // 创建DatagramSocket对象
            socket = new DatagramSocket(PORT);
            // 采用循环接受数据
            for (int i = 0; i < 1000; i++) {
                // 读取socket中的数据，读到的数据放在inPacket所封装的字节数组中
                socket.receive(inPacket);
                // 判断inPacket.getData()和inBuff是否是同一个数组
                System.out.println(inBuff == inPacket.getData());
                // 将接收到的内容转成字符串输出
                System.out.println(new String(inBuff, 0, inPacket.getLength()));
                // 从字符串数组中取出一个元素作为发送的数据
                byte[] sendData = books[i % 4].getBytes();
                // 以指定字节数数组作为发送数据、以刚接收到的DatagramPacket的
                // 源SocketAddress作为目标SocketAddress创建DatagramPacket
                outPacket = new DatagramPacket(sendData, sendData.length, inPacket.getSocketAddress());
                // 发送数据
                socket.send(outPacket);
            }
        } catch (SocketException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (socket != null) {
                socket.close();
            }
        }
    }

    public static void main(String[] args) {
        new UdpServer().init();
    }
}
