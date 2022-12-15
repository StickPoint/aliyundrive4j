package io.github.aliyundrive4j.common.entity.base;

import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;

import java.io.Serial;
import java.io.Serializable;

/**
 * description: BaseBaseResponseEntityEntity
 *
 * @ClassName : BaseBaseResponseEntityEntity
 * @Date 2022/12/15 13:28
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity
 */
public class BaseResponseEntity<T> implements Serializable {

        @Serial
        private static final long serialVersionUID = 6094122838833401860L;

        /**
         * 返回码
         */
        private int code;

        /**
         * 信息
         */
        private String message;
        
        /**
         * 数据
         */
        private T data;
        
        /**
         * 构造方法
         */
        protected BaseResponseEntity() {
                this.code = AliyunDriveCodeEnums.SUCCESS.getCode();
                this.message = AliyunDriveCodeEnums.SUCCESS.getMessage();
        }
        
        /**
         * 构造方法
         *
         * @param data 数据
         */
        protected BaseResponseEntity(T data) {
                this.code = AliyunDriveCodeEnums.SUCCESS.getCode();
                this.data = data;
                this.message = AliyunDriveCodeEnums.SUCCESS.getMessage();
        }
        
        /**
         * 构造方法
         *
         * @param code    状态码
         * @param message 信息
         * @param data    数据
         */
        protected BaseResponseEntity(int code, String message, T data) {
                this.code = code;
                this.message = message;
                this.data = data;
        }
        
        /**
         * 构造方法
         *
         * @param exceptionType AliyunDriveException异常
         */
        protected BaseResponseEntity(AliyunDriveException exceptionType) {
                this.code = exceptionType.getCode();
                this.message = exceptionType.getMessage();
        }
        
        /**
         * 设置状态
         *
         * @param code    状态码
         * @param message 格式化信息
         * @param params  参数
         */
        public void setStatus(int code, String message, Object... params) {
                this.code = code;
                this.message = String.format(message, params);
        }
        
        /**
         * 设置状态
         *
         * @param AliyunDriveCodeEnums 状态码-枚举
         */
        public void setStatus(AliyunDriveCodeEnums AliyunDriveCodeEnums) {
                this.code = AliyunDriveCodeEnums.getCode();
                this.message = AliyunDriveCodeEnums.getMessage();
        }
        
        /**
         * 设置状态
         *
         * @param exceptionType 基础异常
         */
        public void setStatus(AliyunDriveException exceptionType) {
                this.code = exceptionType.getCode();
                this.message = exceptionType.getMessage();
        }
        
        /**
         * 设置状态
         *
         * @param exceptionType 基础异常
         * @param params        格式化信息参数
         */
        public void setStatus(AliyunDriveException exceptionType, Object... params) {
                this.code = exceptionType.getCode();
                this.message = String.format(exceptionType.getMessage(), params);
        }
        
        /**
         * 错误
         *
         * @param code 状态码
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> error(int code) {
                return error(code, AliyunDriveCodeEnums.getMessageByCode(code));
        }
        
        /**
         * 错误
         *
         * @param code    状态码
         * @param message 信息
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> error(int code, String message) {
                return new BaseResponseEntity<>(code, message, null);
        }
        
        /**
         * 错误
         *
         * @param aliyunDriveCodeEnums 枚举
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> error(AliyunDriveCodeEnums aliyunDriveCodeEnums) {
                return error(aliyunDriveCodeEnums.getCode(), aliyunDriveCodeEnums.getMessage());
        }
        
        /**
         * 错误
         *
         * @param aliyunDriveCodeEnums 枚举
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> error(AliyunDriveCodeEnums aliyunDriveCodeEnums, String... message) {
                return error(aliyunDriveCodeEnums.getCode(), String.format(aliyunDriveCodeEnums.getMessage(), (Object[]) message));
        }
        
        /**
         * 成功
         *
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> success() {
                return success(null);
        }
        
        /**
         * 成功
         *
         * @param data 数据
         * @return 相应模型
         */
        public static <T> BaseResponseEntity<T> success(T data) {
                return new BaseResponseEntity<>(AliyunDriveCodeEnums.SUCCESS.getCode(), AliyunDriveCodeEnums.SUCCESS.getMessage(), data);
        }

        @Override
        public String toString() {
                return "BaseResponseEntity{" +
                        "code=" + code +
                        ", message='" + message + '\'' +
                        ", data=" + data +
                        '}';
        }
}
