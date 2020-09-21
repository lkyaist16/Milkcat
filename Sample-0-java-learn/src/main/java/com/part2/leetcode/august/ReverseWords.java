package com.part2.leetcode.august;

public class ReverseWords {
    public String reverseWords(String s) {

        String[] s1 = s.split(" ");
        String result = "";
        for (int i = 0; i < s1.length; i++) {
            StringBuffer s2 = new StringBuffer(s1[i]);
            s2.reverse();
            if (i != s1.length - 1) {
                result += s2 + "";
            } else {
                result += s2;
            }
        }
        return result;
    }

}
