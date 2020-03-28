package designpatten.singleton;

/**
 * 单例模式：有些对象只需要一个就足够了，保证某个实例有且只有一个
 * 类型：饿汉模式
 *
 */
public class SingletonHungry {

    //构造方法私有化，不允许外部直接创建对象
    private SingletonHungry() {
    }

    private static SingletonHungry instance = new SingletonHungry();

    public static SingletonHungry getInstance() {
        return instance;
    }
}
