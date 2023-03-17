package io.github.aliyundrive4j.common.utils;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import org.bouncycastle.util.encoders.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Objects;
import java.util.UUID;

/**
 * 基于雪花算法的id生成器
 * @author puye(0303)
 * @since 2023/2/2
 */
public class AliyunDriveIdUtil {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDriveIdUtil.class);

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

    /**
     * 采用URL Base64字符，即把“+/”换成“-_”
     */
    private static final char[] ALPHABET = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789-_=".toCharArray();


    /**
     * 获得4个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get4Uuid(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[1];
    }
    /**
     * 获得8个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get8Uuid(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0];
    }
    /**
     * 获得12个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get12Uuid(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1];
    }
    /**
     * 获得16个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get16Uuid(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2];
    }
    /**
     * 获得20个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get20Uuid(){

        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3];
    }
    /**
     * 获得24个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get24Uuid(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[4];
    }
    /**
     * 获得32个长度的十六进制的UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String get32Uuid(){
        UUID id=UUID.randomUUID();
        String[] idd=id.toString().split("-");
        return idd[0]+idd[1]+idd[2]+idd[3]+idd[4];
    }

    /**
     * 转成字节
     * @return 字节序列
     */
    @SuppressWarnings("unused")
    private static byte[] toBytes() {
        UUID uuid = UUID.randomUUID();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        byte[] buffer = new byte[16];
        for (int i = 0; i < AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_8.getEnumsIntegerValue(); i++) {
            buffer[i] = (byte) ((msb >>> 8 * (7 - i)) & 0xFF);
            buffer[i + 8] = (byte) ((lsb >>> 8 * (7 - i)) & 0xFF);
        }
        return buffer;
    }

    /**
     * 获取24位UUID
     * @return UUID
     */
    @SuppressWarnings("unused")
    public static String getUuid() {
        return Base64.toBase64String(toBytes());
    }

    /**
     * 获取指定位数的UUID
     * @param bits 指定位数
     * @return 获得指定位数的uuid
     */
    @SuppressWarnings("unused")
    public static String getUuidBits(int bits) {
        UUID uuid = UUID.randomUUID();
        long msb = uuid.getMostSignificantBits();
        long lsb = uuid.getLeastSignificantBits();
        char[] out = new char[24];
        int tmp;
        int idx = 0;
        // 循环写法
        int bit = 0;
        int bt1 = 8;
        int bt2 = 8;
        int mask;
        int offsetm;
        int offset;
        for(; bit < AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_16.getEnumsIntegerValue(); bit += AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_3.getEnumsIntegerValue(), idx += AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_4.getEnumsIntegerValue()) {
            offsetm = 64 - (bit + 3) * 8;
            tmp = 0;
            if(bt1 > 3) {
                mask = (1 << 8 * 3) - 1;
            } else if(bt1 >= 0) {
                mask = (1 << 8 * bt1) - 1;
                bt2 -= 3 - bt1;
            } else {
                mask = (1 << 8 * (Math.min(bt2, 3))) - 1;
                bt2 -= 3;
            }
            if(bt1 > 0) {
                bt1 -= 3;
                tmp = (int) ((offsetm < 0) ? msb : (msb >>> offsetm) & mask);
                if(bt1 < 0) {
                    tmp <<= Math.abs(offsetm);
                    mask = (1 << 8 * Math.abs(bt1)) - 1;
                }
            }
            if(offsetm < 0) {
                offset = 64 + offsetm;
                tmp |= ((offset < 0) ? lsb : (lsb >>> offset)) & mask;
            }
            if(bit == 15) {
                out[idx + 3] = ALPHABET[64];
                out[idx + 2] = ALPHABET[64];
                tmp <<= 4;
            } else {
                out[idx + 3] = ALPHABET[tmp & 0x3f];
                tmp >>= 6;
                out[idx + 2] = ALPHABET[tmp & 0x3f];
                tmp >>= 6;
            }
            out[idx + 1] = ALPHABET[tmp & 0x3f];
            tmp >>= 6;
            out[idx] = ALPHABET[tmp & 0x3f];
        }
        return new String(out, 0, bits);
    }

    @SuppressWarnings("unused")
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
    @SuppressWarnings("unused")
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

    @SuppressWarnings("unused")
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

    /**
     * 获取系统deviceId
     * @return 返回一个deviceId
     */
    public static String getDeviceId(){
        // 首先看看系统表中有没有deviceId
        String deviceId = (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID.getEnumsStringValue());
        // 如果没有的话
        if (Objects.isNull(deviceId) || deviceId.isEmpty() || deviceId.isBlank()) {
            // 就生成
            deviceId = generateDeviceId();
            // 生成之后直接存在系统配置中，
            AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID.getEnumsStringValue(),deviceId);
        }
        // 如果有的话直接返回
        return deviceId;
    }

    /**
     * 生成设备id的方法
     * 后续升级
     * @return 返回一个设备id
     */
    private static String generateDeviceId() {
        log.info("执行了生成deviceId");
        return get24Uuid();
    }

}
