//package com.basic.net.udp.chart;
//
//import java.net.SocketAddress;
//
///**
// * 该类封装了用户名、图标、对应的SocketAddress以及该用户对应的交谈窗口、失去联系的次数等信息
// *
// * 通过该UserInfo类的封装，所有客户端只需要维护该UserInfo类的列表，
// * 程序就可以实现广播、发送私聊信息等功能。
// */
//public class UserInfo {
//    // 该用户的图标
//    private String icon;
//    // 该用户的名字
//    private String name;
//    // 该用户的MulticastSocket所在的IP和端口
//    private SocketAddress address;
//    // 该用户失去联系的次数
//    private int lost;
//    // 该用户对应的交谈窗口
//    private ChatFram chatFrame;
//
//    public UserInfo() {}
//
//    public UserInfo(String icon, String name, SocketAddress address, int lost) {
//        this.icon = icon;
//        this.name = name;
//        this.address = address;
//        this.lost = lost;
//    } // end constructor
//
//    //  setter and getter
//
//    public String getIcon() {
//        return icon;
//    }
//
//    public void setIcon(String icon) {
//        this.icon = icon;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    public SocketAddress getAddress() {
//        return address;
//    }
//
//    public void setAddress(SocketAddress address) {
//        this.address = address;
//    }
//
//    public int getLost() {
//        return lost;
//    }
//
//    public void setLost(int lost) {
//        this.lost = lost;
//    }
//
//    public ChatFram getChatFrame() {
//        return chatFrame;
//    }
//
//    public void setChatFrame(ChatFram chatFrame) {
//        this.chatFrame = chatFrame;
//    }
//
//    //   overwrite hashCode() and equals()
//
//    // 使用address作为该用户的标识，所以根据address作为重写hashCode()和equals方法的标准
//    public int hashCode() {
//        return address.hashCode();
//    } // end hashCode
//
//    public boolean equals(Object obj) {
//        if (obj != null && obj.getClass() == UserInfo.class) {
//            return ((UserInfo) obj).getAddress().equals(address);
//        }
//    } // end equals
//}
