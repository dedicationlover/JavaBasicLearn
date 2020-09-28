//package com.basic.net.udp.chart;
//
//import javax.swing.*;
//import java.io.IOException;
//import java.net.*;
//import java.util.ArrayList;
//
///**
// * 底层通信的工具类需要一个MulticastSocket和一个DatagramSocket
// *
// * 聊天交换信息的工具类
// */
//public class ComUtil {
//    // 使用常量作为本程序的多点广播IP地址
//    private static final String BROADCAST_IP = "230.0.0.1";
//    // 使用常量作为本程序的多点广播目的的端口
//    // DatagramSocket所用的端口为该端口-1
//    private static final int BROADCAST_PORT = 30000;
//    // 定义每个数据报的最大大小为4K
//    private static final int DATA_LEN = 4096;
//
//    // 定义本程序的MulticastSocket实例
//    private MulticastSocket socket = null;
//    // 定义本程序私聊的socket实例
//    private DatagramSocket singleSocket = null;
//    // 定义广播的IP地址
//    private InetAddress broadcastAddress = null;
//    // 定义接收网络数据的字节数组
//    byte[] inBuff = new byte[DATA_LEN];
//    // 以指定字节数组创建准备接收数据的DatagramPacket对象
//    private DatagramPacket inPacket = new DatagramPacket(inBuff, inBuff.length);
//    // 定义一个用于发送的DatagramPacket对象
//    private DatagramPacket outPacket = null;
//    // 聊天主界面
//    private LanChat lanTalk;
//
//    // 构造器 初始化资源
//    public ComUtil(LanChat lanTalk) throws IOException {
//        this.lanTalk = lanTalk;
//        // 创建用于发送、接收数据的MulticastSocket对象
//        // 因为该MulticastSocket对象需要接收，所以有指定端口
//        socket = new MulticastSocket(BROADCAST_PORT);
//        // 创建私聊用的DatagramSocket对象
//        singleSocket = new DatagramSocket(BROADCAST_PORT + 1);
//        broadcastAddress = InetAddress.getByName(BROADCAST_IP);
//        // 将该socket加入指定的多点广播地址
//        socket.joinGroup(broadcastAddress);
//        // 设置本MulticastSocket发送的数据报被回送到自身
//        socket.setLoopbackMode(false);
//        // 初始化发送用的DatagramPacket，它包含一个长度为0的字节数组
//        outPacket = new DatagramPacket(new byte[0], 0, broadcastAddress, BROADCAST_PORT);
//        // 启动两个读取网络数据的线程
//        new ReadBroad().start();
//        Thread.sleep(1);
//        new ReadSingle().start();
//    }
//
//    // 广播消息的工具方法
//    public void broadCast(String msg) {
//        try {
//            // 将msg字符串转换字节数组
//            byte[] buff = msg.getBytes();
//            // 设置发送用的DatagramPacket里的字节数据
//            outPacket.setData(buff);
//            // 发送数据报
//            socket.send(outPacket);
//        } catch (IOException e) {
//            e.printStackTrace();
//            if (socket != null) {
//                socket.close();
//            }
//            JOptionPane.showMessageDialog(null,
//                    "发送信息异常，请确认30000端口空闲，且网络连接正常！",
//                    "网络异常", JOptionPane.ERROR_MESSAGE);
//            System.exit(1);
//        } // end try
//    }
//
//    // 定义向单独用户发送消息的方法
//    public void sendSingle(String msg, SocketAddress dest) {
//        try {
//            // 将msg字符串转换为字节数组
//            byte[] buff = msg.getBytes();
//            DatagramPacket packet = new DatagramPacket(buff, buff.length, dest);
//            singleSocket.send(packet);
//        } catch (IOException e) {
//            e.printStackTrace();
//            if(singleSocket != null){
//                // 关闭该Socket对象
//                singleSocket.close();
//            } // end if
//            JOptionPane.showMessageDialog(null,
//                    "发送信息异常，请确认30001端口空闲，且网络连接正常！",
//                    "网络异常", JOptionPane.ERROR_MESSAGE);
//            System.exit(1);
//        }
//    }
//
//    // 不断从DatagramSocket中读取数据的线程
//    class ReadSingle extends Thread {
//        // 定义接收网络数据的字节数组
//        byte[] singleBuff = new byte[DATA_LEN];
//        private DatagramPacket singlePacket = new DatagramPacket(singleBuff, singleBuff.length);
//
//        public void run() {
//            while (true) {
//                try {
//                    // 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里
//                    singleSocket.receive(singlePacket);
//                    // 处理读到的信息
//                    lanTalk.processMsg(singlePacket, true);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    if (singleSocket != null) {
//                        singleSocket.close();
//                    }
//                    JOptionPane.showMessageDialog(null,
//                            "接收信息异常，请确认30001端口空闲，且网络连接正常！",
//                            "网络异常", JOptionPane.ERROR_MESSAGE);
//                    System.exit(1);
//                }
//            }
//        }
//    }
//
//    // 持续读取MulticastSocket的线程
//    class ReadBroad extends Thread {
//        public void run() {
//            while (true) {
//                try {
//                    // 读取Socket中的数据，读到的数据放在inPacket所封装的字节数组里
//                    socket.receive(inPacket);
//                } catch (IOException e) {
//                    e.printStackTrace();
//                    CloseUtil.closeAll(socket);
//                    JOptionPane.showMessageDialog(null,
//                            "接收信息异常，请确认30000端口空闲，且网络连接正常！",
//                            "网络异常", JOptionPane.ERROR_MESSAGE);
//                    System.exit(1);
//                } // end try
//                // 打印输出从socket中读取的内容
//                String msg = new String(inBuff, 0, inPacket.getLength());
//                // 读到的内容是在线信息 保持该用户在线
//                if (msg.startsWith(YeekuProtocol.PRESENCE) && msg.endsWith(YeekuProtocol.PRESENCE)) {
//                    String userMsg = msg.substring(2, msg.length() - 2);
//                    String[] userInfo = userMsg.split(YeekuProtocol.SPLITTER);
//                    UserInfo user = new UserInfo(userInfo[1], userInfo[0], inPacket.getSocketAddress(), 0);
//                    // 控制是否需要添加该用户的旗标
//                    boolean addFlag = true;
//                    ArrayList<Integer> delList = new ArrayList<>();
//                    // 遍历系统中已有的所有用户，该循环必须循环完成
//                    for (int i = 1; i < lanTalk.getUserNum(); i++) {
//                        UserInfo current = lanTalk.getUser(i);
//                        // 将所有用户失去联系的次数加1
//                        current.setLost(current.getLost() + 1);
//                        // 如果该信息由指定用户发送过来
//                        if (current.equals(user)) {
//                            current.setLost(0);
//                            // 设置该用户无须添加
//                            addFlag = false;
//                        }
//                        if (current.getLost() > 2) {
//                            delList.add(i);
//                        }
//                    }
//                    // 删除delList中的所有索引对应的用户
//                    for (int i = 0; i < delList.size(); i++) {
//                        lanTalk.removeUser(delList.get(i));
//                    }
//                    if (addFlag) {
//                        // 添加新用户
//                        lanTalk.addUser(user);
//                    }
//                } else {  // 读到的内容是公聊信息 直接将该信息显示出来
//                    // 处理读到的信息
//                    lanTalk.processMsg(inPacket, false);
//                }
//            }
//        }
//    }
//}
