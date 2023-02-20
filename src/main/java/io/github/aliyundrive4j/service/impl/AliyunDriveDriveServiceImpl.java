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
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.common.utils.PropertyUtils;
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
    private static final Map<Object,Object> SYS_INFO_MAP = PropertyUtils.initProperties();

    private static final AliyunHttpUtils HTTP_CLIENT = AliyunHttpUtils.getInstance();

    /**
     * 查询所有网盘信息
     *
     * @param baseRequest 传入一个ownerId与权限信息
     * @return 返回一个网盘集合
     */
    @Override
    public BaseResponseEntity<List<DriveItemEntity>> getDriveList(BaseRequestEntity baseRequest) {
        Map<String,String> requestParamMap = new LinkedHashMap<>();
        requestParamMap.put("ownerId",baseRequest.getOwnerId());
        String resp = HTTP_CLIENT.doPostWithAuth((String) SYS_INFO_MAP.get(
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
        String resp = HTTP_CLIENT.doPostWithAuth((String) SYS_INFO_MAP.get(
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
        Map<String,String> paramsMap = new LinkedHashMap<>();
        paramsMap.put("drive_id",baseRequest.getDriveId());
        String resp = HTTP_CLIENT.doPostWithAuth((String) SYS_INFO_MAP.get(
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
