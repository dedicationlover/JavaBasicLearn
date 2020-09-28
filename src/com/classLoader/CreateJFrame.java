package com.classLoader;

import java.lang.reflect.Constructor;

/**
 * 利用反射创建一个JFrame对象，使用指定的构造器
 */
public class CreateJFrame
{
    public static void main(String[] args) throws Exception
    {
        // 获取JFrame对应的Class对象
        Class<?> jframeClazz = Class.forName("javax.swing.JFrame");
        // 获取JFrame中带一个字符串参数的构造器
        Constructor ctor = jframeClazz.getConstructor(String.class);
        // 调用Constructor的newInstance方法创建对象
        Object obj = ctor.newInstance("测试窗口");
        // 输出JFrame对象
        System.out.println(obj);
    }
}
