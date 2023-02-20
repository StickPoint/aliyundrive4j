package io.github.aliyundrive4j.common.entity.aliyun;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;
import java.util.Objects;

/**
 * description: BaseEntity
 * 基础对象
 * @ClassName : BaseEntity
 * @Date 2022/12/15 13:27
 * @Author fntp
 * @PackageName io.github.aliyundrive4j.common.entity
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public abstract class BaseEntity implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -8420024591064880265L;

    @SerializedName("create_at")
    protected String createAt;

    @SerializedName("update_at")
    protected String updateAt;

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
}
