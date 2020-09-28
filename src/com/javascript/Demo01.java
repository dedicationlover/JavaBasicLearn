package com.javascript;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.util.List;

/**
 * 测试脚本引擎执行JavaScript代码
 */
public class Demo01
{
    public static void main(String[] args) throws ScriptException, NoSuchMethodException, IOException
    {
        // 获得脚本引擎对象
        ScriptEngineManager sem = new ScriptEngineManager();
        ScriptEngine engine = sem.getEngineByName("javascript");

        // 定义变量，存储到引擎上下文中（Java和javascript都能取到，引擎相当于中间桥梁）
        engine.put("msg", "my further will be better");
        String str = "var user = {name:'gaoqi', age:18, school:['xian','shanghai']};";
        str += "print(user.name)";

        // 执行脚本
        engine.eval(str);
        engine.eval("msg='today is well';");

        System.out.println(engine.get("msg"));
        System.out.println("=============");

        // 定义函数
        engine.eval("function add(a,b){var sum=a+b;return sum;}");
        // 取得调用接口
        Invocable jsInvoke = (Invocable) engine;
        // 执行脚本中定义的方法
        Object result1 = jsInvoke.invokeFunction("add", new Object[]{13, 20});
        System.out.println(result1);

        // 导入其他Java包，使用其他包中的Java类
        String jsCode = "var list=java.util.Arrays.asList([\"beijing\",\"shanghai\",\"shicheng\"])";
        engine.eval(jsCode);

        List<String> list = (List<String>) engine.get("list");
        for (String temp : list)
        {
            System.out.println(temp);
        }

        // 执行js文件，位于是src下 a.js
        URL url = Demo01.class.getClassLoader().getResource("a.js");
        FileReader fr = new FileReader(url.getPath());
        engine.eval(fr);
        fr.close();   // 实际使用时要用try-catch
    }
}

//        gaoqi
//        today is well
//        =============
//        33.0
//        beijing
//        shanghai
//        shicheng
//        invoke js file:7