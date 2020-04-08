package com.part5.designpatten.singleton;

public class Test {

    public static void main(String[] args) {
        //饿汉模式
        SingletonHungry singletonHungry1 = SingletonHungry.getInstance();
        SingletonHungry singletonHungry2 = SingletonHungry.getInstance();
        comparaObject(singletonHungry1, singletonHungry2);

        SingletonSlacker singletonSlacker1 = SingletonSlacker.getInstance();
        SingletonSlacker singletonSlacker2 = SingletonSlacker.getInstance();
        comparaObject(singletonSlacker1, singletonSlacker2);

    }

    public static void comparaObject(Object a, Object b) {
        if(a == b) {
            System.out.println("属于同一个实例");
        } else {
            System.out.println("不属于同一个实例");
        }
    }

}
