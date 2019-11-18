package java8.classloading.quotedemo;

import java8.classloading.domain.SubClass;

public class NotInitialization1 {
    public static void main(String[] args) {
        /**
         * 例子一：
         * 1. 读取非final修饰的静态字段时，如果类未被初始化则去初始化
         * 2. 当初始化一个类的时候，如果发现父类还未被初始化，则需要先出发父类的初始化
         * 3. 通过子类来引用父类中定义的静态字段，只会触发父类的初始化而不会触发子类的初始化
         * @param args
         */
        System.out.println(SubClass.value);

    }
}
