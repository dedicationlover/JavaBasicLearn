package com.basic.net.inet;

import java.net.InetAddress;
import java.net.InetSocketAddress;
import java.net.UnknownHostException;

public class InetSocketDemo {
    public static void main(String[] args) throws UnknownHostException {
        InetSocketAddress address = new InetSocketAddress("localhost", 2048);
        address = new InetSocketAddress(InetAddress.getByName("localhost"), 2048);
        System.out.println(address.getAddress());  // localhost/127.0.0.1
        System.out.println(address.getHostName());   // localhost
        System.out.println(address.getHostString());  // localhost
        System.out.println(address.getPort());   // 2048
    }
}
