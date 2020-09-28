package com.reflection;

import java.lang.reflect.Method;

/**
 * 通过跳过安全检查，提高反射效率
 * 三种执行方法的效率差异比较
 */
public class demo4
{
    public static void test01()
    {
        User user = new User();
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000L; i++)
        {
            user.getId();
        }
        long endTime = System.currentTimeMillis();
        System.out.println("普通方法调用，执行10亿次，耗时：" + (endTime - startTime) + "ms");
    }

    public static void test02() throws Exception
    {
        User user = new User();
        Class clazz = user.getClass();
        Method m = clazz.getDeclaredMethod("getId", null);
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000L; i++)
        {
            m.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射动态方法调用，执行10亿次，耗时：" + (endTime - startTime) + "ms");
    }

    public static void test03() throws Exception
    {
        User user = new User();
        Class clazz = user.getClass();
        Method m = clazz.getDeclaredMethod("getId", null);
        m.setAccessible(true); // 不需要执行访问安全检查
        long startTime = System.currentTimeMillis();
        for (int i = 0; i < 10_0000_0000L; i++)
        {
            m.invoke(user, null);
        }
        long endTime = System.currentTimeMillis();
        System.out.println("反射动态方法调用，跳过安全检查，执行10亿次，耗时：" + (endTime - startTime) + "ms");
    }

    public static void main(String[] args) throws Exception
    {
        test01();
        test02();
        test03();
    }
}
//        普通方法调用，执行10亿次，耗时：634ms
//        反射动态方法调用，执行10亿次，耗时：1559ms
//        反射动态方法调用，跳过安全检查，执行10亿次，耗时：1135ms