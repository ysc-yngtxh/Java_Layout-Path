package com.example.custom;

import com.google.common.base.Charsets;
import com.google.common.hash.BloomFilter;
import com.google.common.hash.Funnel;
import com.google.common.hash.Funnels;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.List;
import java.util.Random;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-10 00:09
 * @apiNote TODO 自定义实现布隆过滤器
 */
public class CustomBloomFilter {
    // BitSet类：用于存储一个位序列。就是使用位来存储boolean信息，0表示假，1表示真(数据存在)。
    //          位序列是比较省空间的，因为记录数据通过下标，比如：下标为97，对应值为1。那么就是说 BitSet 中存有'a'值
    //             二进制比特位(bit)数据   ...   0   ...   1   ...  1    ...   0      0      1
    //             二进制比特位(bit)下标   ...  100  ...   65  ...  48   ...   2      1      0
    //          上述表示 下标{0, 48, 65} 的比特位存有数据，对应ASCⅡ码（英文编码）数据 {空字符, 0, 'A'} 存在。
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
    public CustomBloomFilter(int size, int hashFunctions) {
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
            int hash = Math.abs( (element + i).hashCode() % this.size );
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

    // 1、布隆过滤器的默认容错率是0.03
    public static void defaultFalsePositiveRate() {
        // 没有设置误判率的情况下，10000 → 312，误判率：3.12%
        // TODO 当布隆过滤器插入一个元素时，会使用多个哈希函数将该元素映射为多个不同的位，每个位都被置为1。
        //  当查询一个元素时，同样会使用多个哈希函数将该元素映射为多个位，检查这些位是否都被置为1，若都为1，则认为该元素存在于集合中。
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create( Funnels.stringFunnel(StandardCharsets.UTF_8), 10000);
        for (int m = 0; m < 10000; m++){
            bloomFilter.put("" + m);
        }
        List<Integer> list = new ArrayList<Integer>();
        for(int n = 20000; n < 30000; n++){
            if(bloomFilter.mightContain("" + n)){
                list.add(n);
            }
        }
        System.out.println("布隆过滤器使用默认误差率时的误判数量：：：" + list.size());
    }

    // 2、测试容错率的变化，所需数组位数的变化
    // 容错率0.0001，所需位数191701
    public static void setFalsePositiveRate() {
        // 创建符合条件的布隆过滤器。预期数据量10000，错误率0.0001
        BloomFilter<CharSequence> bloomFilter =
                BloomFilter.create( Funnels.stringFunnel(Charsets.UTF_8), 10000, 0.0001);
        for (int i = 0; i < 10000; i++) {
            bloomFilter.put("" + i);
        }
        List<Integer> list1 = new ArrayList<Integer>();
        for (int j = 10000; j < 20000; j++) {
            if (bloomFilter.mightContain("" + j)) {
                list1.add(j);
            }
        }
        System.err.println("布隆过滤器使用自定义误差率时的误判数量：：：" + list1.size());
    }
    // 容错率0.01，所需位数95850
    // 由此可见，误判率越低，则底层维护的数组越长，占用空间越大。
    // 因此，误判率实际取值，根据服务器所能够承受的负载来决定，不是拍脑袋瞎想的。
}
