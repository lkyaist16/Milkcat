package designpatten.factory.abt;

import designpatten.factory.abt.domain.*;

public class ColorFactory extends AbstractFactory{


    @Override
    public Color getColor(String color) {
        if(color.equalsIgnoreCase("RED")){
            return new Red();
        } else if(color.equalsIgnoreCase("GREEN")){
            return new Green();
        } else if(color.equalsIgnoreCase("BLUE")){
            return new Blue();
        }
        return null;
    }

    @Override
    public Shape getShape(String shape) {
        return null;
    }
}
