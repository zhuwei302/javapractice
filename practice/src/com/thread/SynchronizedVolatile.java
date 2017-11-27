package com.thread;

/**
 * Created by zhuwei on 2017/11/22 0022.
 */
public class SynchronizedVolatile {
    public volatile int i = 0;
    public synchronized void incrase(){
        i++;
    }


    public static void main(String[] args){
       final SynchronizedVolatile sv = new SynchronizedVolatile();

        for (int i=0;i<10;i++) {
            new Thread(){
                @Override
                public void run(){
                    for (int i=0;i<1000;i++) {
                        try {
                            Thread.sleep(50);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                        sv.incrase();
                    }
                }
            }.start();
        }

        while (Thread.activeCount()>1){ //保证前面的线程都执行完
            Thread.yield(); //让出cpu资源，从运行状态变成就绪状态，重新开始竞争CPU资源
            System.out.println(sv.i);
        }
    }
}
