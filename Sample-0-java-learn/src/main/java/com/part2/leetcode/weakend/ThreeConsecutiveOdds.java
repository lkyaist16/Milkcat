package com.part2.leetcode.weakend;


/**
 * 给你一个整数数组 arr，请你判断数组中是否存在连续三个元素都是奇数的情况：如果存在，请返回 true ；否则，返回 false 。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：arr = [2,6,4,1]
 * 输出：false
 * 解释：不存在连续三个元素都是奇数的情况。
 * 示例 2：
 * <p>
 * 输入：arr = [1,2,34,3,4,5,7,23,12]
 * 输出：true
 * 解释：存在连续三个元素都是奇数的情况，即 [5,7,23] 。
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= arr.length <= 1000
 * 1 <= arr[i] <= 1000
 */
public class ThreeConsecutiveOdds {

    public static boolean threeConsecutiveOdds(int[] arr) {
        int i = 0;
        int index = i + 1;

        while (i <= arr.length - 2 && index <= arr.length - 1) {
            if (arr[i] % 2 == 0) {
                i++;
                index = i + 1;
                continue;
            }

            if (arr[i] % 2 != 0) {
                if (arr[index] % 2 == 0) {
                    i = index + 1;
                    index = i + 1;
                } else {
                    index++;
                }
            }

            if (index - i == 3) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[] arr = new int[] {1,3,2};
        System.out.println(threeConsecutiveOdds(arr));
    }
}
