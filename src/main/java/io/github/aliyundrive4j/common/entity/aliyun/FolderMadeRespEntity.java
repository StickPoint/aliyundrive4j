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
 * @author puye(0303)
 * @since 2023/2/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FolderMadeRespEntity extends BaseEntity{
    @Serial
    private static final long serialVersionUID = -8945663495667362267L;

    @SerializedName("drive_id")
    private String driveId;

    @SerializedName("file_id")
    private String fileId;

    private String name;

    @SerializedName("parent_file_id")
    private String parentFileId;

    private boolean starred;
    /**
     * 类型
     * file folder
     */
    private String type;
    @SerializedName("user_meta")
    private String userMeta;

    @SerializedName("user_tags")
    private UserTagsEntity userTags;

    @SerializedName("file_name")
    private String fileName;

    @SerializedName("encrypt_mode")
    private String encryptMode;

}
