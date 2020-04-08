package com.part3.jvm.jvmlockdemo;

/**
 * 修饰类名，形成类锁
 */
public class SynchronizedDemo3 {

    static int tickets = 100;

    public synchronized void sellTickets() {
        synchronized (SynchronizedDemo3.class) {
            int i = 50;
            while (i > 0) {
                tickets--;
                i--;
                System.out.println(Thread.currentThread().getName() + "---" + tickets);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo3 ticketDemo = new SynchronizedDemo3();

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
