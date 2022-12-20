package io.github.aliyundrive4j.common.entity.aliyun;

import java.io.Serial;
import java.io.Serializable;

/**
 * description: LoginQrcodeInfoEntity
 *
 * @ClassName : LoginQrcodeInfoEntity
 * @Date 2022/12/16 14:39
 * @Author fntp
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
public class LoginQrcodeInfoEntity implements Serializable {

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

    public LoginQrcodeInfoEntity(String timestamp, String ckCode, String codeContent, Integer resultCode, String loginSucResultAction, String st, String qrCodeStatus, String loginType, String bizExt, String loginScene, String appEntrance, boolean smartlock,String loginResult) {
        this.timestamp = timestamp;
        this.ckCode = ckCode;
        this.codeContent = codeContent;
        this.resultCode = resultCode;
        this.loginSucResultAction = loginSucResultAction;
        this.st = st;
        this.qrCodeStatus = qrCodeStatus;
        this.loginType = loginType;
        this.bizExt = bizExt;
        this.loginScene = loginScene;
        this.appEntrance = appEntrance;
        this.smartlock = smartlock;
        this.loginResult = loginResult;
    }

    public String getLoginSucResultAction() {
        return loginSucResultAction;
    }

    public void setLoginSucResultAction(String loginSucResultAction) {
        this.loginSucResultAction = loginSucResultAction;
    }

    public String getSt() {
        return st;
    }

    public void setSt(String st) {
        this.st = st;
    }

    public String getQrCodeStatus() {
        return qrCodeStatus;
    }

    public void setQrCodeStatus(String qrCodeStatus) {
        this.qrCodeStatus = qrCodeStatus;
    }

    public String getLoginType() {
        return loginType;
    }

    public void setLoginType(String loginType) {
        this.loginType = loginType;
    }

    public String getBizExt() {
        return bizExt;
    }

    public void setBizExt(String bizExt) {
        this.bizExt = bizExt;
    }

    public String getLoginScene() {
        return loginScene;
    }

    public void setLoginScene(String loginScene) {
        this.loginScene = loginScene;
    }

    public String getAppEntrance() {
        return appEntrance;
    }

    public void setAppEntrance(String appEntrance) {
        this.appEntrance = appEntrance;
    }

    public boolean getSmartlock() {
        return smartlock;
    }

    public void setSmartlock(boolean smartlock) {
        this.smartlock = smartlock;
    }

    public LoginQrcodeInfoEntity() {
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public String getCkCode() {
        return ckCode;
    }

    public void setCkCode(String ckCode) {
        this.ckCode = ckCode;
    }

    public String getCodeContent() {
        return codeContent;
    }

    public void setCodeContent(String codeContent) {
        this.codeContent = codeContent;
    }

    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public boolean isSmartlock() {
        return smartlock;
    }

    public String getLoginResult() {
        return loginResult;
    }

    public void setLoginResult(String loginResult) {
        this.loginResult = loginResult;
    }

    @Override
    public String toString() {
        return "LoginQrcodeInfoEntity{" +
                "timestamp='" + timestamp + '\'' +
                ", ckCode='" + ckCode + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", resultCode=" + resultCode +
                ", loginSucResultAction='" + loginSucResultAction + '\'' +
                ", st='" + st + '\'' +
                ", qrCodeStatus='" + qrCodeStatus + '\'' +
                ", loginType='" + loginType + '\'' +
                ", bizExt='" + bizExt + '\'' +
                ", loginScene='" + loginScene + '\'' +
                ", appEntrance='" + appEntrance + '\'' +
                ", smartlock=" + smartlock +
                ", loginResult='" + loginResult + '\'' +
                '}';
    }
}
