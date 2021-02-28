package com.part2.leetcode.weakend;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {


    private int n;
    List<String> list = new ArrayList<>();

    public Test(int n) {
        this.n = n;
        for (int i = 0; i < n; i++) {
            list.add("");
        }
    }

    public List<String> insert(int id, String value) {
        list.set(id -1, value);
        List<String> res = new ArrayList();
        if(list.get(id) == "") {
            return res;
        }

        if(id == 1){
            res.add(value);
            return res;
        }
        while(id <= n && list.get(id-1)!="") {
            res.add(list.get(id - 1));
            id++;
        }
        return res;
    }
    public static void main(String[] args) {
    }
}
