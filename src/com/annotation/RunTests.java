package com.annotation;

public class RunTests {
    public static void main(String[] args) throws SecurityException, ClassNotFoundException {
        // 处理MyTest类
        TestProcessor.process("MyTest");
    }

}
