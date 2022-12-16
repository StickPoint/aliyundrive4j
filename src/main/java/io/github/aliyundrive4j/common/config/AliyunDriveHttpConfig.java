package io.github.aliyundrive4j.common.config;

import com.ejlchina.okhttps.Config;
import com.ejlchina.okhttps.HTTP.Builder;

import java.nio.charset.StandardCharsets;

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
        // 配置基本固定参数 全局请求五秒超时自动断开
        builder.preprocTimeoutTimes(5)
                // 配置全局请求字符编码 UTF-8
                .charset(StandardCharsets.UTF_8);
    }
}
