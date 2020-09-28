package com.basic.polymorphic;

public class Test {
    public static void main(String[] args){
        HttpServlet servlet = new MyServlet();
        servlet.service();
        /*
        *  HttpServlet service()
        *  MyServlet doGet
        */
    }
}
