package com.example.local;

import java.util.concurrent.ConcurrentHashMap;

/**
 *  ThreadLocal 是如何做到为每一个线程维护变量的副本的呢？其实实现的思路很简单：
 *     在ThreadLocal类中有一个Map，用于存储每一个线程的变量副本，Map中元素的键为线程对象，而值对应线程的变量副本
 */
public class MyThreadLocal {
    private final ConcurrentHashMap<Thread, Object> valueMap = new ConcurrentHashMap<>();
    public void set(Object newValue) {
        valueMap.put(Thread.currentThread(), newValue);
    }

    public Object get() {
        Thread currentThread = Thread.currentThread();
        Object o = valueMap.get(currentThread);
        if (o == null && !valueMap.containsKey(currentThread)) {
            o = initialValue();
            valueMap.put(currentThread, o);
        }
        return o;
    }

    public Object initialValue() {
        return null;
    }

    public void remove() {
        valueMap.remove(Thread.currentThread());
    }
}