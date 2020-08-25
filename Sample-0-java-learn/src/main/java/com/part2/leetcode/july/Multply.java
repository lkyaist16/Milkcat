package com.part2.leetcode.july;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理
 */
public class Multply {
    public static String multiply(String num1, String num2) {
        char[] chars1 = num1.toCharArray();
        char[] chars2 = num2.toCharArray();

        Long result1 = 0L;
        long sign = 1;
        for (int i = chars1.length - 1; i >= 0; i--) {
            int result2 = 0;
            int sign2 = 1;
            int num3 = chars1[i] - '0';
            for (int j = chars2.length - 1; j >= 0; j--) {
                int num4 = chars2[j] - '0';
                result2 += num3 * num4 * sign2;
                sign2 = sign2 * 10;
            }
            result1 += result2 * sign;
            sign = sign * 10;
        }
        return result1.toString();
    }

    public static void main(String[] args) {
        System.out.println(multiply("123456789", "987654321"));
    }
}
