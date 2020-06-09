package com.part7.sort;

import java.util.Arrays;

/**
 * 插入排序
 *
 *
 */
public class InsertSort {

    public static Integer[] insertSort(Integer[] numbers) {
        for (int i = 0; i < numbers.length; i++) {
            for (int j = i+1; j > 0; j--) {

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
