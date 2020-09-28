package com.basic.Exception;

/**
 * catch中结合使用throw
 */
public class CatchThrowDemo {
    private double initPrice = 30;
    public void bid(String bidPrice) throws MyException {
        double d = 0;
        try {
            d = Double.parseDouble(bidPrice);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            throw new MyException("竞价必须是数值，不能包含其他字符");
        }
        if (initPrice > d) {
            throw new MyException("竞价比拍价低，不允许竞拍！");
        }
        initPrice = d;
    }
    public static void main(String[] args) {
        CatchThrowDemo demo = new CatchThrowDemo();
        try {
            demo.bid("3");
        } catch (MyException e) {
            System.out.println(e.getMessage());
        }
    }
}
