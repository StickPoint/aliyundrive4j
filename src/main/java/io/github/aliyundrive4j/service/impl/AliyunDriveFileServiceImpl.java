package io.github.aliyundrive4j.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.github.aliyundrive4j.common.entity.aliyun.FileDownloadInfoEntity;
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
import java.lang.reflect.Type;
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
     * @param baseRequest 基础请求
     * @return 返回一个文件列表
     */
    @Override
    public BaseResponseEntity<List<FileInfoEntity>> getFileList(BaseRequestEntity baseRequest) {
        // 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("all",baseRequest.getAll());
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("fields",baseRequest.getFields());
        paramsMap.put("image_thumbnail_process",baseRequest.getImageThumbnailProcess());
        paramsMap.put("image_url_process",baseRequest.getImageUrlProcess());
        paramsMap.put("limit",baseRequest.getLimit());
        paramsMap.put("order_by",baseRequest.getOrderBy());
        paramsMap.put("order_direction",baseRequest.getOrderDirection());
        paramsMap.put("parent_file_id",baseRequest.getParentFileId());
        paramsMap.put("url_expire_sec",baseRequest.getUrlExpireSec());
        paramsMap.put("video_thumbnail_process",baseRequest.getVideoThumbnailProcess());
        // 发起文件查询请求，插叙单个文件详细信息
        String resp = HTTP_CLIENT.doNormalFilePost(
                // 文件列表获取 请求地址
                (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FILE_LIST_GET_KEY.getEnumsStringValue()),
                // token类型
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                // token
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(),
                // 请求参数
                paramsMap);
        // 解析响应
        if (!resp.isEmpty() && !resp.isBlank()) {
            JsonElement jsonElement = JsonParser.parseString(resp);
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.isJsonObject()) {
                JsonElement items = asJsonObject.get("items");
                if (items.isJsonArray()) {
                    JsonArray asJsonArray = items.getAsJsonArray();
                    Type listType = new TypeToken<List<FileInfoEntity>>(){}.getType();
                    List<FileInfoEntity> folderMadeRespEntityList = GSON.fromJson(asJsonArray.toString(), listType);
                    if (!folderMadeRespEntityList.isEmpty()) {
                        return BaseResponseEntity.success(folderMadeRespEntityList);
                    }
                }
                JsonElement nextMaker = asJsonObject.get("next_marker");
                log.info("extra message {}",nextMaker);
            }
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
        }
        // 抛出不符合预期的异常
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
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
        // 发起文件查询请求，插叙单个文件详细信息
        String resp = HTTP_CLIENT.doNormalFilePost(
                // 文件详细信息请求地址
                (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FILE_GET_BY_ID_KEY.getEnumsStringValue()),
                // token类型
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                // token
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(),
                // 请求参数
                paramsMap);
        // 解析响应
        if (!resp.isEmpty() && !resp.isBlank()) {
            FileInfoEntity madeResp = GSON.fromJson(resp, FileInfoEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("根据文件id以及网盘id查询文件信息成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        // 抛出不符合预期的异常
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
     * 文件重命名API
     * @param baseRequest 传入一个文件通用请求
     * @return 返回更新之后的文件结果
     */
    @Override
    public BaseResponseEntity<FileInfoEntity> updateFileNameById(BaseRequestEntity baseRequest) {
        // 准备请求参数
        Map<String,Object> requestParam = new LinkedHashMap<>();
        requestParam.put("check_name_mode",baseRequest.getCheckNameMode());
        requestParam.put("file_id",baseRequest.getFileId());
        requestParam.put("drive_id",baseRequest.getDriveId());
        requestParam.put("name",baseRequest.getName());
        // 发起文件更新请求，根据文件id更新文件信息
        String resp = HTTP_CLIENT.doNormalFilePost(
                // 根据文件id更新文件名称
                (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FILE_UPDATE_BY_ID_KEY.getEnumsStringValue()),
                // token类型
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                // token
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(),
                // 请求参数
                requestParam);
        // 解析响应
        if (!resp.isEmpty() && !resp.isBlank()) {
            FileInfoEntity madeResp = GSON.fromJson(resp, FileInfoEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("根据文件id以及网盘id以及新文件名修改文件信息成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        // 抛出不符合预期的异常
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }

    /**
     * 获取文件下载信息
     *
     * @param baseRequest 传入一个获取文件下载请求
     * @return 返回文件下载请求信息 包含请求方式，请求方法，请求地址，请求参数，请求路径等等
     */
    @Override
    public BaseResponseEntity<FileDownloadInfoEntity> getFileDownloadInfo(BaseRequestEntity baseRequest) {
        // 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("file_id",baseRequest.getFileId());
        // 发起文件查询请求，插叙单个文件详细信息
        String resp = HTTP_CLIENT.doNormalFilePost(
                // 获取文件下载信息的请求地址
                (String) AliyunDrivePropertyUtils.get(AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FILE_DOWNLOAD_INFO_GET_KEY.getEnumsStringValue()),
                // token类型
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                // token
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(),
                // 请求参数
                paramsMap);
        // 解析响应
        if (!resp.isEmpty() && !resp.isBlank()) {
            FileDownloadInfoEntity resultEntity = GSON.fromJson(resp, FileDownloadInfoEntity.class);
            if (Objects.nonNull(resultEntity)) {
                log.info("根据文件id以及网盘id查询文件信息成功~");
                return BaseResponseEntity.success(resultEntity);
            }
        }
        // 抛出不符合预期的异常
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }
}
