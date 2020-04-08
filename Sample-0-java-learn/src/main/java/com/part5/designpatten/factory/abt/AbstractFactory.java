package com.part5.designpatten.factory.abt;

import com.part5.designpatten.factory.abt.domain.Color;
import com.part5.designpatten.factory.abt.domain.Shape;

public abstract class AbstractFactory {
    public abstract Color getColor(String color);
    public abstract Shape getShape(String shape);

}
