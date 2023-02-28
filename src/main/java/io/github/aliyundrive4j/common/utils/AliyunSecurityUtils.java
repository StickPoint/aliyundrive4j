package io.github.aliyundrive4j.common.utils;

import lombok.extern.slf4j.Slf4j;
import java.io.Serial;
import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.InvalidAlgorithmParameterException;
import java.security.InvalidKeyException;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.Signature;
import java.security.SignatureException;
import java.security.spec.ECGenParameterSpec;

/**
 * @BelongsProject: aliyundrive4j
 * @BelongsPackage: io.github.aliyundrive4j.common.utils
 * @Author: fntp
 * @CreateTime: 2023-02-26  17:27
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
public class AliyunSecurityUtils implements Serializable {

    @Serial
    private static final long serialVersionUID = -5961156192554113072L;

    private static final String KEY_PAIR_NAME = "EC";

    private static final String ALGORITHM_NAME = "secp256k1";

    private static final String SIGNATURE_NAME = "SHA256withECDSA";

    private static Signature signature = null;

    private static KeyPair keyPair;

    private AliyunSecurityUtils(){
        throw new IllegalStateException("Utility class");
    }

    static {
        try {
            KeyPairGenerator kpg = KeyPairGenerator.getInstance(KEY_PAIR_NAME);
            kpg.initialize(new ECGenParameterSpec(ALGORITHM_NAME));
            keyPair = kpg.generateKeyPair();
            signature = Signature.getInstance(SIGNATURE_NAME);
            signature.initSign(keyPair.getPrivate());
        } catch (InvalidAlgorithmParameterException | NoSuchAlgorithmException | InvalidKeyException e) {
            e.printStackTrace();
        }
    }

    @SuppressWarnings("unused")
    public static String generatePublicKeyHex() {
        return "04" + bytesToHex(keyPair.getPublic().getEncoded());
    }

    @SuppressWarnings("unused")
    public static String generateSignatureHex(byte[] signatureBytes) {
        return bytesToHex(signatureBytes) + "01";
    }

    @SuppressWarnings("unused")
    private static String repack(String appId, String deviceId, String userId, int nonce) {
        return appId + ":" + deviceId + ":" + userId + ":" + nonce;
    }

    @SuppressWarnings("unused")
    private static byte[] sign(String appId, String deviceId, String userId, int nonce){
        // 第一步执行数据重组
        try {
            String repackResult = repack(appId, deviceId, userId, nonce);
            byte[] repackResultBytes = repackResult.getBytes(StandardCharsets.UTF_8);
            signature.update(repackResultBytes);
            return signature.sign();
        } catch (SignatureException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

    /**
     * 将字节数组转为十六进制数据
     * @param bytes 传入一个字节数组
     * @return 返回一个十六进制数据
     */
    private static String bytesToHex(byte[] bytes) {
        StringBuilder sb = new StringBuilder();
        for (byte b : bytes) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }
}
