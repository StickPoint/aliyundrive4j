package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.FolderMadeRespEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

/**
 * description: IAliyunDriveService
 *
 * @ClassName : IAliyunDriveService
 * @Date 2022/12/15 13:26
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service
 */
public interface IAliyunDriveFolderService {

    /**
     * 创建文件夹
     * @param baseRequest 基础请求参数
     * @return 返回一个文件夹创建的响应
     */
    BaseResponseEntity<FolderMadeRespEntity> createFolder(BaseRequestEntity baseRequest);

}
