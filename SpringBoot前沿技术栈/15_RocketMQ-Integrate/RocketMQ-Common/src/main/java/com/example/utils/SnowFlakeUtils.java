package com.example.utils;

/**
 * @author 游家纨绔
 * @dateTime 2023-09-09 22:00
 * @apiNote TODO 雪花算法工具类
 */
public class SnowFlakeUtils {

    /**
     * 起始的时间戳
     */
    private static final long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数
     */
    private static final long SEQUENCE_BIT = 12;  // 序列号占用的位数
    private static final long MACHINE_BIT = 5;    // 机器标识占用的位数
    private static final long DATACENTER_BIT = 5; // 数据中心占用的位数

    /**
     * 每一部分的最大值
     */
    private static final long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private static final long MAX_MACHINE_NUM = -1L ^ (-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = -1L ^ (-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    private static long datacenterId = 7;   // 数据中心
    private static long machineId = 7;      // 机器标识
    private static long sequence = 0L;      // 序列号
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