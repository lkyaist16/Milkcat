package com.part9.others.clone.shallowcopy;

/**
 * 浅拷贝
 *
 * 1.基本数据类型复制值
 *
 * 2.引用数据类型创建新指针指向原来地址
 *
 */
public class ShallowCopyTest {
    public static void main(String[] args) throws CloneNotSupportedException {
        CloneTest test = new CloneTest();
        test.setExperience("我是浅拷贝本人，擅长打篮球");
        test.show();
        CloneTest cloneTest = (CloneTest) test.clone();
        cloneTest.show();
        cloneTest.setExperience("我是浅拷贝复制人，擅长踢足球");
        cloneTest.show();
        test.show();
        System.out.println(cloneTest.getAge());
    }
}
