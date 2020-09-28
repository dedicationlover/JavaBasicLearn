package com.annotation;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.lang.reflect.Field;

public class ActionListenerInstaller {
    // 处理注解的方法，其中obj是包含注解的对象
    public static void processAnnotations(Object obj) {
        try {
            // 获取obj对象的类
            Class c1 = obj.getClass();
            // 获取指定obj对象的所有Field，并遍历每个Field
            for (Field f : c1.getDeclaredFields()) {
                // 将指定Field设置成可自由访问的，避免private的Field不可访问
                f.setAccessible(true);
                // 获取指定Field、ActionListenerFor类型的注解
                ActionListenerFor a = f.getAnnotation(ActionListenerFor.class);
                if ((a != null) && (a instanceof AbstractButton)) {
                    // 获取a注解里的元数据listener(它是一个监听器类)
                    Class listenerClazz = Class.forName(a.listener());
                    // 使用反射来创建listener类的对象
                    ActionListener al = (ActionListener) listenerClazz.newInstance();
                    // 获取f Field实际对应的对象
                    AbstractButton ab = (AbstractButton) f.get(obj);
                    // 为ab对象添加事件监听器
                    ab.addActionListener(al);
                }
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
    }
}
