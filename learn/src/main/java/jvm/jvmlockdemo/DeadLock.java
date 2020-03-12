package jvm.jvmlockdemo;

/**
 * 死锁示例：两个锁A、B
 * 线程一获取锁A后在方法块内尝试获取锁B
 * 线程二获取锁B后在方法块内尝试获取锁A
 *
 * 此时两线程互相等待锁的释放，不能执行接下来的代码
 * 线程卡死在这里，形成死锁
 */
public class DeadLock {

    public static void main(String[] args) {
        Object obj1 = new Object();
        Object obj2 = new Object();

        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (obj1) {
                        Thread.sleep(1000);
                        synchronized (obj2) {
                            System.out.println("执行业务1");
                        }
                    }
                } catch (Exception e) {
                   e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "执行完毕");
                }
            }
        }.start();
        new Thread() {
            @Override
            public void run() {
                try {
                    synchronized (obj2) {
                        Thread.sleep(1000);
                        synchronized (obj1) {
                            System.out.println("执行业务2");
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    System.out.println(Thread.currentThread().getName() + "执行完毕");
                }
            }
        }.start();

//        System.out.println("执行到这里--------");
    }


}
