package com.part5.designpatten.Observer;

public class BinaryObserver extends Observer {

    public BinaryObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("把数字转换成二进制：" + Integer.toBinaryString(subject.getState()));
    }
}
