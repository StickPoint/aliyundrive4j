package io.github.aliyundrive4j.common.utils;
import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.Security;
import java.util.UUID;

import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import org.bouncycastle.asn1.x9.X9ECParameters;
import org.bouncycastle.crypto.digests.SHA256Digest;
import org.bouncycastle.crypto.params.ECDomainParameters;
import org.bouncycastle.crypto.params.ECPrivateKeyParameters;
import org.bouncycastle.crypto.params.ParametersWithRandom;
import org.bouncycastle.crypto.signers.ECDSASigner;
import org.bouncycastle.crypto.signers.HMacDSAKCalculator;
import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.math.ec.ECPoint;

import lombok.extern.slf4j.Slf4j;

/**
 * 阿里云盘2月份大动作之签名验证
 * 之前我专门联系过阿里云盘，本想申请官方API调用，省点事情，
 * 然而阿里云盘言行不一致，意思是我还不够资格使用阿里云盘内测接口
 * 因此，我根据浏览器进行了一次web逆向，简单记录一下这个逆向成果
 * 有一说一，阿里云盘确实吊打百度云盘，这点没得洗
 * 细节方法我都加上了注释，不重要的一笔带过了哈
 * @author puye(0303)
 *
 */
@Slf4j
public class AliyunSecurityUtils {

    private static final String ALGORITHM_NAME = "secp256k1";

    private static final X9ECParameters CURVE_PARAMS = org.bouncycastle.asn1.sec.SECNamedCurves.getByName(ALGORITHM_NAME);

    private static final ECDomainParameters CURVE = new ECDomainParameters(CURVE_PARAMS.getCurve(),
            CURVE_PARAMS.getG(), CURVE_PARAMS.getN(), CURVE_PARAMS.getH());

    private static final SecureRandom SECURE_RANDOM = new SecureRandom();

    private static final ECPrivateKeyParameters PRIVATE_KEY_PARAM;

    private static final String ALGORITHM_NAME_SHA_256 = "SHA-256";

    private static final long CURVE_DIVIDE = 2L;

    static {
        Security.addProvider(new BouncyCastleProvider());
        BigInteger privateKeyNumber = new BigInteger(256, SECURE_RANDOM);
        PRIVATE_KEY_PARAM = new ECPrivateKeyParameters(privateKeyNumber, CURVE);
    }

    /**
     * 工具类禁止实例化
     */
    private AliyunSecurityUtils(){
        throw new IllegalStateException("Utility class");
    }

    /**
     * 生成signature值
     * @param appId 应用id
     * @param userId 用户id
     * 真正对外暴露的方法
     * @return 返回signature值
     */
    public static String getSignatureHexStr(String appId, String deviceId, String userId) {
        SecureRandom secureRandom = new SecureRandom();
        int nonce = secureRandom.nextInt();
        return sign(appId, deviceId, userId, nonce);
    }

    /**
     * 获取公钥
     * 真正对外暴露的方法
     * @return 返回一个公钥
     */
    public static String getPublicKeyHexStr() {
        return getPublicKey();
    }

    /**
     * 生成公钥的底层方法
     * @return 生成一个公钥字符串，并将字符串转为十六进制数据返回
     */
    private static String getPublicKey() {
        ECPoint ecPoint = CURVE.getG().multiply(AliyunSecurityUtils.PRIVATE_KEY_PARAM.getD()).normalize();
        byte[] publicKeyBytes = ecPoint.getEncoded(false);
        return bytesToHex(publicKeyBytes);
    }

    /**
     * 签名方法
     * @param appId 传入一个应用id 如果你只是为了测试，那么你可以从浏览器中获得；还可以通过阿里云接口获得数据。
     * @param deviceId 设备id，这里将设备id单独抽成了一个属性 防止后续设备id的生成规则变动
     * @param userId  传入一个用户id
     * @param nonce 传入一个随机的临时数据
     * @return 返回一个签名数据
     */
    private static String sign(String appId, String deviceId, String userId, int nonce) {
        // 参数组装
        String message = repack(appId, deviceId, userId, nonce);
        // 将要签名的数据转化为UTF-8编码的字节数组
        byte[] messageBytes = message.getBytes(StandardCharsets.UTF_8);
        // 对数据的字节数组进行SHA256哈希运算，得到哈希值
        byte[] hash = sha256(messageBytes);
        // 使用Bouncy Castle库中的ECDSASigner类和SHA256Digest类进行签名。
        // 其中，ECDSASigner类是ECDSA签名算法的实现类，而SHA256Digest类是SHA-256哈希算法的实现类。
        // 此外，使用ParametersWithRandom类将私钥和随机数组成参数传入签名算法的init方法中，以便对数据进行签名
        ECDSASigner signer = new ECDSASigner(new HMacDSAKCalculator(new SHA256Digest()));
        ParametersWithRandom param = new ParametersWithRandom(AliyunSecurityUtils.PRIVATE_KEY_PARAM, SECURE_RANDOM);
        signer.init(true, param);
        BigInteger[] components = signer.generateSignature(hash);
        // 从签名后的结果中提取出r和s两个参数，并根据s的值判断其所在的曲线的左侧或右侧。
        // 如果在右侧，则将s替换为N-s，其中N为曲线参数中的N，然后将recId设置为1；否则recId设置为0：
        BigInteger r = components[0];
        BigInteger s = components[1];
        BigInteger recId = BigInteger.ZERO;
        if (s.compareTo(CURVE_PARAMS.getN().divide(BigInteger.valueOf(CURVE_DIVIDE))) > 0) {
            s = CURVE_PARAMS.getN().subtract(s);
            recId = BigInteger.ONE;
        }
        // 将recId和27相加得到ECDSA签名中的v值，并将其转化为字节数组。
        int headerByte = recId.intValue() + 27;
        byte[] signatureBytes = new byte[65];
        signatureBytes[0] = (byte) headerByte;
        // 接下来，将r和s的字节数组复制到signatureBytes字节数组中。
        System.arraycopy(getBytes(r), 0, signatureBytes, 1, 32);
        System.arraycopy(getBytes(s), 0, signatureBytes, 33, 32);
        // 最后，为了将签名标记为压缩格式，将字节数组的最后一位设置为0x01
        signatureBytes[64] = 0x01;
        // 返回签名结果
        return bytesToHex(signatureBytes);
    }

    private static String repack(String appId, String deviceId, String userId, int nonce) {
        return String.format("%s:%s:%s:%d", appId, deviceId, userId, nonce);
    }

    private static byte[] sha256(byte[] data) {
        try {
            MessageDigest digest = MessageDigest.getInstance(ALGORITHM_NAME_SHA_256);
            return digest.digest(data);
        } catch (NoSuchAlgorithmException e) {
            log.error(e.getCause().getMessage());
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_ALGORITHM_SECURITY);
        }
    }

    private static byte[] getBytes(BigInteger b) {
        byte[] bytes = b.toByteArray();
        if (bytes.length == AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_33.getEnumsIntegerValue() && bytes[0] == 0) {
            byte[] correctedBytes = new byte[32];
            System.arraycopy(bytes, 1, correctedBytes, 0, 32);
            return correctedBytes;
        } else if (bytes.length != AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_32.getEnumsIntegerValue()) {
            byte[] correctedBytes = new byte[32];
            if (bytes.length > AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_NUMBER_32.getEnumsIntegerValue()) {
                System.arraycopy(bytes, bytes.length - 32, correctedBytes, 0, 32);
            } else {
                System.arraycopy(bytes, 0, correctedBytes, 32 - bytes.length, bytes.length);
            }
            return correctedBytes;
        }
        return bytes;
    }

    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

}