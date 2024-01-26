package cn.zk.oauth2.security.provider.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/** 邮箱登陆凭据
 * @author ZK
 * @date 2024/1/18
 */
public class EmilCodeAuthenticationToken extends AbstractCodeAuthenticationToken {

    public EmilCodeAuthenticationToken(Object principal, String code) {
        super(principal, code);
    }

    public EmilCodeAuthenticationToken(Object principal, String code, Collection<? extends GrantedAuthority> authorities) {
        super(principal, code, authorities);
    }
}
