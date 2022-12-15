package io.github.aliyundrive4j.common.config;

import com.ejlchina.okhttps.Config;
import com.ejlchina.okhttps.HTTP.Builder;

/**
 * description: AliyunDriveHttpConfig
 *
 * @ClassName : AliyunDriveHttpConfig
 * @Date 2022/12/15 13:21
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.config
 */
public class AliyunDriveHttpConfig implements Config {

    /**
     * 在这里对 HTTP.Builder 做一些自定义的配置
     * @param builder Http构建者对象
     */
    @Override
    public void with(Builder builder) {
        // 配置基本固定参数

    }
}
