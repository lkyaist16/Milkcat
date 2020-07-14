package com.part5.designpatten.proxy.staticproxy.proxy;

public class CarLogProxy implements Moveable {
    private Moveable moveable;
    public CarLogProxy(Moveable moveable) {
        super();
        this.moveable = moveable;
    }

    @Override
    public void move() {
        System.out.println("日志开始。。。");
        moveable.move();
        System.out.println("日志结束。。。");
    }
}
