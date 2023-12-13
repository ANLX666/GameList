package com.orcl.demo;

import java.util.Arrays;
import java.util.stream.IntStream;

public class 你 {
    public static void main(String[] args) {
        int[] arr = {1, 3, 2};
        arr = Arrays.copyOf(arr, arr.length + 1);
        arr[3] = 22;
        IntStream sorted = Arrays.stream(arr).sorted();
        int[] array = sorted.toArray();
        System.out.println(Arrays.toString(array));
    }
}
