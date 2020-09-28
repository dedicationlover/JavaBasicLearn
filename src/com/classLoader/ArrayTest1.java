package com.classLoader;


import java.lang.reflect.Array;

/**
 * 使用Array来生成数组，为指定数组元素赋值，并获取指定数组元素的方式
 */
public class ArrayTest1
{
    public static void main(String[] args)
    {
        // 创建一个元素类型为String，长度为10的数组
        Object arr = Array.newInstance(String.class, 10);
        // 依次为arr数组中index为5、6的元素赋值
        Array.set(arr, 5, "yeelu");
        Array.set(arr, 6, "tinghua");
        // 依次取出arr数组中index为5、6的元素的值
        Object str1 = Array.get(arr, 5);
        Object str2 = Array.get(arr, 6);
        // 输出arr数组中index为5、6的元素
        System.out.println(str1);
        System.out.println(str2);
    }
}
