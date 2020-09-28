package com.basic.net.url;

import java.io.IOException;
import java.io.InputStream;
import java.io.RandomAccessFile;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

/**
 * 实现多线程下载
 * ①创建URL对象
 * ②获取指定URL对象所指向资源的大小（getFileLength方法），用到了URLConnection类，
 *   该类代表Java应用程序和URL之间的通信连接
 * ③在本地磁盘上创建一个与网络资源相同大小的空文件
 * ④计算每条线程应该下载网络资源的哪个部分（哪开始，哪结束）
 * ⑤依次创建。启动多条线程来下载网络资源的指定部分
 */
public class MutilDown {
    public static void main(String[] args) {
        final int DOWN_THREAD_NUM = 4;
        final String OUT_FILE_NAME = "down.jpg";
        InputStream[] isArr = new InputStream[DOWN_THREAD_NUM];
        RandomAccessFile[] outArr = new RandomAccessFile[DOWN_THREAD_NUM];
        try {
            // 创建URL对象
            URL url = new URL("http://images.china-pub.com/ebook35001-40000/35850/shupi.jpg");
            // 以此URL对象打开第一个输入流
            isArr[0] = url.openStream();
            long fileLen = getFileLength(url);
            System.out.println("网络资源的大小：" + fileLen);
            // 以输出文件名创建第一个RandomAccessFile输出流
            outArr[0] = new RandomAccessFile(OUT_FILE_NAME, "rw");
            // 创建一个与下载资源相同大小的空文件
            for (int i = 0; i < fileLen; i++) {
                outArr[0].write(0);
            }
            // 每个线程应该下载的字节数
            long numPerThread = fileLen / DOWN_THREAD_NUM;
            // 整个下载资源整除后剩下的余数
            long left = fileLen % DOWN_THREAD_NUM;
            for (int i = 0; i < DOWN_THREAD_NUM; i++) {
                // 为每个线程打开一个输入流、一个RandomAccessFile对象
                // 让每个线程分别负责下载资源的不同部分
                if (i != 0) {
                    isArr[i] = url.openStream();
                    outArr[i] = new RandomAccessFile(OUT_FILE_NAME, "rw");
                }
                // 分别启动多条线程来下载网络资源
                if (i == DOWN_THREAD_NUM - 1) {
                    // 最后一个线程下载指定 numPerThread + left个字节
                    new DownThread(i * numPerThread, (i + 1) * numPerThread + left, isArr[i], outArr[i]).start();
                } else {
                    // 每个线程负责下载一定的numPerThread个字节
                    new DownThread(i * numPerThread, (i + 1) * numPerThread, isArr[i], outArr[i]).start();
                }
            }
        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private static long getFileLength(URL url) throws IOException {
        long length = 0;
        URLConnection con = url.openConnection();
        // 获取连接URL资源的长度
        long size = con.getContentLengthLong();
        length = size;
        return length;
    }
}
// 定义下载从start处到end处的内容的线程
class DownThread extends Thread {
    // 定义字节数组的长度（取水的竹筒）
    private final int BUFF_LEN = 32;
    // 定义下载的开始点
    private long start;
    // 定义下载的结束点
    private long end;
    // 下载资源对应的输入流
    private InputStream is;
    // 将下载到的字节输出到raf中
    private RandomAccessFile raf;
    // 构造器，传入输入流、输出流和下载起始点、结束点
    public DownThread(long start, long end, InputStream is, RandomAccessFile raf) {
        // 输出该线程负责下载的字节位置
        System.out.println(start + " ---> " + end);
        this.start = start;
        this.end = end;
        this.is = is;
        this.raf = raf;
    }
    @Override
    public void run() {
        try {
            is.skip(start);  // 跳过和丢弃is流中start个字节
            raf.seek(start);  // 指针偏移量，下一个字节读取或写入
            // 定义读取输入流内容的缓存数组（竹筒）
            byte[] buff = new byte[BUFF_LEN];
            // 计算本线程负责下载资源的大小
            long contentLen = end - start;
            // 定义最多需要读取几次就可以完成本线程的下载
            long times = contentLen / BUFF_LEN + 4;
            // 实际读取的字节数
            int hasRead = 0;
            for (int i = 0; i < times; i++) {
                hasRead = is.read(buff);
                // 如果读取的字节数小于0，则推出循环
                if (hasRead < 0) {
                    break;
                }
                raf.write(buff, 0, hasRead);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            // 使用finally块来关闭当前线程的输入流、输出流
            try {
                if (is != null) {
                    is.close();
                }
                if (raf != null) {
                    raf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}