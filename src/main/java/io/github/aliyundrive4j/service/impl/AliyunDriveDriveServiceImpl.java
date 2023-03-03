package io.github.aliyundrive4j.service.impl;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.github.aliyundrive4j.common.entity.aliyun.DriveItemEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunDriveHttpUtils;
import io.github.aliyundrive4j.common.utils.AliyunDrivePropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveDriveService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * description: AliyunDriveDriveServiceImpl
 *
 * @ClassName : AliyunDriveDriveServiceImpl
 * @Date 2022/12/30 13:38
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.service.impl
 */
public class AliyunDriveDriveServiceImpl implements IAliyunDriveDriveService {

    /**
     * 系统Http请求工具日志
     */
    private static final Logger log = LoggerFactory.getLogger(AliyunDriveDriveServiceImpl.class);
    /**
     * 全局通用gson对象
     */
    private static final Gson GSON = new Gson();

    private static final AliyunDriveHttpUtils HTTP_CLIENT = AliyunDriveHttpUtils.getInstance();

    /**
     * 查询所有网盘信息
     *
     * @param baseRequest 传入一个ownerId与权限信息
     * @return 返回一个网盘集合
     */
    @Override
    public BaseResponseEntity<List<DriveItemEntity>> getDriveList(BaseRequestEntity baseRequest) {
        Map<String,Object> requestParamMap = new LinkedHashMap<>();
        requestParamMap.put("ownerId",baseRequest.getOwnerId());
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_LIST_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), requestParamMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            JsonElement respJsonElement = JsonParser.parseString(resp);
            JsonObject jsonObject = respJsonElement.getAsJsonObject();
            JsonArray asJsonArray = jsonObject.getAsJsonArray("items");
            Type listType = new TypeToken<List<DriveItemEntity>>(){}.getType();
            List<DriveItemEntity> detailList = GSON.fromJson(asJsonArray.toString(), listType);
            if (!detailList.isEmpty()) {
                return BaseResponseEntity.success(detailList);
            }
            log.warn("【AliyunDrive-4j】当前获得的用户网盘列表信息为null");
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }

    /**
     * 默认 drive(网盘)
     * Post请求地址：https://api.aliyundrive.com/v2/drive/get_default_drive
     * 携带token信息直接请求这个接口地址
     *
     * @return 返回的是一个默认的网盘
     */
    @Override
    public BaseResponseEntity<DriveItemEntity> getDefaultDrive(BaseRequestEntity baseRequest) {
        // 获得相应结果
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_DEFAULT_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), Collections.emptyMap());
        if (!resp.isEmpty() && !resp.isBlank()) {
            DriveItemEntity driveItem = GSON.fromJson(resp, DriveItemEntity.class);
            if (Objects.nonNull(driveItem)) {
                return BaseResponseEntity.success(driveItem);
            }
            log.warn("【AliyunDrive-4j】当前获得的用户网盘列表信息为null");
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }

    /**
     * 根据网盘id查询网盘信息
     * POST请求地址：https://api.aliyundrive.com/v2/drive/get
     *
     * @param baseRequest 传入一个网盘id
     * @return 返回一个网盘信息
     */
    @Override
    public BaseResponseEntity<DriveItemEntity> getDriveInfoByDriveId(BaseRequestEntity baseRequest) {
        // 获得相应结果
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        String resp = HTTP_CLIENT.doNormalPostWithAuth((String) AliyunDrivePropertyUtils.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_DRIVE_GET_BY_ID_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            DriveItemEntity driveItem = GSON.fromJson(resp, DriveItemEntity.class);
            if (Objects.nonNull(driveItem)) {
                return BaseResponseEntity.success(driveItem);
            }
            log.warn("【AliyunDrive-4j】当前获得的用户网盘列表信息为null");
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
    }
}
