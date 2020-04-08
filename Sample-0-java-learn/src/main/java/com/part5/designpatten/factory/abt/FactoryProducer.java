package com.part5.designpatten.factory.abt;

public class FactoryProducer {
    public static AbstractFactory getFactory(String type){
        if(type.equalsIgnoreCase("SHAPE")){
            return new ShapeFactory();
        } else if(type.equalsIgnoreCase("COLOR")){
            return new ColorFactory();
        }
        return null;
    }
}
