package com.classLoader;

import javax.swing.*;
import java.util.Date;

/**
 * 反射+泛型
 */

public class YeekuObjectFactory2
{
    public static <T> T getInstance(Class<T> cls)
    {
        try
        {
            return cls.newInstance();
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }

    public static void main(String[] args)
    {
        // 获取实例后无须类型转换
        Date d = YeekuObjectFactory2.getInstance(Date.class);
        JFrame f = YeekuObjectFactory2.getInstance(JFrame.class);
    }
}
