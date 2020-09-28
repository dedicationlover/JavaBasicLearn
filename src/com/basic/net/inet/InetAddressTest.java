package com.basic.net.inet;

import java.io.IOException;
import java.net.InetAddress;

public class InetAddressTest {
    public static void main(String[] args) throws IOException {
        // 根据主机名来获取对应的InetAddress实例
        InetAddress ip = InetAddress.getByName("www.baidu.com");
        // 获取该InetAddress实例的IP字符串
        System.out.println(ip.getHostAddress());   // 14.215.177.38
        // 根据原始IP地址获取对应的InetAddress实例
        InetAddress local = InetAddress.getByAddress(new byte[]{127,0,0,1});
        System.out.println("本机是否可达：" + local.isReachable(5000));  // 本机是否可达：true
        // 获取该InetAddress实例的对应的完全限定域名
        System.out.println(local.getCanonicalHostName());  // 127.0.0.1
    }
}
