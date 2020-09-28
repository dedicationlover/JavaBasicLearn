package com.classLoader;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * 自定义的ClassLoader, 通过重写findClass方法来实现自定义的类加载机制，
 * 这个ClassLoader可以在加载类之前先编译该类的源文件，从而实现运行java之前先编译该程序的目标，
 * 从而允许使用该ClassLoader来直接运行java源文件。
 * （findClass方法中先检查需要加载类的class文件是否存在，若不存在则先编译源文件，
 * 再调用ClassLoader的defineClass()方法来加载这个class文件，并生成相应的Class对象）
 */
public class CompileClassLoader extends ClassLoader
{
    // 读取一个文件的内容
    @SuppressWarnings("resource")
    private byte[] getBytes(String filename) throws IOException
    {
        File file = new File(filename);
        long len = file.length();
        byte[] raw = new byte[(int) len];
        FileInputStream fin = new FileInputStream(file);
        // 一次读取class文件的全部二进制数据
        int r = fin.read(raw);
        if (r != len)
        {
            throw new IOException("无法读取全部文件：" + r + " != " + len);
        }
        fin.close();
        return raw;
    }

    // 定义编译指定Java文件的方法
    private boolean compile(String javaFile) throws IOException
    {
        System.out.println("CompileClassLoader:正在编译 " + javaFile + "...");
        // 调用系统的javac命令
        Process p = Runtime.getRuntime().exec("javac " + javaFile);
        try
        {
            // 其他线程都等待这个线程完成
            p.waitFor();
        }
        catch (InterruptedException ie)
        {
            System.out.println(ie);
        }
        int ret = p.exitValue();
        // 返回编译是否成功
        return ret == 0;
    }

    // 重写ClassLoader的findClass方法
    @Override
    protected Class<?> findClass(String name) throws ClassNotFoundException
    {
        Class clazz = null;
        // 将包路径中的点（.）替换成斜线（/）
        String fileStub = name.replace(".", "/");
        String javaFilename = fileStub + ".java";
        String classFilename = fileStub + ".class";
        File javaFile = new File(javaFilename);
        File classFile = new File(classFilename);
        // 当指定Java源文件存在，且class文件不存在、或者Java源文件的修改时间比class文件修改时间晚时，重新编译
        if (javaFile.exists() &&
                (!classFile.exists() || javaFile.lastModified() > classFile.lastModified()))
        {
            // 如果编译失败，或者该class文件不存在
            try
            {
                if (!compile(javaFilename) || !classFile.exists())
                {
                    throw new ClassNotFoundException("ClassNotFoundException:" + javaFilename);
                }
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // 如果class文件，系统负责将该文件转换成class对象
        if (classFile.exists())
        {
            try
            {
                // 将class文件的二进制数据读入数组
                byte[] raw = getBytes(classFilename);
                // 调用ClassLoader的defineClass方法将二进制数据转换成Class对象
                clazz = defineClass(name, raw, 0, raw.length);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        // 如果clazz为null，表明加载失败，则抛出异常
        if (clazz == null)
        {
            throw new ClassNotFoundException(name);
        }
        return clazz;
    }

    public static void main(String[] args) throws ClassNotFoundException
    {
        // 如果运行该程序时没有参数，即没有目标类
        if (args.length < 1)
        {
            System.out.println("缺少运行的目标类，请按如下格式运行java源文件：");
            System.out.println("java CompileClassLoader ClassName");
        }
        // 第一个参数是需要运行的类
        String progClass = args[0];
        // 剩下的参数将作为运行目标类时的参数，所以将这些参数复制到一个新数组中
        String progArgs[] = new String[args.length - 1];
        System.arraycopy(args, 1, progArgs, 0, progArgs.length);
        CompileClassLoader col = new CompileClassLoader();
        // 加载需要运行的类
        Class<?> clazz = col.loadClass(progClass);

    }


}
