package com.part5.designpatten.proxy.dynamicproxy.cglibproxy;

public class Test {
    public static void main(String[] args) {

        CglibProxy proxy = new CglibProxy();
        Train t = (Train) proxy.getProxy(Train.class);
        t.movie();
    }
}
