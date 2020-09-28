package com.classLoader.DynaProxy;

/**
 * JDK动态代理只能创建指定接口的动态代理
 */
public interface Dog
{
    // info方法声明
    void info();
    // run方法声明
    void run();
}

//        如果直接使用Proxy为该接口创建动态代理对象
//        则动态代理对象的所有方法的执行效果又将完全一样。
//        在这种情况下，先为该Dog接口提供一个简单的实现类：GunDog