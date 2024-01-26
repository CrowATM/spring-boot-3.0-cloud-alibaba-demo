package cn.zk.oauth2.security.provider.token;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/** 手机短信登陆凭据
 * @author ZK
 * @date 2024/1/18
 */
public class SmsCodeAuthenticationToken extends AbstractCodeAuthenticationToken {

    public SmsCodeAuthenticationToken(Object principal, String code) {
        super(principal, code);
    }

    public SmsCodeAuthenticationToken(Object principal, String code, Collection<? extends GrantedAuthority> authorities) {
        super(principal, code, authorities);
    }
}
