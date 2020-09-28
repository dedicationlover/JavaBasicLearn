package com.basic.nio;

/**
 * 使用Channel和Buffer传统的“用竹筒多次重复取水”的方式
 */
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.FileChannel;
import java.nio.charset.Charset;
import java.nio.charset.CharsetDecoder;

public class ReadFile {

    public static void main(String[] args) {
        FileChannel fcin = null;
        try {
            // 创建文件输入流
            FileInputStream fis = new FileInputStream("src/test/doc/ReadFile.java");
            // 创建一个FileChannel
            fcin = fis.getChannel();
            // 定义一个ByteBuffer对象，用于重复取水
            ByteBuffer bbuff = ByteBuffer.allocate(1024);
            // 将FileChannel中数据放入ByteBuffer中
            while( fcin.read(bbuff) != -1 ){
                // 锁定Buffer的空白区
                bbuff.flip();
                // 创建Charset对象
                Charset charset = Charset.forName("gb2312");
                // 创建解码器（CharsetDecoder)对象
                CharsetDecoder decoder = charset.newDecoder();
                // 将ByteBuffer的内容转码
                CharBuffer cbuff = decoder.decode(bbuff);
                System.out.println(cbuff);
                // 将buffer初始化，为下一次读取数据做准备
                bbuff.clear(); // 没有这句会无限输出
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (fcin != null){
                    fcin.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        } // end try-finally

    } // end main

} // end ReadFile

