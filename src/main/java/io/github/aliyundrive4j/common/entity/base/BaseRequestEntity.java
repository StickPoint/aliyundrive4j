package io.github.aliyundrive4j.common.entity.base;
import com.google.gson.annotations.SerializedName;
import io.github.aliyundrive4j.common.entity.aliyun.AliyunBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import java.io.Serial;

/**
 * description: BaseRequestEntity
 *
 * @ClassName : BaseRequestEntity
 * @Date 2022/12/15 13:28
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@ToString
public class BaseRequestEntity implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -4420006432074106509L;

    @SerializedName("office_thumbnail_process")
    private String officeThumbnailProcess;

    @SerializedName("image_thumbnail_process")
    private String imageThumbnailProcess;

    @SerializedName("file_id")
    private String fileId;

    @SerializedName("video_thumbnail_process")
    private String videoThumbnailProcess;

    private boolean permanently;

    @SerializedName("image_url_process")
    private String imageUrlProcess;

    @SerializedName("url_expire_sec")
    private Integer urlExpireSec;

    private String refreshToken;
    /**
     * 请求基本地址
     */
    private String aliyundriveRequestUrl;
    /**
     * 请求基本请求头
     */
    private BaseHeaderEntity aliyundriveRequestBaseHeader;
    /**
     * 其实就是userId
     */
    private String ownerId;
    /**
     * 网盘Id
     */
    private String driveId;

}
