package com.basic.net.url;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.net.URLEncoder;

/**
 * 普通字符串<--->MIME字符串
 * 每个中文字符占2个字节，每个字节可以转换成2个十六进制的数字，
 * 所以每个中文字符将转换成“%XX%XX”的形式
 * 不同字符集，中文字节数并不一定完全相同，必须指定编码字符集
 */
public class URLDecoderTest {
    public static void main(String[] args) throws UnsupportedEncodingException {
        // 将application/x-www-form-urlencoded字符串
        // 转换成普通字符串
        // 其中的字符串直接从（书上示例-疯狂Java讲义）复制
        String keyWord = URLDecoder.decode("%E6%9D%8E%E5%88%9A+j2ee", "utf-8");
        System.out.println(keyWord);
        // 将普通字符串转换成
        // application/x-www-form-urlencoded字符串
        String urlStr = URLEncoder.encode("ROR敏捷开发最佳指南", "GBK");
        System.out.println(urlStr);
    }
}
