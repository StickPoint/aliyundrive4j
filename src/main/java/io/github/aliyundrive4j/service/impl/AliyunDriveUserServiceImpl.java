package io.github.aliyundrive4j.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import io.github.aliyundrive4j.common.entity.aliyun.LoginQrcodeInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.PdsLoginResult;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.common.utils.PropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Map;
import java.util.Objects;

/**
 * description: AliyunDriveServiceImpl
 *
 * @ClassName : AliyunDriveServiceImpl
 * @Date 2022/12/15 13:27
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service.impl
 */
public class AliyunDriveUserServiceImpl implements IAliyunDriveUserService {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDriveUserServiceImpl.class);
    /**
     * 系统初始化加载一个本地配置MAP
     * 为什么这么写呢，我考虑了几点：
     * （1）这个项目采用了模块化，引入了module-info，在降低耦合的需要下，我尽可能少的引入新的依赖，
     *      不使用Spring来管理bean，并且对象较少，可以直接引用，这是提供给第三方应用使用的，我们尽量
     *      不使用反射。
     * （2）这是作为依赖jar运行在main程序中，他不会在主程序中直接进入runtime，
     *      我仅仅需要对外提供一个加载本地配置的map即可，让外部程序去做存储，sdk本身在没有接入bean管理
     *      的前提下，本身是无法长期驻留在内存中的，数据会丢失。那么只要服务对象存在于应用内部，就可以保留map
     */
    private static final Map<Object,Object> SYS_INFO_MAP = PropertyUtils.initProperties();

    private static final AliyunHttpUtils HTTP_CLIENT = AliyunHttpUtils.getInstance();

    /**
     * 二维码扫码登录
     * @return 扫码登录，扫码登录后返回用户的token
     */
    @Override
    public BaseResponseEntity loginWithQrcodeImage() {
        return null;
    }

    /**
     * 刷新token
     *
     * @param baseRequestEntity 传入一个包含以下三个参数的对象：
     * {"grant_type": "refresh_token","app_id": "pJZInNHN2dZWk8qg","refresh_token": "c65bf6d104ac510885c0124d74c4a099"}
     * @return 返回一个刷新Token之后的基础响应
     */
    @Override
    public BaseResponseEntity refreshUserToken(BaseRequestEntity baseRequestEntity) {

        return null;
    }

    /**
     * 账户登出操作，不需要传递任何按参数
     *
     * @return 返回一个退出登录的响应结果
     */
    @Override
    public BaseResponseEntity<String> signOut() {
        // 拿到请求地址
        String requestUrl = (String) SYS_INFO_MAP.get(AliyunDriveInfoEnums.
                ALIYUN_DRIVE_PROPERTIES_KEY_SIGN_OUT.getEnumsStringValue());
        // 响应请求结果
        return BaseResponseEntity.success(HTTP_CLIENT.doGetWithNoParams(requestUrl));
    }

    /**
     * 检查账号是否存在
     *
     * @param baseRequestEntity 检查账号是否存在的请求对象，请求需要包含以下鸡哥参数
     * { "app_id": "pJZInNHN2dZWk8qg", "phone_number": "151***111", "phone_region": "86" }
     * @return 返回一个检查账号是否存在的基础响应，响应示例：{ "is_exist": true }
     */
    @Override
    public BaseResponseEntity<Boolean> checkExist(BaseRequestEntity baseRequestEntity) {
        // 校验用户状态
        return null;
    }

    /**
     * 校验当前二维码是否有效
     * @param timestamp 时间戳
     * @param ckCode    ck校验码
     * @return 返回一个基础响应，内容是布尔值，有两种结果：（1）false  二维码仍旧有效； （2）true二维码已过期；
     */
    @Override
    public BaseResponseEntity<Boolean> checkLoginQrcodeStatus(String timestamp, String ckCode) {
        String requestJsonResult = HTTP_CLIENT.queryQrCode(timestamp, ckCode);
        log.info(requestJsonResult);
        JsonElement resultElement = JsonParser.parseString(requestJsonResult);
        if (resultElement.isJsonObject()) {
            Gson gson = new Gson();
            JsonObject object = resultElement.getAsJsonObject().get(AliyunDriveInfoEnums.ALIYUN_DRIVE_RESP_JSON_KEY_CONTENT.getEnumsStringValue())
                    .getAsJsonObject().get(AliyunDriveInfoEnums.ALIYUN_DRIVE_RESP_JSON_KEY_DATA.getEnumsStringValue()).
                    getAsJsonObject();
            LoginQrcodeInfoEntity loginQrcodeInfoEntity = gson.fromJson(object.toString(), LoginQrcodeInfoEntity.class);
            return BaseResponseEntity
                    .success(AliyunDriveInfoEnums.ALIYUN_DRIVE_LOGIN_QR_CODE_STATUS_NEW .getEnumsStringValue().equals(loginQrcodeInfoEntity.getQrCodeStatus()));
        }
        // 相应结果不是JSON肯定是HTTP请求异常
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_HTTP);
    }

    /**
     * 获得扫码登录的二维码信息
     *
     * @return 返回一个二维码信息，使用者需要使用的是content内容以及ckCode与timestamp时间戳数据。
     */
    @Override
    public BaseResponseEntity<LoginQrcodeInfoEntity> getLoginQrcodeInfo() {
        // 第一步获得请求响应的JSON字符串
        String respJsonStr = HTTP_CLIENT.getQrCodeUrl();
        // 第二步 解析JSON字符串
        JsonElement respJson = JsonParser.parseString(respJsonStr);
        LoginQrcodeInfoEntity loginQrcodeInfoEntity = new LoginQrcodeInfoEntity();
        if (respJson.isJsonObject()) {
            JsonObject respJsonObject = respJson.getAsJsonObject();
            JsonElement dataJsonElement = respJsonObject.get(AliyunDriveInfoEnums.
                    ALIYUN_DRIVE_RESP_JSON_KEY_CONTENT.getEnumsStringValue())
                    .getAsJsonObject().get(AliyunDriveInfoEnums.
                    ALIYUN_DRIVE_RESP_JSON_KEY_DATA.getEnumsStringValue());
            if (dataJsonElement.isJsonObject()) {
                JsonObject dataJsonObject = dataJsonElement.getAsJsonObject();
                loginQrcodeInfoEntity.setTimestamp(dataJsonObject.get(AliyunDriveInfoEnums.
                        ALIYUN_DRIVE_RESP_JSON_KEY_T.getEnumsStringValue()).getAsString());
                loginQrcodeInfoEntity.setCodeContent(dataJsonObject.get(AliyunDriveInfoEnums.
                        ALIYUN_DRIVE_RESP_JSON_KEY_CODE_CONTENT.getEnumsStringValue()).getAsString());
                loginQrcodeInfoEntity.setCkCode(dataJsonObject.get(AliyunDriveInfoEnums.
                        ALIYUN_DRIVE_RESP_JSON_KEY_CK.getEnumsStringValue()).getAsString());
                loginQrcodeInfoEntity.setResultCode(dataJsonObject.get(AliyunDriveInfoEnums.
                        ALIYUN_DRIVE_RESP_JSON_KEY_RESULT_CODE.getEnumsStringValue()).getAsInt());
            }
            return BaseResponseEntity.success(loginQrcodeInfoEntity);
        }
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_HTTP);
    }

    /**
     * 扫码登录操作
     * 扫码登录之后执行的登录操作
     * @param loginQrcodeInfoEntity 扫码登录成功之后返回的信息，实际上我们需要的是这个实体里面的信息
     * @return 返回一个登录之后的 refreshToken
     */
    @Override
    public String doLogin(LoginQrcodeInfoEntity loginQrcodeInfoEntity) {
        if (Objects.isNull(loginQrcodeInfoEntity)) {
            // 如果传入的参数是空的
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_PARAM);
        }
        // 首先获得扫码登录之后的结果
        String bizExt = loginQrcodeInfoEntity.getBizExt();
        Decoder decoder = Base64.getDecoder();
        // 将响应的JSON字符串解析为Java对象
        String resultJson= new String(decoder.decode(bizExt.getBytes(StandardCharsets.UTF_8)),StandardCharsets.UTF_8);
        JsonElement jsonElement = JsonParser.parseString(resultJson);
        if (jsonElement.isJsonObject()) {
            // 如果确定resp响应是一个json字符串之后
            JsonObject respJsonObject = jsonElement.getAsJsonObject().get(AliyunDriveInfoEnums.
                    ALIYUN_DRIVE_LOGIN_PDS_LOGIN_RESULT.getEnumsStringValue()).getAsJsonObject();
            // 开始解析
            Gson gson = new Gson();
            // 映射完成
            try {
                // 执行对象解析
                PdsLoginResult pdsLoginResult = gson.fromJson(respJsonObject, PdsLoginResult.class);
                if (Objects.nonNull(pdsLoginResult)) {
                    log.info(pdsLoginResult.toString());
                    return pdsLoginResult.getAccessToken();
                }
            } catch (JsonSyntaxException e) {
                throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
            }
        }
        // 响应给调用对象，这不是JSON对象
        //return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
        return null;
    }
}
