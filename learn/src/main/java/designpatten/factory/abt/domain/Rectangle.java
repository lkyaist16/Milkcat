package designpatten.factory.abt.domain;


public class Rectangle implements Shape {
    @Override
    public void draw() {
        System.out.println("这是一个长方形");
    }
}
