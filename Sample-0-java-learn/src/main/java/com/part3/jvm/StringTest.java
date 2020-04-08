package com.part3.jvm;

/**
 * String在jvm里的内存区域
 */
public class StringTest {

    public static void main(String[] args) {
        //字节码常量
        String a = "a1";

        //运行时常量，编译期生成的
        String a1 = "a" + 1;

        //如果字符串池有这个值，则返回
        //没有则在字符串池里创建，保证对象来自唯一字符串池
        String a2 = new String("a1").intern();

        System.out.println(a == a1);

        System.out.println(a == a2);
    }
}
