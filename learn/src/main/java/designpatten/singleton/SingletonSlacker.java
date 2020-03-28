package designpatten.singleton;

/**
 * 单例模式：有些对象只需要一个就足够了，保证某个实例有且只有一个
 * 类型：懒汉模式
 *
 */
public class SingletonSlacker {

    //构造方法私有化，不允许外部直接创建对象
    private SingletonSlacker() {
    }

    private static SingletonSlacker instance;

    public static SingletonSlacker getInstance() {
        if(instance==null) {
            instance = new SingletonSlacker();
        }
        return instance;
    }
}
