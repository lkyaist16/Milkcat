package designpatten.chainofresponsibility;

public class ErrorLogger extends AbstractLogger {
    public ErrorLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("ERROR控制台打印logger：" + message);
    }
}
