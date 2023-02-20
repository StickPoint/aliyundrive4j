package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * description: DriveItemEntity
 *
 * @ClassName : DriveItemEntity
 * @Date 2022/12/30 10:33
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class DriveItemEntity extends BaseEntity {

    @Serial
    private static final long serialVersionUID = -8908437438112700624L;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("drive_id")
    private String driveId;

    @SerializedName("drive_name")
    private String driveName;

    private String description;

    private String creator;

    private String owner;

    @SerializedName("owner_type")
    private String ownerType;

    @SerializedName("drive_type")
    private String driveType;

    private String status;

    @SerializedName("used_size")
    private long usedSize;

    @SerializedName("total_size")
    private long totalSize;

    @SerializedName("store_id")
    private String storeId;

    @SerializedName("relative_path")
    private String relativePath;

    @SerializedName("encrypt_mode")
    private String encryptMode;

    @SerializedName("encrypt_data_access")
    private String encryptDataAccess;

    private String permission;

    @SerializedName("subdomain_id")
    private String subdomainId;

    private String category;

}
