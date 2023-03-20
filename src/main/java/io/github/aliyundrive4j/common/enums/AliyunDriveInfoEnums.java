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
     * HTTP状态码 201 创建成功
     */
    ALIYUN_DRIVE_HTTP_STATUS_CREATED_OK(201),
    /**
     * 阿里云盘-通用数字-33
     */
    ALIYUN_DRIVE_COMMON_NUMBER_33(33),
    /**
     * 阿里云盘-通用数字-10000
     */
    ALIYUN_DRIVE_COMMON_NUMBER_10000(10000),
    /**
     * 阿里云盘-通用数字-32
     */
    ALIYUN_DRIVE_COMMON_NUMBER_32(32),
    /**
     * 阿里云盘-通用数字-6
     */
    ALIYUN_DRIVE_COMMON_NUMBER_6(6),
    /**
     * 阿里云盘-通用数字-3
     */
    ALIYUN_DRIVE_COMMON_NUMBER_3(3),
    /**
     * 阿里云盘-通用数字-4
     */
    ALIYUN_DRIVE_COMMON_NUMBER_4(4),
    /**
     * 阿里云盘-通用数字-16
     */
    ALIYUN_DRIVE_COMMON_NUMBER_16(16),
    /**
     * 阿里云盘-通用数字-8
     */
    ALIYUN_DRIVE_COMMON_NUMBER_8(8),
    /**
     * HTTP状态码 202 创建成功
     */
    ALIYUN_DRIVE_HTTP_STATUS_DELETED_OK(202),
    /**
     * 阿里云盘-系统配置-key-登出
     */
    ALIYUN_DRIVE_PROPERTIES_KEY_SIGN_OUT("sign_out"),
    /**
     * 阿里云盘-系统配置-key-系统容量查询
     */
    ALIYUN_DRIVE_PROPERTIES_KEY_USER_CAPACITY("user_capacity_details"),
    /**
     * 阿里云盘-系统配置-key-用户已使用容量查询
     */
    ALIYUN_DRIVE_PROPERTIES_KEY_USER_ALREADY_IN_USED_CAPACITY("user_already_in_used_capacity"),
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
     * 阿里云盘-系统配置-key-signature
     */
    ALIYUN_DRIVE_INFO_ENUMS_SIGNATURE("aliyundrive4j-signature"),
    /**
     * 阿里云盘-系统配置-key-deviceId
     */
    ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID("aliyundrive4j-deviceId"),
    /**
     * 阿里云盘-系统配置-key-appId
     */
    ALIYUN_DRIVE_INFO_ENUMS_APP_ID("aliyundrive4j-appId"),
    /**
     * 阿里云盘-系统配置-key-userId
     */
    ALIYUN_DRIVE_INFO_ENUMS_USER_ID("aliyundrive4j-userId"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-ck
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_CK("ck"),
    /**
     * set-cookie
     */
    ALIYUN_DRIVE_COMMON_STR_SET_COOKIE("set-cookie"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-resultCode
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_RESULT_CODE("resultCode"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-qrCodeStatus
     */

    ALIYUN_DRIVE_RESP_JSON_KEY_QR_CODE_STATUS("qrCodeStatus"),
    /**
     * 阿里云盘-参数请求类型-json
     */
    ALIYUN_DRIVE_REQUEST_TYPE_JSON("json"),
    /**
     * 阿里云盘-系统响应-JSON对象-Key-data
     */
    ALIYUN_DRIVE_RESP_JSON_KEY_DATA("data"),
    /**
     * 阿里云盘-请求头代理-user-agent
     */
    ALIYUN_DRIVE_HTTP_HEADER_VALUE_USER_AGENT("Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/110.0.0.0 Safari/537.36 Edg/110.0.1587.41"),
    /**
     * 阿里云盘-请求头-key-authorization
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_AUTHORIZATION("authorization"),
    /**
     * 阿里云盘-请求头-key-origin
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_ORIGIN ( "origin"),
    /**
     * 阿里云盘-请求头-key-User-Agent
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_USER_AGENT ( "User-Agent"),
    /**
     * 阿里云盘-请求头-key-Content-Type
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_CONTENT_TYPE ( "Content-Type"),
    /**
     * 阿里云盘-请求头-key-referer
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_REFER ( "referer"),
    /**
     * 阿里云盘-请求头-key-accept
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_ACCEPT ( "accept"),
    /**
     * 阿里云盘-请求头-key-cookie
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_COOKIE ( "cookie"),
    /**
     * 阿里云盘-请求头-key-json
     */
    ALIYUN_DRIVE_OK_HTTPS_BODY_TYPE_JSON ( "json"),
    /**
     * 阿里云盘-请求头-key-x-device-id
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_X_DEVICE_ID ( "x-device-id"),
    /**
     * 阿里云盘-请求头-key-x-signature
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_SIGNATURE ( "x-signature"),
    /**
     * 阿里云盘-请求头-key-x-canary
     */
    ALIYUN_DRIVE_HTTP_HEADER_KEY_X_CANARY ( "x-canary"),
    /**
     * 阿里云盘-请求头-key-deviceName
     */
    ALIYUN_DRIVE_REQUEST_KEY_DEVICE_NAME ( "deviceName"),
    /**
     * 阿里云盘-请求头-value-deviceName
     */
    ALIYUN_DRIVE_REQUEST_VALUE_DEVICE_NAME ( "Edge浏览器"),
    /**
     * 阿里云盘-请求头-key-modelName
     */
    ALIYUN_DRIVE_REQUEST_KEY_MODEL_NAME ( "modelName"),
    /**
     * 阿里云盘-请求头-value-modelName
     */
    ALIYUN_DRIVE_REQUEST_VALUE_MODEL_NAME ( "Windows网页版"),
    /**
     * 阿里云盘-请求头-key-pubKey
     */
    ALIYUN_DRIVE_REQUEST_KEY_PUB_KEY ("pubKey"),
    /**
     * 阿里云盘-Http请求的header参数值：json
     */
    ALIYUN_DRIVE_REQUEST_HEADER_VALUE_JSON("application/json; charset(UTF-8"),
    /**
     * 阿里云盘-Http请求的header参数值：x-canary
     */
    ALIYUN_DRIVE_REQUEST_HEADER_VALUE_X_CANARY("client=web,app=adrive,version=v3.17.0"),
    /**
     * 阿里云盘-扫码登录-二维码状态-正常
     */

    ALIYUN_DRIVE_LOGIN_QR_CODE_STATUS_NEW("NEW"),
    /**
     * 阿里云登录结果
     */
    ALIYUN_DRIVE_LOGIN_PDS_LOGIN_RESULT("pds_login_result"),
    /**
     * 阿里云 扫码登录-二维码状态-已过期
     */

    ALIYUN_DRIVE_LOGIN_QR_CODE_STATUS_EXPIRED("qrCodeStatus"),
    /**
     * 阿里云盘系统配置-token刷新-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_REFRESH_KEY("token_refresh"),
    /**
     * 阿里云盘系统配置-token-get-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_GET_KEY("token_get"),
    /**
     * 阿里云盘系统配置-token-login-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_LOGIN_KEY("token_login"),
    /**
     * 阿里云盘系统配置-drive-list-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_LIST_KEY("drive_list"),
    /**
     * 阿里云盘系统配置-drive-default-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_DEFAULT_KEY("drive_default"),
    /**
     * 阿里云盘系统配置-drive-get-by-id-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_GET_BY_ID_KEY("drive_getDriveInfoByDriveId"),
    /**
     * 阿里云盘系统配置-folder-create-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_CREATE_KEY("folder_create"),
    /**
     * 阿里云盘系统配置-folder-delete-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_DELETED_KEY("folder_delete"),
    /**
     * 阿里云盘系统配置-folder-update-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_UPDATE_KEY("folder_update"),
    /**
     * 阿里云盘系统配置-folder-delete-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_GET_KEY("folder_get"),
    /**
     * 阿里云盘系统配置-file-getInfoById-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FILE_GET_BY_ID_KEY("file_get_info_by_id"),
    /**
     * 阿里云盘系统配置-file-updateInfoById-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FILE_LIST_GET_KEY("file_list_get"),
    /**
     * 阿里云盘系统配置-file-updateInfoById-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FILE_UPDATE_BY_ID_KEY("file_update_info_by_id"),
    /**
     * 阿里云盘系统配置-file-updateInfoById-key
     */
    ALIYUN_DRIVE_SYS_PROPERTY_FILE_DOWNLOAD_INFO_GET_KEY("file_download_info_get"),
    /**
     * 创建新的Session
     */
    ALIYUN_DRIVE_SYS_PROPERTY_CREATE_SESSION_KEY("create_new_session"),
    /**
     * 重新创建session
     */
    ALIYUN_DRIVE_SYS_PROPERTY_RENEW_SESSION_KEY("renew_new_session"),
    /**
     * 阿里云盘主机域名
     */
    ALIYUN_DRIVE_SYS_PROPERTY_HOST_KEY("host"),
    /**
     * 阿里云盘一次性随机数据
     */
    ALIYUN_DRIVE_SYS_PROPERTY_NONCE_KEY("nonce"),
    /**
     * 阿里云Http请求日志信息-第001条日志模板
     */
    ALIYUN_DRIVE_INFO_TEMPLATE_001("初始化加载HttpIUtils工具类对象"),
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
    @SuppressWarnings("unused")
    AliyunDriveInfoEnums(String enumsStringValue, Integer enumsIntegerValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = enumsIntegerValue;
    }

}
