package com.basic.Enum;

/**
 * 枚举类的用法，实现接口
 */
public enum SeasonEnum implements  temp {
    SPRING(1){
        @Override
        public int degree(){
            return 25;
        }
    },
    SUMMER(2){
        @Override
        public int degree(){
            return 35;
        }
    },
    FALL(3){
        @Override
        public int degree(){
            return 20;
        }
    },
    WINTER(4){
        @Override
        public int degree(){
            return 0;
        }
    };
    private int order;
    private SeasonEnum(int order){
        this.order = order;
    }
    public int getOrder(){
        return order;
    }

    @Override
    public int degree(){
        return -1;
    }
}
interface temp{
    int degree();
}