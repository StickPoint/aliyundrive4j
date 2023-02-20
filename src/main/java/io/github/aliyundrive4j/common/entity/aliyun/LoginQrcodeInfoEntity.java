package io.github.aliyundrive4j.common.entity.aliyun;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;


/**
 * description: LoginQrcodeInfoEntity
 *
 * @ClassName : LoginQrcodeInfoEntity
 * @Date 2022/12/16 14:39
 * @Author fntp
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class LoginQrcodeInfoEntity implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -2974478427538950220L;
    /**
     * 时间戳数据
     */
    private String timestamp;
    /**
     * ck验证码
     */
    private String ckCode;
    /**
     * 二维码内容
     */
    private String codeContent;
    /**
     * 结果代码 一般成功是100，一般哈，并不代表所有情况
     */
    private Integer resultCode;

    private String loginSucResultAction;

    private String st;

    private String qrCodeStatus;

    private String loginType;

    private String bizExt;

    private String loginScene;

    private String appEntrance;

    private boolean smartlock;

    private String loginResult;

}
