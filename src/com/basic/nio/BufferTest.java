package com.basic.nio;

import java.nio.CharBuffer;

public class BufferTest {
    public static void main(String[] args) {
        // 创建Buffer
        CharBuffer buff = CharBuffer.allocate(8);
        System.out.println("capacity: " + buff.capacity());
        System.out.println("limit: " + buff.limit());
        System.out.println("position: " + buff.position());
        // 放入元素
        buff.put('a');
        buff.put('b');
        buff.put('c');
        System.out.println("加入三个元素后，position=" + buff.position());
        // 调用flip()方法
        buff.flip();
        System.out.println("执行flip()后，limit = " + buff.limit());
        System.out.println("position = " + buff.position());
        // 取出第一个元素
        System.out.println("第一个元素（position = 0）:" +
                buff.get());
        System.out.println("取出第一个元素后 position = " +
                buff.position());
        // 调用clear方法
        buff.clear();
        System.out.println("执行clear方法后的position = " +
                buff.position());
        System.out.println("clear后，limit = " + buff.limit());
        System.out.println("clear后，缓冲区内容并没有被清除：" +
                buff.get(2));//若访问索引为3的值会是乱码
        System.out.println("执行绝对读取后,position = " +
                buff.position());

    }
}
