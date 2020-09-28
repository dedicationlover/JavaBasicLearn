package com.basic.synchronize;

/**
 * 单例设计模式
 * 1、懒汉式  MyJvm
 *    1）、构造器私有
 *    2）、声明内部静态成员属性（类的对象）
 *    3）、提供public方法访问内部静态属性，如果没有对象，需要创建对象
 * @author Administrator
 *
 */
public class MyJvm {
    private static MyJvm instance;
    private MyJvm(){
    } // end constructor
    public static MyJvm getInstance(){
        if (null == instance){  // 提高效率
            synchronized(MyJvm.class){
                if (null == instance){   // 线程安全
                    instance = new MyJvm();
                }
            } // end synchronized
        } // end if
        return instance;
    } // end getInstance
} // end MyJvm
/**
 * 饿汉式   线程安全 类加载时创建对象（类只加载一次）
 *    1）、构造器私有
 *    2）、声明并创建内部静态成员属性（new类的对象）
 *    3）、提供public方法访问内部静态属性
 * @author Administrator
 *
 */
class MyJvm2 {
    private static MyJvm2 instance = new MyJvm2();  // 加载类就得到初始化，不管用不用的到
    private MyJvm2(){
    } // end constructor
    public static MyJvm2 getInstance(){
        return instance;
    } // end getInstance
} // end MyJvm2
/**
 * 提高效率  类在使用时才加载
 * 只用MyJvm3,不调用get方法就涉及不到JVMholder类，就不会创建对象instance
 * 从而延缓了加载时机，  使用时才加载
 * @author Administrator
 *
 */
class MyJvm3 {
    private static class JVMholder{
        private static MyJvm3 instance = new MyJvm3();
    }
    private MyJvm3(){
    } // end constructor
    public static MyJvm3 getInstance(){
        return JVMholder.instance;
    } // end getInstance
} // end MyJvm3

