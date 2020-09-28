package com.basic.nio;

import java.nio.charset.Charset;
import java.util.SortedMap;

public class CharsetTest {
    public static void main(String[] args) {
        // 获取全部字符集
        SortedMap<String, Charset> map = Charset.availableCharsets();
        System.out.println(map.size());
        for (String alias : map.keySet()) {
            // 输出字符集的别名和对应的Charset对象
            System.out.println(alias + "--->" + map.get(alias));
        }
    }
}
