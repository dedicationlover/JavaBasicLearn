package com.basic.thread.demo3;

/**
 * 静态代理  设计模式
 * 1、真实角色
 * 2、代理角色  要持有真实角色的引用
 * 3、二者实现相同的接口
 * @author Administrator
 */
public class StaticProxy {
    public static void main(String[] args) {
        // 创建真实角色
        Marry you = new You();
        // 创建代理角色  + 真实角色的引用
        Marry wc = new WeddingCompany(you);
        // 执行任务
        wc.marry();
    } // end main
} // end StaticProxy
// 共同的接口
interface Marry {
    void marry(); // public abstruct
} // end Marry
// 真实角色
class You implements Marry {
    @Override
    public void marry() {
        System.out.println("you and your object marry...");
    }
} // end You
// 代理角色
class WeddingCompany implements Marry {
    private Marry you;
    public WeddingCompany() {
    }
    public WeddingCompany(Marry you) {
        this.you = you;
    }
    private void before(){
        System.out.println("before marry.....");
    }
    private void after(){
        System.out.println("after marry....");
    }
    @Override
    public void marry() {
        before();
        you.marry();
        after();
    }
} // end WeddingCompany

