package com.collection.map;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *  Properties继承Hashtable 无序
 */
public class PropertiesTest {
    public static void main(String[] args) throws IOException {
        Properties properties = new Properties();
        // 添加属性
        properties.setProperty("username", "yeelu");
        properties.setProperty("password", "mln");
        // 将属性保存到a.ini文件中
        properties.store(new FileOutputStream("a.ini"), "comment line");
        // 新建
        Properties properties1 = new Properties();
        // 增加属性
        properties1.setProperty("gender", "male");
        // 将a.ini文件名中的属性名-属性值追加到p1中
        properties1.load(new FileInputStream("a.ini"));
        System.out.println(properties1);  // {password=mln, gender=male, username=yeelu}
    }
}
