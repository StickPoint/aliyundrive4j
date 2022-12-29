package io.github.aliyundrive4j.service.impl;

import io.github.aliyundrive4j.common.entity.aliyun.FileInfoEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.utils.PropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveFileService;

import java.util.List;
import java.util.Map;

/**
 * description: AliyunDriveFileServiceImpl
 * 文件服务实现
 * @ClassName : AliyunDriveFileServiceImpl
 * @Date 2022/12/16 9:29
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service.impl
 */
public class AliyunDriveFileServiceImpl implements IAliyunDriveFileService {

    /**
     * 系统配置参数数据
     */
    private static final Map<Object,Object> SYS_PROPERTIES = PropertyUtils.initProperties();

    @Override
    public BaseResponseEntity<List<FileInfoEntity>> getCloudFolderList() {

        return null;
    }

    /**
     * 根据目录名称获得文件列表
     *
     * @param folderName 目录名称
     * @return 返回一个文件列表
     */
    @Override
    public BaseResponseEntity<List<FileInfoEntity>> getFileListFromFolder(String folderName) {
        return null;
    }

    /**
     * 根据文件id获得文件详细信息
     *
     * @param fileId 文件id
     * @return 返回一个文件详细信息
     */
    @Override
    public BaseResponseEntity<FileInfoEntity> getFileInfoById(String fileId) {
        return null;
    }

    /**
     * 根据文件id删除文件
     *
     * @param fileId 传入一个文件id
     * @return 返回一个删除结果
     */
    @Override
    public BaseResponseEntity<Boolean> deleteFileById(String fileId) {
        return null;
    }

    /**
     * 创建阿里云盘文件
     *
     * @param fileInfo 返回一个创建好的文件信息
     * @return 返回一个文件信息实体
     */
    @Override
    public BaseResponseEntity<FileInfoEntity> createFileInfo(FileInfoEntity fileInfo) {
        return null;
    }

    /**
     * 根据文件id更新文件信息
     *
     * @param fileId 传入一个文件id
     * @return 返回更新之后的文件结果
     */
    @Override
    public BaseResponseEntity<FileInfoEntity> updateFileInfoById(String fileId) {
        return null;
    }
}
