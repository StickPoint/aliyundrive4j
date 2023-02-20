package io.github.aliyundrive4j.common.entity.aliyun;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * description: PdsLoginResult
 *
 * @ClassName : PdsLoginResult
 * @Date 2022/12/19 16:14
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class PdsLoginResult implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -243789119443235909L;

    private String role;

    private boolean isFirstLogin;

    private boolean needLink;

    private String loginType;

    private String nickName;

    private String userName;

    private boolean needRpVerify;

    private String avatar;

    private String accessToken;

    private String userId;

    private String defaultDriveId;

    private String[] existLink;
    /**
     * 过期时间时长 不知道是多久 7200不晓得是秒还是分钟；
     * 应该是分钟 如果是7200分钟，那就是5天免登录；
     * 如果是秒，那就是7200秒 120分钟 2小时免登录；
     */
    private long expiresIn;
    /**
     * 过期时间 2022-12-16T13:12:37Z
     */
    private String expireTime;
    /**
     * 请求id
     */
    private String requestId;
    /**
     * 暂时不知道什么字段含义，但是根据阿里云One-Data数据治理来看应该是数据监控字段
     */
    private boolean dataPinSetup;
    /**
     * 一般默认是空字符串
     */
    private String state;
    /**
     * token类型，一般返回的是token-Header信息，比如：Bearer 这种一般是指使用的是JWT-Token校验
     */
    private String tokenType;

    private String dataPinSaved;

    /**
     * 刷新token所使用的token
     */
    private String refreshToken;

    private String status;

}
