package com.basic.Exception;

/**
 * 自定义异常
 */
public class MyException extends Exception {
    /**
     * 提供默认无参构造器
     */
    public MyException() {}

    /**
     * 提供带参构造器
     * @param massage 异常信息
     */
    public MyException(String massage) {
        super(massage);
    }
}
