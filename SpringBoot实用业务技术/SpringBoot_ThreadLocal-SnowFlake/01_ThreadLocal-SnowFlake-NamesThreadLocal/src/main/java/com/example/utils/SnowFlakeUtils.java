package com.example.utils;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-09 22:00
 * @apiNote TODO 雪花算法工具类
 */
public class SnowFlakeUtils {

    /*
     *  SnowFlake是Twitter公司采用的一种算法，目的是在分布式系统中产生全局唯一且趋势递增的ID。
     *  组成部分（64bit）
     *      1、第一位 占用1bit，其值始终是0，没有实际作用。
     *      2、时间戳 占用41bit，精确到毫秒，总共可以容纳约69年的时间。
     *      3、工作机器id 占用10bit，其中高位5bit是数据中心ID，低位5bit是工作节点ID，做多可以容纳1024个节点。
     *      4、序列号 占用12bit，每个节点每毫秒0开始不断累加，最多可以累加到4095，一共可以产生4096个ID。
     *
     *  SnowFlake算法在同一毫秒内最多可以生成多少个全局唯一ID呢：： 同一毫秒的ID数量 = 1024 X 4096 = 4194304
     */

    /**
     * 起始的时间戳
     */
    private static final long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12;  // 序列号占用的位数
    private static final long DATACENTER_BIT = 5; // 数据中心占用的位数
    private static final long MACHINE_BIT = 5;    // 机器标识占用的位数

    /**
     * 每一部分的最大值
     *
     * 举例 MAX_SEQUENCE 的运算过程
     *  Java二进制中使用补码来表示负数进行运算（Long类型有64个比特位，这里省略一部分不影响结果）
     *    -1L     --> 原码：1000 0000 0000 0000 0000 0000 0000 0001
     *                反码：1111 1111 1111 1111 1111 1111 1111 1110
     *                补码：1111 1111 1111 1111 1111 1111 1111 1111
     *
     *    -1L<<12 将负数左移12位，用负数做运算应该使用补码
     *           -1L的补码：1111 1111 1111 1111 1111 1111 1111 1111
     *         -1L<<12补码：1111 1111 1111 1111 1111 0000 0000 0000
     *                反码：1111 1111 1111 1111 1110 1111 1111 1111
     *                原码：1000 0000 0000 0000 0001 0000 0000 0000 --> -(2^12)=-4096
     *
     *    ~(-1L<<12) 将负数取反，也就是将负数的补码取反成为正数，正数没有反码的概念，因此取反后就是为结果的原码
     *         -1L<<12补码：1111 1111 1111 1111 1111 0000 0000 0000
     *                原码：0000 0000 0000 0000 0000 1111 1111 1111 --> (2^12)+(2^11)+...+1=4095
     */
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);         // 序列号最大值  -- 4095
    private static final long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT); // 数据中心最大值 -- 31
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);       // 机器标识最大值 -- 31

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private static long datacenterId = 7;   // 数据中心，默认值7
    private static long machineId = 7;      // 机器标识，默认值7
    private static long sequence = 0L;      // 序列号，默认值0
    private static long lastStmp = -1L;     // 上一次时间戳

    public SnowFlakeUtils(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        SnowFlakeUtils.datacenterId = datacenterId;
        SnowFlakeUtils.machineId = machineId;
    }

    /**
     * 产生下一个ID
     */
    public synchronized static long nextId() {
        long currStmp = System.currentTimeMillis();
        if (currStmp < lastStmp) {
            throw new RuntimeException("Clock moved backwards.  Refusing to generate id");
        }
        if (currStmp == lastStmp) {
            // 相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            // 同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStmp = getNextMill();
            }
        } else {
            // 不同毫秒内，序列号置为0
            sequence = 0L;
        }

        lastStmp = currStmp;

        return (currStmp - START_STMP) << TIMESTMP_LEFT // 时间戳部分
                | datacenterId << DATACENTER_LEFT       // 数据中心部分
                | machineId << MACHINE_LEFT             // 机器标识部分
                | sequence;                             // 序列号部分
    }

    private static long getNextMill() {
        long mill = System.currentTimeMillis();
        while (mill <= lastStmp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    public static void main(String[] args) {
        for (int i = 0; i < (1 << 12); i++) {
            System.out.println(SnowFlakeUtils.nextId());
        }
    }
}
