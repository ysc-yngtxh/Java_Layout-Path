package com.example.funnel;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-12 00:00
 * @apiNote TODO 漏斗
 */
class Funnel {

    int capacity;     // 漏斗容量
    double leakingRate;// 漏嘴流水速率（）
    int leftQuota;    // 漏斗剩余空间
    long leakingTs;   // 上一次漏水时间
    long fullTs;      // 漏斗空了的时间

    public Funnel(int capacity, double leakingRate) {
        this.capacity = capacity;
        this.leakingRate = leakingRate;
        this.leftQuota = 0;
        this.leakingTs = System.currentTimeMillis();
    }
    void makeSpace(int quota){
        long nowTs = System.currentTimeMillis();
        // 距离上一次漏水过去了多久
        long deltaTs = nowTs - leakingTs;
        // 当前时间距上次漏水时间内的水滴流量
        int deltaQuota = (int) (deltaTs * leakingRate);
        // 间隔时间过长，整数数字过大溢出（数字过大溢出就会成负数）
        if(deltaQuota < 0){
            this.leftQuota = capacity;
            this.leakingTs = nowTs;
            return;
        }
        // 腾出空间太小，最小单位是1，那就等下次吧
        if(deltaQuota < 1){
            return;
        }
        // 增加剩余空间
        this.leftQuota += deltaQuota;
        // 记录漏水时间
        this.leakingTs = nowTs;
        // 剩余空间不得高于容量
        if(this.leftQuota >= this.capacity){
            this.leftQuota = this.capacity;
        }
    }

    boolean watering(int quota){
        makeSpace(quota);
        // 判断剩余空间是否足够
        if(this.leftQuota >= quota){
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }


    // // 漏斗容量
    // private int capacity;
    //
    // // 每毫秒内允许的流量
    // private float leakingRate;
    //
    // // 已经消耗的水滴流量
    // private int leftQuota;
    //
    // // 上一次水滴漏出被消耗的时间
    // private long leakingTs= System.currentTimeMillis();
    //
    // public Funnel(int capacity, int count, int perSecond) {
    //     this.capacity = capacity;
    //     // 因为计算使用毫秒为单位的
    //     perSecond *= 1000;
    //     // 每毫秒内允许的流量
    //     this.leakingRate = (float) count / perSecond;
    // }
    //
    // /**
    //  * 漏斗漏水
    //  * @param quota 一次请求需要的水滴流量
    //  * @return 是否有足够的水可以流出（是否允许访问）
    //  */
    // public boolean watering(int quota) {
    //     makeSpace();
    //     // 已经漏出消耗的水滴流量 - 每个请求所需要的水滴流量 = 当前请求消耗后剩余的水滴流量
    //     int left = leftQuota - quota;
    //     // 剩余水滴流量 >= 0，说明当前请求满足访问要求，允许通行
    //     if (left >= 0) {
    //         leftQuota = left;
    //         return true;
    //     }
    //     return false;
    // }
    //
    // // 根据上次水流动的时间，腾出已流出的空间
    // private void makeSpace() {
    //     long now = System.currentTimeMillis();
    //     // 当前时间 - 上次水滴时间 = 一次水滴时间
    //     long time = now - leakingTs;
    //     // 一次水滴时间毫秒数 * 每毫秒内允许的流量 = 一次水滴的流量
    //     int leaked = (int) (time * leakingRate);
    //     if (leaked < 1) {
    //         return;
    //     }
    //     // 已经漏出消耗的水滴流量
    //     leftQuota += leaked;
    //     // 如果消耗大于容量，则容量赋值给消耗
    //     if (leftQuota > capacity) {
    //         leftQuota = capacity;
    //     }
    //     // 此刻消耗水滴的时间 赋值给 leakingTs
    //     leakingTs = now;
    // }
}