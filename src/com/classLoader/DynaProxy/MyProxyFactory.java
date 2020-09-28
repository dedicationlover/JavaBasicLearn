package com.classLoader.DynaProxy;

import java.lang.reflect.Proxy;

/**
 * 提供一个MyProxyFactory类，该对象专为指定的target生成动态代理实例
 */
public class MyProxyFactory
{
    // 为指定target生成动态代理对象
    public static Object getProxy(Object target)
    {
        // 创建一个MyInvokationHandler对象
        MyInvokationHandler handler = new MyInvokationHandler();
        // 为MyInvokationHandler设置target对象
        handler.setTarget(target);
        // 创建、并返回一个动态代理
        return Proxy.newProxyInstance(target.getClass().getClassLoader(), target.getClass().getInterfaces(), handler);
    }
}
