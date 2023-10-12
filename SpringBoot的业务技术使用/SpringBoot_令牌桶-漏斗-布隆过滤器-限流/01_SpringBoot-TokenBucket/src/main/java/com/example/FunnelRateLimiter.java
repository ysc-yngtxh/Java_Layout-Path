package com.example;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 漏斗限流算法
 *
 * @author 游家纨绔
 * @date 2023/10/12
 */
public class FunnelRateLimiter {
    private Map<String, Funnel> funnelMap = new ConcurrentHashMap<>();
    
    public static void main(String[] args) throws InterruptedException {
        FunnelRateLimiter limiter = new FunnelRateLimiter();
        int testAccessCount = 30;
        int capacity = 5;
        int allowQuota = 5;
        int perSecond = 30;
        int allowCount = 0;
        int denyCount = 0;
        for (int i = 0; i < testAccessCount; i++) {
            boolean isAllow = limiter.isActionAllowed("dadiyang", "doSomething", 5, 5, 30);
            if (isAllow) {
                allowCount++;
            } else {
                denyCount++;
            }
            System.out.println("访问权限：" + isAllow);
            Thread.sleep(1000);
        }
        System.out.println("报告：");
        System.out.println("漏斗容量：" + capacity);
        System.out.println("漏斗流动速率：" + allowQuota + "次/" + perSecond + "秒");

        System.out.println("测试次数=" + testAccessCount);
        System.out.println("允许次数=" + allowCount);
        System.out.println("拒绝次数=" + denyCount);
    }

    /**
     * 根据给定的漏斗参数检查是否允许访问
     *
     * @param username   用户名
     * @param action     操作
     * @param capacity   漏斗容量
     * @param allowQuota 每单个单位时间允许的流量
     * @param perSecond  单位时间（秒）
     * @return 是否允许访问
     */
    public boolean isActionAllowed(String username, String action, int capacity, int allowQuota, int perSecond) {
        String key = "funnel:" + action + ":" + username;
        if (!funnelMap.containsKey(key)) {
            funnelMap.put(key, new Funnel(capacity, allowQuota, perSecond));
        }
        Funnel funnel = funnelMap.get(key);
        return funnel.watering(1);
    }

    private static class Funnel {
        private int capacity;
        private float leakingRate;
        private int leftQuota;
        private long leakingTs;

        public Funnel(int capacity, int count, int perSecond) {
            this.capacity = capacity;
            // 因为计算使用毫秒为单位的
            perSecond *= 1000;
            this.leakingRate = (float) count / perSecond;
        }

        /**
         * 根据上次水流动的时间，腾出已流出的空间
         */
        private void makeSpace() {
            long now = System.currentTimeMillis();
            long time = now - leakingTs;
            int leaked = (int) (time * leakingRate);
            if (leaked < 1) {
                return;
            }
            leftQuota += leaked;
            // 如果剩余大于容量，则剩余等于容量
            if (leftQuota > capacity) {
                leftQuota = capacity;
            }
            leakingTs = now;
        }

        /**
         * 漏斗漏水
         *
         * @param quota 流量
         * @return 是否有足够的水可以流出（是否允许访问）
         */
        public boolean watering(int quota) {
            makeSpace();
            int left = leftQuota - quota;
            if (left >= 0) {
                leftQuota = left;
                return true;
            }
            return false;
        }
    }
}

