package com.distributedlock.lock.jvmlockdemo;

/**
 * 修饰代码块
 */
public class SynchronizedDemo2 {

    static int tickets = 100;

    public synchronized void sellTickets() {
        synchronized (this) {
            int i = 50;
            while (i > 0) {
                tickets--;
                i--;
                System.out.println(Thread.currentThread().getName() + "---" + tickets);
            }
        }
    }

    public static void main(String[] args) {
        SynchronizedDemo2 ticketDemo = new SynchronizedDemo2();

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
