package io.github.aliyundrive4j.common.utils;
import com.ejlchina.data.ListMap;
import com.ejlchina.okhttps.HTTP;
import com.ejlchina.okhttps.HttpCall;
import com.ejlchina.okhttps.HttpResult;
import com.ejlchina.okhttps.OkHttps;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * description: AliyunHttpUtils
 *
 * @ClassName : AliyunHttpUtils
 * @Date 2022/12/15 11:52
 * @Author fntp
 * @PackageName io.github.aliyundrive4j.utils
 */
public class AliyunHttpUtils {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunHttpUtils.class);
    /**
     * 系统Http请求工具类实例
     */
    private static AliyunHttpUtils aliyunHttpRequest;
    /**
     * 系统Http请求对象
     */
    private static final HTTP HTTP = OkHttps.getHttp();

    private static final Map<Object,Object> SYS_INFO = PropertyUtils.initProperties();
    /**
     * 私有化构造，启用单例模式
     */
    private AliyunHttpUtils() { }

    /**
     * 获得系统HttpUtils对象实例
     * @return 返回一个HttpUtils请求对象
     */
    public static AliyunHttpUtils getInstance() {
        if (Objects.isNull(aliyunHttpRequest)) {
            synchronized (AliyunHttpUtils.class) {
                log.info(AliyunDriveInfoEnums.ALIYUN_DRIVE_INFO_TEMPLATE_001.getEnumsStringValue());
                aliyunHttpRequest = new AliyunHttpUtils();
            }
        }
        return aliyunHttpRequest;
    }

    /**
     * 直接发起Get请求 不携带任何参数
     * @param requestUrl 请求地址
     * @return 返回一个请求的结果字符串
     */
    public String doGetWithNoParams(String requestUrl) {
        HttpCall httpCall = HTTP.async(requestUrl).get();
        HttpResult result = httpCall.getResult();
        return getResponse(result);
    }

    /**
     * 进行基础的常见的Get请求
     * @param requestUrl 请求地址
     * @param requestParams 请求参数
     * @return 返回一个请求的最终json响应字符串
     */
    public String doGetWithParams(String requestUrl, Map<String, String> requestParams){
        HttpCall httpCall = HTTP.async(requestUrl) .addUrlPara(requestParams).get();
        HttpResult httpResult = httpCall.getResult();
        return getResponse(httpResult);
    }

    /**
     * 封装内部的返回基本响应的方法
     * @param httpResult httpResult 请求结果对象
     * @return 返回一个相应的字符串内容
     */
    private String getResponse(HttpResult httpResult) {
        String responseStr;
        if (Objects.isNull(httpResult)) {
            throw new AliyunDriveException(AliyunDriveCodeEnums.valueOf("a"));
        }
        if (AliyunDriveInfoEnums.ALIYUN_DRIVE_HTTP_STATUS_OK.getEnumsIntegerValue() == httpResult.getStatus()) {
            responseStr = httpResult.getBody().toString();
            httpResult.getBody().close();
            httpResult.close();
        }else {
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_HTTP);
        }
        return responseStr;
    }

    /**
     * 获取阿里云扫码登录的二维码
     * 扫码登录-这是扫码登录的第一步
     */
    public String getQrCodeUrl(){
        HttpResult httpResult = HTTP.async("https://passport.aliyundrive.com/newlogin/qrcode/generate.do?appName=aliyun_drive&fromSite=52&appName=aliyun_drive&appEntrance=web")
                .get().getResult();
        return getResponse(httpResult);
    }

    /**
     * 查询二维码状态
     * 扫码登录-这是扫码登录的第二步
     * @param t 阿里云盘扫码登录参数一 名称无实际意义，字段含义：时间戳
     * @param ck 阿里云盘扫码登录参数二 名称无实际意义，字段含义：分布式加密子串
     * @return 返回一个布尔值 包含两种状态 （1）false 失效状态；（2）true 有效状态。
     */
    public String queryQrCode(String t,String ck){
        // 查询二维码状态
        HttpResult httpResult = HTTP.async("https://passport.aliyundrive.com/newlogin/qrcode/query.do?appName=aliyun_drive")
                .addBodyPara("t", t)
                .addBodyPara("ck", ck)
                .post().getResult();
        // 获得请求结果
        return getResponse(httpResult);
    }

    /**
     * getToken（1）第一步 先去获得Cookie
     * 通过访问网页来获得Cookie
     * @return 返回一个cookie
     */
    private StringBuilder getAliyunDriveCookie(String requestUrl) {
        ListMap<String> headers = HTTP.async(requestUrl)
                .addHeader("User-Agent",AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_USER_AGENT.getEnumsStringValue())
                .addHeader("referer","https://aliyundrive.com/")
                .get().getResult().allHeaders();
        StringBuilder resultValue = new StringBuilder();
        headers.forEach((key,value)->{
            if (AliyunDriveInfoEnums.ALIYUN_DRIVE_COMMON_STR_SET_COOKIE.getEnumsStringValue().equals(key)){
                resultValue.append(value).append(";");
            }
        });
        return resultValue;
    }

    /**
     * 先去获得requestCode请求码
     * 通过轮训查询二维码状态我们已经能拿到用户扫码登录确认之后的pds登录信息了，但是想要访问网盘还是不够的，
     * 还是需要借助已经获得信息再次继续请求网盘
     * 先从扫码登录获取的accessToken中提取accessToken信息
     * 然后通过访问登录页面与其他页面获得cookie信息
     * 最后组装cookie然后请求获得token地址就可以拿到最后的token
     * 期间需要获得clientId，requestCode，accessToken，组合Cookie信息等
     * 操作略有繁琐，但是好在最后Token能够正常获取
     * @param accessToken 传入一个accessToken
     * @return 返回一个授权地址
     */
    private String getAliyunOriginalToken(String accessToken) {
        // 要想完成登录，首先第一步 获取clientId
        HttpResult clientIdResult = HTTP.async("https://aliyundrive.com/sign/in")
                .addHeader("User-Agent", AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_USER_AGENT.getEnumsStringValue())
                .addHeader("referer", "https://aliyundrive.com/").get().getResult();
        String getClientIdResp = clientIdResult.getBody().toString();
        int startIndex = getClientIdResp.indexOf("client_id:");
        int endIndex = getClientIdResp.indexOf("redirect_uri");
        String cacheString = getClientIdResp.substring(startIndex, endIndex);
        // 得到clientId
        String clientId = cacheString.replace("client_id: '", "")
                .replace("',", "")
                .replaceAll("\\s*","");
        // 第二步 获得授权地址
        String authUrl = "https://auth.aliyundrive.com/v2/oauth/authorize?client_id=" +clientId+
                "&redirect_uri=https://www.aliyundrive.com/sign/callback&response_type=code&login_type=custom&state={\"origin\":\"https://aliyundrive.com\"}";
        StringBuilder aliyunDriveCookie1 = getAliyunDriveCookie(authUrl);
        StringBuilder aliyunDriveCookie2 = getAliyunDriveCookie("https://passport.aliyundrive.com/mini_login.htm?lang=zh_cn&appName=aliyun_drive");
        // 第三步 获得最终cookie字符串
        String finalCookieStr = aliyunDriveCookie1.append(aliyunDriveCookie2).toString();
        HttpResult httpResult = HTTP.async((String) SYS_INFO.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_LOGIN_KEY.getEnumsStringValue()))
                .bodyType(AliyunDriveInfoEnums.ALIYUN_DRIVE_HTTP_REQUEST_CONTENT_TYPE_JSON.getEnumsStringValue())
                .addBodyPara("token", accessToken)
                .addHeader("accept", "application/json, text/plain, */*")
                .addHeader(AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_HEADER_NAME_CONTENT_TYPE.getEnumsStringValue(),
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_HEADER_VALUE_JSON.getEnumsStringValue())
                .addHeader("cookie", finalCookieStr)
                .post().getResult();
        String respBody = httpResult.getBody().toString();
        JsonElement jsonElement = JsonParser.parseString(respBody);
        String codeStr;
        if (jsonElement.isJsonObject()) {
            String gotoUrlStr = jsonElement.getAsJsonObject().get("goto").getAsString();
            log.info(gotoUrlStr);
            int codeStartIndex = gotoUrlStr.indexOf("code=")+"code=".length();
            int codeEndIndex = gotoUrlStr.indexOf("&domain_id");
            codeStr = gotoUrlStr.substring(codeStartIndex, codeEndIndex).replaceAll("\\s*","");
            log.info(codeStr);
        }else {
            // 不是JSON对象
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
        }
        HttpResult finalResult = HTTP.async((String) SYS_INFO.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_GET_KEY.getEnumsStringValue()))
                .bodyType(AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_TYPE_JSON.getEnumsStringValue())
                .addHeader(AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_HEADER_NAME_CONTENT_TYPE.getEnumsStringValue(),
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_REQUEST_HEADER_VALUE_JSON.getEnumsStringValue())
                .addHeader("accept", "*/*")
                .addBodyPara("code", codeStr).post().getResult();
        String respResult = finalResult.getBody().toString();
        if (Objects.isNull(respResult)) {
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_HTTP);
        }
        log.info(respResult);
        return respResult;
    }

    public String getToken(String accessToken){
        // 这是内部方法不允许直接调用，所以隐藏起来不对外暴露
        return getAliyunOriginalToken(accessToken);
    }

    /**
     * 刷新阿里云登录token
     * @param refreshTokenStr 刷新token字符串
     * @return 返回一个登录最终结果
     */
    public String refreshToken(String refreshTokenStr) {
        Map<String,String> refreshTokenRequestParamMap = new LinkedHashMap<>();
        refreshTokenRequestParamMap.put("grant_type","refresh_token");
        refreshTokenRequestParamMap.put("refresh_token",refreshTokenStr);
        HttpResult httpResult = HTTP.async((String) SYS_INFO.get(
                AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_TOKEN_REFRESH_KEY.getEnumsStringValue()))
                .addBodyPara(refreshTokenRequestParamMap)
                .bodyType("json").post().getResult();
        if (AliyunDriveCodeEnums.ALI_SUCCESS.getCode()==httpResult.getStatus()){
            // 请求成功
            return httpResult.getBody().toString();
        }
        return null;
    }

    /**
     * 阿里云盘交互请求
     * @param requestUrl 传入一个请求地址
     * @param postBodyParam 传入一个请求体参数
     * @param token 传入一个token
     * @return 返回一个请求结果
     */
    @SuppressWarnings("unused")
    public String doPost(String requestUrl,Map<String,String> postBodyParam,String token) {
        AtomicInteger errorCounter = new AtomicInteger(1);
        try {
            HttpResult httpResult = HTTP.async(requestUrl)
                    .addBodyPara(postBodyParam)
                    .addHeader("authorization", token)
                    .addHeader("Content-Type", "application/json")
                    .post().getResult();
            if (AliyunDriveCodeEnums.ALI_BLOCK.getCode() == httpResult.getStatus()){
                log.warn(AliyunDriveCodeEnums.ALI_BLOCK.getMessage());
                Thread.sleep(5000);
                return doPost(requestUrl,postBodyParam,token);
            }else if (AliyunDriveCodeEnums.ALI_SUCCESS.getCode()==httpResult.getStatus()) {
                return httpResult.getBody().toString();
            }
        } catch (AliyunDriveException exception) {
            if (errorCounter.get()>6){
                return null;
            }else {
                return doPost(requestUrl,postBodyParam,token);
            }
        }catch (InterruptedException interruptedException) {
            log.error(interruptedException.getCause().toString());
            Thread.currentThread().interrupt();
            return null;
        }
        return null;
    }

    /**
     * 文件上传请求
     * @param requestUrl 请求地址
     * @param paramBody 请求参数
     * @param token 请求所需要携带的token
     * @return 返回一个文件上传之后的响应
     */
    @SuppressWarnings("unused")
    public String doFileUploadPost(String requestUrl,Map<String,String> paramBody,String token) {
        return null;
    }

    /**
     * 带有Auth认证的基础请求
     * @param requestUrl 请求地址
     * @param tokenType token类型
     * @param token token内容
     * @param paramBody 参数内容
     * @return 返回一个相应结果
     */
    public String doPostWithAuth(String requestUrl,String tokenType,String token,Map<String,String> paramBody){
        HttpResult httpResult = HTTP.async(requestUrl)
                .bodyType("json")
                .addBodyPara(Optional.ofNullable(paramBody).orElse(Collections.emptyMap()))
                .addHeader("Authorization", tokenType.concat(token))
                .post().getResult();
        if (httpResult.getStatus()== AliyunDriveInfoEnums.ALIYUN_DRIVE_HTTP_STATUS_OK.getEnumsIntegerValue()) {
            return httpResult.getBody().toString();
        }
        // HTTP请求异常
        return StringUtils.emptyString();
    }

}
