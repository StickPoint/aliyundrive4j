package io.github.aliyundrive4j.service;

import io.github.aliyundrive4j.common.entity.aliyun.FileInfoEntity;
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
     * @param folderName 目录名称
     * @return 返回一个文件列表
     */
    BaseResponseEntity<List<FileInfoEntity>> getFileListFromFolder(String folderName);

    /**
     * 根据文件id获得文件详细信息
     * @param fileId 文件id
     * @return 返回一个文件详细信息
     */
    BaseResponseEntity<FileInfoEntity> getFileInfoById(String fileId);

    /**
     * 根据文件id删除文件
     * @param fileId 传入一个文件id
     * @return 返回一个删除结果
     */
    BaseResponseEntity<Boolean> deleteFileById(String fileId);

    /**
     * 创建阿里云盘文件
     * @param fileInfo 返回一个创建好的文件信息
     * @return 返回一个文件信息实体
     */
    BaseResponseEntity<FileInfoEntity> createFileInfo(FileInfoEntity fileInfo);

    /**
     * 根据文件id更新文件信息
     * @param fileId 传入一个文件id
     * @return 返回更新之后的文件结果
     */
    BaseResponseEntity<FileInfoEntity> updateFileInfoById(String fileId);


}
