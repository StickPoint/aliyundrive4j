module io.github.aliyundrive4j {
    requires org.slf4j;
    requires ch.qos.logback.core;
    requires ch.qos.logback.classic;
    requires okhttps;
    requires okhttps.gson;
    requires com.google.gson;
    requires data.core;
    requires lombok;
    requires org.bouncycastle.provider;
    // 配置SPI机制下的HttpConfig封装
    provides com.ejlchina.okhttps.Config with io.github.aliyundrive4j.common.config.AliyunDriveHttpConfig;
    exports io.github.aliyundrive4j.common.config;
    exports io.github.aliyundrive4j.common.entity.aliyun;
    exports io.github.aliyundrive4j.common.entity.base;
    exports io.github.aliyundrive4j.common.enums;
    exports io.github.aliyundrive4j.common.exception;
    exports io.github.aliyundrive4j.common.utils;
    exports io.github.aliyundrive4j.service.impl;
    exports io.github.aliyundrive4j.service;
}
