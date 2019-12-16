package com.distributedlock;

import com.distributedlock.lock.zookeeper.CuratorLock;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ZkLockTest {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Integer count = 100;

    @Autowired
    private CuratorLock curatorLock;

    @Test
    public void test() throws InterruptedException {

        CuratorFramework client = curatorLock.initCuratorFramework();

        logger.info("zookeeper-lock-demo:  client begin!");

        //创建大小无界的线程池
        ExecutorService executorService = Executors.newScheduledThreadPool(count);

        //同步工具类
        CountDownLatch latch = new CountDownLatch(count);

        for (int i = 0; i < 100; i++) {
            //从线程池中获取300个线程，并执行
            executorService.submit(new MyLock(client, "client-" + i, latch));
            logger.info("client-" + i + " init completed !");
        }

        //关闭线程池
        executorService.shutdown();

        latch.await();

        logger.info("zookeeper-lock-demo: all client completed -> count: {} ", count);

    }

    class MyLock implements Runnable {

        private String name;
        private CountDownLatch latch;
        private CuratorFramework client;

        public MyLock(CuratorFramework client, String name, CountDownLatch latch) {
            this.name = name;
            this.latch = latch;
            this.client = client;
        }

        @Override
        public void run() {
            InterProcessMutex lock = new InterProcessMutex(client, curatorLock.getLockPath());
            try {

//              在"/root/test"下创建临时序列节点，申请越早的编号越小；尝试获取锁，将临时序列节点排序，判断自己是不是首位，是则获取锁
                if (lock.acquire(100, TimeUnit.SECONDS)) {
                    count -= 1;
                    logger.info(this.name + " get lock success key: " + curatorLock.getLockPath() + "  work -> count = " + count);
                } else {
                    logger.error(this.name + " get lock key: " + curatorLock.getLockPath() + " timeout!");
                }

//                Thread.sleep(100);
                latch.countDown();
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                try {
                    lock.release();
                    logger.info(this.name + " release lock key: " + curatorLock.getLockPath());
                } catch (Exception e) {
                    logger.error(this.name + " get lock key error reason: " + e.getMessage());
                }
            }
        }
    }

}


