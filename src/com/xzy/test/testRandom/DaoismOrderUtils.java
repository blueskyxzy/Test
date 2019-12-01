package com.xzy.test.testRandom;


import java.text.SimpleDateFormat;


public class DaoismOrderUtils {

    // ==============================Fields===========================================
    /**
     * 开始时间截 (2015-01-01)
     */
    @SuppressWarnings("unused")
    private final long twepoch = 1420041600000L;

    /**
     * 机器id所占的位数
     */
    private final long workerIdBits = 5L;

    /**
     * 数据标识id所占的位数
     */
    private final long datacenterIdBits = 5L;

    /**
     * 支持的最大机器id，结果是31 (这个移位算法可以很快的计算出几位二进制数所能表示的最大十进制数)
     */
    private final long maxWorkerId = -1L ^ (-1L << workerIdBits);

    /**
     * 支持的最大数据标识id，结果是31
     */
    private final long maxDatacenterId = -1L ^ (-1L << datacenterIdBits);

    /**
     * 序列在id中占的位数
     */
    private final long sequenceBits = 12L;

    /**
     * 机器ID向左移12位
     */
    private final long workerIdShift = sequenceBits;

    /**
     * 数据标识id向左移17位(12+5)
     */
    private final long datacenterIdShift = sequenceBits + workerIdBits;

    /**
     * 时间截向左移22位(5+5+12)
     */
    @SuppressWarnings("unused")
    private final long timestampLeftShift = sequenceBits + workerIdBits + datacenterIdBits;

    /**
     * 生成序列的掩码，这里为4095 (0b111111111111=0xfff=4095)
     */
    private final long sequenceMask = -1L ^ (-1L << sequenceBits);

    /**
     * 订单编号前缀，如NO
     **/
    private final String orderPrefix;

    /**
     * 工作机器ID(0~31)
     */
    private long workerId = 1;

    /**
     * 数据中心ID(0~31)
     */
    private long datacenterId;

    /**
     * 毫秒内序列(0~4095)
     */
    private long sequence = 0L;

    /**
     * 上次生成ID的时间截
     */
    private long lastTimestamp = -1L;

    // ==============================Constructors=====================================
    public DaoismOrderUtils() {
        // this.workerId =
        // Long.parseLong(PropertiesUtil.getPropertity("orderWorkerId"));
        this.datacenterId = 0;
        this.orderPrefix = "";
    }

    /**
     * 构造函数
     *
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public DaoismOrderUtils(long workerId, long datacenterId) {
        this("", workerId, datacenterId);
    }

    /**
     * 构造函数
     *
     * @param orderPrefix  订单编号前缀
     * @param workerId     工作ID (0~31)
     * @param datacenterId 数据中心ID (0~31)
     */
    public DaoismOrderUtils(String orderPrefix, long workerId, long datacenterId) {
        if (workerId > maxWorkerId || workerId < 0) {
            throw new IllegalArgumentException(String.format("worker Id can't be greater than %d or less than 0", maxWorkerId));
        }
        if (datacenterId > maxDatacenterId || datacenterId < 0) {
            throw new IllegalArgumentException(String.format("datacenter Id can't be greater than %d or less than 0", maxDatacenterId));
        }
        this.workerId = workerId;
        this.datacenterId = datacenterId;
        if (orderPrefix == null) {
            this.orderPrefix = "";
        } else {
            this.orderPrefix = orderPrefix;
        }
    }

    // ==============================Methods==========================================

    /**
     * 获得下一个ID (该方法是线程安全的)
     *
     * @return SnowflakeId
     */
    public synchronized String nextId(String orderPrefix) {
        long timestamp = timeGen();
        // 如果当前时间小于上一次ID生成的时间戳，说明系统时钟回退过这个时候应当抛出异常
        if (timestamp < lastTimestamp) {
            throw new RuntimeException(String.format("Clock moved backwards.  Refusing to generate id for %d milliseconds", lastTimestamp - timestamp));
        }
        // 如果是同一时间生成的，则进行毫秒内序列
        if (lastTimestamp == timestamp) {
            sequence = (sequence + 1) & sequenceMask;
            // 毫秒内序列溢出
            if (sequence == 0) {
                // 阻塞到下一个毫秒,获得新的时间戳
                timestamp = tilNextMillis(lastTimestamp);
            }
        }
        // 时间戳改变，毫秒内序列重置
        else {
            sequence = 0L;
        }
        // 上次生成ID的时间截
        lastTimestamp = timestamp;
        /* 移位并通过或运算拼到一起组成64位的ID return ((timestamp - twepoch) <<
         * timestampLeftShift) // | (datacenterId << datacenterIdShift) // |
         * (workerId << workerIdShift) // | sequence; */
        long suffix = (datacenterId << datacenterIdShift) | (workerId << workerIdShift) | sequence;
        // System.out.println(suffix);
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyyMMddHHmmssSSS" );
        String datePrefix = sdf1.format(timestamp);
//        String datePrefix = DateFormat.format(timestamp, "yyyyMMddHHmmssSSS");
        // return orderPrefix + datePrefix + suffix;
        return orderPrefix + datePrefix.substring(0, 8) + suffix + datePrefix.substring(8);
    }

    public synchronized String nextId() {
        return nextId(this.orderPrefix);
    }

    /**
     * 阻塞到下一个毫秒，直到获得新的时间戳
     *
     * @param lastTimestamp 上次生成ID的时间截
     * @return 当前时间戳
     */
    protected long tilNextMillis(long lastTimestamp) {
        long timestamp = timeGen();
        while (timestamp <= lastTimestamp) {
            timestamp = timeGen();
        }
        return timestamp;
    }

    /**
     * 返回以毫秒为单位的当前时间
     *
     * @return 当前时间(毫秒)
     */
    protected long timeGen() {
        return System.currentTimeMillis();
    }

    // ==============================Test=============================================

    /**
     * 测试
     */
    public static void main(String[] args) {
        /* DaoismOrderNoGenerator idWorker = new DaoismOrderNoGenerator(0, 0);
         * for (int i = 0; i < 1000; i++) { String id = idWorker.nextNo();
         * System.out.println(id); } */
        final DaoismOrderUtils idWorker = new DaoismOrderUtils(1, 0);
        Runnable runner = () -> {
            String id = idWorker.nextId();
            System.out.println("1 : " + id);
        };
        final DaoismOrderUtils idWorker2 = new DaoismOrderUtils(2, 0);
        Runnable runner2 = () -> {
            String id = idWorker2.nextId();
            System.out.println("2 : " + id);
        };
        int runnerCount = 1000;
        for (int i = 0; i < runnerCount; i++) {
            runner.run();
            runner2.run();
        }
    }

}