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

    public LoginQrcodeInfoEntity(String timestamp, String ckCode, String codeContent, Integer resultCode) {
        this.timestamp = timestamp;
        this.ckCode = ckCode;
        this.codeContent = codeContent;
        this.resultCode = resultCode;
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

    @Override
    public String toString() {
        return "LoginQrcodeInfoEntity{" +
                "timestamp='" + timestamp + '\'' +
                ", ckCode='" + ckCode + '\'' +
                ", codeContent='" + codeContent + '\'' +
                ", resultCode=" + resultCode +
                '}';
    }
}
