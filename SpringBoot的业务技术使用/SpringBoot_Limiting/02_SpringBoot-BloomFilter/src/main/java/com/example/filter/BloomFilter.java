package com.example.filter;

import java.util.BitSet;
import java.util.Random;

// TODO 布隆过滤器的巨大用处就是，能够迅速判断一个元素是否在一个集合中
public class BloomFilter {
    // BitSet类：用于存储一个位序列。就是使用位来存储boolean信息，0表示假，1表示真。
    //          位序列是比较省空间的，因为记录数据通过下标，比如：下标为96，对应值为1；那么就是说BitSet中存在'A'值
    // 常见 BitSet 的应用是那些需要对海量数据进行一些统计工作的时候，比如日志分析等。
    // 面试题中也常出现，比如：统计40亿个数据中没有出现的数据，将40亿个不同数据进行排序等。
    // 又如：现在有1千万个随机数，随机数的范围在1到1亿之间。现在要求写出一种算法，将1到1亿之间没有在随机数中的数求出来(百度)。
    private BitSet filter;
    private int size;
    private int hashFunctions;
    private Random random;

    /**
     * 构造函数，初始化布隆过滤器
     * @param size 布隆过滤器的大小
     * @param hashFunctions 布隆过滤器使用的哈希函数数量
     */
    public BloomFilter(int size, int hashFunctions) {
        // 把bit位下标size位置设置为1（0为false，1为true）
        this.filter = new BitSet(size);
        this.size = size;
        this.hashFunctions = hashFunctions;
        this.random = new Random();
    }

    /**
     * 添加元素到布隆过滤器
     * @param element 要添加的元素
     */
    public void addElement(String element) {
        for (int i = 0; i < this.hashFunctions; i++) {
            int hash = Math.abs((element + i).hashCode() % this.size);
            this.filter.set(hash, true);
        }
    }

    /**
     * 检查元素是否存在于布隆过滤器
     * @param element 要检查的元素
     * @return 如果元素可能存在于布隆过滤器中，则返回 true，否则返回 false
     */
    public boolean checkElement(String element) {
        for (int i = 0; i < this.hashFunctions; i++) {
            int hash = Math.abs((element + i).hashCode() % this.size);
            if (!this.filter.get(hash)) {
                return false;
            }
        }
        return true;
    }

    /**
     * 生成一个随机字符串
     * @param length 字符串的长度
     * @return 随机字符串
     */
    public static String generateRandomString(int length) {
        StringBuilder builder = new StringBuilder();
        Random random = new Random();
        for (int i = 0; i < length; i++) {
            builder.append( (char) ('a' + random.nextInt(26)) );
        }
        return builder.toString();
    }
}
