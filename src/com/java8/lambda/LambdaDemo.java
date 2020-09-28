package com.java8.lambda;

/**
 *  1. 使用lambda表达式
 *  2. 关于域外变量的访问，类成员变量均可，方法内需有final含义
 *  3. lambda表达式中不允许声明一个与前文中局部变量同名的变量
 */
public class LambdaDemo {
    /* final变量, 非static变量，lambda不能访问 */
//    final int fa = 5;
    /* static变量，是不是final，lambda都可以访问 */
//    static int fa = 5;
    public static void main(String[] args){
        /* 方法内的变量，不用final修饰, 但后续不能修改 -- 隐含有final的特性，否则报错 */
//        int fa =4;
        final int fa = 9;
//        int a = 0;    // lambda表达式之前不允许声明同名变量
        LambdaDemo lambdaDemo = new LambdaDemo();
        // 采用lambda表达式实现接口，每一个都不能作为实现类
        MathOperater add = (a, b) -> {return a + b;};
        MathOperater sub = (int a, int b) -> a - b;
        MathOperater mul = (a, b) -> a * b + fa;
        MathOperater div = (a, b) -> a / b;


        System.out.println(lambdaDemo.operation(1,3,add));
        System.out.println(lambdaDemo.operation(1,3,sub));
        System.out.println(lambdaDemo.operation(1,3,mul));
        System.out.println(lambdaDemo.operation(1,3,div));

        sayStr s1 = massage -> System.out.println("this is massage: " + massage);
        sayStr s2 = str -> System.out.println("this is str: " + str);
        s1.say("hello today");
        s2.say("tomorrow will better!");

        /* -------------------------------- */



    }

    interface sayStr{
        void say(String str);
    }

    interface MathOperater{
        int operation(int a, int b);
    }

    // 私有方法，用于调用接口内的方法
    private int operation(int a, int b, MathOperater mathOperater){
        return mathOperater.operation(a,b);
    }
}
interface Operater{
    int fun(int a, int b);
    int fun(String a, int b);
}
