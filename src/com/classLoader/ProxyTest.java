package com.classLoader;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * 使用Proxy和InvocationHandler来生成动态代理对象
 */

interface Person2
{
    void walk();
    void sayHello(String name);
}

class MyInvokationHandler implements InvocationHandler
{

    /**
     * 执行动态代理对象的所有方法时，都会被替换成执行如下invoke方法
     * @param proxy 代表动态代理对象
     * @param method 代表正在执行的方法
     * @param args 执行代理对象方法时传入的实参
     * @return
     * @throws Throwable
     */
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        System.out.println("正在执行的方法：" + method);
        if (args != null)
        {
            System.out.println("下面是执行该方法时传入的实参：");
            for (Object val : args)
            {
                System.out.println(val);
            }
        }
        else
        {
            System.out.println("调用该方法无须实参！");
        }
        return null;
    }
}

public class ProxyTest
{
    public static void main(String[] args)
    {
        // 创建一个InvocationHandler对象
        InvocationHandler handler = new MyInvokationHandler();
        // 使用指定的InvocationHandler来生成一个动态代理对象
        Person2 p = (Person2) Proxy.newProxyInstance(Person2.class.getClassLoader(), new Class[]{Person2.class}, handler);
        // 调用动态代理对象的walk()和sayHello()方法
        p.walk();
        p.sayHello("孙悟空");

    }
}
