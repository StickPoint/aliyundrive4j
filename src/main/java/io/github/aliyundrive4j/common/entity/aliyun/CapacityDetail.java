package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;
import java.util.Date;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class CapacityDetail implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = 7259267149496788459L;
    /**
     * 类型
     */
    private String type;
    /**
     * 空间大小
     */
    private long size;
    /**
     * 过期时间
     */
    private String expired;
    /**
     * 描述
     */
    private String description;
    /**
     * 最新接收时间
     */
    @SerializedName("latest_receive_time")
    private Date latestReceiveTime;

    @SerializedName("drive_used_size")
    private long driveUsedSize;

}
