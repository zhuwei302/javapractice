package com.thread.file.read;

import java.io.RandomAccessFile;
import java.util.concurrent.CountDownLatch;

/**
 * 网址：http://www.cnblogs.com/langtianya/p/3874019.html
 * 用来读取文件，当获取到指定关键字时，在指定的对象加1
 * Created by zhuwei on 2017/11/6 0006.
 */
public class ReadThread extends Thread{
    // 定义字节数组长度
    private final int BUFF_LEN = 256;
    //定义读取的起始点
    private long start;
    //读取的结束点
    private long end;
    //将读取的字节输出到raf中 randomAccessFile可以理解为文件流，即文件中提取指定的一部分的包装对象
    private RandomAccessFile raf;
    //线程中需要指定的关键字
    private  String keywords;
    //此线程读取关键字的次数
    private int curCount = 0;

    /**
     * jdk1.5开始加入的类，是个多线程辅助类
     * 用于多线程开始前统一执行操作或者多线程执行完成后调用主线程执行相应操作的类
     */
    private CountDownLatch doneSignal;

}
