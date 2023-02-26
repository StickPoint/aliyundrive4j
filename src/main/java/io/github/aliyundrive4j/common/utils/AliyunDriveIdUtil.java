package io.github.aliyundrive4j.common.utils;

import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;

/**
 * 基于雪花算法的id生成器
 * @author puye(0303)
 * @since 2023/2/2
 */
public class AliyunDriveIdUtil {
    
    private AliyunDriveIdUtil(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 起始的时间戳
     */
    private static final long START_STMP = 1480166465631L;

    /**
     * 每一部分占用的位数 序列号占用的位数
     */
    private static final long SEQUENCE_BIT = 12;
    /**
     * 机器标识占用的位数
     */
    private static final long MACHINE_BIT = 5;
    /**
     * 数据中心占用的位数
     */
    private static final long DATACENTER_BIT = 5;

    /**
     * 每一部分的最大值
     */
    private static final long MAX_DATACENTER_NUM = ~(-1L << DATACENTER_BIT);
    private static final long MAX_MACHINE_NUM = ~(-1L << MACHINE_BIT);
    private static final long MAX_SEQUENCE = ~(-1L << SEQUENCE_BIT);

    /**
     * 每一部分向左的位移
     */
    private static final long MACHINE_LEFT = SEQUENCE_BIT;
    private static final long DATACENTER_LEFT = SEQUENCE_BIT + MACHINE_BIT;
    private static final long TIMESTAMP_LEFT = DATACENTER_LEFT + DATACENTER_BIT;

    public static long getRandomId(long datacenterId, long machineId) {
        if (datacenterId > MAX_DATACENTER_NUM || datacenterId < 0) {
            throw new IllegalArgumentException("datacenterId can't be greater than MAX_DATACENTER_NUM or less than 0");
        }
        if (machineId > MAX_MACHINE_NUM || machineId < 0) {
            throw new IllegalArgumentException("machineId can't be greater than MAX_MACHINE_NUM or less than 0");
        }
        // 序列号
        long lastStamp = -1L;
        // 上一次时间戳
        long sequence = 0L;
        return nextId(datacenterId, machineId, lastStamp, sequence);
    }

    /**
     * 产生下一个ID
     *
     * @return id
     */
    private static  synchronized long nextId(long datacenterId, long machineId,long lastStamp,long sequence) {
        long currStamp = System.currentTimeMillis();
        if (currStamp < lastStamp) {
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_PARAM.getCode(), AliyunDriveCodeEnums.ERROR_PARAM.getMessage());
        }
        if (currStamp == lastStamp) {
            //相同毫秒内，序列号自增
            sequence = (sequence + 1) & MAX_SEQUENCE;
            //同一毫秒的序列数已经达到最大
            if (sequence == 0L) {
                currStamp = getNextMill(lastStamp);
            }
        } else {
            //不同毫秒内，序列号置为0
            sequence = 0L;
        }
        return (currStamp - START_STMP) << TIMESTAMP_LEFT
                | datacenterId << DATACENTER_LEFT
                | machineId << MACHINE_LEFT
                | sequence;
    }

    private static long getNextMill(long lastStamp) {
        long mill = System.currentTimeMillis();
        while (mill <= lastStamp) {
            mill = System.currentTimeMillis();
        }
        return mill;
    }

    /**
     * 获得固定稳定的雪花id
     * @return 返回一个固定规格的雪花id
     */
    @SuppressWarnings("unused")
    public static long getStableSerializeId(){
        return getRandomId(11, 22);
    }

}
