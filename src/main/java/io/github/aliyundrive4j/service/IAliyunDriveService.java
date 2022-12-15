package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.base.BaseEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

/**
 * description: IAliyunDriveService
 *
 * @ClassName : IAliyunDriveService
 * @Date 2022/12/15 13:26
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service
 */
public interface IAliyunDriveService {

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

}
