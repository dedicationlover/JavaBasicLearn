package com.reflection;

public class User
{
    private int id;
    private String name;
    private String description;

    String city;

    public String state;

    public User() {}

    private User(int id)
    {
        this.id = id;
    }

    public User(int id, String name, String description)
    {
        this.id = id;
        this.description = description;
        this.name = name;
    }

    public void say(String msg)
    {
        System.out.println("say " + msg);
    }

    public int getId()
    {
        return id;
    }

    private void learnBySelf()
    {
        System.out.println("learning");
    }
}
