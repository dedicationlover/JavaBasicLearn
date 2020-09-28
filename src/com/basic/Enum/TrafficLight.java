package com.basic.Enum;

enum Signal {
    GREEN,
    YELLOW,
    RED
}

public class TrafficLight {
    Signal color = Signal.RED;

    public void change() {
        switch(color) {
            case RED:
                color = Signal.GREEN;
                break;
            case GREEN:
                color = Signal.YELLOW;
                break;
            case YELLOW:
                color = Signal.RED;
                break;
        }
    }

    public void change(String color) {
        switch (Signal.valueOf(color.toUpperCase())) {
            case RED:
                this.color = Signal.GREEN;
                break;
            case GREEN:
                this.color = Signal.YELLOW;
                break;
            case YELLOW:
                this.color = Signal.RED;
                break;
        }
    }

    public static void main(String[] args) {
        System.out.println(Signal.GREEN.toString());  // GREEN

        Signal signal = Enum.valueOf(Signal.class, "RED");
        System.out.println(signal);   // RED

        System.out.println(Signal.GREEN.ordinal());  // 0

        Signal signal1 = Signal.valueOf("RED");
        System.out.println(signal1);   // RED

        new TrafficLight().change("red");

        System.out.println(Signal.GREEN == Signal.GREEN);  // true
    }
}
