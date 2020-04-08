package jvm.classloading.quotedemo;

import jvm.classloading.domain.SuperClass;

public class NotInitialization2 {
    public static void main(String[] args) {
        /**
         * 例子二：
         * 通过数组定义来引用类，不会触发此类的初始化
         */
        SuperClass[] superClasses = new SuperClass[10];

    }
}
