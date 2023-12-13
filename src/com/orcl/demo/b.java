package com.orcl.demo;

import java.util.Set;

import static java.lang.Math.max;

public class b {
    public static void main(String[] args) {
        //    随机生成数据元素
        //    找出数组中相同元素及个数 并输出
        int[] arr=new int[100];
        int []cnt = new int[100] ;
        int []ans = new int[100];
        for(int i=0;i<arr.length;i++){
            arr[i]=(int)(Math.random()*100);
        }
        int maxx = 0 ;
        int qw = 0 ;
        for(int i=0;i<arr.length;i++){
            for(int j=i+1;j<arr.length;j++) {
                if (arr[i] == arr[j]){
                    ans[qw ++ ] = arr[i];
                }
            }
        }
        for(int i=0;i<arr.length;i++){
            System.out.print(arr[i]+",");
            cnt[arr[i]]++;
        }
        System.out.println("");
        for (int i = 0; i < qw; i++) {
            System.out.printf("%d 出现了 %d 次\n", arr[i], cnt[arr[i]]);
        }
    }
}