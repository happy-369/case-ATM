package com.itheima;
import java.util.Random;
import java.util.Scanner;

public class Test6 {
    public static void main(String[] args){
        //完成抽奖
        int[] moneys = {9, 666, 188, 520, 9999};
        start(moneys);
    }
    public static void start(int[] moneys){
        Random r = new Random();
        Scanner sc = new Scanner(System.in);
        for(int i = 0; i < moneys.length; i++){
            int j =r.nextInt(moneys.length);
            int temp = moneys[i];
            moneys[i] = moneys[j];
            moneys[j] = temp;
        }
        for(int i = 0; i < moneys.length; i++){
            System.out.println("请您输入任意内容进行抽奖:");
            sc.next();
            System.out.println("恭喜您，您抽中了红包：" + moneys[i]);


        }
        System.out.println("活动结束。。");
    }
}
