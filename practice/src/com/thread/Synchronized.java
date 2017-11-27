package com.thread;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;

/**
 * Created by zhuwei on 2017/11/23 0023.
 */
public class Synchronized {
    public static void main(String[] args) {
//        synchronized(Synchronized.class){}
//        m();
    }

    public static synchronized void m(){
        Lock lock = null;
        ReadWriteLock lock1 = null;
    }
}
