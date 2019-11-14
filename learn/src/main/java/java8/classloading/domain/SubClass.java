package java8.classloading.domain;

public class SubClass extends SuperClass {
    static {
        System.out.println("SubClass init!");
    }
}
