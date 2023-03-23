package io.github.aliyundrive4j.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.github.aliyundrive4j.common.entity.aliyun.FolderInfoEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunDriveHttpUtils;
import io.github.aliyundrive4j.common.utils.AliyunDrivePropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveFolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
public class AliyunDriveFolderServiceImpl implements IAliyunDriveFolderService {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDriveFolderServiceImpl.class);
    /**
     * 全局通用gson对象
     */
    private static final Gson GSON = new Gson();

    private static final AliyunDriveHttpUtils HTTP_CLIENT = AliyunDriveHttpUtils.getInstance();


    /**
     * 创建文件夹
     *
     * @param baseRequest 基础请求参数
     * @return 返回一个文件夹创建的响应
     */
    @Override
    public BaseResponseEntity<FolderInfoEntity> createFolder(BaseRequestEntity baseRequest) {
        // 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("check_name_mode",baseRequest.getCheckNameMode());
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("parent_file_id",baseRequest.getParentFileId());
        paramsMap.put("type",baseRequest.getType());
        paramsMap.put("name",baseRequest.getName());
        String resp = HTTP_CLIENT.doCreatePost((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_CREATE_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            FolderInfoEntity madeResp = GSON.fromJson(resp, FolderInfoEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("创建文件夹成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }

    /**
     * 删除文件夹
     *
     * @param baseRequest 基础请求参数
     * @return 返回一个删除文件夹的响应
     */
    @Override
    public BaseResponseEntity<Boolean> deleteFolder(BaseRequestEntity baseRequest) {
        try {
            // 准备参数
            Map<String,Object> paramsMap = new LinkedHashMap<>();
            paramsMap.put("drive_id",baseRequest.getDriveId());
            paramsMap.put("file_id",baseRequest.getFileId());
            String resp = HTTP_CLIENT.doDirDeletePost((String) AliyunDrivePropertyUtils.get(
                            AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_DELETED_KEY.getEnumsStringValue()),
                    baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                    baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
            if (!resp.isEmpty() && !resp.isBlank()) {
                FolderInfoEntity madeResp = GSON.fromJson(resp, FolderInfoEntity.class);
                if (Objects.nonNull(madeResp)) {
                    log.info("删除文件夹成功~");
                    return BaseResponseEntity.success(true);
                }
            }
            return BaseResponseEntity.success(true);
            // 删除文件无任何响应，只要此过程中没有抛出任何异常，那么执行就算成功
        } catch (AliyunDriveException e) {
            log.error("文件删除出现异常问题！{}",e.getCause().getMessage());
        }

        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }

    /**
     * 修改文件夹·
     *
     * @param baseRequest 基础请求参数
     * @return 返回一个修改文件夹的响应
     */
    @Override
    public BaseResponseEntity<FolderInfoEntity> updateFolder(BaseRequestEntity baseRequest) {
// 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("file_id",baseRequest.getFileId());
        paramsMap.put("name",baseRequest.getName());
        paramsMap.put("check_name_mode",baseRequest.getCheckNameMode());
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_UPDATE_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            FolderInfoEntity madeResp = GSON.fromJson(resp, FolderInfoEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("修改文件夹成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }

    /**
     * 查询文件夹
     *
     * @param baseRequest 基础请求参数
     * @return 返回一个查询文件夹的响应
     */
    @Override
    public BaseResponseEntity<List<FolderInfoEntity>> getFolderList(BaseRequestEntity baseRequest) {
        // 准备参数
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("all",baseRequest.getAll());
        paramsMap.put("fields",baseRequest.getFields());
        paramsMap.put("image_thumbnail_process",baseRequest.getImageThumbnailProcess());
        paramsMap.put("image_url_process",baseRequest.getImageUrlProcess());
        paramsMap.put("limit",baseRequest.getLimit());
        paramsMap.put("order_by",baseRequest.getOrderBy());
        paramsMap.put("order_direction",baseRequest.getOrderDirection());
        paramsMap.put("parent_file_id",baseRequest.getParentFileId());
        paramsMap.put("url_expire_sec",baseRequest.getUrlExpireSec());
        paramsMap.put("video_thumbnail_process",baseRequest.getVideoThumbnailProcess());
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_GET_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            JsonElement jsonElement = JsonParser.parseString(resp);
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.isJsonObject()) {
                JsonElement items = asJsonObject.get("items");
                if (items.isJsonArray()) {
                    JsonArray asJsonArray = items.getAsJsonArray();
                    Type listType = new TypeToken<List<FolderInfoEntity>>(){}.getType();
                    List<FolderInfoEntity> folderMadeRespEntityList = GSON.fromJson(asJsonArray.toString(), listType);
                    if (!folderMadeRespEntityList.isEmpty()) {
                        return BaseResponseEntity.success(folderMadeRespEntityList);
                    }
                }
                JsonElement nextMaker = asJsonObject.get("next_marker");
                log.info("extra message {}",nextMaker);
            }
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
        }
        return null;
    }

}
