package com.reflection;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * 应用反射的API，获取类的信息（类的名字、方法、构造器等）
 */
@SuppressWarnings("all")
public class demo2
{
    public static void main(String[] args)
    {
        String path = "User";

        try
        {
            Class clazz = Class.forName(path);

            // 获取类名
            System.out.println(clazz.getName());         // User
            System.out.println(clazz.getSimpleName());   // User

            // 获取属性
            Field[] field = clazz.getFields();
            System.out.println(field.length);    // 1  public属性
            Field[] fields = clazz.getDeclaredFields();
            System.out.println(fields.length);   // 5  所有属性

            for (Field temp : fields)
            {
                System.out.println(temp);
            }
            // private int User.id
            // private java.lang.String User.name
            // private java.lang.String User.age
            // java.lang.String User.city
            // public java.lang.String User.state


            Field f = clazz.getDeclaredField("name");
            System.out.println(f);  // private java.lang.String User.name

            // 获取方法
            Method[] methods = clazz.getMethods();
            System.out.println(methods.length);   // 11  所有public方法（包括父类、object等）
            for (Method temp : methods)
            {
                System.out.println(temp);
            }
//            public int User.getId()
//            public void User.say()
//            public final void java.lang.Object.wait() throws java.lang.InterruptedException
//            public final void java.lang.Object.wait(long,int) throws java.lang.InterruptedException
//            public final native void java.lang.Object.wait(long) throws java.lang.InterruptedException
//            public boolean java.lang.Object.equals(java.lang.Object)
//            public java.lang.String java.lang.Object.toString()
//            public native int java.lang.Object.hashCode()
//            public final native java.lang.Class java.lang.Object.getClass()
//            public final native void java.lang.Object.notify()
//            public final native void java.lang.Object.notifyAll()

            Method[] mts = clazz.getDeclaredMethods();
            System.out.println(mts.length);   // 3  all
            for (Method temp : mts)
            {
                System.out.println(temp);
            }
//            public int User.getId()
//            public void User.say(java.lang.String)
//            private void User.learnBySelf()

            Method m1 = clazz.getDeclaredMethod("getId", null);  // 无参写null
            Method m2 = clazz.getDeclaredMethod("say", String.class);
            System.out.println(m1); // public int User.getId()
            System.out.println(m2); // public void User.say(java.lang.String)

            // 获取构造器
            Constructor c = clazz.getDeclaredConstructor(null);
            System.out.println(c);  // public User()

            Constructor[] all = clazz.getConstructors();   // public
            Constructor[] allcon = clazz.getDeclaredConstructors();   // all
            System.out.println(all.length);  // 2
            for (Constructor temp : all)
            {
                System.out.println(temp);
            }
//            public User()
//            public User(int,java.lang.String,java.lang.String)
            System.out.println(allcon.length);   // 3
            for (Constructor temp : allcon)
            {
                System.out.println(temp);
            }
//            public User(int,java.lang.String,java.lang.String)
//            private User(int)
//            public User()

            Constructor con = clazz.getDeclaredConstructor(int.class, String.class, String.class);
            System.out.println(con);
            // public User(int,java.lang.String,java.lang.String)
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchFieldException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
    }
}
