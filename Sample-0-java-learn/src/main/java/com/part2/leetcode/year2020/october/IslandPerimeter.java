package com.part2.leetcode.year2020.october;

/**
 *
 */
public class IslandPerimeter {
    public int islandPerimeter(int[][] grid) {
        int res = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (grid[i][j] == 1) {
                    if (j - 1 >= 0) {
                        if (grid[i][j - 1] == 0) {
                            res++;
                        }
                    }else  {
                        res++;
                    }
                    if (i - 1 >= 0) {
                        if (grid[i - 1][j] == 0) {
                            res++;
                        }
                    }else {
                        res++;
                    }
                    if (j + 1 < grid[i].length) {
                        if (grid[i][j + 1] == 0) {
                            res++;
                        }
                    } else {
                        res ++;
                    }
                    if (i + 1 < grid.length) {
                        if (grid[i + 1][j] == 0) {
                            res++;
                        }
                    } else {
                        res++;
                    }
                }
            }
        }
        return res;
    }

}
