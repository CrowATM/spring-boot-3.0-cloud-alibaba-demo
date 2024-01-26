package cn.zk.oauth2.security.provider.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/** 第三方登陆凭据
 * @author ZK
 * @date 2024/1/18
 */
@Deprecated
public class ThirdCodeAuthenticationToken extends AbstractCodeAuthenticationToken {

    public ThirdCodeAuthenticationToken(Object principal, String code) {
        super(principal, code);
    }

    public ThirdCodeAuthenticationToken(Object principal, String code, Collection<? extends GrantedAuthority> authorities) {
        super(principal, code, authorities);
    }
}
