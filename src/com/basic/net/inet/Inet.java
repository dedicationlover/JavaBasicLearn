package com.basic.net.inet;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * InetAddress 没有封装端口
 */
public class Inet {
    public static void main(String[] args) throws UnknownHostException {
        //使用getLocalHost()返回InetAddress对象
        InetAddress addr = InetAddress.getLocalHost();
        System.out.println(addr.getHostAddress());  // 192.168.31.54
        System.out.println(addr.getHostName());     // LAPTOP-A84CDCAM
        // 根据域名得到InetAddress对象
        addr = InetAddress.getByName("www.baidu.com");
        System.out.println(addr.getHostAddress());   // 14.215.177.38
        System.out.println(addr.getHostName());      // www.baidu.com
        // 根据IP得到InetAddress 对象
        addr = InetAddress.getByName("220.181.112.244");
        System.out.println(addr.getHostAddress());   // 220.181.112.244
        // 如果IP不存在或者DNS服务器不允许进行IP地址和域名的映射，则返回地址
        System.out.println(addr.getHostName());      // 220.181.112.244
    }
}
