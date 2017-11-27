package com.thread;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by zhuwei on 2017/11/23 0023.
 */
public class WaitNotify {

    static boolean flag = true;
    static Object lock = new Object();

    static class Wait implements Runnable{
        @Override
        public void run() {
            //加锁 拥有lock的Monitor
            synchronized(lock){
                //条件不满足时，继续wait，同时释放lock的锁
                while(flag){
                    try {
                        System.out.println(Thread.currentThread()+" flag is true. wait " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                        lock.wait();
                    }catch (Exception e){

                    }
                }
                //条件满足时，完成工作
                System.out.println(Thread.currentThread() + " flag is false. running @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
            }
        }
    }


    static class Notify implements Runnable{
        @Override
        public void run() {
            // 加锁 拥有lock的Monitor
            synchronized (lock){
                //获取lock的锁，然后进行通知，通知时不会释放lock的锁，知道当前线程释放了lock后，waitThread才能从wait方法中返回
                System.out.println(Thread.currentThread() + " hold lock. notify @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                lock.notifyAll();
                flag = false;
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

            }

            //再次加锁
            synchronized (lock){
                System.out.println(Thread.currentThread() + " hold lock again. sleep @ " + new SimpleDateFormat("HH:mm:ss").format(new Date()));
                try {
                    Thread.sleep(5);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread waitThread = new Thread(new Wait(),"WaitThread");
        waitThread.start();
        Thread notifyThread = new Thread(new Notify(),"NotifyThread");
        notifyThread.start();
    }



}
