open module io.github.aliyundrive4j {
    requires org.slf4j;
    requires ch.qos.logback.core;
    requires ch.qos.logback.classic;
    requires okhttps;
    requires okhttps.gson;
    requires com.google.gson;
    requires data.core;
    requires lombok;
    // 配置SPI机制下的HttpConfig封装
    provides com.ejlchina.okhttps.Config with io.github.aliyundrive4j.common.config.AliyunDriveHttpConfig;
}