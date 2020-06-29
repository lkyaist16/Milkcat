package com.part5.designpatten.producerconsumermodel;

import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class TestPC {

    public static final int MAX_POOL=10;
    public static final int MAX_PRODUCER=5;
    public static final int MAX_CONSUMER=4;
    public static Queue<Goods> queue = new ArrayBlockingQueue<>(MAX_POOL);

    public static void main(String[] args) {
        Producer producer = new Producer();
        Comsumer comsumer = new Comsumer();




    }

}
