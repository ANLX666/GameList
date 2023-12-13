package com.orcl.gameDemo;

import java.util.Scanner;

public class prac1 {
    public static void main(String[] args) {
        int h = 10;
        int days =0 ;
        boolean flag = true;
        int ans = 0;
        do {
            if (flag ) {
                ans += 5;
                flag = false;
            }
            if (ans > h ) {
                break;
            }
            if (!flag) {
                ans -= 4;
                flag = true;
            }
            days ++ ;
        }while (ans <= h);
        System.out.println(days);
    }
}
