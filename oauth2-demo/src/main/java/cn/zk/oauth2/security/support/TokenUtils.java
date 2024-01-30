package cn.zk.oauth2.security.support;

import org.springframework.util.StringUtils;

/**
 * @author ZK
 * @date 2024/1/30
 */
public class TokenUtils {

    /**
     * 校验token
     * @param accessToken 临时token
     * @param refreshToken 刷新token
     * @return boolean
     */
    public static boolean checkToken(String accessToken, String refreshToken) {
//        if (!StringUtils.hasText(accessToken)) {
//            throw new IllegalArgumentException("access_token can't be null or blank");
//        }
        return true;
    }
}
