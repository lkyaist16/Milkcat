package java8.classloading.resolution;

/**
 * JVM解析阶段：将常量池内的符号引用替换为直接引用的过程
 *
 * 从下向上搜索父类或父接口
 *
 * demo字段解析：如果父接口和父类中同时有那个字段 则拒绝编译
 *
 */
public class FieldResolution {

    interface Interface0 {
        int A = 0;
    }

    interface Interface1{
        int A = 1;
    }

    interface Interface2 {
        int A = 2;
    }

    static class Parent implements Interface1 {
        public static int A = 3;
    }

    static class Sub extends Parent implements Interface2 {
        public static int A = 4;
    }

    public static void main(String[] args) {
        System.out.println(Sub.A);
    }

}
