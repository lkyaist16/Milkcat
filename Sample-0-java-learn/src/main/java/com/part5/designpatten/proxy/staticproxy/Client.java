package com.part5.designpatten.proxy.staticproxy;

public class Client {

    public static void main(String[] args) {
//        Car car = new Car();
//        car.move();

//        Car2 car2 = new Car2();
//        car2.move();

        Car car = new Car();
        Moveable m = new Car3(car);
        m.move();
    }
}
