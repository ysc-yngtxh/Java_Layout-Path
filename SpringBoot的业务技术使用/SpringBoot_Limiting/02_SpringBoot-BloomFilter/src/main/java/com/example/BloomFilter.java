package com.example;

import java.util.BitSet;
import java.util.Random;

public class BloomFilter {
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
            builder.append((char) ('a' + random.nextInt(26)));
        }
        return builder.toString();
    }

    /**
     * 测试布隆过滤器
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        int size = 1000000;
        int hashFunctions = 5;
        BloomFilter filter = new BloomFilter(size, hashFunctions);

        // 添加一些随机字符串到布隆过滤器
        for (int i = 0; i < 10000; i++) {
            filter.addElement(generateRandomString(10));
        }

        // 检查一些随机字符串是否在布隆过滤器中
        for (int i = 0; i < 100; i++) {
            String randomString = generateRandomString(10);
            if (filter.checkElement(randomString)) {
                System.out.println(randomString + " may be in the filter.");
            } else {
                System.out.println(randomString + " is not in the filter.");
            }
        }
    }
}
