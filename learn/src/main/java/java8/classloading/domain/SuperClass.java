package java8.classloading.domain;

public class SuperClass {
    static {
        System.out.println("SuperClass init!");
    }
    public static int value = 123;

}
