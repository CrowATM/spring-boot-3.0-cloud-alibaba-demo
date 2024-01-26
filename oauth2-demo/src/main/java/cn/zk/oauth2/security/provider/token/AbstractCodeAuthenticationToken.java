package cn.zk.oauth2.security.provider.token;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author ZK
 * @date 2024/1/19
 */
public abstract class AbstractCodeAuthenticationToken extends AbstractAuthenticationToken {

    protected final Object principal;
    protected String code;

    public AbstractCodeAuthenticationToken(Object principal, String code) {
        super(null);
        this.principal = principal;
        this.code = code;
        super.setAuthenticated(false);
    }

    public AbstractCodeAuthenticationToken(Object principal, String code, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.code = code;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.code;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    @Override
    public void setAuthenticated(boolean authenticated) {
        if (authenticated) {
            throw new IllegalArgumentException("Cannot set this token to trusted - " +
                    "use constructor which takes a GrantedAuthority list instead");
        }
        super.setAuthenticated(false);
    }

    @Override
    public void eraseCredentials() {
        super.eraseCredentials();
        this.code = null;
    }
}
