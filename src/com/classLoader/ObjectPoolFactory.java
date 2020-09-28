package com.classLoader;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 简单的对象池，该对象池会根据配置文件读取name-value对，
 * 然后创建这些对象，并将这些对象放入一个HashMap中
 */
public class ObjectPoolFactory
{
    // 定义一个对象池，前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    // 定义一个创建对象的方法
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        // 根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        // 使用clazz对应类的默认构造器创建实例
        return clazz.newInstance();
    }

    // 该方法根据指定文件来初始化对象池，它会根据配置文件来创建对象
    public void initPool(String fileName)
    {
        FileInputStream fis = null;
        try
        {
            fis = new FileInputStream(fileName);
            Properties props = new Properties();
            props.load(fis);
            for (String name : props.stringPropertyNames())
            {
                // 每读出一对属性名--属性值对，就根据属性值创建一个对象
                // 调用createObject创建对象，并将对象添加到对象池中
                objectPool.put(name, createObject(props.getProperty(name)));
            }
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        finally
        {
            try
            {
                if (fis != null)
                {
                    fis.close();
                }
            }catch (IOException e)
            {
                e.printStackTrace();
            }
        }
    }

    public Object getObject(String name)
    {
        return objectPool.get(name);
    }

    public static void main(String[] args)
    {
        ObjectPoolFactory pf = new ObjectPoolFactory();
        pf.initPool("obj.txt");
        System.out.println(pf.getObject("a"));
    }
}
