package cn.zk.oauth2.security.provider;

import cn.zk.oauth2.security.provider.token.ThirdCodeAuthenticationToken;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/** 第三方登陆认证器
 * @author ZK
 * @date 2024/1/18
 */
@Deprecated
//@Component
public class ThirdCodeAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        return null;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return ThirdCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
