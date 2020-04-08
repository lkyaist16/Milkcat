package com.part3.jvm.classloading.domain;

public class ConstClass {
    static {
        System.out.println("ConstClass init!");
    }

    public static final String HELLOWORLD = "hello world";

}
