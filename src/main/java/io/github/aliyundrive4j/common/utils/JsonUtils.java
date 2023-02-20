package io.github.aliyundrive4j.common.utils;

import java.util.Objects;

/**
 * @author puye(0303)
 * @since 2023/2/20
 */
public class JsonUtils {

    private JsonUtils() {
        throw new IllegalStateException("Utility class");
    }

    private static final String JSON_CHARACTER_LEFT = "{";
    private static final String JSON_CHARACTERS_LEFT = "\"{";

    private static final String JSON_CHARACTER_RIGHT = "}";
    private static final String JSON_CHARACTERS_RIGHT = "}\"";

    private static final String JSON_CHARACTER_ARRAY_LEFT = "[";
    private static final String JSON_CHARACTERS_ARRAY_LEFT = "\"[";

    private static final String JSON_CHARACTER_ARRAY_RIGHT = "]";
    private static final String JSON_CHARACTERS_ARRAY_RIGHT = "]\"";

    /**
     * 判断字符串是否是Json
     * 写的很粗糙，后续提取到单的jar包中之后再作说明
     * @param str 字符串
     * @return 返回Boolean
     */
    @SuppressWarnings("unused")
    public static Boolean objectOfJsonOrNot(String str) {
        boolean result = false;
        if (Objects.isNull(str)) {
            return false;
        }
        if (str.isEmpty()) {
            str = str.trim();
            if (str.startsWith(JSON_CHARACTER_LEFT) && str.endsWith(JSON_CHARACTER_RIGHT) ||
                    str.startsWith(JSON_CHARACTERS_LEFT) && str.endsWith(JSON_CHARACTERS_RIGHT) ||
                    str.startsWith(JSON_CHARACTER_ARRAY_LEFT) && str.endsWith(JSON_CHARACTER_ARRAY_RIGHT)||
                    str.startsWith(JSON_CHARACTERS_ARRAY_LEFT) && str.endsWith(JSON_CHARACTERS_ARRAY_RIGHT)) {
                result = true;
            }
        }
        return result;
    }
}
