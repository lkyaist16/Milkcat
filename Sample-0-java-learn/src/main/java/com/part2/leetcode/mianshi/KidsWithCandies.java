package com.part2.leetcode.mianshi;

import java.util.ArrayList;
import java.util.List;

public class KidsWithCandies {
    public List<Boolean> kidsWithCandies(int[] candies, int extraCandies) {
       int maxCandies = candies[0];
        List<Boolean> result = new ArrayList<>();
        for (int i = 1; i < candies.length; i++) {
            if(maxCandies < candies[i]) {
                maxCandies = candies[i];
            }
        }
        for (int i = 0; i < candies.length; i++) {
            if(candies[i] + extraCandies >= maxCandies) {
                result.add(true);
            }else {
                result.add(false);
            }
        }
        return result;
    }
}
