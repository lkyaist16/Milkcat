package com.part5.designpatten.producerconsumermodel;

public class Producer implements Runnable {

    private Goods goods;

    @Override
    public void run() {

        while (true) {
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            synchronized (TestPC.queue) {
                goods = new Goods(1, "商品");
                if(TestPC.queue.size() < )
            }
        }


    }
}
