package juc.MyThreadPool;

import java.util.concurrent.CountDownLatch;

/**
 * 实现一个简单的线程池
 */
public class DemoTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(10);
        CountDownLatch latch = new CountDownLatch(20);

        for (int i = 0; i< 20;i++) {
            threadPoolExecutor.execute(new Task(i, new Runnable() {
                @Override
                public void run() {
                    System.out.println("执行任务");
                    latch.countDown();
                }
            }));
        }
        latch.await();
        threadPoolExecutor.shutDown();
    }
}
