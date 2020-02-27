package java8.stream;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 关于并行流parallelStream的使用demo
 * 1. 多线程下并行处理，出现异步问题，主线程处理不过来了，会开新线程进行处理
 * <p>
 * 2. 访问不是线程共享的变量时，访问不到
 */
public class StreamTest {

    public static void main(String[] args) {

        List list = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
        list.parallelStream()
                .forEach(num -> System.out.println("当前线程名称：" + Thread.currentThread().getName()));
        List filterList = Arrays.asList(1, 2, 3, 4, 5);
        List resultList = new ArrayList();
        list.parallelStream().filter(num -> !filterList.contains(num)).forEach(num -> {
            resultList.add(num);
        });

        resultList.parallelStream().forEach(num -> {
            System.out.println("resultList里获取到的值" + num);
        });


//        ThreadLocal threadLocal = new ThreadLocal();
//        threadLocal.set("aaa");
//        List list2 = Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9);
//
//        list2.stream().forEach(num -> {
//            System.out.println("当前线程："+ Thread.currentThread().getName() + " 获取到值："+threadLocal.get());
//        });
//
//        list2.parallelStream().forEach(num -> {
//            System.out.println("当前线程："+ Thread.currentThread().getName() + " 获取到值："+threadLocal.get());
//        });


    }

}
