package com.basic.net.tcp.chatroom.mutil.demo0;

import java.io.Closeable;
import java.io.IOException;

/**
 * 关闭流的方法
 */
public class CloseUtil {
    public static void closeAll(Closeable... io) {
        for (Closeable temp : io) {
            try {
                if (temp != null) {
                    temp.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
