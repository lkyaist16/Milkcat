package com.part3.jvm.gc;

/**
 * 引用计数算法
 *
 * 以下A、B对象都有被引用，但还是被回收了
 *
 * 说明Java8的虚拟机hotspot vm 不是采用引用计数算法
 *
 * VM: -verbose:gc -XX:+PrintGCDetails
 */
public class ReferenceCountingGC {

    public Object instance = null;

    private static final int _1MB = 1024 * 1024;

    private byte[] bigSize = new byte[2 * _1MB];

    public static void testGC() {
        ReferenceCountingGC objA = new ReferenceCountingGC();
        ReferenceCountingGC objB = new ReferenceCountingGC();

        objA.instance = objB;
        objB.instance = objA;

        objA = null;
        objB = null;

        System.gc();

    }

    public static void main(String[] args) {
        testGC();
    }


}
