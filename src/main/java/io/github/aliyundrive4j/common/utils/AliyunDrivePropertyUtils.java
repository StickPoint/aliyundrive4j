package io.github.aliyundrive4j.common.utils;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

/**
 * description: SysInnerProperties
 *
 * @ClassName : SysInnerProperties
 * @Date 2022/12/15 16:15
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.utils
 */
public class AliyunDrivePropertyUtils {
    /**
     * 明确告知无法使用构造器：工具类
     */
    private AliyunDrivePropertyUtils() {
        throw new IllegalStateException("Utility class");
    }
    /**
     * 装载
     */
    public static Map<Object,Object> initProperties() {
        try {
            InputStream resourceAsStream = AliyunDrivePropertyUtils.class.getClassLoader().getResourceAsStream("application.properties");
            Properties properties = new Properties();
            properties.load(resourceAsStream);
            return properties;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return Collections.emptyMap();
    }
}
