package io.github.aliyundrive4j.common.entity.base;

import io.github.aliyundrive4j.common.entity.aliyun.AliyunBaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.io.Serial;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
@Data
@Builder
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class BaseHeaderEntity implements AliyunBaseEntity {

    @Serial
    private static final long serialVersionUID = -7590905441464601556L;

    private String authType;

    private String authToken;
}
