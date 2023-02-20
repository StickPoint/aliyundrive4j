package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
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
public class LoginResultEntity implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -243789119443235909L;

    @SerializedName("default_sbox_drive_id")
    private String defaultsBoxDriveId;

    private String role;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("pin_setup")
    private boolean pinSetup;

    @SerializedName("is_first_login")
    private boolean isFirstLogin;

    @SerializedName("need_link")
    private boolean needLink;

    @SerializedName("login_type")
    private String loginType;

    @SerializedName("nick_name")
    private String nickName;

    @SerializedName("user_name")
    private String userName;


    @SerializedName("need_rp_verify")
    private boolean needRpVerify;

    private String avatar;
    /**
     * 重要的字段，主要就是为了拿这个数据
     */
    @SerializedName("access_token")
    private String accessToken;

    @SerializedName("user_id")
    private String userId;

    @SerializedName("default_drive_id")
    private String defaultDriveId;

    @SerializedName("exist_link")
    private String[] existLink;
    /**
     * 过期时间时长 不知道是多久 7200不晓得是秒还是分钟；
     * 应该是分钟 如果是7200分钟，那就是5天免登录；
     * 如果是秒，那就是7200秒 120分钟 2小时免登录；
     */
    @SerializedName("expires_in")
    private long expiresIn;
    /**
     * 过期时间 2022-12-16T13:12:37Z
     */
    @SerializedName("expire_time")
    private String expireTime;
    /**
     * 请求id
     */
    @SerializedName("request_id")
    private String requestId;
    /**
     * 暂时不知道什么字段含义，但是根据阿里云One-Data数据治理来看应该是数据监控字段
     */
    @SerializedName("data_pin_setup")
    private boolean dataPinSetup;
    /**
     * 一般默认是空字符串
     */
    private String state;
    /**
     * token类型，一般返回的是token-Header信息，比如：Bearer 这种一般是指使用的是JWT-Token校验
     */
    @SerializedName("token_type")
    private String tokenType;

    @SerializedName("data_pin_saved")
    private String dataPinSaved;
    /**
     * 刷新token所使用的token
     */
    @SerializedName("refresh_token")
    private String refreshToken;

    private String status;

}
