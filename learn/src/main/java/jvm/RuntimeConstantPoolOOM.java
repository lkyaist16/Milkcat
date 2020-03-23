package jvm;

import java.util.HashSet;
import java.util.Set;

/**
 * 方法区和运行时常量池溢出
 *
 * Java8中设置了元空间，存放常量池
 */
public class RuntimeConstantPoolOOM {

    public static void main(String[] args) {
        Set<String> set = new HashSet<>();

        short i = 0;
        while (true) {
            set.add(String.valueOf(i++).intern());
        }

    }
}
