package com.part5.designpatten.factory.common;

/**
 * 普通工厂模式
 *
 * 隐藏对象创建逻辑，使用一个共同的接口创建对象
 */
public class Test {
    public static void main(String[] args) {
        ShapeFactory shapeFactory = new ShapeFactory();
        Shape shape = shapeFactory.getShape("CIRCLE");
        shape.draw();
        Shape shape1 = shapeFactory.getShape("RECTANGLE");
        shape1.draw();
        Shape shape2 = shapeFactory.getShape("SQUARE");
        shape2.draw();
    }
}
