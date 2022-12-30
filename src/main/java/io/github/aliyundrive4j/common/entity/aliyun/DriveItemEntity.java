package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;

import java.io.Serial;
import java.io.Serializable;

/**
 * description: DriveItemEntity
 *
 * @ClassName : DriveItemEntity
 * @Date 2022/12/30 10:33
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity.aliyun
 */
public class DriveItemEntity extends BaseEntity implements Serializable {

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

    public String getDomainId() {
        return domainId;
    }

    public void setDomainId(String domainId) {
        this.domainId = domainId;
    }

    public String getDriveId() {
        return driveId;
    }

    public void setDriveId(String driveId) {
        this.driveId = driveId;
    }

    public String getDriveName() {
        return driveName;
    }

    public void setDriveName(String driveName) {
        this.driveName = driveName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getOwnerType() {
        return ownerType;
    }

    public void setOwnerType(String ownerType) {
        this.ownerType = ownerType;
    }

    public String getDriveType() {
        return driveType;
    }

    public void setDriveType(String driveType) {
        this.driveType = driveType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getUsedSize() {
        return usedSize;
    }

    public void setUsedSize(long usedSize) {
        this.usedSize = usedSize;
    }

    public long getTotalSize() {
        return totalSize;
    }

    public void setTotalSize(long totalSize) {
        this.totalSize = totalSize;
    }

    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    public String getRelativePath() {
        return relativePath;
    }

    public void setRelativePath(String relativePath) {
        this.relativePath = relativePath;
    }

    public String getEncryptMode() {
        return encryptMode;
    }

    public void setEncryptMode(String encryptMode) {
        this.encryptMode = encryptMode;
    }

    public String getEncryptDataAccess() {
        return encryptDataAccess;
    }

    public void setEncryptDataAccess(String encryptDataAccess) {
        this.encryptDataAccess = encryptDataAccess;
    }

    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    public String getSubdomainId() {
        return subdomainId;
    }

    public void setSubdomainId(String subdomainId) {
        this.subdomainId = subdomainId;
    }
}
