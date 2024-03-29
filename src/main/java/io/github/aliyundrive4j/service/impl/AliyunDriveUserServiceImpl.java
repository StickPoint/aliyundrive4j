package io.github.aliyundrive4j.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;
import io.github.aliyundrive4j.common.entity.aliyun.AliyunBaseEntity;
import io.github.aliyundrive4j.common.entity.aliyun.CapacityDetail;
import io.github.aliyundrive4j.common.entity.aliyun.LoginQrcodeInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.LoginResultEntity;
import io.github.aliyundrive4j.common.entity.aliyun.PdsLoginResult;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunDriveHttpUtils;
import io.github.aliyundrive4j.common.utils.AliyunDrivePropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.util.Base64;
import java.util.Base64.Decoder;
import java.util.Collections;
import java.util.List;
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
     * 全局通用gson对象
     */
    private static final Gson GSON = new Gson();

    private static final AliyunDriveHttpUtils HTTP_CLIENT = AliyunDriveHttpUtils.getInstance();


    /**
     * 刷新token
     *
     * @param baseRequestEntity 传入一个包含以下三个参数的对象：
     * {"grant_type": "refresh_token","app_id": "pJZInNHN2dZWk8qg","refresh_token": "c65bf6d104ac510885c0124d74c4a099"}
     * @return 返回一个刷新Token之后的基础响应
     */
    @Override
    public BaseResponseEntity<AliyunBaseEntity> refreshUserToken(BaseRequestEntity baseRequestEntity) {
        String refreshTokenJsonResp = HTTP_CLIENT.refreshToken(baseRequestEntity.getRefreshToken());
        if (Objects.isNull(refreshTokenJsonResp)) {
            // 请求失败
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_HTTP);
        }
        // 请求成功
        
        LoginResultEntity loginResultEntity;
        try {
            loginResultEntity = GSON.fromJson(refreshTokenJsonResp, LoginResultEntity.class);
        } catch (JsonSyntaxException e) {
            log.error(e.getCause().toString());
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
        }
        if (Objects.nonNull(loginResultEntity)) {
            return BaseResponseEntity.success(loginResultEntity);
        }
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_HTTP);
    }

    /**
     * 账户登出操作，不需要传递任何按参数
     *
     * @return 返回一个退出登录的响应结果
     */
    @Override
    public BaseResponseEntity<String> signOut() {
        // 拿到请求地址
        String requestUrl = (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.
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
     * 获得扫码登录的二维码信息
     * 扫码登录过程第一步骤：获得二维码信息
     * 注意哦！扫码登录过程第二步骤是生成二维码，这一步交由用户自行完成，SDK不提供二维码base64数据，直接提供二维码的内容
     *
     * @return 返回一个二维码信息，使用者需要使用的是content内容以及ckCode与timestamp时间戳数据。
     */
    @Override
    public BaseResponseEntity<AliyunBaseEntity> getLoginQrcodeInfo() {
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
     * 阿里云盘扫码登录-确认用户登录状态
     * 扫码登录过程第三步骤：确认状态
     * 注意：当用户点击了确认登陆之后，只能查询一次，如果此时不记录用户数据，将会被丢失，无法二次查询，数据自动过期。
     * 存在三种扫码状态
     * （1）未扫码，二维码仍在有效期
     * （2）已扫码，但用户并未确认登录
     * （3）已扫码，用户已确认登录
     * @param timestamp 时间戳
     * @param ckCode    ck校验码
     * @return 返回一个基础响应，内容是布尔值，有两种结果：（1）false  二维码仍旧有效； （2）true二维码已过期；
     */
    @Override
    public BaseResponseEntity<AliyunBaseEntity> checkLoginQrcodeStatus(String timestamp, String ckCode) {
        String requestJsonResult = HTTP_CLIENT.queryQrCode(timestamp, ckCode);
        log.info(requestJsonResult);
        JsonElement resultElement = JsonParser.parseString(requestJsonResult);
        if (resultElement.isJsonObject()) {
            JsonObject object = resultElement.getAsJsonObject().get(AliyunDriveInfoEnums.ALIYUN_DRIVE_RESP_JSON_KEY_CONTENT.getEnumsStringValue())
                    .getAsJsonObject().get(AliyunDriveInfoEnums.ALIYUN_DRIVE_RESP_JSON_KEY_DATA.getEnumsStringValue()).
                    getAsJsonObject();
            LoginQrcodeInfoEntity loginQrcodeInfoEntity = GSON.fromJson(object.toString(), LoginQrcodeInfoEntity.class);
            return BaseResponseEntity.success(loginQrcodeInfoEntity);
        }
        // 相应结果不是JSON肯定是HTTP请求异常
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_HTTP);
    }

    /**
     * 二维码扫码登录-扫码登录
     * 扫码登录过程第四步骤：登录回调
     * 最终登录前的扫码登录回调事件
     * 登录流程：（1）获取二维码内容；（2）生成二维码；（3）客户端扫码；（4）
     * 扫码登录之后执行的登录操作
     * @param loginQrcodeInfoEntity 扫码登录成功之后返回的信息，实际上我们需要的是这个实体里面的信息
     * @return 返回一个登录之后的 refreshToken
     */
    @Override
    public BaseResponseEntity<AliyunBaseEntity> doLoginWithQrcode(LoginQrcodeInfoEntity loginQrcodeInfoEntity) {
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
            try {
                // 映射完成 执行对象解析
                PdsLoginResult pdsLoginResult = GSON.fromJson(respJsonObject, PdsLoginResult.class);
                if (Objects.nonNull(pdsLoginResult)) {
                    return BaseResponseEntity.success(pdsLoginResult);
                }
            } catch (JsonSyntaxException e) {
                throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
            }
        }
        // 响应给调用对象，这不是JSON对象
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }

    /**
     * 真正的登录接口
     * 扫码登录过程第五步骤：最终登录
     *
     * @param pdsLoginResult 传入一个网盘登录结果对象
     * @return 返回一个网盘登录最终结果对象
     */
    @Override
    public BaseResponseEntity<AliyunBaseEntity> doLogin(PdsLoginResult pdsLoginResult) {
        if (Objects.isNull(pdsLoginResult.getAccessToken())) {
            // 如果accessToken是空的直接抛出异常
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_ACCESS_TOKEN_IS_NULL);
        }
        // 返回最终登录的token
        String resultJsonResp = HTTP_CLIENT.getToken(pdsLoginResult.getAccessToken());
        LoginResultEntity finalLoginResult = GSON.fromJson(resultJsonResp, LoginResultEntity.class);
        if (Objects.nonNull(finalLoginResult)) {
            return BaseResponseEntity.success(finalLoginResult);
        }
        return BaseResponseEntity.error(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }

    /**
     * 获得用户容量明细
     * @param baseRequest 基础请求对象
     * @return 返回一个阿里云盘用户容量明细
     */
    @Override
    public BaseResponseEntity<List<CapacityDetail>> getCapacityDetails(BaseRequestEntity baseRequest) {
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                AliyunDriveInfoEnums.ALIYUN_DRIVE_PROPERTIES_KEY_USER_CAPACITY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), Collections.emptyMap());
        if (!resp.isEmpty() && !resp.isBlank()) {
            JsonElement respJsonElement = JsonParser.parseString(resp);
            JsonObject jsonObject = respJsonElement.getAsJsonObject();
            JsonArray asJsonArray = jsonObject.getAsJsonArray("capacity_details");
            Type listType = new TypeToken<List<CapacityDetail>>(){}.getType();
            List<CapacityDetail> detailList = GSON.fromJson(asJsonArray.toString(), listType);
            if (!detailList.isEmpty()) {
                return BaseResponseEntity.success(detailList);
            }
            log.warn("【AliyunDrive-4j】当前获得的用户容量信息为null");
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }

    /**
     * 获得用户已使用的容量明细
     *
     * @param baseRequest 基础请求对象
     * @return 返回一个阿里云盘用户容量明细
     */
    @Override
    public BaseResponseEntity<CapacityDetail> getAlreadyInUsedCapacity(BaseRequestEntity baseRequest) {
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_PROPERTIES_KEY_USER_ALREADY_IN_USED_CAPACITY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), Collections.emptyMap());
        if (!resp.isEmpty() && !resp.isBlank()) {
            CapacityDetail capacityDetail = GSON.fromJson(resp, CapacityDetail.class);
            if (Objects.nonNull(capacityDetail)) {
                return BaseResponseEntity.success(capacityDetail);
            }
            log.warn("【AliyunDrive-4j】当前获得的用户容量信息为null");
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }


}
