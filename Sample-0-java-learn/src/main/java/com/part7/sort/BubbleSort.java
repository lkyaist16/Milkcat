package com.part7.sort;

import java.util.Arrays;

/**
 * 冒泡排序
 * <p>
 * 算法描述：
 * <p>
 * 比较相邻的两个元素，如果第一个比第二个大，就交换
 * <p>
 * 找出最大的到顶部，像冒泡一样
 *
 * 平均时间复杂度：O(n2)
 */
public class BubbleSort {

    public static Integer[] bubbleSort(Integer[] numbers) {
        int tem = 0;
        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = 0; j < numbers.length - 1 - i; j++) {
                if (numbers[j] > numbers[j + 1]) {
                    tem = numbers[j];
                    numbers[j] = numbers[j + 1];
                    numbers[j + 1] = tem;
                }
            }
        }
        return numbers;
    }

    public static void main(String[] args) {
        Integer[] numbers = initArray(1, 5, 3, 7, 6);
        bubbleSort(numbers);
        Arrays.asList(numbers).stream().forEach(x -> System.out.println(x));
    }

    /**
     * 初始化数组
     *
     * @param values
     * @param <T>
     * @return
     */
    public static <T> T[] initArray(T... values) {
        return values;
    }

}
