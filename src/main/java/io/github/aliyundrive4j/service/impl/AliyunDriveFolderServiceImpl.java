package io.github.aliyundrive4j.service.impl;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import io.github.aliyundrive4j.common.entity.aliyun.FolderMadeRespEntity;
import io.github.aliyundrive4j.common.entity.base.BaseRequestEntity;
import io.github.aliyundrive4j.common.entity.base.BaseResponseEntity;
import io.github.aliyundrive4j.common.enums.AliyunDriveCodeEnums;
import io.github.aliyundrive4j.common.enums.AliyunDriveInfoEnums;
import io.github.aliyundrive4j.common.exception.AliyunDriveException;
import io.github.aliyundrive4j.common.utils.AliyunHttpUtils;
import io.github.aliyundrive4j.common.utils.PropertyUtils;
import io.github.aliyundrive4j.service.IAliyunDriveFolderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.reflect.Type;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

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
     * 创建文件夹
     *
     * @param baseRequest 基础请求参数
     * @return 返回一个文件夹创建的响应
     */
    @Override
    public BaseResponseEntity<List<FolderMadeRespEntity>> createFolder(BaseRequestEntity baseRequest) {
        // 获得相应结果
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("all",baseRequest.getAll());
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("fields",baseRequest.getDriveId());
        paramsMap.put("image_thumbnail_process",baseRequest.getDriveId());
        paramsMap.put("image_url_process",baseRequest.getDriveId());
        paramsMap.put("limit",baseRequest.getDriveId());
        paramsMap.put("order_by",baseRequest.getDriveId());
        paramsMap.put("order_direction",baseRequest.getDriveId());
        paramsMap.put("parent_file_id",baseRequest.getDriveId());
        paramsMap.put("url_expire_sec",baseRequest.getDriveId());
        paramsMap.put("video_thumbnail_process",baseRequest.getDriveId());
        String resp = HTTP_CLIENT.doPostWithAuth((String) SYS_INFO_MAP.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_CREATE_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            JsonElement jsonElement = JsonParser.parseString(resp);
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            if (asJsonObject.isJsonObject()) {
                JsonElement items = asJsonObject.get("items");
                if (items.isJsonArray()) {
                    JsonArray asJsonArray = items.getAsJsonArray();
                    Type listType = new TypeToken<List<FolderMadeRespEntity>>(){}.getType();
                    List<FolderMadeRespEntity> folderMadeRespEntityList = GSON.fromJson(asJsonArray.toString(), listType);
                    if (!folderMadeRespEntityList.isEmpty()) {
                        return BaseResponseEntity.success(folderMadeRespEntityList);
                    }
                }
                JsonElement nextMaker = asJsonObject.get("next_marker");
                log.info("extra message {}",nextMaker);
            }
            throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_IS_NOT_JSON);
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_HTTP);
    }
}
