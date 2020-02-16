package com.distributedlock.lock.jvmlockdemo;

/**
 * Synchronized修饰方法，给方法加锁
 * <p>
 * 线程抛出异常时由jvm释放锁
 * <p>
 * 由jvm控制的锁，也可以说为jvm锁
 */
public class SynchronizedDemo1 {
    static int tickets = 100;

    public synchronized void sellTickets() {
        int i = 50;
        while (i > 0) {
            tickets--;
            i--;
            System.out.println(Thread.currentThread().getName() + "---" + tickets);
        }

    }

    public static void main(String[] args) {
        SynchronizedDemo1 ticketDemo = new SynchronizedDemo1();

        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.sellTickets();
            }
        });

        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                ticketDemo.sellTickets();
            }
        });

        thread1.start();
        thread2.start();

    }


}
