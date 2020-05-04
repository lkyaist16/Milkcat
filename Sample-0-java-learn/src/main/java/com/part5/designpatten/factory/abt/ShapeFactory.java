package com.part5.designpatten.factory.abt;

import com.part5.designpatten.factory.abt.domain.*;

public class ShapeFactory extends AbstractFactory {
    @Override
    public Color getColor(String color) {
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        if(shape.equalsIgnoreCase("CIRCLE")){
            return new Circle();
        } else if(shape.equalsIgnoreCase("RECTANGLE")){
            return new Rectangle();
        } else if(shape.equalsIgnoreCase("SQUARE")){
            return new Square();
        }
        return null;
    }
}
