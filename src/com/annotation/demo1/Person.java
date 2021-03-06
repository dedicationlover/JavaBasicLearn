package com.annotation.demo1;

/**
 * 使用自定义的三个注解
 */
@Persistent(table = "persons_table")
public class Person
{
    @IdProperty(column = "person_id", type = "integer", generator = "identity")
    private int id;
    @Property(column = "person_name", type = "varchar(32)")
    private String name;
    @Property(column = "person_age", type = "integer")
    private int age;

    public Person()
    {
    }

    public Person(int id, String name, int age)
    {
        this.id = id;
        this.name = name;
        this.age = age;
    }

    // setter and getter

    public int getId()
    {
        return id;
    }

    public void setId(int id)
    {
        this.id = id;
    }

    public String getName()
    {
        return name;
    }

    public void setName(String name)
    {
        this.name = name;
    }

    public int getAge()
    {
        return age;
    }

    public void setAge(int age)
    {
        this.age = age;
    }
}
