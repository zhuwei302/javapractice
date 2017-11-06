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

    Map map = new HashMap(); //非线程安全，可以接受null的键、值
    Map map1 = new Hashtable(); //部分方法 synchronized 修饰，线程安全、键、值不能为空
    Map map2 = new ConcurrentHashMap();
    StringBuffer sb = new StringBuffer();  //方法有synchronized修饰，线程安全
    StringBuilder sb1 = new StringBuilder(); //非线程安全，效率高
}
