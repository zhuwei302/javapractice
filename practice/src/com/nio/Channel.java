package com.nio;

import java.io.FileNotFoundException;
import java.io.RandomAccessFile;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;

/**
 * Created by zhuwei on 2017/10/16 0016.
 */
public class Channel {

    public static void main(String[] args) {

        try {
            RandomAccessFile aFile = new RandomAccessFile("E:\\Users\\zhuwei\\Desktop\\gm\\temp.txt", "rw");
            FileChannel inChannel = aFile.getChannel();

            long pos = inChannel.position();//获取channel的当前位置
            inChannel.position(pos+1);//设置channel的当前位置
            pos = inChannel.position();

            long size = inChannel.size();//返回该实例对应的文件的大小,

            inChannel = inChannel.truncate(10);//此方法将直接作用于文件内容，截取文件的前10个字符，后面的全部删除
            inChannel.position(10);
            ByteBuffer bf1 = ByteBuffer.wrap("asfsaf".getBytes());
            inChannel.write(bf1);
            inChannel.force(true); //将channel中未写入磁盘的数据强制写入磁盘
            /**
             *  此方法与Java内存使用机制有关，用于实例化 ByteBuffer类 并分配内存空间
             *  非直接字节缓冲区 ByteBuffer.allocate(int capacity)  分配方式产生的内存开销是在JVM中，分配一个HeapByteBuffer的实例，其底层是byte数组，capacity：新缓存区容量  返回：新的字节缓存区
             *  直接字节缓冲区 ByteBuffer.allocateDirect(int capacity)  分配方式产生的内存开销是在JVM之外，capacity：新缓存区容量  返回：新的字节缓存区
             *  当Java程序接收到外部传来的数据时，首先是被系统内存所获取，然后在由系统内存复制复制到JVM内存中供Java程序使用。
             *  所以在另外一种分配方式中，能够省去复制这一步操作，效率上会有所提高。可是系统级内存的分配比起JVM内存的分配要耗时得多，所以并非不论什么时候allocateDirect的操作效率都是最高的
             *  数据量小时，两种方式效率差不多，当数据量大于1024000时，allocate效率高于allocateDirect
             */
            ByteBuffer buf = ByteBuffer.allocate(48);


            int bytesRead = inChannel.read(buf); // 将channel中的数据写入到buffer   inChannel.write(buf) channel从buffer中读取数据
            while(bytesRead != -1){
                System.out.println("Read: " + bytesRead);
                buf.flip(); // 将buffer从写入模式切换到读模式
                while (buf.hasRemaining()) { //判断当前位置是否在限制范围内，告知程序在当前位置和限制之间是否有元素，是否继续往下读取数据。
                    System.out.println((char) buf.get());
                }

                buf.clear(); //读完所有数据，清空缓存区
                bytesRead = inChannel.read(buf);
            }
            aFile.close();

        }catch (Exception e){
            e.printStackTrace();
        }
    }



}
