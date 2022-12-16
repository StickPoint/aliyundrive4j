package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;

import java.util.Objects;

/**
 * description: BaseEntity
 * 基础对象
 * @ClassName : BaseEntity
 * @Date 2022/12/15 13:27
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity
 */
public abstract class BaseEntity {

    @SerializedName("create_at")
    protected String createAt;

    @SerializedName("update_at")
    protected String updateAt;

    public String getCreateAt() {
        return createAt;
    }

    public void setCreateAt(String createAt) {
        this.createAt = createAt;
    }

    public String getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(String updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        BaseEntity that = (BaseEntity) o;
        return Objects.equals(createAt, that.createAt) && Objects.equals(updateAt, that.updateAt);
    }

    @Override
    public int hashCode() {
        return Objects.hash(createAt, updateAt);
    }

    protected BaseEntity(String createAt, String updateAt) {
        this.createAt = createAt;
        this.updateAt = updateAt;
    }

    protected BaseEntity() {
    }
}
