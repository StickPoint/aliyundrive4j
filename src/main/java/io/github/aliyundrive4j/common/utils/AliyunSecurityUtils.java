package io.github.aliyundrive4j.common.utils;

import java.io.Serial;
import java.io.Serializable;
import java.security.SecureRandom;
import java.security.spec.ECPrivateKeySpec;
import java.util.Random;
import java.util.stream.IntStream;

/**
 * @BelongsProject: aliyundrive4j
 * @BelongsPackage: io.github.aliyundrive4j.common.utils
 * @Author: fntp
 * @CreateTime: 2023-02-26  17:27
 * @Description: TODO
 * @Version: 1.0
 */
public class AliyunSecurityUtils implements Serializable {

    @Serial
    private static final long serialVersionUID = -5961156192554113072L;

    private AliyunSecurityUtils(){
        throw new IllegalStateException("Utility class");
    }


    /**
     * 生成阿里云盘本地公钥
     * @return 返回一个十六进制数据的字符串
     */
    public String generateAliyunDriveLocalPublicKey() {
        Random random = new SecureRandom();
        return null;
    }
}
