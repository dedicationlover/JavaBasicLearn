package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * 通过反射动态操作：属性、方法、构造器
 */
public class demo3
{
    public static void main(String[] args)
    {
        String path = "User";

        try
        {
            Class<User> clazz = (Class<User>) Class.forName(path);

            // 通过反射API调用构造方法，构造对象
            User user = clazz.newInstance();  // 实际上调用了User的无参构造器
            // 若User没有无参构造器，上面代码编译不会报错，运行时异常(初始化异常)
            // java.lang.InstantiationException: com.third.reflection.demo1.User
            // 作为javabean必须要有无参构造方法
            System.out.println(user);   // User@1b6d3586

            Constructor<User> c = clazz.getDeclaredConstructor(int.class, String.class, String.class);
            User u = c.newInstance(1, "wang", "小孩子");
            System.out.println(u.getId());   // 1

            // 通过反射API调用普通方法
            User u3 = clazz.newInstance();
            Method method = clazz.getDeclaredMethod("say", String.class);
            method.invoke(u3, "method invoke");   // say method invoke

            // 通过反射API操作属性
            User u4 = clazz.newInstance();
            Field f = clazz.getDeclaredField("id");
            f.setAccessible(true);  // 该属性不需要做安全检查，可以直接访问
            f.set(u4, 233);  // 通过反射直接写属性
            System.out.println(u4.getId());    // 233
            System.out.println(f.get(u4));  // 233    通过反射直接读属性
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InstantiationException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
}
