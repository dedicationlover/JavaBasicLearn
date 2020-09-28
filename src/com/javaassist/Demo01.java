//package com.javaassist;
//
//import java.io.IOException;
//
//import javassist.CannotCompileException;
//import javassist.ClassPool;
//import javassist.CtClass;
//import javassist.CtConstructor;
//import javassist.CtField;
//import javassist.CtMethod;
//import javassist.NotFoundException;
//
///**
// * 测试使用javassist生成一个新的类
// *
// * @author Administrator
// */
//public class Demo01
//{
//    public static void main(String[] args) throws CannotCompileException, NotFoundException, IOException
//    {
//        // 获取类池
//        ClassPool pool = ClassPool.getDefault();
//        // 要创建的类
//        CtClass cc = pool.makeClass("com.third.testJavassist.bean.Emp");
//
//        // 添加属性
//        CtField empno = CtField.make("private int empno;", cc);
//        CtField ename = CtField.make("private String ename;", cc);
//        cc.addField(empno);
//        cc.addField(ename);
//
//        // 添加方法
//        CtMethod get = CtMethod.make("public int getEmpno(){return empno;}", cc);
//        CtMethod set = CtMethod.make("public void setEmpno(int empno){this.empno = empno;}", cc);
//        cc.addMethod(get);
//        cc.addMethod(set);
//
//        // 添加构造器
//        // 方法头，参数
//        CtConstructor constructor = new CtConstructor(new CtClass[]{CtClass.intType, pool.get("java.lang.String")}, cc);
//        constructor.setBody("{this.empno = empno; this.ename = ename;}");
//        cc.addConstructor(constructor);
//        // 无惨构造器
//        CtConstructor constructor2 = new CtConstructor(new CtClass[]{}, cc);
//        constructor2.setBody("{}");
//        cc.addConstructor(constructor2);
//        cc.writeFile("d:/MyJava");  // .class文件
//        System.out.println("ok");
//    }
//}
//
