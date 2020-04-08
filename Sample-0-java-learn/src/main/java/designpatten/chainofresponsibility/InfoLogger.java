package designpatten.chainofresponsibility;

public class InfoLogger extends AbstractLogger {
    public InfoLogger(int level) {
        this.level = level;
    }

    @Override
    protected void write(String message) {
        System.out.println("INFO控制台打印logger：" + message);
    }
}
