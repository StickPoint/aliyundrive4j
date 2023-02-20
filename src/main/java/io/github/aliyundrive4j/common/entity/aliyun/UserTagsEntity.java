package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class UserTagsEntity implements AliyunBaseEntity{

    @Serial
    private static final long serialVersionUID = -7524102609043008796L;
    /**
     * 通道 eg:file_upload
     */
    private String channel;
    /**
     * 客户端 eg：web
     */
    private String client;

    @SerializedName("device_id")
    private String deviceId;

    @SerializedName("device_name")
    private String deviceName;
}
