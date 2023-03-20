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
 * description: FileInfoEntity
 *
 * @ClassName : FileInfoEntity
 * @Date 2022/12/16 9:32
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileInfoEntity extends BaseEntity{

    @Serial
    private static final long serialVersionUID = 8148999832838183476L;
    @SerializedName("drive_id")
    private String driveId;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("file_id")
    private String fileId;

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
    /**
     * 文件加密模式
     */
    @SerializedName("encrypt_mode")
    private String encryptMode;
    /**
     * 创建者类型
     */
    @SerializedName("creator_type")
    private String creatorType;

    @SerializedName("creator_id")
    private String creatorId;

    @SerializedName("last_modifier_type")
    private String lastModifierType;

    @SerializedName("last_modifier_id")
    private String lastModifierId;

    @SerializedName("user_tags")
    private UserTagsEntity userTags;

    @SerializedName("revision_id")
    private String revisionId;

    @SerializedName("ex_fields_info")
    private String exFieldsInfo;

    @SerializedName("content_type")
    private String contentType;

    @SerializedName("file_extension")
    private String fileExtension;

    private Long size;

    @SerializedName("upload_id")
    private String uploadId;

    @SerializedName("crc64_hash")
    private String crc64Hash;

    @SerializedName("content_hash")
    private String contentHash;

    @SerializedName("content_hash_name")
    private String contentHashName;
    /**
     * 下载地址
     */
    @SerializedName("download_url")
    private String downloadUrl;
    /**
     * 直接地址
     */
    private String url;

    /**
     * 短连接：thumbnail
     */
    private String thumbnail;
    /**
     * 文件类别
     */
    private String category;
    /**
     * 惩罚性标志
     */
    @SerializedName("punish_flag")
    private Integer punishFlag;
    /**
     * 修订版版本
     */
    @SerializedName("revision_version")
    private Integer revisionVersion;
    /**
     * 违反禁令的
     */
    private boolean trashed;

    private String expiration;
}
