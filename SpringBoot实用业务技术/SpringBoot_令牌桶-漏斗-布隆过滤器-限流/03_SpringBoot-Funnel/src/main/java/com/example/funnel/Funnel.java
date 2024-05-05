package com.example.funnel;

/**
 * @author 游家纨绔
 * @dateTime 2023-10-12 00:00
 * @apiNote TODO 漏斗
 */
class Funnel {

    int capacity;      // 漏斗容量
    double leakingRate;// 漏嘴流水速率（double/ms）
    int leftQuota;     // 漏斗剩余空间
    long leakingTs;    // 上一次漏水时间

    public Funnel(int capacity, int allowQuota, int perSecond) {
        this.capacity = capacity;
        // 每 {perSecond} 秒内水滴的流量 / {perSecond}的毫秒数 = 每毫秒漏嘴流水流量(流水速率)
        this.leakingRate = (double)allowQuota/(1000*perSecond);
        // 漏斗剩余空间为0，即初始化为漏斗一开始是满的
        this.leftQuota = 0;
        this.leakingTs = System.currentTimeMillis();
    }
    void makeSpace(){
        long nowTs = System.currentTimeMillis();
        // 距离上一次漏水过去了多久
        long deltaTs = nowTs - leakingTs;
        // 当前时间距上次漏水时间内的水滴流量
        int deltaQuota = (int) (deltaTs * leakingRate);
        // 间隔时间过长，整数数字过大溢出（数字过大超出int数据范围就会成负数）
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

    // 每次调用即意味着判断漏斗状态：是否满水，是否空漏斗，漏斗剩余水是否满足一次请求
    // 尝试灌水，返回是否成功
    // 如果成功，则减少剩余空间
    boolean watering(int quota){
        makeSpace();
        // 判断剩余空间是否足够（就算剩余空间占满了容器，也能正常限流，不用担心。剩余空间满了就填满，正常执行保证限流就行）
        if(this.leftQuota >= quota){
            this.leftQuota -= quota;
            return true;
        }
        return false;
    }
}