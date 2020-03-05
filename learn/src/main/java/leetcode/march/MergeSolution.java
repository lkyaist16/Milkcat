package leetcode.march;

/**
 * 2020/3/3
 *
 * 给定两个排序后的数组 A 和 B，其中 A 的末端有足够的缓冲空间容纳 B。 编写一个方法，将 B 合并入 A 并排序。
 * <p>
 * 初始化 A 和 B 的元素数量分别为 m 和 n。
 * <p>
 * 输入:
 * A = [1,2,3,0,0,0], m = 3
 * B = [2,5,6],       n = 3
 * <p>
 * 输出: [1,2,2,3,5,6]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/sorted-merge-lcci
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class MergeSolution {
    public static void merge(int[] A, int m, int[] B, int n) {
        int index = m + n - 1;
        int aIndex = m - 1;
        int bIndex = n - 1;

        while (aIndex >= 0 && bIndex >= 0) {
            if (A[aIndex] < B[bIndex]) {
                A[index] = B[bIndex];
                bIndex--;
            } else {
                A[index] = A[aIndex];
                aIndex--;
            }
            index--;
        }

        while (bIndex >=0) {
            A[index--] = B[bIndex--];
        }

    }

    public static void main(String[] args) {
        int[]A = new int[]{0};
        int[]B = new int[]{1};
        int m = 0;
        int n = 1;

        merge(A, m, B, n);
        for (int num : A) {
            System.out.println(num);
        }
    }

}
