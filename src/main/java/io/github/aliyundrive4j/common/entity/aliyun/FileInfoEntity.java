package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * description: FileInfoEntity
 *
 * @ClassName : FileInfoEntity
 * @Date 2022/12/16 9:32
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileInfoEntity implements AliyunBaseEntity{

    @Serial
    private static final long serialVersionUID = 2343981038208102005L;

    @SerializedName("drive_id")
    private String driveId;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("name")
    private String name;

    @SerializedName("type")
    private String type;

    @SerializedName("hidden")
    private boolean hidden;

    @SerializedName("starred")
    private boolean starred;

    @SerializedName("status")
    private String status;

    @SerializedName("user_meta")
    private String userMeta;

    @SerializedName("parent_file_id")
    private String parentFileId;

    @SerializedName("encrypt_mode")
    private String encryptMode;

    @SerializedName("creator_type")
    private String creatorType;

    @SerializedName("creator_id")
    private String creatorId;

    @SerializedName("last_modifier_type")
    private String lastModifierType;

    @SerializedName("last_modifier_id")
    private String lastModifierId;

    @SerializedName("revision_id")
    private String revisionId;

    @SerializedName("ex_fields_info")
    private String exFieldsInfo;

    private boolean trashed;

}
