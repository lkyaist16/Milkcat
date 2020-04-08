package com.part3.jvm.classloading.init;

/**
 * 类初始化阶段：<client>()方法在多线程环境中会被加锁
 *
 * 多线程去初始化一个类，如果这个类的初始化阶段超时或死循环，其他线程则会阻塞等待
 *
 */
public class Test {

    public static void main(String[] args) {
        Runnable script = new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread() + "start");
                DeadLoopClass dlc = new DeadLoopClass();
                System.out.println(Thread.currentThread() + " run over");
            }
        };

        Thread thread1 = new Thread(script);
        Thread thread2 = new Thread(script);

        thread1.start();
        thread2.start();
    }
}
