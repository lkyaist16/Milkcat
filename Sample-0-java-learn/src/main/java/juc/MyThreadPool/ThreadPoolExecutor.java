package juc.MyThreadPool;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingDeque;

public class ThreadPoolExecutor {

    private int poolSize = 0;

    private int coreSize = 0;

    private BlockingQueue<Task> blockingQueue;

    private volatile boolean shutdown = false;

    public ThreadPoolExecutor(int poolSize) {
        this.poolSize = poolSize;
        blockingQueue = new LinkedBlockingDeque<>();
    }


    public void execute(Task task) throws InterruptedException {
        if (shutdown == true) {
            return;
        }

        if (task == null) {
            throw new NullPointerException("ERROR: 传入的task为空");
        }
        if (coreSize < poolSize) {
            blockingQueue.put(task);
            addWorker(task);
        } else {
            blockingQueue.put(task);
        }
    }

    private void addWorker(Task task) {
        Thread thread = new Thread(new Worker(task));
        thread.start();
        coreSize++;
    }

    public void shutDown() {
        shutdown = true;
    }

    class Worker implements Runnable {

        private Task task1;

        public Worker(Task task1) {
            this.task1 = task1;
        }

        @Override
        public void run() {
            while (!shutdown) {
                try {
                    Task task = blockingQueue.take();
                    task.job();
                    System.out.println("taskId = " + task.getId() + " 执行完毕");

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
