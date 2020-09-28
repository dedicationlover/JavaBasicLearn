package com.classLoader;

import java.net.URL;

public class BootstrapTest
{
    public static void main(String[] args)
    {
        // 获取根类加载器所加载的全部URL数组
        URL[] urls = sun.misc.Launcher.getBootstrapClassPath().getURLs();
        // 遍历、输出根类加载器加载的全部URL
        for (int i = 0; i < urls.length; i++)
        {
            System.out.println(urls[i].toExternalForm());
        }
    }
}


//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/resources.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/rt.jar    --- 核心类库
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/sunrsasign.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jsse.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jce.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/charsets.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/lib/jfr.jar
//        file:/C:/Program%20Files/Java/jdk1.8.0_231/jre/classes