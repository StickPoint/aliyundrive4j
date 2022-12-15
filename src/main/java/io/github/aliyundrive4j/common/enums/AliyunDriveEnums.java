package io.github.aliyundrive4j.common.enums;

/**
 * @Author SunChengXin_0303
 * @ClassName AliyunDriveEnums.Class
 * @PackageName io.github.aliyundrive4j.common.enums
 * @jdk_version 17
 * @Date 2022年12月15日 13:29
 * @since 1.5
 */
public enum AliyunDriveEnums {

    ALIYUN_DRIVE_HTTP_STATUS_OK(200),

    ALIYUN_DRIVE_HTTP_REQUEST_CONTENT_TYPE_JSON("json");

    private String enumsStringValue;

    private Integer enumsIntegerValue;

    AliyunDriveEnums (String enumsStringValue) {
        this.enumsStringValue = enumsStringValue;
    }

    AliyunDriveEnums (Integer enumsIntegerValue) {
        this.enumsIntegerValue = enumsIntegerValue;
    }

    AliyunDriveEnums(String enumsStringValue, Integer enumsIntegerValue) {
        this.enumsStringValue = enumsStringValue;
        this.enumsIntegerValue = enumsIntegerValue;
    }

}
