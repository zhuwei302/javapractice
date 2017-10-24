package com.volatitle;

/**
 * Created by zhuwei on 2017/10/10 0010.
 */
public class TestWithVolatile {
    private static volatile boolean bChanged;

    public static void main(String[] args) throws InterruptedException {
        new Thread() {

            @Override
            public void run() {
                for (;;) {
//                    System.out.println("t1循环："+bChanged);
                    if (bChanged == !bChanged) {
                        System.out.println("!=");
                        System.exit(0);
                    }
                }
            }
        }.start();
        Thread.sleep(1);
        new Thread() {

            @Override
            public void run() {
                for (;;) {
//                    System.out.println("t2循环："+bChanged);
                    bChanged = !bChanged;
                }
            }
        }.start();
    }
}
