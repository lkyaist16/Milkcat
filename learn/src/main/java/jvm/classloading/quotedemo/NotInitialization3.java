package jvm.classloading.quotedemo;

import jvm.classloading.domain.ConstClass;

public class NotInitialization3 {
    public static void main(String[] args) {
        /**
         * 例子三：
         * 常量在编译阶段会存入调用类的常量池。访问类的常量，不会触发定义常量的类的初始化
         */
        System.out.println(ConstClass.HELLOWORLD);
    }
}
