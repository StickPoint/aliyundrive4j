package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;
import java.util.Arrays;

/**
 * description: PdsLoginResult
 *
 * @ClassName : PdsLoginResult
 * @Date 2022/12/19 16:14
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
public class PdsLoginResult implements Serializable {

    @Serial
    private static final long serialVersionUID = -243789119443235909L;

    @SerializedName("role")
    private String role;

    private boolean isFirstLogin;

    private boolean needLink;

    private String loginType;

    private String nickName;

    private boolean needRpVerify;

    private String avatar;
    /**
     * 重要的字段，主要就是为了拿这个数据
     */
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

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public boolean isFirstLogin() {
        return isFirstLogin;
    }

    public void setFirstLogin(boolean firstLogin) {
        isFirstLogin = firstLogin;
    }

    public boolean isNeedLink() {
        return needLink;
    }

    public void setNeedLink(boolean needLink) {
        this.needLink = needLink;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public boolean isNeedRpVerify() {
        return needRpVerify;
    }

    public void setNeedRpVerify(boolean needRpVerify) {
        this.needRpVerify = needRpVerify;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getDefaultDriveId() {
        return defaultDriveId;
    }

    public void setDefaultDriveId(String defaultDriveId) {
        this.defaultDriveId = defaultDriveId;
    }

    public String[] getExistLink() {
        return existLink;
    }

    public void setExistLink(String[] existLink) {
        this.existLink = existLink;
    }

    public long getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(long expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public boolean isDataPinSetup() {
        return dataPinSetup;
    }

    public void setDataPinSetup(boolean dataPinSetup) {
        this.dataPinSetup = dataPinSetup;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }

    public String getDataPinSaved() {
        return dataPinSaved;
    }

    public void setDataPinSaved(String dataPinSaved) {
        this.dataPinSaved = dataPinSaved;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PdsLoginResult{" +
                "role='" + role + '\'' +
                ", isFirstLogin=" + isFirstLogin +
                ", needLink=" + needLink +
                ", loginType='" + loginType + '\'' +
                ", nickName='" + nickName + '\'' +
                ", needRpVerify=" + needRpVerify +
                ", avatar='" + avatar + '\'' +
                ", accessToken='" + accessToken + '\'' +
                ", userId='" + userId + '\'' +
                ", defaultDriveId='" + defaultDriveId + '\'' +
                ", existLink=" + Arrays.toString(existLink) +
                ", expiresIn=" + expiresIn +
                ", expireTime='" + expireTime + '\'' +
                ", requestId='" + requestId + '\'' +
                ", dataPinSetup=" + dataPinSetup +
                ", state='" + state + '\'' +
                ", tokenType='" + tokenType + '\'' +
                ", dataPinSaved='" + dataPinSaved + '\'' +
                ", refreshToken='" + refreshToken + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    public PdsLoginResult() {
    }

    public PdsLoginResult(String role, boolean isFirstLogin, boolean needLink, String loginType, String nickName, boolean needRpVerify, String avatar, String accessToken, String userId, String defaultDriveId, String[] existLink, long expiresIn, String expireTime, String requestId, boolean dataPinSetup, String state, String tokenType, String dataPinSaved, String refreshToken, String status) {
        this.role = role;
        this.isFirstLogin = isFirstLogin;
        this.needLink = needLink;
        this.loginType = loginType;
        this.nickName = nickName;
        this.needRpVerify = needRpVerify;
        this.avatar = avatar;
        this.accessToken = accessToken;
        this.userId = userId;
        this.defaultDriveId = defaultDriveId;
        this.existLink = existLink;
        this.expiresIn = expiresIn;
        this.expireTime = expireTime;
        this.requestId = requestId;
        this.dataPinSetup = dataPinSetup;
        this.state = state;
        this.tokenType = tokenType;
        this.dataPinSaved = dataPinSaved;
        this.refreshToken = refreshToken;
        this.status = status;
    }
}
