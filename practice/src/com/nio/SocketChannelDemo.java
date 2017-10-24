package com.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by zhuwei on 2017/10/17 0017.
 *
 * Java NIO中的SocketChannel是一个连接到TCP网络套接字的通道。可以通过以下2种方式创建SocketChannel：
 *
 */
public class SocketChannelDemo {

    public static void main(String[] args) {

        /**
         打开一个SocketChannel并连接到互联网上的某台服务器。
         一个新连接到达ServerSocketChannel时，会创建一个SocketChannel。
         */

        try {
            // 打开socketCHannel
            SocketChannel sc = SocketChannel.open();
            sc.connect(new InetSocketAddress("http://jenkov.com",80));

            //从SocketChannel中读取数据
            ByteBuffer buf = ByteBuffer.allocate(48);
            int byteRead = sc.read(buf);

            //写入SocketChannel
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf1 = ByteBuffer.allocate(48);
            buf1.clear();
            buf1.put(newData.getBytes());

            buf.flip();
            while (buf1.hasRemaining()) {
                sc.write(buf1);
            }


            /**
             * 非阻塞模式
             * 可以设置 SocketChannel 为非阻塞模式（non-blocking mode）.设置之后，就可以在异步模式下调用connect(), read() 和write()了。
            */

            // 如果SocketChannel在非阻塞模式下，此时调用connect()，该方法可能在连接建立之前就返回了。为了确定连接是否建立，可以调用finishConnect()的方法
            sc.configureBlocking(false);
            sc.connect(new InetSocketAddress("http://jenkov.com",80));
            while ((!sc.finishConnect())) {
                // wait,or do something else...
            }













            // 关闭SocketChannel
            sc.close();


        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
