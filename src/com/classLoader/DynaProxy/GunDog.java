package com.classLoader.DynaProxy;

public class GunDog implements Dog
{
    @Override
    public void info()
    {
        System.out.println("我是一只猎狗");
    }

    @Override
    public void run()
    {
        System.out.println("我奔跑迅速");
    }
}
/*
假设info()、run()两个方法代表代码块1、代码块2。
要求：程序执行info()、run()方法时能调用某个通用方法，但又不想以硬编码的方式调用该方法。
提供一个DogUtil类，该类里包含两个通用方法
 */