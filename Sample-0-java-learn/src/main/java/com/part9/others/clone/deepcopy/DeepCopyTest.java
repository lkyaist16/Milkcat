package com.part9.others.clone.deepcopy;

/**
 * 深拷贝
 *
 * 1、基本数据类型直接复制值
 *
 * 2、引用数据类型创建一个新的对象，并复制其中的值
 */
public class DeepCopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest test = new CloneTest();
        test.setExperience("我是深拷贝本人，擅长打篮球");
        test.show();
        CloneTest cloneTest = (CloneTest) test.clone();
        cloneTest.show();
        cloneTest.setExperience("我是深拷贝复制人，擅长踢足球");
        cloneTest.show();
        test.show();
        System.out.println(cloneTest.getAge());
    }
}
