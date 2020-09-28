package com.basic.reference;

import java.lang.ref.WeakReference;

/**
 * 强引用与弱引用
 */
public class WeakReferenceDemo {
    public static void main(String[] args) {
//        String string = "常量池中，强引用，不能回收";
//        WeakReference<String> weakReference = new WeakReference<>(string);
//        System.out.println("gc运行前：" + weakReference.get());
//        string = null;
//
//        System.gc();
//        System.runFinalization();
//        System.out.println("gc运行后：" + weakReference.get());

        String string = new String("string object");
        WeakReference<String> weakReference = new WeakReference<>(string);
        System.out.println("gc运行前：" + weakReference.get());  // gc运行前：string object
        string = null;

        System.gc();
        System.runFinalization();
        System.out.println("gc运行后：" + weakReference.get());  // gc运行后：null
    }
}
