package com.part7.sort;

import java.util.Arrays;

/**
 * 插入排序
 * <p>
 * 从第二位开始比较
 * <p>
 * 默认第一位是排好序的
 * <p>
 * 然后插入到相应的位置
 */
public class InsertSort {

    public static Integer[] insertSort(Integer[] numbers) {
        Integer temp;

        for (int i = 0; i < numbers.length - 1; i++) {
            for (int j = i + 1; j > 0; j--) {
                if (numbers[j] < numbers[j - 1]) {
                    temp = numbers[j - 1];
                    numbers[j - 1] = numbers[j];
                    numbers[j] = temp;
                } else {
                    break;
                }
            }
        }

        return numbers;
    }

    public static void main(String[] args) {
        Integer[] numbers = initArray(1, 5, 3, 7, 6);
        insertSort(numbers);
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
