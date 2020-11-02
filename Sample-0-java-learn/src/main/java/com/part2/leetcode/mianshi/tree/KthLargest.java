package com.part2.leetcode.mianshi.tree;

import com.part2.leetcode.october.PreorderTraversal;

/**
 * 给定一棵二叉搜索树，请找出其中第k大的节点。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: root = [3,1,4,null,2], k = 1
 * 3
 * / \
 * 1   4
 * \
 *    2
 * 输出: 4
 * 示例 2:
 * <p>
 * 输入: root = [5,3,6,2,4,null,null,1], k = 3
 * 5
 * / \
 * 3   6
 * / \
 * 2   4
 * /
 * 1
 * 输出: 4
 *  
 * <p>
 * 限制：
 * <p>
 * 1 ≤ k ≤ 二叉搜索树元素个数
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/er-cha-sou-suo-shu-de-di-kda-jie-dian-lcof
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 */
public class KthLargest {

    public int k;
    public int result;

    public int kthLargest(TreeNode root, int k) {
        this.k = k;
        dfs(root);
        return result;
    }


    //深度优先搜索：中序遍历
//    public static void dfs(TreeNode root) {
//        if(root == null) {
//            return;
//        }
//        dfs(root.left);
//        System.out.println(root.val);
//        dfs(root.right);
//    }

    public static void dfs(TreeNode root) {
        if (root == null) {
            return;
        }
        System.out.println(root.val);
        dfs(root.left);
        dfs(root.right);
    }


    //深度优先搜索：中序遍历倒叙
    //深度优先：先找到最深处节点
//    public void dfs(TreeNode root) {
//        if (root == null) {
//            return;
//        }
//        dfs(root.right);
//        if (k == 0) {
//            return;
//        }
//        if (--k == 0) {
//            result = root.val;
//        }
//        dfs(root.left);
//    }

    public static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        TreeNode node1 = new TreeNode(1);
        TreeNode node2 = new TreeNode(2);
        TreeNode node3 = new TreeNode(3);
        TreeNode node4 = new TreeNode(4);
        TreeNode node5 = new TreeNode(5);
        TreeNode node6 = new TreeNode(6);

        node5.left = node3;
        node5.right = node6;
        node3.left = node2;
        node3.right = node4;
        node2.left = node1;
        dfs(node5);
    }


}
