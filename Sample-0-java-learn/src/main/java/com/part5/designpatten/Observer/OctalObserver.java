package com.part5.designpatten.Observer;

public class OctalObserver extends Observer {

    public OctalObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("把数字转换成八进制：" + Integer.toOctalString(subject.getState()));
    }
}
