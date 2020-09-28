package com.annotation.demo2;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;

/**
 * 使用反射读取注解的信息，模拟处理注解信息的流程
 */
public class demo
{
    public static void main(String[] args)
    {
        try
        {
            Class clazz = Class.forName("com.annotation.demo2.SxtStudent");
            // 获取类的注解
            Annotation[] annotations = clazz.getAnnotations();
            for (Annotation a : annotations)
            {
                System.out.println(a);  // @com.annotation.demo2.SxtTable(value=tb_student)
            }

            // 按注解名字获取
            SxtTable s = (SxtTable) clazz.getAnnotation(SxtTable.class);
            System.out.println(s.value());  // tb_student

            // 获取属性的注解
            Field f = clazz.getDeclaredField("studentName");
            // 获取该属性的某个注解
            SxtField sf = f.getAnnotation(SxtField.class);
            System.out.println(sf.columnName() + "--" + sf.type() + "--" + sf.length());  // sname--varchar--10
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
    }
}
