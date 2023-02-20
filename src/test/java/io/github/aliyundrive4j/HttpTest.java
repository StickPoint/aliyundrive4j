package io.github.aliyundrive4j;
import io.github.aliyundrive4j.common.entity.aliyun.AliyunBaseEntity;
import io.github.aliyundrive4j.common.entity.aliyun.CapacityDetail;
import io.github.aliyundrive4j.common.entity.aliyun.LoginQrcodeInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.LoginResultEntity;
import io.github.aliyundrive4j.common.entity.aliyun.PdsLoginResult;
import io.github.aliyundrive4j.common.entity.base.BaseHeaderEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
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
        BaseResponseEntity<AliyunBaseEntity> booleanBaseResponseEntity = userService.checkLoginQrcodeStatus("1676857028425","14b8365cd3c921bdf993359453abb6ae");
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
        loginQrcodeInfoEntity.setBizExt("eyJwZHNfbG9naW5fcmVzdWx0Ijp7InJvbGUiOiJ1c2VyIiwidXNlckRhdGEiOnsiRGluZ0RpbmdSb2JvdFVybCI6Imh0dHBzOi8vb2FwaS5kaW5ndGFsay5jb20vcm9ib3Qvc2VuZD9hY2Nlc3NfdG9rZW49MGI0YTkzNmQwZTk4YzA4NjA4Y2Q5OWY2OTMzOTNjMThmYTkwNWFhMDg2ODIxNTQ4NWEyODQ5NzUwMTkxNmZlYyIsIkVuY291cmFnZURlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsIkZlZWRCYWNrU3dpdGNoIjp0cnVlLCJGb2xsb3dpbmdEZXNjIjoiMzQ4NDgzNzIiLCJkaW5nX2Rpbmdfcm9ib3RfdXJsIjoiaHR0cHM6Ly9vYXBpLmRpbmd0YWxrLmNvbS9yb2JvdC9zZW5kP2FjY2Vzc190b2tlbj0wYjRhOTM2ZDBlOThjMDg2MDhjZDk5ZjY5MzM5M2MxOGZhOTA1YWEwODY4MjE1NDg1YTI4NDk3NTAxOTE2ZmVjIiwiZW5jb3VyYWdlX2Rlc2MiOiLE2rLixtq85NPQ0Ke3tMChx7AxMMP708O7p72ru/G1w9bVye3D4rfRu+HUsSIsImZlZWRfYmFja19zd2l0Y2giOnRydWUsImZvbGxvd2luZ19kZXNjIjoiMzQ4NDgzNzIifSwiaXNGaXJzdExvZ2luIjpmYWxzZSwibmVlZExpbmsiOmZhbHNlLCJsb2dpblR5cGUiOiJxckNvZGVMb2dpbiIsIm5pY2tOYW1lIjoiIiwibmVlZFJwVmVyaWZ5IjpmYWxzZSwiYXZhdGFyIjoiaHR0cHM6Ly9pbWcuYWxpeXVuZHJpdmUuY29tL2F2YXRhci9kZWZhdWx0LzAxLnBuZyIsImFjY2Vzc1Rva2VuIjoiZXlKaGJHY2lPaUpTVXpJMU5pSXNJblI1Y0NJNklrcFhWQ0o5LmV5SjFjMlZ5U1dRaU9pSXlNelF6TURJMlpqRXhNRE0wWkRSbU9XTTROV0U0WmpRd09UTXdaakJpWWlJc0ltTjFjM1J2YlVwemIyNGlPaUo3WENKamJHbGxiblJKWkZ3aU9sd2ljRXBhU1c1T1NFNHlaRnBYYXpoeFoxd2lMRndpWkc5dFlXbHVTV1JjSWpwY0ltSnFNamxjSWl4Y0luTmpiM0JsWENJNlcxd2lSRkpKVmtVdVFVeE1YQ0lzWENKR1NVeEZMa0ZNVEZ3aUxGd2lWa2xGVnk1QlRFeGNJaXhjSWxOSVFWSkZMa0ZNVEZ3aUxGd2lVMVJQVWtGSFJTNUJURXhjSWl4Y0lsTlVUMUpCUjBWR1NVeEZMa3hKVTFSY0lpeGNJbFZUUlZJdVFVeE1YQ0lzWENKQ1FWUkRTRndpTEZ3aVFVTkRUMVZPVkM1QlRFeGNJaXhjSWtsTlFVZEZMa0ZNVEZ3aUxGd2lTVTVXU1ZSRkxrRk1URndpTEZ3aVUxbE9RMDFCVUZCSlRrY3VURWxUVkZ3aVhTeGNJbkp2YkdWY0lqcGNJblZ6WlhKY0lpeGNJbkpsWmx3aU9sd2lYQ0lzWENKa1pYWnBZMlZmYVdSY0lqcGNJakJtWmpFM016ZzNZMk5qTXpRMk5XUTRNMk0wTXpVd01tTmxNemcyWVRkalhDSjlJaXdpWlhod0lqb3hOamMyT0RZME16STRMQ0pwWVhRaU9qRTJOelk0TlRjd05qaDkuQzdURXZxTzVBOWM1SVpxaEZDaU9SM01KNkFZVVJUb3BLRTllS2lsaF9LdXRJX1JYZnBiWDZiM3AxY2NvVnFkemhfVUZ1RlBSUEEyc3N0ZDVWQV9PdmZiajJNRGdJZzRqYkVBVnB4Tmg2TFFZX3RpZXA3OWtrZ3pleWdzcE5kQ3pYZTA5bWg2WGpiVVVGbXNZNTBTV0VjZnBQR1diaE1Hdkc4Q2dpSGktT1Q4IiwidXNlck5hbWUiOiIxODEqKio5NDgiLCJ1c2VySWQiOiIyMzQzMDI2ZjExMDM0ZDRmOWM4NWE4ZjQwOTMwZjBiYiIsImRlZmF1bHREcml2ZUlkIjoiNzM1NTQzOTAyIiwiZXhpc3RMaW5rIjpbXSwiZXhwaXJlc0luIjo3MjAwLCJleHBpcmVUaW1lIjoiMjAyMy0wMi0yMFQwMzozODo0OFoiLCJyZXF1ZXN0SWQiOiIyMzEwOUUzNS0wM0ZFLTREQkEtOUE3Ri01RTQyNDRGOThBNkQiLCJkYXRhUGluU2V0dXAiOmZhbHNlLCJzdGF0ZSI6IiIsInRva2VuVHlwZSI6IkJlYXJlciIsImRhdGFQaW5TYXZlZCI6ZmFsc2UsInJlZnJlc2hUb2tlbiI6IjBmZjE3Mzg3Y2NjMzQ2NWQ4M2M0MzUwMmNlMzg2YTdjIiwic3RhdHVzIjoiZW5hYmxlZCJ9fQ==");
        IAliyunDriveUserService userService = new AliyunDriveUserServiceImpl();
        log.info("通过扫码登录回调，最终将登录前需要的信息都传递给最终登录方法");
        BaseResponseEntity<AliyunBaseEntity> pdsLoginResultBaseResponseEntity = userService.doLoginWithQrcode(loginQrcodeInfoEntity);
        log.info("测试执行阿里云扫码登录-最终登录");
        PdsLoginResult data = (PdsLoginResult)pdsLoginResultBaseResponseEntity.getData();
        BaseResponseEntity<AliyunBaseEntity> finalResponseEntity = userService.doLogin(data);
        log.info(finalResponseEntity.getData().toString());
    }

    @Test
    void testRefreshToken() {
        log.info("开始测试刷新token");
        IAliyunDriveUserService aliyunDriveUserService = new AliyunDriveUserServiceImpl();
        BaseRequestEntity baseRequestEntity = new BaseRequestEntity();
        baseRequestEntity.setRefreshToken("7410a4827774440495702bd2acde215c");
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
}
