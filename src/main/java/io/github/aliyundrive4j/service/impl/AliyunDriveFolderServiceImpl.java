package io.github.aliyundrive4j.service.impl;
import com.google.gson.Gson;
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

import java.util.LinkedHashMap;
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
    public BaseResponseEntity<FolderMadeRespEntity> createFolder(BaseRequestEntity baseRequest) {
        // 获得相应结果
        Map<String,Object> paramsMap = new LinkedHashMap<>();
        paramsMap.put("check_name_mode",baseRequest.getCheckNameMode());
        paramsMap.put("drive_id",baseRequest.getDriveId());
        paramsMap.put("parent_file_id",baseRequest.getParentFileId());
        paramsMap.put("type",baseRequest.getType());
        paramsMap.put("name",baseRequest.getName());
        String resp = HTTP_CLIENT.doCreatePost((String) SYS_INFO_MAP.get(
                        AliyunDriveInfoEnums.ALIYUN_DRIVE_SYS_PROPERTY_FOLDER_CREATE_KEY.getEnumsStringValue()),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthType(),
                baseRequest.getAliyundriveRequestBaseHeader().getAuthToken(), paramsMap);
        if (!resp.isEmpty() && !resp.isBlank()) {
            FolderMadeRespEntity madeResp = GSON.fromJson(resp, FolderMadeRespEntity.class);
            if (Objects.nonNull(madeResp)) {
                log.info("创建文件夹成功~");
                return BaseResponseEntity.success(madeResp);
            }
        }
        throw new AliyunDriveException(AliyunDriveCodeEnums.ERROR_JSON_PARSER);
    }
    
}
