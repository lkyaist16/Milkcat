package com.part2.leetcode.year2021.math;

public class NumMatrix {
    public int[][] sum;
    public NumMatrix(int[][] matrix) {
        int n = matrix.length;
        int m = matrix[0].length;
        sum = new int[n][m];
        for(int i = 0;i<n;i++) {
            for(int j = 0; j<m;j++) {
                int x = i - 1 <0? 0 : sum[i-1][j];
                int y = j - 1 <0? 0 : sum[i][j-1];
                int z = i - 1 < 0 || j - 1 < 0 ?  0 : sum[i - 1][j - 1];
                sum[i][j] = x + y - z + matrix[i][j];
            }
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int x = row1 - 1 <0 ? 0 : sum[row1 - 1][col2];
        int y = col1 - 1 <0 ? 0 : sum[row2][col1 - 1];
        int z = row1 - 1 < 0 || col1 - 1 < 0 ? 0 : sum[row1 - 1][col1 - 1];
        return sum[row2][col2] - x - y + z;
    }

    public static void main(String[] args) {
        int [][] matrix = new int[][]{{3,0,1,4,2},{5,6,3,2,1},{1,2,0,1,5},{4,1,0,1,7},{1,0,3,0,5}};
        NumMatrix numMatrix = new NumMatrix(matrix);
        System.out.println(numMatrix.sumRegion(2,1,4,3));
    }
}
