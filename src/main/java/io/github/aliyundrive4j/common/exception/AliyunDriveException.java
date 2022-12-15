package io.github.aliyundrive4j.common.exception;

import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;

/**
 * description: AliyunDriveExceptions
 * 自定义阿里云盘-SDK异常类
 * @ClassName : AliyunDriveExceptions
 * @Date 2022/12/15 15:54
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.exception
 */
public class AliyunDriveException extends RuntimeException{

    /**
     * 状态码
     */
    private final int code;

    /**
     * 异常类构造器
     * @param code 异常码
     * @param msg 异常信息
     */
    public AliyunDriveException(int code, String msg) {
        super(msg);
        this.code = code;
    }

    /***
     * 异常类构造器
     * @param code 异常码
     * @param msg 异常信息
     * @param cause 异常原有
     */
    public AliyunDriveException(int code, String msg, Throwable cause) {
        super(msg, cause);
        this.code = code;
    }

    /**
     * 阿里云异常构造器
     * @param aliyunDriveCodeEnums 传入一个阿里云异常码对象
     */
    public AliyunDriveException(AliyunDriveCodeEnums aliyunDriveCodeEnums) {
        super(aliyunDriveCodeEnums.getMessage());
        this.code = aliyunDriveCodeEnums.getCode();
    }

    /**
     * 阿里云盘异常构造器
     * @param e 传入一个异常对象
     */
    public AliyunDriveException(Exception e) {
        super(e);
        this.code = AliyunDriveCodeEnums.FAILED.getCode();
    }

    /**
     * 阿里云盘SDK异常构造器
     * @param aliyunDriveCodeEnums 传入一个阿里云盘异常信息枚举
     * @param msg 传入一个或者多个异常信息
     */
    public AliyunDriveException(AliyunDriveCodeEnums aliyunDriveCodeEnums, String... msg) {
        super(String.format(aliyunDriveCodeEnums.getMessage(), (Object) msg));
        this.code = aliyunDriveCodeEnums.getCode();
    }

    /**
     * 获得请求码
     * @return 返回一个请求码
     */
    public int getCode() {
        return code;
    }
}
