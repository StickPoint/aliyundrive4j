package io.github.aliyundrive4j.common.utils;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * description: SysInnerProperties
 *系统初始化加载一个本地配置MAP
 *  * 为什么这么写呢，我考虑了几点：
 *  * （1）这个项目采用了模块化，引入了module-info，在降低耦合的需要下，我尽可能少的引入新的依赖，
 *  *      不使用Spring来管理bean，并且对象较少，可以直接引用，这是提供给第三方应用使用的，我们尽量
 *  *      不使用反射。
 *  * （2）这是作为依赖jar运行在main程序中，他不会在主程序中直接进入runtime，
 *  *      我仅仅需要对外提供一个加载本地配置的map即可，让外部程序去做存储，sdk本身在没有接入bean管理
 *  *      的前提下，本身是无法长期驻留在内存中的，数据会丢失。那么只要服务对象存在于应用内部，就可以保留map
 * @ClassName : SysInnerProperties
 * @Date 2022/12/15 16:15
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.utils
 */
public class AliyunDrivePropertyUtils {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDrivePropertyUtils.class);

    /**
     * 明确告知无法使用构造器：工具类
     */
    private AliyunDrivePropertyUtils() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * 创建一个本地配置对象
     */
    private static final Properties MAP = new Properties();

    static {
        try {
            InputStream resourceAsStream = AliyunDrivePropertyUtils.class.getClassLoader().getResourceAsStream("application.properties");
            MAP.load(resourceAsStream);
            log.info("系统配置加载成功");
        } catch (IOException e) {
            log.error("系统配置装载异常！具体的异常信息是：\n {}",e.getCause().getMessage());
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_PROPERTIES_LOAD);
        }
    }

    public static synchronized void put(Object key, Object value) {
        MAP.put(key, value);
    }

    public static Object get(Object key) {
        return MAP.get(key);
    }

    public static synchronized void remove(Object key) {
        MAP.remove(key);
    }

}
