package designpatten.Observer;

public class HexaObserver extends Observer {

    public HexaObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }

    @Override
    public void update() {
        System.out.println("把数字转换成六进制：" + Integer.toHexString(subject.getState()).toUpperCase());
    }
}
