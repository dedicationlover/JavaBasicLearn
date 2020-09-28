package com.classLoader;

import java.io.FileInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * 对前面的对象池工厂进行加强，程序允许在配置文件增加配置对象的属性值，
 * 对象池工厂会读取该对象的属性值，并利用该对象对应的setter方法为对应属性设置值
 */
public class ExtendedObjectPoolFactory
{
    // 定义一个对象池，前面是对象名，后面是实际对象
    private Map<String, Object> objectPool = new HashMap<>();
    private Properties config = new Properties();
    // 从指定属性文件中初始化Properties对象
    public void init(String fileName)
    {
        try(// 初始化文件输入流
            FileInputStream fis = new FileInputStream(fileName))
        {
            config.load(fis);
        }
        catch (Exception e)
        {
            System.out.println("读取" + fileName + "异常");
        }
    }

    // 定义一个创建对象的方法
    // 该方法只要传入一个字符串类名，程序可以根据该类名生成Java对象
    private Object createObject(String clazzName) throws ClassNotFoundException, IllegalAccessException, InstantiationException
    {
        // 根据字符串来获取对应的Class对象
        Class<?> clazz = Class.forName(clazzName);
        // 使用clazz对应类的默认构造器创建实例
        return clazz.newInstance();
    }

    // 该方法根据指定文件来初始化对象池
    // 它会根据配置文件来创建对象
    public void initPool() throws IllegalAccessException, InstantiationException, ClassNotFoundException
    {
        for (String name : config.stringPropertyNames())
        {
            // 每取出一对属性名——属性值对，如果属性名中不包含百分号（%）
            // 就根据属性值创建一个对象
            // 调用createObject创建对象，并将对象添加到对象池中
            if (!name.contains("%"))
            {
                objectPool.put(name, createObject(config.getProperty(name)));
            }
        }
    }

    // 该方法根据指定文件来初始化对象池，它会根据配置文件来创建对象
    public void initProperty() throws NoSuchMethodException, InvocationTargetException, IllegalAccessException
    {
        for (String name : config.stringPropertyNames())
        {
            // 每取出一对属性名——属性值对，如果属性名中包含百分号(%)
            // 即可认为该属性是用于为对象设置属性值，下划线前半为对象名字，
            // 后半为属性名，程序将调用对应的setter方法来为对应属性设置属性值
            if (name.contains("%"))
            {
                // 将配置文件中属性名按%分割
                String[] objAndProp = name.split("%");
                // 取出需要设置属性的目标对象
                Object target = getObject(objAndProp[0]);
                // 该属性对应的setter方法名：set + "属性的首字母大写" + 剩下部分
                String mtdName = "set" +
                        objAndProp[1].substring(0, 1).toUpperCase() +
                        objAndProp[1].substring(1);
                // 通过target的getClass()获取它实现类所对应的Class对象
                Class<?> targetClass = target.getClass();
                // 获取该属性对应的setter方法
                Method mtd = targetClass.getMethod(mtdName, String.class);
                // 通过Method的invoke方法执行setter方法，
                // 将config.getProperty(name)的属性值作为调用setter的方法的实参
                mtd.invoke(target, config.getProperty(name));
            }
        }
    }

    public Object getObject(String name)
    {
        // 从objectPool中取出指定name对应的对象
        return objectPool.get(name);
    }

    public static void main(String[] args) throws IllegalAccessException, ClassNotFoundException,
            InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        ExtendedObjectPoolFactory epf = new ExtendedObjectPoolFactory();
        epf.init("extObj.txt");
        epf.initPool();
        epf.initProperty();
        System.out.println(epf.getObject("a"));
    }
}
