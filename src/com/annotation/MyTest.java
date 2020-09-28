package com.annotation;

public class MyTest {
    @Testable
    public static void m1() {}
    public static void m2() {}
    @Testable
    public static void m3() {}
    public static void m4() {}
    @Testable
    public static void m5() {}
    public static void m6() {}

    public static void m7() {
        throw new RuntimeException("Crash");
    }
    public static void m8() {}
}
