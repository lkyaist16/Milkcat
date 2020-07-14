package com.part5.designpatten.proxy.staticproxy;

import com.part5.designpatten.proxy.staticproxy.po.Car;
import com.part5.designpatten.proxy.staticproxy.proxy.CarLogProxy;
import com.part5.designpatten.proxy.staticproxy.proxy.CarTimeProxy;

public class Client2 {
    public static void main(String[] args) {
        Car car = new Car();
        CarTimeProxy carTimeProxy = new CarTimeProxy(car);
        CarLogProxy carLogProxy = new CarLogProxy(carTimeProxy);
        carLogProxy.move();
    }
}
