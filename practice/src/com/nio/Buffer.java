package com.nio;

/**
 * Created by zhuwei on 2017/10/16 0016.
 *
 *
 * Buffer的分配

 要想获得一个Buffer对象首先要进行分配。 每一个Buffer类都有一个allocate方法。下面是一个分配48字节capacity的ByteBuffer的例子。

 ByteBuffer buf = ByteBuffer.allocate(48);
 这是分配一个可存储1024个字符的CharBuffer：

 CharBuffer buf = CharBuffer.allocate(1024);
 向Buffer中写数据

 写数据到Buffer有两种方式：

 从Channel写到Buffer。
 通过Buffer的put()方法写到Buffer里。
 从Channel写到Buffer的例子

 int bytesRead = inChannel.read(buf); //read into buffer.
 通过put方法写Buffer的例子：

 buf.put(127);
 put方法有很多版本，允许你以不同的方式把数据写入到Buffer中。例如， 写到一个指定的位置，或者把一个字节数组写入到Buffer。 更多Buffer实现的细节参考JavaDoc。

 flip()方法

 flip方法将Buffer从写模式切换到读模式。调用flip()方法会将position设回0，并将limit设置成之前position的值。

 换句话说，position现在用于标记读的位置，limit表示之前写进了多少个byte、char等 —— 现在能读取多少个byte、char等。

 从Buffer中读取数据

 从Buffer中读取数据有两种方式：

 从Buffer读取数据到Channel。
 使用get()方法从Buffer中读取数据。
 从Buffer读取数据到Channel的例子：

 //read from buffer into channel.
 int bytesWritten = inChannel.write(buf);
 使用get()方法从Buffer中读取数据的例子

 byte aByte = buf.get();
 get方法有很多版本，允许你以不同的方式从Buffer中读取数据。例如，从指定position读取，或者从Buffer中读取数据到字节数组。更多Buffer实现的细节参考JavaDoc。

 rewind()方法

 Buffer.rewind()将position设回0，所以你可以重读Buffer中的所有数据。limit保持不变，仍然表示能从Buffer中读取多少个元素（byte、char等）。

 clear()与compact()方法

 一旦读完Buffer中的数据，需要让Buffer准备好再次被写入。可以通过clear()或compact()方法来完成。

 如果调用的是clear()方法，position将被设回0，limit被设置成 capacity的值。换句话说，Buffer 被清空了。Buffer中的数据并未清除，只是这些标记告诉我们可以从哪里开始往Buffer里写数据。

 如果Buffer中有一些未读的数据，调用clear()方法，数据将“被遗忘”，意味着不再有任何标记会告诉你哪些数据被读过，哪些还没有。

 如果Buffer中仍有未读的数据，且后续还需要这些数据，但是此时想要先先写些数据，那么使用compact()方法。

 compact()方法将所有未读的数据拷贝到Buffer起始处。然后将position设到最后一个未读元素正后面。limit属性依然像clear()方法一样，设置成capacity。现在Buffer准备好写数据了，但是不会覆盖未读的数据。

 mark()与reset()方法

 通过调用Buffer.mark()方法，可以标记Buffer中的一个特定position。之后可以通过调用Buffer.reset()方法恢复到这个position。例如：

 buffer.mark();

 //call buffer.get() a couple of times, e.g. during parsing.

 buffer.reset();  //set position back to mark.
 equals()与compareTo()方法

 可以使用equals()和compareTo()方法两个Buffer。

 equals()

 当满足下列条件时，表示两个Buffer相等：

 有相同的类型（byte、char、int等）。
 Buffer中剩余的byte、char等的个数相等。
 Buffer中所有剩余的byte、char等都相同。
 如你所见，equals只是比较Buffer的一部分，不是每一个在它里面的元素都比较。实际上，它只比较Buffer中的剩余元素。

 compareTo()方法

 compareTo()方法比较两个Buffer的剩余元素(byte、char等)， 如果满足下列条件，则认为一个Buffer“小于”另一个Buffer：

 第一个不相等的元素小于另一个Buffer中对应的元素 。
 所有元素都相等，但第一个Buffer比另一个先耗尽(第一个Buffer的元素个数比另一个少)。
 *
 */
public class Buffer {
}
