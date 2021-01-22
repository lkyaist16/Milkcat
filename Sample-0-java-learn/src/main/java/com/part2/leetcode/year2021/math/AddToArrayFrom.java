package com.part2.leetcode.year2021.math;

import java.util.LinkedList;
import java.util.List;

/**
 * 989. 数组形式的整数加法
 * <p>
 * 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。
 * <p>
 * 给定非负整数 X 的数组形式 A，返回整数 X+K 的数组形式。
 * <p>
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入：A = [1,2,0,0], K = 34
 * 输出：[1,2,3,4]
 * 解释：1200 + 34 = 1234
 * 示例 2：
 * <p>
 * 输入：A = [2,7,4], K = 181
 * 输出：[4,5,5]
 * 解释：274 + 181 = 455
 * 示例 3：
 * <p>
 * 输入：A = [2,1,5], K = 806
 * 输出：[1,0,2,1]
 * 解释：215 + 806 = 1021
 * 示例 4：
 * <p>
 * 输入：A = [9,9,9,9,9,9,9,9,9,9], K = 1
 * 输出：[1,0,0,0,0,0,0,0,0,0,0]
 * 解释：9999999999 + 1 = 10000000000
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= A.length <= 10000
 * 0 <= A[i] <= 9
 * 0 <= K <= 10000
 * 如果 A.length > 1，那么 A[0] != 0
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/add-to-array-form-of-integer
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class AddToArrayFrom {
    public static List<Integer> addToArrayForm(int[] A, int K) {
        int n = A.length;
        LinkedList<Integer> res = new LinkedList<>();
        // i位数：从个位开始。 sum当前和。 carry进位
        int i = n - 1, sum = 0, carry = 0;

        //如果A位数为0或者K值为0
        while (i >= 0 || K !=0) {
            //A当前位
            int x = i >= 0? A[i] : 0;

            //B当前位
            int y = K != 0? K % 10 : 0;

            //当前和
            sum = x + y + carry;

            //进位为当前和/10
            carry = sum / 10;

            //B下一位值
            K = K / 10;
            //A下一位坐标
            i--;
            //加入头部
            res.addFirst(sum % 10);
        }
        if (carry != 0) {
            res.add(0, carry);
        }
        return res;
    }

    public static void main(String[] args) {
        addToArrayForm(new int[]{1, 2, 0, 0}, 34);
    }
}
