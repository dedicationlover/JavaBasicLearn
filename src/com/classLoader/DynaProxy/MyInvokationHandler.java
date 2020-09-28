package com.classLoader.DynaProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

/**
 * 借助于Proxy和InvocationHandler就可以实现：
 * 当程序调用info()方法和run()方法时，系统可以“自动”将method1和method2两个通用方法
 * 插入info()和run()方法执行中。
 * 这个程序的关键在MyInvokationHandler类，是一个InvocationHandler实现类，
 * 该实现类的invoke方法将会作为代理对象的方法实现
 */
public class MyInvokationHandler implements InvocationHandler
{
    // 需要被代理的对象
    private Object target;
    public void setTarget(Object target)
    {
        this.target = target;
    }

    // 执行动态代理对象的所有方法时，都会被替换成执行如下的invoke方法
    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable
    {
        DogUtil du = new DogUtil();
        // 执行DogUtil对象中的method1
        du.method1();
        // 以target作为主调来执行method方法
        Object result = method.invoke(target, args);
        // 执行DogUtil对象中的method2
        du.method2();
        return result;
    }
}
