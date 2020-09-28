package com.basic.net.nionet;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.nio.charset.Charset;
import java.util.Scanner;

/**
 * 客户端需要两个线程，一个线程负责读取用户的键盘输入，并将输入的内容写入SocketChannel中，
 * 另一个线程则不断地查询Selector对象的select()方法的返回值
 */
public class NClient {
    // 定义检测SocketChannel的Selector对象
    private Selector selector = null;
    // 定义处理编码和解码的字符集
    private Charset charset = Charset.forName("UTF-8");
    // 客户端SocketChannel
    private SocketChannel sc = null;

    public void init() throws IOException {
        selector = Selector.open();
        InetSocketAddress isa = new InetSocketAddress("127.0.0.1", 30000);
        // 调用open静态方法创建连接到指定主机的SocketChannel
        sc = SocketChannel.open(isa);
        // 设置该sc以非阻塞方式工作
        sc.configureBlocking(false);
        // 将SocketChannel对象注册到指定的Selector
        sc.register(selector, SelectionKey.OP_READ);
        // 启动读取服务器端数据的线程
        new ClientThread().start();
        // 创建键盘输入流
        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()) {
            // 读取键盘输入流
            String line = scanner.nextLine();
            // 将键盘输入的内容输出到SocketChannel中
            sc.write(charset.encode(line));
        }
    }

    // 定义读取服务端数据的线程
    private class ClientThread extends Thread {
        public void run() {
            try {
                while (selector.select() > 0) {
                    // 遍历每个有可能IO操作的channel对应的SelectionKey
                    for (SelectionKey sk : selector.selectedKeys()) {
                        // 删除正在处理的SelectionKey
                        selector.selectedKeys().remove(sk);
                        // 如果该SelectionKey对应的Channel中有可读的数据
                        if (sk.isReadable()) {
                            // 使用NIO读取channel中的数据
                            SocketChannel sc = (SocketChannel) sk.channel();
                            ByteBuffer buff = ByteBuffer.allocate(1024);
                            String content = "";
                            while (sc.read(buff) > 0) {
                                sc.read(buff);
                                buff.flip();
                                content += charset.decode(buff);
                            }
                            System.out.println("聊天信息：" + content);
                            sk.interestOps(SelectionKey.OP_READ);
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main (String[] args) throws IOException {
        new NClient().init();
    }
}
