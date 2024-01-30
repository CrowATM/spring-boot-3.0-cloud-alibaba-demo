package cn.zk.oauth2.security.provider;

import cn.zk.oauth2.security.provider.token.EmilCodeAuthenticationToken;
import cn.zk.oauth2.security.service.CustomLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Component;

/** 邮箱登陆认证器
 * @author ZK
 * @date 2024/1/18
 */
@Component
public class EmilAuthenticationProvider implements AuthenticationProvider {

    private final CustomLoginService customLoginService;

    @Autowired
    public EmilAuthenticationProvider(@Qualifier("customLoginServiceImpl") CustomLoginService customLoginService) {
        this.customLoginService = customLoginService;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        String emil = authentication.getName();
        EmilCodeAuthenticationToken emilCodeAuthenticationToken = customLoginService.loadUserByEmil(emil);
        emilCodeAuthenticationToken.setDetails(authentication.getDetails());
        return emilCodeAuthenticationToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return EmilCodeAuthenticationToken.class.isAssignableFrom(authentication);
    }
}
