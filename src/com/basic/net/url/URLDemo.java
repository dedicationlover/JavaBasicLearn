package com.basic.net.url;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

public class URLDemo {
    public static void main(String[] args) throws IOException {
        // 绝对路径构建
        URL url = new URL("http://www.baidu.com:80/inde.html#aa?name=xxx");
        System.out.println(url.toString()); // http://www.baidu.com:80/inde.html#aa?name=xxx
        System.out.println(url.getAuthority());  // www.baidu.com:80
        System.out.println(url.getContent());  // sun.net.www.protocol.http.HttpURLConnection$HttpInputStream@6d311334
        System.out.println(url.getDefaultPort());  // 80
        System.out.println(url.getFile());  // /inde.html   资源
        System.out.println(url.getHost());  // www.baidu.com
        System.out.println(url.getProtocol());  // http
        System.out.println(url.getPath());   // /inde.html  对资源路径(相对于主机域名)
        System.out.println(url.getPort());  // 80    初始化不写输出-1
        System.out.println(url.getRef());   // aa?name=xxx
        System.out.println(url.getQuery());  // null   ?之后的从参数，存在锚点返回null，当做锚点的一部分,去掉#aa返回name=xxx
    }
}
