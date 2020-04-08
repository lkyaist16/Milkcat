package com.part3.jvm.classloading.domain;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
