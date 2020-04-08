package com.part2.leetcode.dynamicplanning;

/**
 * 算法：动态规划
 *
 * 求解 0 1背包问题
 *
 * 第i个物品不装
 */
public class BackPacket {

    public static void main(String[] args) {

        int w[] = {0, 3, 4, 5};

        int v[] = {0, 4, 5, 6};

        int num = w.length;

        System.out.println(maxValue(v, w, num, 10));
    }


    public static Integer maxValue(int v[], int w[], int num, int packetTotal) {

        if (num == 0 || packetTotal == 0) {
            return 0;
        }

        if (w[num - 1] > packetTotal) {
            return maxValue(v, w, num - 1, packetTotal);
        }

        int maxValue1 = maxValue(v, w, num - 1, packetTotal);

        int maxValue2 = maxValue(v, w, num - 1, packetTotal - w[num - 1]) + v[num - 1];

        return maxValue1 > maxValue2 ? maxValue1 : maxValue2;

    }

}
