package io.github.aliyundrive4j.common.utils;

import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import java.io.Serial;
import java.io.Serializable;
import java.util.Objects;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
public class StringUtils implements Serializable {

    @Serial
    private static final long serialVersionUID = -2132546233262511169L;

    private static final String EMPTY_STRING = "";

    public static String emptyString(){
        return EMPTY_STRING;
    }

    /**
     * 截取字符串
     * @param sourceString 源字符串
     * @param startIndex 起始位置
     * @return 最终字符串
     */
    @SuppressWarnings("unused")
    public static String subFrom(String sourceString,Integer startIndex) {
        if (Objects.isNull(sourceString)) {
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_PARAM);
        }
        return sourceString.substring(startIndex);
    }

    /**
     * 截取字符串
     * @param sourceString 源字符串
     * @param startString 起始位置
     * @return 最终字符串
     */
    @SuppressWarnings("unused")
    public static String subFrom(String sourceString,String startString) {
        if (Objects.isNull(sourceString)) {
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_PARAM);
        }
        return sourceString.substring(sourceString.indexOf(startString));
    }

}
