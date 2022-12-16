package io.github.aliyundrive4j.service.impl;

import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.common.utils.PropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveUserService;
import java.util.Map;

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
     * 账号密码登录
     * @return 返回用户的token等信息，此类型暂未进行封装处理
     */
    @Override
    public BaseResponseEntity loginWithUserNameAndPassword() {
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
    public BaseResponseEntity checkExist(BaseRequestEntity baseRequestEntity) {

        return null;
    }
}
