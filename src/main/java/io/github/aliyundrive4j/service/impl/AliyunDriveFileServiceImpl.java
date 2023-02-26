package io.github.aliyundrive4j.service.impl;

import com.google.gson.Gson;
import io.github.aliyundrive4j.common.entity.aliyun.FileInfoEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunDriveHttpUtils;
import io.github.aliyundrive4j.common.utils.AliyunDrivePropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveFileService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

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
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDriveFileServiceImpl.class);
    /**
     * 全局通用gson对象
     */
    private static final Gson GSON = new Gson();
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
    private static final Map<Object,Object> SYS_INFO_MAP = AliyunDrivePropertyUtils.initProperties();
    /**
     * HTTP请求工具
     */
    private static final AliyunDriveHttpUtils HTTP_CLIENT = AliyunDriveHttpUtils.getInstance();

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
     * @param baseRequest 文件id 网盘id
     * @return 返回一个文件详细信息
     */
    @Override
    public BaseResponseEntity<FileInfoEntity> getFileInfoById(BaseRequestEntity baseRequest) {
        // 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("file_id",baseRequest.getFileId());
        String resp = HTTP_CLIENT.doNormalFilePost((String) SYS_INFO_MAP.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FILE_GET_BY_ID_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            FileInfoEntity madeResp = GSON.fromJson(resp, FileInfoEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("根据文件id以及网盘id查询文件信息成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
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
