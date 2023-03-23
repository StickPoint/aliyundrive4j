package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.FileDownloadInfoEntity;
import io.github.aliyundrive4j.common.entity.aliyun.FileInfoEntity;
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
public interface IAliyunDriveFileService {

    BaseResponseEntity<List<FileInfoEntity>> getCloudFolderList();

    /**
     * 根据目录名称获得文件列表
     * @param baseRequest 基础请求对象
     * @return 返回一个文件列表
     */
    BaseResponseEntity<List<FileInfoEntity>> getFileList(BaseRequestEntity baseRequest);


    /**
     * 根据文件id获得文件详细信息
     * @param baseRequest 文件id 网盘id
     * @return 返回一个文件详细信息
     */
    BaseResponseEntity<FileInfoEntity> getFileInfoById(BaseRequestEntity baseRequest);

    /**
     * 根据文件id删除文件
     * @param baseRequest 实际上需要的是：fileId + driveId 传入一个文件id
     * @return 返回一个删除结果
     */
    BaseResponseEntity<Boolean> deleteFileById(BaseRequestEntity baseRequest);

    /**
     * 创建阿里云盘文件
     * @param fileInfo 返回一个创建好的文件信息
     * @return 返回一个文件信息实体
     */
    BaseResponseEntity<FileInfoEntity> createFileInfo(FileInfoEntity fileInfo);

    /**
     * 根据文件id更新文件信息
     * @param baseRequest 传入一个文件id
     * @return 返回更新之后的文件结果
     */
    BaseResponseEntity<FileInfoEntity> updateFileNameById(BaseRequestEntity baseRequest);

    /**
     * 获取文件下载信息
     * @param baseRequest 传入一个获取文件下载请求
     * @return 返回文件下载请求信息 包含请求方式，请求方法，请求地址，请求参数，请求路径等等
     */
    BaseResponseEntity<FileDownloadInfoEntity> getFileDownloadInfo(BaseRequestEntity baseRequest);


}
