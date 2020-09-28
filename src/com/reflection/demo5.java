package com.reflection;

import java.lang.reflect.Method;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Map;

public class demo5
{
    public void test1(Map<String, User> map, List<Integer> list)
    {
        System.out.println("test1 method...");
    }

    public Map<String, User> test2()
    {
        System.out.println("test2 method...");
        return null;
    }

    public static void main(String[] args)
    {
        demo5 demo = new demo5();
        Class clazz = demo.getClass();
        try
        {
            Method method = clazz.getDeclaredMethod("test1", Map.class, List.class);
            // 参数
            Type[] paramtypes = method.getGenericParameterTypes();
            for (Type temp : paramtypes)
            {
                System.out.println("===" + temp);
                if (temp instanceof ParameterizedType)
                {
                    Type[] types = ((ParameterizedType) temp).getActualTypeArguments();
                    for (Type t : types)
                    {
                        System.out.println("泛型类型" + t);
                    }
                }
            }

            // 返回类型
            Method me = clazz.getDeclaredMethod("test2", null);
            Type returntype = me.getGenericReturnType();
            if (returntype instanceof ParameterizedType)
            {
                Type[] types = ((ParameterizedType) returntype).getActualTypeArguments();
                for (Type temp : types)
                {
                    System.out.println("返回值类型，泛型" + temp);
                }
            }
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
