package com.orcl.demo;

import java.util.HashMap;
import java.util.Map;

public class a {
    public static void main(String[] args) {
        String[] arr = {"ab", "abc", "qw", "abc"};

        Map<String, Integer> count = new HashMap<>();
        for (String s : arr) {
            count.put(s, count.getOrDefault(s, 0) + 1);
        }
        for (String s : count.keySet()) {
            System.out.printf("%s 出现了 %d 次\n", s, count.get(s));
        }
    }
}