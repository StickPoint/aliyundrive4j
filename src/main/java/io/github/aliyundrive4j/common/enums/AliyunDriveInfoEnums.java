package io.github.aliyundrive4j.common.enums;

/**
 * @Author SunChengXin_0303
 * @ClassName AliyunDriveEnums.Class
 * @PackageName io.github.aliyundrive4j.common.enums
 * @jdk_version 17
 * @Date 2022年12月15日 13:29
 * @since 1.5
 */
public enum AliyunDriveInfoEnums {
    /**
     * 阿里云盘-HTTP请求状态码-ok-200
     */
    ALIYUN_DRIVE_HTTP_STATUS_OK(200),
    /**
     * 阿里云盘-系统配置-key-登出
     */
    ALIYUN_DRIVE_PROPERTIES_KEY_SIGN_OUT("sign_out"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-content
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_CONTENT("content"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-t
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_T("t"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-codeContent
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_CODE_CONTENT("codeContent"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-ck
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_CK("ck"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-resultCode
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_RESULT_CODE("resultCode"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-qrCodeStatus
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_QR_CODE_STATUS("qrCodeStatus"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-data
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_DATA("data"),
    /**
     * 阿里云盘-扫码登录-二维码状态-正常
     */
    ALIYUN_DRIVE_LOGIN_QR_CODE_STATUS_NEW("NEW"),
    /**
     * 阿里云 扫码登录-二维码状态-已过期
     */
    ALIYUN_DRIVE_LOGIN_QR_CODE_STATUS_EXPIRED("qrCodeStatus"),
    /**
     * 阿里云盘-HTTP请求-请求的content-type内容-json
     */
    ALIYUN_DRIVE_HTTP_REQUEST_CONTENT_TYPE_JSON("json");
    /**
     * 字符串类型数据
     */
    private final String enumsStringValue;
    /**
     * 数值类型数据
     */
    private final Integer enumsIntegerValue;
    /**
     * 获得枚举字符串类型数据
     */
    public String getEnumsStringValue () {
        return enumsStringValue;
    }

    /**
     *  获得枚举数值类型数据
     * @return 返回枚举字符串内
     */
    public Integer getEnumsIntegerValue () {
        return enumsIntegerValue;
    }

    /**
     * 设置枚举字符串类型内容
     * @param enumsStringValue 传入枚举字符串数据
     */
    AliyunDriveInfoEnums(String enumsStringValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = null;
    }

    /**
     * 设置枚举数值类型数据
     * @param enumsIntegerValue 传入枚举数值数据
     */
    AliyunDriveInfoEnums(Integer enumsIntegerValue) {
        this.enumsIntegerValue = enumsIntegerValue;
        this.enumsStringValue = null;
    }

    /**
     * 构造方式创建枚举对象
     * @param enumsStringValue 传入枚举字符串数据
     * @param enumsIntegerValue 传入枚举数值数据
     */
    AliyunDriveInfoEnums(String enumsStringValue, Integer enumsIntegerValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = enumsIntegerValue;
    }

}
