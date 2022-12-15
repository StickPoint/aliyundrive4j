package io.github.aliyundrive4j.service.impl;

import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.service.IAliyunDriveService;

/**
 * description: AliyunDriveServiceImpl
 *
 * @ClassName : AliyunDriveServiceImpl
 * @Date 2022/12/15 13:27
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service.impl
 */
public class AliyunDriveServiceImpl implements IAliyunDriveService {


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
}
