package com.nio;

import javax.print.DocFlavor;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

/**
 * Created by zhuwei on 2017/10/17 0017.
 * Java NIO 管道是2个线程之间的单向数据连接。Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

 这里是Pipe原理的图示：

               \---------------Pipe----------------\
               \                                   \
 threadA ------\-->SinkChannel--->SourceChannel----\-->ThreadB
               \                                   \
               \-----------------------------------\
 */

public class PipeDemo {


    public static void main(String[] args) {

        try {

            //创建通道
            Pipe pipe = Pipe.open();
            //向通道写数据，需要访问sink通道
            Pipe.SinkChannel sinkChannel = pipe.sink();

            //通过sinkChannel的write方法，将数据写入sinkChannel
            String newData = "New String to write to file..." + System.currentTimeMillis();
            ByteBuffer buf = ByteBuffer.allocate(48);
            buf.clear();
            buf.put(newData.getBytes());
            buf.flip();
            while (buf.hasRemaining()) {
                sinkChannel.write(buf);
            }

            // 从管道读取数据，需要sourceChannel
            Pipe.SourceChannel sourceChannel = pipe.source();
            // 调用SourceChannel的read方法来读取数据，read返回的int值告诉我们多少字节被读进了缓存
            ByteBuffer buf1 = ByteBuffer.allocate(48);
            int byteRead = sourceChannel.read(buf1);


        } catch (IOException e) {
            e.printStackTrace();
        }


    }


}
