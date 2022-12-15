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

    ALIYUN_DRIVE_HTTP_STATUS_OK(200),

    ALIYUN_DRIVE_PROPERTIES_KEY_SIGN_OUT("sign_out"),

    ALIYUN_DRIVE_HTTP_REQUEST_CONTENT_TYPE_JSON("json");

    private final String enumsStringValue;

    private final Integer enumsIntegerValue;

    public String getEnumsStringValue () {
        return enumsStringValue;
    }

    public Integer getEnumsIntegerValue () {
        return enumsIntegerValue;
    }

    AliyunDriveInfoEnums(String enumsStringValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = null;
    }

    AliyunDriveInfoEnums(Integer enumsIntegerValue) {
        this.enumsIntegerValue = enumsIntegerValue;
        this.enumsStringValue = null;
    }

    AliyunDriveInfoEnums(String enumsStringValue, Integer enumsIntegerValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = enumsIntegerValue;
    }

}
