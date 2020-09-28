//package com.basic.net.tcp.chatroom.mutil.demo2;
//
//import com.basic.net.tcp.chatroom.mutil.demo0.CloseUtil;
//
//import java.io.Closeable;
//import java.io.DataInputStream;
//import java.io.DataOutputStream;
//import java.io.IOException;
//import java.net.ServerSocket;
//import java.net.Socket;
//import java.util.ArrayList;
//import java.util.List;
//
///**
// * 创建服务器
// */
//public class ChatServer {
//
//    private List<MyChannel> all = new ArrayList<>();
//
//    public static void main(String[] args) {
//        new ChatServer().start();
//    }
//
//    public void start() {
//        ServerSocket server = new ServerSocket(9999);
//        while (true) {
//            Socket socket = server.accept();
//            MyChannel myChannel = new MyChannel(socket);
//            all.add(myChannel);
//            new Thread(myChannel).start();
//        }
//    }
//
//    /**
//     * 一个客户端一条道路
//     * 1、输入流
//     * 2、输出流
//     * 3、接收数据
//     * 4、发送数据
//     */
//    private class MyChannel implements Runnable {
//        private DataInputStream dis;
//        private DataOutputStream dos;
//        private boolean isRunning = true;
//        private String username;
//
//        public MyChannel(Socket socket) {
//            try {
//                dis = new DataInputStream(socket.getInputStream());
//                dos = new DataOutputStream(socket.getOutputStream());
//                this.username = dis.readUTF();
////                this.send("welcome in chatroom!"); // 发给自己的
////                sendOthers(this.username + "online."); // 发送给其他客户
//            } catch (IOException e) {
//                isRunning = false;
//                CloseUtil.closeAll(dis, dos);
//                all.remove(this);  // 移除自己
//            }
//        }
//
//
//
//        @Override
//        public void run() {
//
//        }
//    }
//}
