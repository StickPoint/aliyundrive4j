package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;

/**
 * description: FileInfoEntity
 *
 * @ClassName : FileInfoEntity
 * @Date 2022/12/16 9:32
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
public class FileInfoEntity extends BaseEntity{

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

    public FileInfoEntity(String createAt, String updateAt,
                          String driveId, String domainId,
                          String name, String type, boolean hidden, boolean starred,
                          String status, String userMeta, String parentFileId,
                          String encryptMode, String creatorType, String creatorId,
                          String lastModifierType, String lastModifierId, String revisionId,
                          String exFieldsInfo, boolean trashed) {
        super(createAt, updateAt);
        this.driveId = driveId;
        this.domainId = domainId;
        this.name = name;
        this.type = type;
        this.hidden = hidden;
        this.starred = starred;
        this.status = status;
        this.userMeta = userMeta;
        this.parentFileId = parentFileId;
        this.encryptMode = encryptMode;
        this.creatorType = creatorType;
        this.creatorId = creatorId;
        this.lastModifierType = lastModifierType;
        this.lastModifierId = lastModifierId;
        this.revisionId = revisionId;
        this.exFieldsInfo = exFieldsInfo;
        this.trashed = trashed;
    }

    public FileInfoEntity() {
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    public boolean isStarred() {
        return starred;
    }

    public void setStarred(boolean starred) {
        this.starred = starred;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getUserMeta() {
        return userMeta;
    }

    public void setUserMeta(String userMeta) {
        this.userMeta = userMeta;
    }

    public String getParentFileId() {
        return parentFileId;
    }

    public void setParentFileId(String parentFileId) {
        this.parentFileId = parentFileId;
    }

    public String getEncryptMode() {
        return encryptMode;
    }

    public void setEncryptMode(String encryptMode) {
        this.encryptMode = encryptMode;
    }

    public String getCreatorType() {
        return creatorType;
    }

    public void setCreatorType(String creatorType) {
        this.creatorType = creatorType;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getLastModifierType() {
        return lastModifierType;
    }

    public void setLastModifierType(String lastModifierType) {
        this.lastModifierType = lastModifierType;
    }

    public String getLastModifierId() {
        return lastModifierId;
    }

    public void setLastModifierId(String lastModifierId) {
        this.lastModifierId = lastModifierId;
    }

    public String getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(String revisionId) {
        this.revisionId = revisionId;
    }

    public String getExFieldsInfo() {
        return exFieldsInfo;
    }

    public void setExFieldsInfo(String exFieldsInfo) {
        this.exFieldsInfo = exFieldsInfo;
    }

    public boolean isTrashed() {
        return trashed;
    }

    public void setTrashed(boolean trashed) {
        this.trashed = trashed;
    }
}
