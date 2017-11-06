package com.collection;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * Created by zhuwei on 2017/10/14 0014.
 */
public class Demo1 {
    List list = new ArrayList<>();
    Set set = new HashSet();
    Set tset = new TreeSet();

    /**
     * 非线程安全，可以接受null的键、值，
     * 迭代器Iterator是fail-fast迭代器，当有其它线程改变了HashMap的结构（增加或者移除元素），
     * 将会抛出ConcurrentModificationException，但迭代器本身的remove()方法移除元素则不会抛出ConcurrentModificationException异常
     * HashMap不能保证随着时间的推移Map中的元素次序是不变的。
     *
     * 我们能否让HashMap同步？
     * HashMap可以通过下面的语句进行同步：
     * Map m = Collections.synchronizeMap(hashMap);
     */
    Map map = new HashMap();

    /**
     * 部分方法 synchronized 修饰，线程安全、键、值不能为空，迭代器enumerator不是fail-fast
     */
    Map map1 = new Hashtable();

    /**
     * JDK5中提供的，是HashTable的替代，比HashTable的扩展性更好
     * 并发编程实践中，ConcurrentHashMap是一个经常被使用的数据结构，相比于Hashtable以及Collections.synchronizedMap()，
     * ConcurrentHashMap在线程安全的基础上提供了更好的写并发能力，但同时降低了对读一致性的要求（这点好像CAP理论啊 O(∩_∩)O）。
     * ConcurrentHashMap的设计与实现非常精巧，大量的利用了volatile，final，CAS等lock-free技术来减少锁竞争对于性能的影响，
     * 无论对于Java并发编程的学习还是Java内存模型的理解，ConcurrentHashMap的设计以及源码都值得非常仔细的阅读与揣摩。
     *
     * lock-free:无锁编程是指在不使用锁的情况下，在多线程环境下实现多变量的同步。即在没有线程阻塞的情况下实现同步。这样可以避免竞态、死锁等问题。
     *          原理是：CAS是一个原子操作，用于多线程环境下的同步。它比较内存中的内容和给定的值，只有当两者相同时（说明其未被修改），才会修改内存中的内容。
     */
    Map map2 = new ConcurrentHashMap();



    StringBuffer sb = new StringBuffer();  //方法有synchronized修饰，线程安全
    StringBuilder sb1 = new StringBuilder(); //非线程安全，效率高


    /**
     * Iterator 和 ListIterator 的区别
     * 一．相同点

     都是迭代器，当需要对集合中元素进行遍历不需要干涉其遍历过程时，这两种迭代器都可以使用。

     二．不同点

     1.使用范围不同，Iterator可以应用于所有的集合，Set、List和Map和这些集合的子类型。而ListIterator只能用于List及其子类型。

     2.ListIterator有add方法，可以向List中添加对象，而Iterator不能。

     3.ListIterator和Iterator都有hasNext()和next()方法，可以实现顺序向后遍历，但是ListIterator有hasPrevious()和previous()方法，可以实现逆向（顺序向前）遍历。Iterator不可以。

     4.ListIterator可以定位当前索引的位置，nextIndex()和previousIndex()可以实现。Iterator没有此功能。

     5.都可实现删除操作，但是ListIterator可以实现对象的修改，set()方法可以实现。Iterator仅能遍历，不能修改。
     */
}
