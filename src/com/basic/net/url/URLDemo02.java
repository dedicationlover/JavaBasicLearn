package com.basic.net.url;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;

/**
 *  获取资源：源代码
 */
public class URLDemo02 {
    public static void main(String[] args) throws IOException {
        URL url = new URL("http://www.baidu.com");  // 主页默认资源
        // 获取资源   网络流
        /*   乱码
        InputStream is = url.openStream();
        byte[] info = new byte[1024];
        int len = 0;
        while ((len = is.read(info)) != -1) {
            System.out.println(new String(info));
        }
        is.close();
        */

        // 使用转换流
        BufferedReader br = new BufferedReader(new InputStreamReader(url.openStream(), "utf-8"));
        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream("baidu.html"), "utf-8"));
        String message = null;
        while ((message = br.readLine()) != null) {
            System.out.println(message);
            bw.append(message);
            bw.newLine();
        }
        bw.flush();
        bw.close();
        br.close();
    }
}
