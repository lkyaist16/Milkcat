package designpatten.factory.abt.domain;

public class Square implements Shape {
    @Override
    public void draw() {
        System.out.println("这是一个正方形");
    }
}
