package designpatten.factory.abt;

import designpatten.factory.abt.domain.Color;
import designpatten.factory.abt.domain.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);

}
