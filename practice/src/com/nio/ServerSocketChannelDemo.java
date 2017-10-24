package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;

/**
 * Created by zhuwei on 2017/10/17 0017.
 *
 * ava NIO中的 ServerSocketChannelDemo 是一个可以监听新进来的TCP连接的通道, 就像标准IO中的ServerSocket一样
 */
public class ServerSocketChannelDemo {

    public static void main(String[] args) {

        try {

            // 打开ServerSocketChannel
            ServerSocketChannel ssc = ServerSocketChannel.open();
            ssc.socket().bind(new InetSocketAddress(9999));

            // 设置成非阻塞模式，
            ssc.configureBlocking(false);

            // 关闭ServerSocketChannel
            //ssc.close();

            while (true) {
                //1、阻塞模式：监听新进来的连接，当accept方法返回的时候，返回一个包含新进来连接的SocketChannel，accept方法一直阻塞到新链接到达
                //2、非阻塞模式：accept方法会立刻返回，如果还没有新链接进来，则返回null，所以要加为null的判断
                SocketChannel sc =  ssc.accept();
                if (sc != null) {
                    // do something with socketChannel...
                }
            }




        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
