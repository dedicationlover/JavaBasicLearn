package com.DynamicCompile;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;

/**
 * 将一段String内容编译：先将其保存为对应的java文件，再编译
 */
public class Demo01
{
    public static void main(String[] args)
    {
        // 通过IO流操作，将字符串存储为一个临时文件，然后调用动态编译方法
        String str = "public class Hi{public static void main(String[] args){System.out.println(\"haha\");}}";
        File file = new File("d:/Hi.java");

        try
        {
            if (!file.exists())
            {
                file.createNewFile();
            }

            FileWriter  fileWriter = new FileWriter(file);
            System.out.println(fileWriter);
            fileWriter.write(str);
            fileWriter.flush();
            fileWriter.close();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
        int result = compiler.run(null, null, null, "d:/Hi.java");
        System.out.println(result == 0 ? "success" : "fail");

        // run

        // 通过Runtime执行类
        Runtime runtime = Runtime.getRuntime();
        try
        {
            Process process = runtime.exec("java -cp d:/ Hi");
            InputStream is = process.getInputStream();
            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String massage = "";
            while ((massage = br.readLine()) != null)
            {
                System.out.println(massage);
            }
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

        // 通过反射
        try
        {
            URL[] urls = new URL[]{new URL("file:/" + "d:/")};
            URLClassLoader loader = new URLClassLoader(urls);
            Class c = loader.loadClass("Hi");
            // 调用加载类的main方法
            Method m = c.getDeclaredMethod("main", String[].class);
            m.invoke(null, (Object) new String[]{});  // 静态方法不传object，设为null
            // 若要传两个数组
            // public static void test(String[] a, String[] b){}
            // m.invoke(null, (Object)new String[]{}, (Object)new String[]{})
            // 将String[]转为Object：如果按照如下方式
            // m.invoke(null, new String[]{"aa", "bb"});
            // 因为可变参数是JDK5之后才有，上面写法会被编译成
            // m.invoke(null, "aa", "bb");
            // 即将数组转为多个参数，不再和原方法匹配，所以要加（Object）转型

        }
        catch (MalformedURLException e)
        {
            e.printStackTrace();
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (NoSuchMethodException e)
        {
            e.printStackTrace();
        }
        catch (IllegalAccessException e)
        {
            e.printStackTrace();
        }
        catch (InvocationTargetException e)
        {
            e.printStackTrace();
        }
    }
}

/*
若抛空指针异常：源码中查找文件的目录在：C：\java\jre6\lib\tools.jar
但tools.jar在JDK中，所以手动将tool.jar复制到jre\lib下，就可以了
 */
