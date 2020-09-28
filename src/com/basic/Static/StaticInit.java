package com.basic.Static;

/**
 * static与初始化
 * ①调用static变量，该变量在哪个类，初始化该类和其父类的static模块
 * ②第一次实例化   根到子类的static →  根非static+构造器  → ... → 该类非static+构造器
 *   第二次实例化   根非static+构造器  → ... → 该类非static+构造器
 *   静态语句块在加载该类时执行且只执行一次
 */
public class StaticInit {
    public static void main(String[] args){
      //  System.out.println(Son.name);   // static初始化，调用son.name
      //  System.out.println(Son.string);   // root类static初始化，调用该类string
      //  System.out.println(Son.string2);    // root,father初始化static，调用father类string2

        Son son = new Son();   // 根到该类的static模块 > 根类的非static+构造器 > 父类的非static+构造器 > ... > 该类的非static+构造器
        System.out.println("第二次实例化Son");
        new Son();    // 根类非static+构造器 → ... → 该类非static+构造器
    }
}
class Root {
    static String name = "root";
    static String string = "Root string";
    boolean flag = true;
    int age = 0;
    static {
        System.out.println("this is root static block.");
    }
    {
        System.out.println("this is non-static block");
    }
    public Root() {
        System.out.println("root constructor");
}
}
class Father extends Root {
    static String name = "father";
    static String string2 = "father string2";
    int age = 1;
    static {
        System.out.println("father static block1");
    }

    {
        System.out.println("father non-static block");
    }

    static {
        System.out.println("father static block 2");
    }

    public Father() {
        System.out.println("father constuctor");
    }
}
class Son extends Father {
    int age = 2;
    static String name = "son";
    {
        System.out.println("son non-static block");
    }
    static {
        System.out.println("son static block");
    }

    public Son() {
        System.out.println("son constructor");
    }
}