package com.classLoader;

import java.lang.reflect.Array;

public class YeekuArray
{
    // 对Array的newInstance方法进行包装
    public static <T> T[] newInstance(Class<T> componentType, int length)
    {
        return (T[]) Array.newInstance(componentType, length);
    }

    public static void main(String[] args)
    {
        // 使用YeekuArray的newInstance()创建一维数组
        String[] arr = YeekuArray.newInstance(String.class, 10);
        // 使用YeekuArray的newInstance()创建二维数组
        // 在这种情况下，只要设置数组元素的类型是int[]即可
        int[][] intArr = YeekuArray.newInstance(int[].class, 5);
        arr[5] = "string array";
        // intArr是二维数组，初始化该数组的第二个数组元素——数组元素必须是一维数组
        intArr[1] = new int[]{23, 12};
        System.out.println(arr[5]);
        System.out.println(intArr[1][1]);
    }
}
