package com.part5.designpatten.chainofresponsibility;

public class DebugLogger extends AbstractLogger {
    public DebugLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("DEBUG控制台打印logger：" + message);
    }
}
