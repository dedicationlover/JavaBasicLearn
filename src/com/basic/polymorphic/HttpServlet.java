package com.basic.polymorphic;

public class HttpServlet {
    public void service(){
        System.out.println("HttpServlet service()");
        this.doGet();
    }
    public void doGet(){
        System.out.println("HttpServlet doGet");
    }
}

