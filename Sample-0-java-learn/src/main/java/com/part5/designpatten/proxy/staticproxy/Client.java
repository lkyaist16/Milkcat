package com.part5.designpatten.proxy.staticproxy;

import com.part5.designpatten.proxy.staticproxy.po.Car;
import com.part5.designpatten.proxy.staticproxy.proxy.Car3;
import com.part5.designpatten.proxy.staticproxy.proxy.Moveable;

public class Client {

    public static void main(String[] args) {
//        Car car = new Car();
//        car.move();

        //继承实现的静态代理
//        Car2 car2 = new Car2();
//        car2.move();

        //聚合方式实现的动态代理
        Car car = new Car();
        Moveable m = new Car3(car);
        m.move();
    }
}
