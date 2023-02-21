package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.FolderServiceRespEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;

import java.util.List;

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
    BaseResponseEntity<FolderServiceRespEntity> createFolder(BaseRequestEntity baseRequest);

    /**
     * 删除文件夹
     * @param baseRequest 基础请求参数
     * @return 返回一个删除文件夹的响应
     */
    BaseResponseEntity<FolderServiceRespEntity> deleteFolder(BaseRequestEntity baseRequest);

    /**
     * 修改文件夹
     * @param baseRequest 基础请求参数
     * @return 返回一个修改文件夹的响应
     */
    BaseResponseEntity<FolderServiceRespEntity> updateFolder(BaseRequestEntity baseRequest);

    /**
     * 查询文件夹
     * @param baseRequest 基础请求参数
     * @return 返回一个查询文件夹的响应
     */
    BaseResponseEntity<List<FolderServiceRespEntity>> getFolderList(BaseRequestEntity baseRequest);

}
