package designpatten.factory.abt.domain;

public class Circle implements Shape {
    @Override
    public void draw() {
        System.out.println("这是一个圆形");
    }
}
