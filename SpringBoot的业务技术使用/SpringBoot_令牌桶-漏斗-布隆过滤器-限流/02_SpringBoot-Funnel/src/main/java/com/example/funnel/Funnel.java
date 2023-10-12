package com.example.funnel;

class Funnel {
    // 漏斗容量
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