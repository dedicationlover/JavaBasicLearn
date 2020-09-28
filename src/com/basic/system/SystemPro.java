package com.basic.system;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class SystemPro {
    public static void main(String[] args) throws IOException {
        // 获取系统的所有环境变量
        Map<String, String> map = System.getenv();
        for (String str : map.keySet()) {
            System.out.println(str + "--->" + map.get(str));
        }
        // 获取指定环境变量的值
        System.out.println(map.get("JAVA_HOME"));
        // 获取所有的系统属性
        Properties properties = System.getProperties();
        // 将所有属性保存到pro.txt中
        properties.store(new FileOutputStream("pro.txt"), "System.properties");
        // 输出特定的系统属性
        System.out.println(System.getProperty("os.name"));
    }
}
