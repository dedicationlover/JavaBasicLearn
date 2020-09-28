package com.basic.synchronize;

/**
 * 单例设计模式：外部使用时，确保一个类只有一个对象
 * @author Administrator
 *
 */
public class SynchronizedDemo02 {
    public static void main(String[] args) {
        JVMThread jvm1 = new JVMThread(1000);
        JVMThread jvm2 = new JVMThread(1000);
        // getInstance1  Thread-0---create-->Thread.JVM@1048210  Thread-1---create-->Thread.JVM@70dec
        // getInstance2  Thread-0---create-->Thread.JVM@1048210  Thread-1---create-->Thread.JVM@1048210
        // getInstance3  锁定类的字节码，效率不高
        // Thread-1---create-->Thread.JVM@70dec    Thread-0---create-->Thread.JVM@70dec

        jvm1.start();
        jvm2.start();

    } // end main
} // end SynchronizedDemo02
class JVMThread extends Thread {
    private long time;
    public JVMThread() {
    } // end constructor
    public JVMThread(long time) {
        this.time = time;
    } // end constructor
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + "---create-->" + JVM.getInstance(time));
    } // end run
} // end JVMThread
/*
 *  单例设计模式
 *  确保一个类只有一个对象
 *  懒汉式 (在使用时创建对象)   提高效率：double checking(双重检查)
 *  缺点：
 *     单线程中地址一样，是一个对象
 *     多线程中，判断完后创建有延时，各个线程延时不同，可能创建出的对象地址不同
 *  步骤：
 * 	   1、构造器私有化 避免外部直接创建对象
 *     2、声明一个私有的静态变量
 *     3、创建一个对外的公共的静态方法 访问改静态变量，如果变量没有对象，创建该对象
 */
class JVM {
    // 声明一个私有的静态变量
    private static JVM instance = null;
    // 构造器私有化 避免外部直接创建对象
    private JVM(){
    } // end constructor
    // 创建一个对外的公共的静态方法 访问改静态变量，如果变量没有对象，创建该对象
    public static JVM getInstance(long time){
        if (null == instance){  // 提高效率  避免已经存在对象时还要继续等待
            synchronized(JVM.class){
                if (null == instance){
                    try {
                        Thread.sleep(time);  // 延时 放大时间看效果
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    instance = new JVM();
                } // end if
            } // end synchronized
        } // end if
        return instance;
    } // end getInstance
    // 锁定类的字节码 效率不高  对象存在也需要等
    public static JVM getInstance3(long time){
        // 不能用this，因为没有对象，锁JVM.class 类JVM的字节码信息（任何类在JVM中都有它对应的模板，类加载器）
        synchronized(JVM.class){
            if (null == instance){
                try {
                    Thread.sleep(time);  // 延时 放大时间看效果
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                instance = new JVM();
            } // end if
            return instance;
        } // end synchronized
    } // end getInstance3
    // synchronized 方法  同一个对象
    public static synchronized JVM getInstance2(long time){
        if (null == instance){
            try {
                Thread.sleep(time);  // 延时 放大时间看效果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new JVM();
        } // end if
        return instance;
    } // end getInstance2
    // 没有同步   不是一个对象
    public static JVM getInstance1(long time){
        if (null == instance){
            try {
                Thread.sleep(time);  // 延时 放大时间看效果
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            instance = new JVM();
        } // end if
        return instance;
    } // end getInstance1
} // JVM

