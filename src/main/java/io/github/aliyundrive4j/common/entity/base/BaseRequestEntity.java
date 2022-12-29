package io.github.aliyundrive4j.common.entity.base;
import com.google.gson.annotations.SerializedName;
import java.io.Serial;
import java.io.Serializable;

/**
 * description: BaseRequestEntity
 *
 * @ClassName : BaseRequestEntity
 * @Date 2022/12/15 13:28
 * @Author puye(0303)
 * @PackageName io.github.aliyundrive4j.common.entity
 */
public class BaseRequestEntity implements Serializable {

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

    public String getOfficeThumbnailProcess() {
        return officeThumbnailProcess;
    }

    public void setOfficeThumbnailProcess(String officeThumbnailProcess) {
        this.officeThumbnailProcess = officeThumbnailProcess;
    }

    public String getImageThumbnailProcess() {
        return imageThumbnailProcess;
    }

    public void setImageThumbnailProcess(String imageThumbnailProcess) {
        this.imageThumbnailProcess = imageThumbnailProcess;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getVideoThumbnailProcess() {
        return videoThumbnailProcess;
    }

    public void setVideoThumbnailProcess(String videoThumbnailProcess) {
        this.videoThumbnailProcess = videoThumbnailProcess;
    }

    public boolean isPermanently() {
        return permanently;
    }

    public void setPermanently(boolean permanently) {
        this.permanently = permanently;
    }

    public String getImageUrlProcess() {
        return imageUrlProcess;
    }

    public void setImageUrlProcess(String imageUrlProcess) {
        this.imageUrlProcess = imageUrlProcess;
    }

    public Integer getUrlExpireSec() {
        return urlExpireSec;
    }

    public void setUrlExpireSec(Integer urlExpireSec) {
        this.urlExpireSec = urlExpireSec;
    }

    public BaseRequestEntity() {
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }
}
