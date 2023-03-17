package io.github.aliyundrive4j;
import io.github.aliyundrive4j.common.entity.aliyun.AliyunBaseEntity;
import io.github.aliyundrive4j.common.entity.aliyun.CapacityDetail;
import io.github.aliyundrive4j.common.entity.aliyun.DriveItemEntity;
import io.github.aliyundrive4j.common.entity.aliyun.FileInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.FolderInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.LoginQrcodeInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.PdsLoginResult;
import io.github.aliyundrive4j.common.entity.base.BaseHeaderEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.utils.AliyunDriveHttpUtils;
import io.github.aliyundrive4j.common.utils.AliyunDrivePropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveDriveService;
import io.github.aliyundrive4j.service.IAliyunDriveFileService;
import io.github.aliyundrive4j.service.IAliyunDriveFolderService;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
import io.github.aliyundrive4j.service.impl.AliyunDriveDriveServiceImpl;
import io.github.aliyundrive4j.service.impl.AliyunDriveFileServiceImpl;
import io.github.aliyundrive4j.service.impl.AliyunDriveFolderServiceImpl;
import io.github.aliyundrive4j.service.impl.AliyunDriveUserServiceImpl;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

/**
 * description: HttpTest
 *
 * @ClassName : HttpTest
 * @Date 2022/12/15 13:54
 * @Author fntp
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
        AliyunDriveHttpUtils httpUtils = AliyunDriveHttpUtils.getInstance();
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
        log.info("测试获得阿里云盘扫码登录的二维码");
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        BaseResponseEntity<AliyunBaseEntity> loginQrcodeInfo = userService.getLoginQrcodeInfo();
        log.info(loginQrcodeInfo.toString());
    }

    /**
     * 测试阿里云盘扫码登录的二维码是否过期
     */
    @Test
    void testGetAliyunDriveQrcodeImageAvailable() {
        log.info("测试获得阿里云盘扫码登录的二维码是否过期");
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        BaseResponseEntity<AliyunBaseEntity> booleanBaseResponseEntity = userService.checkLoginQrcodeStatus("1676960002703","1bf7e4da8cc1af753b435ce5e97041e3");
        LoginQrcodeInfoEntity data = (LoginQrcodeInfoEntity) booleanBaseResponseEntity.getData();
        log.info(data.toString());
    }

    /**
     * 测试执行阿里云扫码登录扫码登录后，查询扫码结果，如果用户点击了确认登录，那么查询结果会拿到
     * 确认登录返回的bizExt码，然后将这个码传递给扫码登录回调
     * 通过扫码登录回调，最终将登录前需要的信息都传递给最终登录方法
     * 最终登录方法执行登录
     */
    @Test
    void testLoginWithQrcodeResultResp() {
        log.info("查询扫码结果，如果用户点击了确认登录，那么查询结果会拿到确认登录返回的bizExt码");
        LoginQrcodeInfoEntity loginQrcodeInfoEntity = new LoginQrcodeInfoEntity();
        loginQrcodeInfoEntity.setBizExt("eyJwZHNfbG9naW5fcmVzdWx0Ijp7InJvbGUiOiJ1c2VyIiwidXNlckRhdGEiOnsiRGluZ0RpbmdSb2JvdFVybCI6Imh0dHBzOi8vb2FwaS5kaW5ndGFsay5jb20vcm9ib3Qvc2VuZD9hY2Nlc3NfdG9rZW49MGI0YTkzNmQwZTk4YzA4NjA4Y2Q5OWY2OTMzOTNjMThmYTkwNWFhMDg2ODIxNTQ4NWEyODQ5NzUwMTkxNmZlYyIsIkVuY291cmFnZURlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsIkZlZWRCYWNrU3dpdGNoIjp0cnVlLCJGb2xsb3dpbmdEZXNjIjoiMzQ4NDgzNzIiLCJkaW5nX2Rpbmdfcm9ib3RfdXJsIjoiaHR0cHM6Ly9vYXBpLmRpbmd0YWxrLmNvbS9yb2JvdC9zZW5kP2FjY2Vzc190b2tlbj0wYjRhOTM2ZDBlOThjMDg2MDhjZDk5ZjY5MzM5M2MxOGZhOTA1YWEwODY4MjE1NDg1YTI4NDk3NTAxOTE2ZmVjIiwiZW5jb3VyYWdlX2Rlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsImZlZWRfYmFja19zd2l0Y2giOnRydWUsImZvbGxvd2luZ19kZXNjIjoiMzQ4NDgzNzIifSwiaXNGaXJzdExvZ2luIjpmYWxzZSwibmVlZExpbmsiOmZhbHNlLCJsb2dpblR5cGUiOiJxckNvZGVMb2dpbiIsIm5pY2tOYW1lIjoiIiwibmVlZFJwVmVyaWZ5IjpmYWxzZSwiYXZhdGFyIjoiaHR0cHM6Ly9pbWcuYWxpeXVuZHJpdmUuY29tL2F2YXRhci9kZWZhdWx0LzAxLnBuZyIsImFjY2Vzc1Rva2VuIjoiZXlKaGJHY2lPaUpTVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SjFjMlZ5U1dRaU9pSXlNelF6TURJMlpqRXhNRE0wWkRSbU9XTTROV0U0WmpRd09UTXdaakJpWWlJc0ltTjFjM1J2YlVwemIyNGlPaUo3WENKamJHbGxiblJKWkZ3aU9sd2ljRXBhU1c1T1NFNHlaRnBYYXpoeFoxd2lMRndpWkc5dFlXbHVTV1JjSWpwY0ltSnFNamxjSWl4Y0luTmpiM0JsWENJNlcxd2lSRkpKVmtVdVFVeE1YQ0lzWENKR1NVeEZMa0ZNVEZ3aUxGd2lWa2xGVnk1QlRFeGNJaXhjSWxOSVFWSkZMa0ZNVEZ3aUxGd2lVMVJQVWtGSFJTNUJURXhjSWl4Y0lsTlVUMUpCUjBWR1NVeEZMa3hKVTFSY0lpeGNJbFZUUlZJdVFVeE1YQ0lzWENKQ1FWUkRTRndpTEZ3aVFVTkRUMVZPVkM1QlRFeGNJaXhjSWtsTlFVZEZMa0ZNVEZ3aUxGd2lTVTVXU1ZSRkxrRk1URndpTEZ3aVUxbE9RMDFCVUZCSlRrY3VURWxUVkZ3aVhTeGNJbkp2YkdWY0lqcGNJblZ6WlhKY0lpeGNJbkpsWmx3aU9sd2lYQ0lzWENKa1pYWnBZMlZmYVdSY0lqcGNJalkyT0dJM1ptSmtaV1U0WkRSa05HTTVNekZoWVdKak5qVmxOall5T0dOa1hDSjlJaXdpWlhod0lqb3hOamMyT1RZM016azFMQ0pwWVhRaU9qRTJOelk1TmpBeE16VjkuRXUtZGd5UkY0MHFXVDFhNkdKYVltQzFUQ1dyanlUUmRmQkJQWnVwelEwSW55elVsdVlFYkpfU1pyZnM0MGowLWdCWTlyTXVfVHJja2ZIOFdPSUVQTUh2M1pRV0tvQmd0NTk4SUlfQnFwYU4xMGU4M0JTejdmTkR5aG8zbDJVVXJHZzBkaHhyTDNLZ21oS0x2all6YUJjTk9DYTBvdkZZbngxUDE3YzBwU3FRIiwidXNlck5hbWUiOiIxODEqKio5NDgiLCJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImRlZmF1bHREcml2ZUlkIjoiNzM1NTQzOTAyIiwiZXhpc3RMaW5rIjpbXSwiZXhwaXJlc0luIjo3MjAwLCJleHBpcmVUaW1lIjoiMjAyMy0wMi0yMVQwODoxNjozNVoiLCJyZXF1ZXN0SWQiOiJBQUFDQTRDMy1DQjhCLTQ4OTUtQjAwMy0xQTRCRUNBODc5QTAiLCJkYXRhUGluU2V0dXAiOmZhbHNlLCJzdGF0ZSI6IiIsInRva2VuVHlwZSI6IkJlYXJlciIsImRhdGFQaW5TYXZlZCI6ZmFsc2UsInJlZnJlc2hUb2tlbiI6IjY2OGI3ZmJkZWU4ZDRkNGM5MzFhYWJjNjVlNjYyOGNkIiwic3RhdHVzIjoiZW5hYmxlZCJ9fQ==");
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        log.info("通过扫码登录回调，最终将登录前需要的信息都传递给最终登录方法");
        BaseResponseEntity<AliyunBaseEntity> pdsLoginResultBaseResponseEntity = userService.doLoginWithQrcode(loginQrcodeInfoEntity);
        log.info("测试执行阿里云扫码登录-最终登录");
        PdsLoginResult data = (PdsLoginResult)pdsLoginResultBaseResponseEntity.getData();
        BaseResponseEntity<AliyunBaseEntity> finalResponseEntity = userService.doLogin(data);
        log.info(finalResponseEntity.getData().toString());
    }

    /**
     * 刷新token
     */
    @Test
    void testRefreshToken() {
        log.info("开始测试刷新token");
        IAliyunDriveUserService aliyunDriveUserService = new AliyunDriveUserServiceImpl();
        BaseRequestEntity baseRequestEntity = new BaseRequestEntity();
        baseRequestEntity.setRefreshToken("-----------");
        BaseResponseEntity<AliyunBaseEntity> aliyunBaseEntityBaseResponseEntity = aliyunDriveUserService.refreshUserToken(baseRequestEntity);
        log.info(aliyunBaseEntityBaseResponseEntity.getData().toString());
    }

    /**
     * 测试简单方法
     */
    @Test
    void onlyTestMethod() {
        String bizExt = "eyJwZHNfbG9naW5fcmVzdWx0Ijp7InJvbGUiOiJ1c2VyIiwidXNlckRhdGEiOnsiRGluZ0RpbmdSb2JvdFVybCI6Imh0dHBzOi8vb2FwaS5kaW5ndGFsay5jb20vcm9ib3Qvc2VuZD9hY2Nlc3NfdG9rZW49MGI0YTkzNmQwZTk4YzA4NjA4Y2Q5OWY2OTMzOTNjMThmYTkwNWFhMDg2ODIxNTQ4NWEyODQ5NzUwMTkxNmZlYyIsIkVuY291cmFnZURlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsIkZlZWRCYWNrU3dpdGNoIjp0cnVlLCJGb2xsb3dpbmdEZXNjIjoiMzQ4NDgzNzIiLCJiYWNrX3VwX2NvbmZpZyI6eyLK1rv6sbi33SI6eyJmb2xkZXJfaWQiOiI2MDViY2IyYmRjZWNhNTQ1ODRmOTQ0ZmRiZDlhNjY0OWNjZDYzMTUyIiwicGhvdG9fZm9sZGVyX2lkIjoiNjA1YmNiMmJiZmQ5NDAzZjQxMWY0MzM1ODQ5ZjAzYTVlNmRhMmJiNCIsInN1Yl9mb2xkZXIiOnt9LCJ2aWRlb19mb2xkZXJfaWQiOiI2MDViY2IyYjk0MWJiMzliYmRjNzQyNmE5YzBiOGY4OWU3MWNiOTI4In19LCJkaW5nX2Rpbmdfcm9ib3RfdXJsIjoiaHR0cHM6Ly9vYXBpLmRpbmd0YWxrLmNvbS9yb2JvdC9zZW5kP2FjY2Vzc190b2tlbj0wYjRhOTM2ZDBlOThjMDg2MDhjZDk5ZjY5MzM5M2MxOGZhOTA1YWEwODY4MjE1NDg1YTI4NDk3NTAxOTE2ZmVjIiwiZW5jb3VyYWdlX2Rlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsImZlZWRfYmFja19zd2l0Y2giOnRydWUsImZvbGxvd2luZ19kZXNjIjoiMzQ4NDgzNzIifSwiaXNGaXJzdExvZ2luIjpmYWxzZSwibmVlZExpbmsiOmZhbHNlLCJsb2dpblR5cGUiOiJxckNvZGVMb2dpbiIsIm5pY2tOYW1lIjoiZm50cCIsIm5lZWRScFZlcmlmeSI6ZmFsc2UsImF2YXRhciI6Imh0dHBzOi8vaW1nLmFsaXl1bmRyaXZlLmNvbS9hdmF0YXIvYTJmMjVjNTliYWY1NDliZmFhZGFmOTRjM2FkN2VmZGQuanBlZyIsImFjY2Vzc1Rva2VuIjoiZXlKaGJHY2lPaUpTVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SjFjMlZ5U1dRaU9pSTJNR1k0WkdJMk9XUmhZV1EwTjJFd1lqTXhZbUkwWWpjNU56ZzBObVl3WmlJc0ltTjFjM1J2YlVwemIyNGlPaUo3WENKamJHbGxiblJKWkZ3aU9sd2ljRXBhU1c1T1NFNHlaRnBYYXpoeFoxd2lMRndpWkc5dFlXbHVTV1JjSWpwY0ltSnFNamxjSWl4Y0luTmpiM0JsWENJNlcxd2lSRkpKVmtVdVFVeE1YQ0lzWENKR1NVeEZMa0ZNVEZ3aUxGd2lWa2xGVnk1QlRFeGNJaXhjSWxOSVFWSkZMa0ZNVEZ3aUxGd2lVMVJQVWtGSFJTNUJURXhjSWl4Y0lsTlVUMUpCUjBWR1NVeEZMa3hKVTFSY0lpeGNJbFZUUlZJdVFVeE1YQ0lzWENKQ1FWUkRTRndpTEZ3aVFVTkRUMVZPVkM1QlRFeGNJaXhjSWtsTlFVZEZMa0ZNVEZ3aUxGd2lTVTVXU1ZSRkxrRk1URndpTEZ3aVUxbE9RMDFCVUZCSlRrY3VURWxUVkZ3aVhTeGNJbkp2YkdWY0lqcGNJblZ6WlhKY0lpeGNJbkpsWmx3aU9sd2lYQ0lzWENKa1pYWnBZMlZmYVdSY0lqcGNJall5WWpsaU4yUmxNREk0T1RRNE1tSmhNbUk0TURaaE4yRTRNVEV5TUdZMFhDSjlJaXdpWlhod0lqb3hOamN5TXpBNU16azBMQ0pwWVhRaU9qRTJOekl6TURJeE16UjkuUGRaVWtQekxTZVEzdU81N0JLYjNYdm9aSzZJYW5VQVBhRFc3aUJaTDd5blU4R3o0TUlKUDRnVjZWQjc3VEhKY252dHJXbmtWbGhBNnNfTXVrckNrOVZ4cWtlQ0Q2QmhZZXA1U2RVZFJiR0NQbU9QM3V4TlFCX2pTWXh1MTNUa28tWjJLS3N6Y0xYNjBla1FwMEJmQjZEZWw4MmVueFZoSHZzd3Y1RS00VE9RIiwidXNlck5hbWUiOiIxMzIqKio3NTAiLCJ1c2VySWQiOiI2MGY4ZGI2OWRhYWQ0N2EwYjMxYmI0Yjc5Nzg0NmYwZiIsImRlZmF1bHREcml2ZUlkIjoiNjAwMTg2NzAiLCJleGlzdExpbmsiOltdLCJleHBpcmVzSW4iOjcyMDAsImV4cGlyZVRpbWUiOiIyMDIyLTEyLTI5VDEwOjIzOjE0WiIsInJlcXVlc3RJZCI6IjlEMUY2NDAwLTM0MjctNDVCMy04Q0FELTZDMTNGMzQyQjRGRCIsImRhdGFQaW5TZXR1cCI6ZmFsc2UsInN0YXRlIjoiIiwidG9rZW5UeXBlIjoiQmVhcmVyIiwiZGF0YVBpblNhdmVkIjpmYWxzZSwicmVmcmVzaFRva2VuIjoiNjJiOWI3ZGUwMjg5NDgyYmEyYjgwNmE3YTgxMTIwZjQiLCJzdGF0dXMiOiJlbmFibGVkIn19";
        Decoder decoder = Base64.getDecoder();
        // 将响应的JSON字符串解析为Java对象
        String resultJson= new String(decoder.decode(bizExt.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
        System.out.println(resultJson);
    }

    /**
     * 测试用户容量查询
     */
    @Test
    void testUserCapacityDetails(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiNjIyYzBjYjI1YWQ5NDY1ZGI0NzkyMDA1Yjc1MmMxNTVcIn0iLCJleHAiOjE2NzY4ODE0NDcsImlhdCI6MTY3Njg3NDE4N30.Xp2anSUHK_zkwlMzrQopJyTnuJNOEqwfX_a-8FA-hXGpLOH6nt02WI7t7IC0bo4Q-0wAqPnD_q_ronEHDN_0SQHuN4HPXV5AjEWjn_TG-IlPvWnuqx08L77Ccv8xf6313ADnPH_uXIXMklazdm5W_akOQJXlduoN0ZXx01wkcsE")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder().aliyundriveRequestBaseHeader(headerEntity).build();
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        List<CapacityDetail> data = userService.getCapacityDetails(request).getData();
        data.forEach(capacityDetail-> log.info(capacityDetail.toString()));
    }

    /**
     * 测试用户已使用的容量信息获取
     */
    @Test
    void testUserAlreadyInUsedCapacity(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiNjIyYzBjYjI1YWQ5NDY1ZGI0NzkyMDA1Yjc1MmMxNTVcIn0iLCJleHAiOjE2NzY4ODE0NDcsImlhdCI6MTY3Njg3NDE4N30.Xp2anSUHK_zkwlMzrQopJyTnuJNOEqwfX_a-8FA-hXGpLOH6nt02WI7t7IC0bo4Q-0wAqPnD_q_ronEHDN_0SQHuN4HPXV5AjEWjn_TG-IlPvWnuqx08L77Ccv8xf6313ADnPH_uXIXMklazdm5W_akOQJXlduoN0ZXx01wkcsE")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder().aliyundriveRequestBaseHeader(headerEntity).build();
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        CapacityDetail data = userService.getAlreadyInUsedCapacity(request).getData();
        log.info(data.toString());
    }

    @Test
    void normalTest(){
        Map<String,String> hash = new LinkedHashMap<>();
        hash.put("a","a");
        Map<String, String> map = testMap(hash);
        System.out.println(map.size());
    }

    private Map<String,String> testMap(Map<String,String> map){
       return Optional.ofNullable(map).orElse(Collections.emptyMap());
    }

    /**
     * 测试网盘列表信息获取
     */
    @Test
    void testAliyunDriveListGet(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYzFjMzcyYWZlMDFmNDVkNGIzYmIxOTMxNDgyZTQ5YWFcIn0iLCJleHAiOjE2NzY4ODc1NjUsImlhdCI6MTY3Njg4MDMwNX0.fXCThy7MH_7Er4GBHO9sCV8XluFW_GAJHOivHk-DCoAdjXXwQP6MJoFGYwgARtvLzlYgMZWxI5xoLq6ip-KfyndxElP-N7xZ2sfpazKPI6ySy0VsPGn3YJ8NwOdBbCt34iDcMExUQKHJomjLZCHVAxaqA5S__Htjr-fEk0AWs0s")
                .build();
        // 2023-02-20 经过测试发现，这里不带参数ownerId也是一样，照样正常获取网盘列表数据信息
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .ownerId("2343026f11034d4f9c85a8f40930f0bb")
                .build();
        IAliyunDriveDriveService driveService = new AliyunDriveDriveServiceImpl();
        List<DriveItemEntity> list = driveService.getDriveList(request).getData();
        list.forEach(item-> log.info(item.toString()));
    }

    /**
     * 测试获取默认网盘信息
     */
    @Test
    void testAliyunDriveDefaultGet(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiMGIxYjZlMTY0NjU3NGEyMWE5YzFkZWRjNjQzM2YwODFcIn0iLCJleHAiOjE2NzY5Njc1MjQsImlhdCI6MTY3Njk2MDI2NH0.L8O12dx2r-Ff9mDsjJXOlYWsnowAyEHxaYONqwQMYIncoizQ6BqPsxL_Q-oF1N0wwyccJeKS4U6KHkHKFtVvBXsoYq4cNzz9m46TtPWjd3SvOim_-zHXWoHwjVMMO8CZcx26jEdENDt39X0X4rdv5-HiQpu349QBdrq_3LxhaQc")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder().aliyundriveRequestBaseHeader(headerEntity).build();
        IAliyunDriveDriveService driveService = new AliyunDriveDriveServiceImpl();
        DriveItemEntity driveItem = driveService.getDefaultDrive(request).getData();
        log.info(driveItem.toString());
    }

    /**
     * 测试根据网盘id获取网盘信息
     */
    @Test
    void testAliyunDriveInfoGetByDriveId(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYzFjMzcyYWZlMDFmNDVkNGIzYmIxOTMxNDgyZTQ5YWFcIn0iLCJleHAiOjE2NzY4ODc1NjUsImlhdCI6MTY3Njg4MDMwNX0.fXCThy7MH_7Er4GBHO9sCV8XluFW_GAJHOivHk-DCoAdjXXwQP6MJoFGYwgARtvLzlYgMZWxI5xoLq6ip-KfyndxElP-N7xZ2sfpazKPI6ySy0VsPGn3YJ8NwOdBbCt34iDcMExUQKHJomjLZCHVAxaqA5S__Htjr-fEk0AWs0s")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .driveId("514543902")
                .build();
        IAliyunDriveDriveService driveService = new AliyunDriveDriveServiceImpl();
        DriveItemEntity driveItem = driveService.getDriveInfoByDriveId(request).getData();
        log.info(driveItem.toString());
    }

    /**
     * 测试创建文件夹：成功
     */
    @Test
    void testMkdir(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYjY1YTc4MzNmM2U0NDk2YmIzYTFhZDc3MWIzNzk2NTFcIn0iLCJleHAiOjE2NzcyMTExMDcsImlhdCI6MTY3NzIwMzg0N30.UdpQU_3PM0LNGvAdBvTQpXZwN-AzXt4ti1ApR71RnD3Uyqmg6aAKFx37GqBZD13iLSqQ8T422pb959rSXFK_ykUUtMAYI2swT5HI0fT6XabHgyMPLRcpmaMHdB4-w1Ky5W6f5WTfk4mfU892Zo5cugy3YbeWqOZmp_vHlpme1GM")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .driveId("735543902")
                .parentFileId("root")
                .checkNameMode("refuse")
                .type("folder")
                .name("顶点音乐")
                .build();
        IAliyunDriveFolderService folderService = new AliyunDriveFolderServiceImpl();
        FolderInfoEntity folderMadeResp = folderService.createFolder(request).getData();
        log.info(folderMadeResp.toString());
    }

    /**
     * 测试查询文件夹
     */
    @Test
    void testGetDir(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYjY1YTc4MzNmM2U0NDk2YmIzYTFhZDc3MWIzNzk2NTFcIn0iLCJleHAiOjE2NzcyMTExMDcsImlhdCI6MTY3NzIwMzg0N30.UdpQU_3PM0LNGvAdBvTQpXZwN-AzXt4ti1ApR71RnD3Uyqmg6aAKFx37GqBZD13iLSqQ8T422pb959rSXFK_ykUUtMAYI2swT5HI0fT6XabHgyMPLRcpmaMHdB4-w1Ky5W6f5WTfk4mfU892Zo5cugy3YbeWqOZmp_vHlpme1GM")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .driveId("735543902")
                .all(false)
                .fields("*")
                .imageThumbnailProcess("image/resize,w_256/format,jpeg")
                .imageUrlProcess("image/resize,w_1920/format,jpeg/interlace,1")
                .limit(20)
                .orderBy("updated_at")
                .orderDirection("DESC")
                .parentFileId("root")
                .urlExpireSec(14400)
                .videoThumbnailProcess("video/snapshot,t_1000,f_jpg,ar_auto,w_256")
                .build();
        IAliyunDriveFolderService folderService = new AliyunDriveFolderServiceImpl();
        List<FolderInfoEntity> list = folderService.getFolderList(request).getData();
        list.forEach(folderMadeResp->log.info(folderMadeResp.toString()));
    }

    /**
     * 测试修改文件夹：成功
     */
    @Test
    void testUpdateDir(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYjY1YTc4MzNmM2U0NDk2YmIzYTFhZDc3MWIzNzk2NTFcIn0iLCJleHAiOjE2NzcyMTExMDcsImlhdCI6MTY3NzIwMzg0N30.UdpQU_3PM0LNGvAdBvTQpXZwN-AzXt4ti1ApR71RnD3Uyqmg6aAKFx37GqBZD13iLSqQ8T422pb959rSXFK_ykUUtMAYI2swT5HI0fT6XabHgyMPLRcpmaMHdB4-w1Ky5W6f5WTfk4mfU892Zo5cugy3YbeWqOZmp_vHlpme1GM")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .driveId("735543902")
                .fileId("63f8207b9ec5948a407645a9bd999e26a9f153be")
                .name("顶点音乐2")
                .checkNameMode("refuse")
                .build();
        IAliyunDriveFolderService folderService = new AliyunDriveFolderServiceImpl();
        FolderInfoEntity folderMadeResp = folderService.updateFolder(request).getData();
        log.info(folderMadeResp.toString());
    }

    /**
     * 测试删除文件夹：成功
     * 2023-02-17 测试通过
     */
    @Test
    void testDeleteDir(){
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiYjY1YTc4MzNmM2U0NDk2YmIzYTFhZDc3MWIzNzk2NTFcIn0iLCJleHAiOjE2NzcyMTExMDcsImlhdCI6MTY3NzIwMzg0N30.UdpQU_3PM0LNGvAdBvTQpXZwN-AzXt4ti1ApR71RnD3Uyqmg6aAKFx37GqBZD13iLSqQ8T422pb959rSXFK_ykUUtMAYI2swT5HI0fT6XabHgyMPLRcpmaMHdB4-w1Ky5W6f5WTfk4mfU892Zo5cugy3YbeWqOZmp_vHlpme1GM")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .driveId("735543902")
                .fileId("63f4a09025d821ed3def435bab8f5738521393a9")
                .build();
        IAliyunDriveFolderService folderService = new AliyunDriveFolderServiceImpl();
        FolderInfoEntity folderMadeResp = folderService.deleteFolder(request).getData();
        log.info(folderMadeResp.toString());
    }

    /**
     * 阿里云盘-测试-通过文件ID+网盘ID查询文件信息
     * 2023-03-17 测试通过
     */
    @Test
    void testGetFileInfoById(){
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID.getEnumsStringValue(),"04f0b6f06e9b7cd0ac8cc73b");
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_SIGNATURE.getEnumsStringValue(),"1b8e931a9b8d240a7e07a679a1159b579282f26983a1ff025d14ee9fef453f1e6138163959a145ca1d239ea3e8b18b910a53aa426333497b78a670b62489f9d101");
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiY2UyMTJjZTIzZGM2NDJlOWIzNzQ1NDcxZTAzYjk5ZGJcIn0iLCJleHAiOjE2NzkwNDk2MzksImlhdCI6MTY3OTA0MjM3OX0.H8PXQbvfTzlRPpTZFSKfkQPPAnxN5vYni61NcWsaR_fjj5f2o9r7YiZwG6yBXRzFTw9-rGhqk6owZtstQYDmP1FPnBLuWV0y8otv3wyoLa4MTGDzHxFMokqX9VvB0OWh3lXiJdcOZCLzU58mHSzUclku-zWG3CbYMnSU7mvFVU4")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .fileId("63f81aba393a80b5d1294301b73bfcec83c38cbd")
                .driveId("735543902")
                .build();
        IAliyunDriveFileService fileService = new AliyunDriveFileServiceImpl();
        FileInfoEntity fileInfoEntity = fileService.getFileInfoById(request).getData();
        log.info(fileInfoEntity.toString());
    }

    /**
     * 阿里云盘-测试-通过文件ID+网盘ID+新文件名重命名文件
     * 2023-03-17 测试通过
     */
    @Test
    void testUpdateFileInfoById(){
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID.getEnumsStringValue(),"zZVfG1NuVVkCAXrpXIwdeAyE");
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_SIGNATURE.getEnumsStringValue(),"2717817a79b5c751c7f7c1546bba3b7352ee2a458e041a070e4e84dcbceff1af7216d9a6d7747955c510a2bbf83ca5b1084288c08045dcad2facaffd37c8954100");
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_NONCE_KEY.getEnumsStringValue(),0);
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiY2UyMTJjZTIzZGM2NDJlOWIzNzQ1NDcxZTAzYjk5ZGJcIn0iLCJleHAiOjE2NzkwNDk2MzksImlhdCI6MTY3OTA0MjM3OX0.H8PXQbvfTzlRPpTZFSKfkQPPAnxN5vYni61NcWsaR_fjj5f2o9r7YiZwG6yBXRzFTw9-rGhqk6owZtstQYDmP1FPnBLuWV0y8otv3wyoLa4MTGDzHxFMokqX9VvB0OWh3lXiJdcOZCLzU58mHSzUclku-zWG3CbYMnSU7mvFVU4")
                .build();
        BaseRequestEntity request = BaseRequestEntity.builder()
                .aliyundriveRequestBaseHeader(headerEntity)
                .checkNameMode("false")
                .fileId("63f81ad1c953d94e335048178a4dfb209538b7dd")
                .driveId("735543902")
                .name("Java编程思想-test版本.pdf")
                .build();
        IAliyunDriveFileService fileService = new AliyunDriveFileServiceImpl();
        FileInfoEntity fileInfoEntity = fileService.updateFileNameById(request).getData();
        log.info(fileInfoEntity.toString());
    }

    /**
     * 阿里云盘-test-测试
     * 测试向阿里云服务端Session注册deviceId与Signature值
     * 2023-03-17 测试通过
     * deviceIdStr：04f0b6f06e9b7cd0ac8cc73b
     * signatureStr：1b8e931a9b8d240a7e07a679a1159b579282f26983a1ff025d14ee9fef453f1e6138163959a145ca1d239ea3e8b18b910a53aa426333497b78a670b62489f9d101
     */
    @Test
    void testCreateNewSessionWithAliyunDrive(){
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_APP_ID.getEnumsStringValue(),"25dzX3vbYqktVxyX");
        AliyunDrivePropertyUtils.put(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_USER_ID.getEnumsStringValue(),"2343026f11034d4f9c85a8f40930f0bb");
        BaseHeaderEntity headerEntity = BaseHeaderEntity.builder()
                .authType("Bearer ")
                .authToken("eyJhbGciOiJSUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImN1c3RvbUpzb24iOiJ7XCJjbGllbnRJZFwiOlwiMjVkelgzdmJZcWt0Vnh5WFwiLFwiZG9tYWluSWRcIjpcImJqMjlcIixcInNjb3BlXCI6W1wiRFJJVkUuQUxMXCIsXCJTSEFSRS5BTExcIixcIkZJTEUuQUxMXCIsXCJVU0VSLkFMTFwiLFwiVklFVy5BTExcIixcIlNUT1JBR0UuQUxMXCIsXCJTVE9SQUdFRklMRS5MSVNUXCIsXCJCQVRDSFwiLFwiT0FVVEguQUxMXCIsXCJJTUFHRS5BTExcIixcIklOVklURS5BTExcIixcIkFDQ09VTlQuQUxMXCIsXCJTWU5DTUFQUElORy5MSVNUXCIsXCJTWU5DTUFQUElORy5ERUxFVEVcIl0sXCJyb2xlXCI6XCJ1c2VyXCIsXCJyZWZcIjpcImh0dHBzOi8vd3d3LmFsaXl1bmRyaXZlLmNvbS9cIixcImRldmljZV9pZFwiOlwiY2UyMTJjZTIzZGM2NDJlOWIzNzQ1NDcxZTAzYjk5ZGJcIn0iLCJleHAiOjE2NzkwNDk2MzksImlhdCI6MTY3OTA0MjM3OX0.H8PXQbvfTzlRPpTZFSKfkQPPAnxN5vYni61NcWsaR_fjj5f2o9r7YiZwG6yBXRzFTw9-rGhqk6owZtstQYDmP1FPnBLuWV0y8otv3wyoLa4MTGDzHxFMokqX9VvB0OWh3lXiJdcOZCLzU58mHSzUclku-zWG3CbYMnSU7mvFVU4")
                .build();
        AliyunDriveHttpUtils instance = AliyunDriveHttpUtils.getInstance();
        boolean createSessionResult = instance.createNewSessionOrRenewSessionPost(headerEntity.getAuthType(), headerEntity.getAuthToken());
        log.info("注册新的deviceId是否成功了{}",createSessionResult);
        String deviceIdStr = (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_X_DEVICE_ID.getEnumsStringValue());
        String signatureStr = (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_ENUMS_SIGNATURE.getEnumsStringValue());
        log.info("deviceIdStr：{}",deviceIdStr);
        log.info("signatureStr：{}",signatureStr);
    }




    @Test
    void testUuid(){
        log.info(UUID.randomUUID().toString());
    }

}
