package com.thread;

import java.math.BigDecimal;

/**
 * Created by zhuwei on 2017/9/30 0030.
 */
public class ThreadDemo1 {
    public static void main(String[] args) {
//        Thread thread = new Thread("t1"){
//            public void run(){
//                System.out.println(Thread.currentThread().getName());
//            }
//        };
//
//        thread.run(); //main 在当前main方法线程中执行
//        thread.start(); //t1 新创建一个系统线程执行，名称为t1


        double m = 2.0; //首次投注资金
        double h = 0.0; //回报资金
        double s = 9.8; //回报比例
        double n = 0.3; //投注增长比例
        double sum = 0.0; //投注总金额
//        for (int j = 0; j < 1000; j++) {
            for (int i = 1; i <100 ; i++) {
                if (i>1) {
//                    m = m * Math.pow(1+n,i-1); //第i次投注金额
                    m = m * (1+n); //第i次投注金额
                }
                h = m*s; //第i次回报金额
                sum += m; //前i次投入总金额

                if (h<sum) {
                    System.out.println("当增长倍数为"+n+"时，第"+i+"次后会亏损，本次投入："+
                            new BigDecimal(m/100).setScale(4,BigDecimal.ROUND_HALF_UP)+"，本次回报："+
                            new BigDecimal(h/100).setScale(4,BigDecimal.ROUND_HALF_UP)+"，投入总额："+
                            new BigDecimal(sum/100).setScale(4,BigDecimal.ROUND_HALF_UP));
                    break;
                }

                System.out.println("当增长倍数为"+n+"时，第"+i+"次投入金额："+
                        new BigDecimal(m/100).setScale(4,BigDecimal.ROUND_HALF_UP)+"，本次回报："+
                        new BigDecimal(h/100).setScale(4,BigDecimal.ROUND_HALF_UP)+"，投入总额："+
                        new BigDecimal(sum/100).setScale(4,BigDecimal.ROUND_HALF_UP)+"，盈利："+
                        new BigDecimal((h-sum)/100).setScale(2,BigDecimal.ROUND_HALF_UP));

            }

//            n += 0.1;
        }

//    }
}
