package com.classLoader;

import java.lang.reflect.Field;

class Person
{
    private String name;
    private int age;
    @Override
    public String toString()
    {
        return "Person [ name: " + name + ", age: " + age + "]";
    }
}

public class FieldTest
{
    public static void main(String[] args) throws Exception
    {
        // 创建一个Person对象
        Person p = new Person();
        // 获取Person类对应的Class对象
        Class<Person> personClass = Person.class;
        // 获取Person类名为name的属性
        // 使用getDeclaredField，表明可获取各种访问控制符的field
        Field nameField = personClass.getDeclaredField("name");
        // 设置通过反射访问该Field时取消访问权限检查
        nameField.setAccessible(true);
        // 调用set方法为p对象的指定Field设置值
        nameField.set(p, "Yeeku.H.Lee");
        // 获取Person类名为age的属性
        Field ageField = personClass.getDeclaredField("age");
        // 设置通过反射访问该Field时取消访问权限检查
        ageField.setAccessible(true);
        // 调用setInt方法为p对象的指定Field设置值
        ageField.set(p, 34);
        System.out.println(p);   // Person [ name: Yeeku.H.Lee, age: 34]
    }
}
