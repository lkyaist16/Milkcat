package com.part5.designpatten.proxy.dynamicproxy.jdkproxy;

import com.part5.designpatten.proxy.staticproxy.po.Car;
import com.part5.designpatten.proxy.staticproxy.proxy.Moveable;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;

public class Test {
    public static void main(String[] args) {
        Car car = new Car();
        InvocationHandler h = new TimeHandler(car);
        Class<?> cls = car.getClass();
        Moveable m = (Moveable) Proxy.newProxyInstance(cls.getClassLoader(), cls.getInterfaces(), h);
        m.move();
    }

}
