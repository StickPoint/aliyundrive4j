package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

/**
 * description: IAliyunDriveUserService
 *
 * @ClassName : IAliyunDriveUserService
 * @Date 2022/12/15 15:50
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service
 */
public interface IAliyunDriveUserService {

    /**
     * 二维码扫码登录
     * @return 扫码登录，扫码登录后返回用户的token，此类型暂未进行封装处理
     */
    BaseResponseEntity loginWithQrcodeImage();

    /**
     * 账号密码登录
     * @return 返回用户的token等信息，此类型暂未进行封装处理
     */
    BaseResponseEntity loginWithUserNameAndPassword();

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

}
