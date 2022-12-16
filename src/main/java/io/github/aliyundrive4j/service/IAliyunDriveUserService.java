package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.LoginQrcodeInfoEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

/**
 * description: IAliyunDriveUserService
 *
 * @ClassName : IAliyunDriveUserService
 * @Date 2022/12/15 15:50
 * @Author fntp
 * @PackageName io.github.aliyundrive4j.service
 */
public interface IAliyunDriveUserService {

    /**
     * 二维码扫码登录
     * @return 扫码登录，扫码登录后返回用户的token，此类型暂未进行封装处理
     */
    BaseResponseEntity loginWithQrcodeImage();


    /**
     * 刷新token
     * @param baseRequestEntity 传入一个包含以下三个参数的对象：
     * {"grant_type": "refresh_token","app_id": "pJZInNHN2dZWk8qg","refresh_token": "c65bf6d104ac510885c0124d74c4a099"}
     * @return 返回一个刷新Token之后的基础响应
     */
    BaseResponseEntity refreshUserToken(BaseRequestEntity baseRequestEntity);

    /**
     * 账户登出操作，不需要传递任何按参数
     * @return 返回一个退出登录的响应结果
     */
    BaseResponseEntity<String> signOut();

    /**
     * 检查账号是否存在
     * @param baseRequestEntity 检查账号是否存在的请求对象，请求需要包含以下鸡哥参数
     *  { "app_id": "pJZInNHN2dZWk8qg", "phone_number": "151***111", "phone_region": "86" }
     * @return 返回一个检查账号是否存在的基础响应，响应示例：{ "is_exist": true }
     */
    BaseResponseEntity checkExist(BaseRequestEntity baseRequestEntity);

    /**
     * 校验当前二维码是否有效
     * @param timestamp 时间戳
     * @param ckCode ck校验码
     * @return 返回一个基础响应，内容是布尔值，有两种结果：（1）false 二维码已过期；（2）true 二维码仍旧有效；
     */
    BaseResponseEntity<Boolean> checkLoginQrcodeStatus(String timestamp, String ckCode);

    /**
     * 获得扫码登录的二维码信息
     * @return 返回一个二维码信息，使用者需要使用的是content内容以及ckCode与timestamp时间戳数据。
     */
    BaseResponseEntity<LoginQrcodeInfoEntity> getLoginQrcodeInfo();

}
