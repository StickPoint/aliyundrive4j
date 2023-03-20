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
 * @since 2023/3/20
 */
@EqualsAndHashCode(callSuper = true)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class FileDownloadInfoEntity extends BaseEntity{

    @Serial
    private static final long serialVersionUID = -2725624219262760323L;

    @SerializedName("internal_url")
    private String internalUrl;

    @SerializedName("crc64_hash")
    private String crc64Hash;

    @SerializedName("content_hash")
    private String contentHash;

    @SerializedName("content_hash_name")
    private String contentHashName;

    @SerializedName("drive_id")
    private String driveId;

    @SerializedName("domain_id")
    private String domainId;

    @SerializedName("file_id")
    private String fileId;

    private String expiration;

    private String method;

    @SerializedName("revision_id")
    private String revisionId;

    private Long size;

    private String url;

}
