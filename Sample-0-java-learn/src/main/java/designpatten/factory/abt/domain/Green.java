package designpatten.factory.abt.domain;

public class Green implements Color{
    @Override
    public void fill() {
        System.out.println("填充绿色");
    }
}
