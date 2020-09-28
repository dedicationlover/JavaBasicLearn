//package com.javaassist;
//
//import java.io.IOException;
//import java.lang.reflect.Method;
//import java.util.Arrays;
//
//import javassist.CannotCompileException;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtConstructor;
//import javassist.CtField;
//import javassist.CtMethod;
//import javassist.CtNewMethod;
//import javassist.NotFoundException;
//
///**
// * 测试javassist的API
// * 方法及属性的操作
// * @author Administrator
// *
// */
//public class Demo02 {
//    /**
//     * 处理类的基本用法
//     * @throws NotFoundException
//     * @throws CannotCompileException
//     * @throws IOException
//     */
//    public static void test01() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.third.testJavassist.Emp");
//        byte[] bytes = cc.toBytecode();
//        System.out.println(Arrays.toString(bytes));
//        // 获取类名
//        System.out.println(cc.getName());//包+类名
//        // 获取简要类名
//        System.out.println(cc.getSimpleName()); // 类名
//        // 获取父类
//        System.out.println(cc.getSuperclass());
//        // 获得接口
//        System.out.println(cc.getInterfaces());
//    }
//    /**
//     * 测试产生新的方法
//     * @throws Exception
//     */
//    public static void test02() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.third.testJavassist.Emp");
//
//        // 添加新方法
//
//        //	CtMethod m = CtNewMethod.make("public int add(int a, int b){return a+b;}", cc);
//
//        // 方法声明
//        CtMethod m = new CtMethod(CtClass.intType, "add",
//                new CtClass[]{CtClass.intType,CtClass.intType}, cc);
//        // 访问修饰
//        m.setModifiers(1);
//        // 方法体
//        m.setBody("{System.out.println(\"method add...\");return $1+$2;}");
//        // 加入类中
//        cc.addMethod(m);
//
//        // 通过反射调用新增方法
//        Class clazz = cc.toClass();
//        // 通过调用Emp无参构造器，创建新的Emp对象
//        Object obj = clazz.newInstance();
//        Method method = clazz.getDeclaredMethod("add", int.class,int.class);
//        Object result = method.invoke(obj, 200,520);
//        System.out.println((Integer)result);
//    }
//    /**
//     * 修改已有的方法信息，修改已有的方法体
//     * @throws Exception
//     */
//    public static void test03() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.third.testJavassist.Emp");
//
//        CtMethod cm = cc.getDeclaredMethod("sayHello", new CtClass[]{CtClass.intType});
//        // 添加到已有方法体前面
//        cm.insertBefore("System.out.println($1);System.out.println(\"start\");");
//        // 在某一行加入代码
//        cm.insertAt(9, "int b = 9; System.out.println(a+b);");
//        // 添加到已有方法体后面
//        cm.insertAfter("System.out.println(\"after....\");");
//
//        // 通过反射调用新增方法
//        Class clazz = cc.toClass();
//        // 通过调用Emp无参构造器，创建新的Emp对象
//        Object obj = clazz.newInstance();
//        Method method = clazz.getDeclaredMethod("sayHello", int.class);
//        method.invoke(obj, 520);
//    }
//    /**
//     * 增加属性，及其set、get方法
//     * @throws Exception
//     */
//    public static void test04() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.third.testJavassist.Emp");
//
//        // 添加属性
//        // 一种方式
//        //	CtField empno = CtField.make("private int empno;", cc);
//        // 另一种方法
//        CtField field = new CtField(CtClass.intType, "salary", cc);
//        field.setModifiers(2);
//        cc.addField(field, "1000");
//
//        // 获取指定的属性
//        //	cc.getDeclaredField("empno");
//
//        // 增加相应的set和get方法
//        cc.addMethod(CtNewMethod.getter("getSalary", field));
//        cc.addMethod(CtNewMethod.setter("setSalary", field));
//    }
//    /**
//     * 构造器操作
//     * @throws Exception
//     */
//    public static void test05() throws Exception {
//        ClassPool pool = ClassPool.getDefault();
//        CtClass cc = pool.get("com.third.testJavassist.Emp");
//        // 获取所有的构造器
//        CtConstructor[] cs = cc.getConstructors();
//        for(CtConstructor constructor : cs){
//            System.out.println(constructor.getLongName());
//            // 可以加入insertBefore, insertAfter
//        }
//    }
//    public static void main(String[] args) throws Exception {
//        //	test01();
//        //	test02();
//        //	test03();
//        //	test04();
//        test05();
//    }
//}
//
