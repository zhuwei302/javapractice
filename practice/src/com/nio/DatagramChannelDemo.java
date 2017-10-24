package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

/**
 * Created by zhuwei on 2017/10/17 0017.
 *
 * DatagramChannel是一个能收发UDP包的通道。因为UDP是无连接的网络协议，所以不能像其它通道那样读取和写入。它发送和接收的是数据包
 */
public class DatagramChannelDemo {

    public static void main(String[] args) {


        try {

            // 打开 在9999端口接受数据包
            DatagramChannel channel = DatagramChannel.open();
            channel.socket().bind(new InetSocketAddress(9999));

            // 通过receive方法从DatagramChannel接收数据,复制到buffer,如果buffer容不下接收的数据，多出的将被丢弃
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            channel.receive(buf);

            // 通过send()方法从DatagramChannel发送数据
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf1 = ByteBuffer.allocate(48);
            buf1.clear();
            buf1.put(newData.getBytes());
            buf1.flip();
            int byteSent = channel.send(buf1,new InetSocketAddress("jenkov.com",80));


            /**
             * 连接到特定的地址
               可以将DatagramChannel“连接”到网络中的特定地址的。由于UDP是无连接的，连接到特定地址并不会像TCP通道那样创建一个真正的连接。而是锁住DatagramChannel ，让其只能从特定地址收发数据。
             */
            channel.connect(new InetSocketAddress("jenkov.com",80));
            //当连接后，也可以使用read()和write()方法，就像在用传统的通道一样。只是在数据传送方面没有任何保证
            int byteRead = channel.read(buf);
            int byteWritten = channel.write(buf);



        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
