package io.github.aliyundrive4j;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.service.IAliyunDriveFileService;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
import io.github.aliyundrive4j.service.impl.AliyunDriveUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: HttpTest
 *
 * @ClassName : HttpTest
 * @Date 2022/12/15 13:54
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j
 */
class HttpTest {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(HttpTest.class);

    /**
     * 测试网络连接
     */
    @Test
    void testHttp() {
        Map<String,String> params = new ConcurrentHashMap<>();
        params.put("key","第三人称");
        AliyunHttpUtils httpUtils = AliyunHttpUtils.getInstance();
        String getResult = httpUtils.doGetWithParams("http://1.2.3.4:5000/v1/wy/search",params);
        log.info(getResult);
    }

    /**
     * 测试登出操作
     */
    @Test
    void testSingOut() {
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        BaseResponseEntity<String> stringBaseResponseEntity = userService.signOut();
        log.info(stringBaseResponseEntity.toString());
    }

    /**
     * 测试获得阿里云盘扫码登录的二维码
     */
    @Test
    void testGetAliyunDriveQrcodeImage() {
        AliyunHttpUtils instance = AliyunHttpUtils.getInstance();
        log.info("测试获得阿里云盘扫码登录的二维码");
        instance.getQrCodeUrl();
    }

    /**
     * 测试阿里云盘扫码登录的二维码是否过期
     */
    @Test
    void testGetAliyunDriveQrcodeImageAvailable() {
        AliyunHttpUtils instance = AliyunHttpUtils.getInstance();
        log.info("测试获得阿里云盘扫码登录的二维码是否过期");
        instance.queryQrCode("1671160648246","108ed3e35a06baf68170cd40b5147182");
    }
}
