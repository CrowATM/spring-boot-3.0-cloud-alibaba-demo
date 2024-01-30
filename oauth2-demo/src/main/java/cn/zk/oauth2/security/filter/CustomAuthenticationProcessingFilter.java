package cn.zk.oauth2.security.filter;

import cn.zk.oauth2.security.params.LoginParam;
import cn.zk.oauth2.security.provider.token.EmilCodeAuthenticationToken;
import cn.zk.oauth2.security.provider.token.SmsCodeAuthenticationToken;
import cn.zk.oauth2.security.support.IOUtils;
import cn.zk.oauth2.security.support.LoginTypeEnum;
import cn.zk.oauth2.support.SpringValidUtil;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.bind.annotation.RequestMethod;

import java.io.IOException;

/** 登陆接口过滤器
 * @author ZK
 * @date 2024/1/18
 */
public class CustomAuthenticationProcessingFilter extends AbstractAuthenticationProcessingFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER
            = new AntPathRequestMatcher("/auth/login", "POST");

    public CustomAuthenticationProcessingFilter(AuthenticationManager authenticationManager) {
        super(DEFAULT_ANT_PATH_REQUEST_MATCHER, authenticationManager);
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
            throws AuthenticationException, IOException, ServletException {

        //过滤post
        if (RequestMethod.valueOf(request.getMethod()) != RequestMethod.POST) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        LoginParam loginParam = IOUtils.getTByRequest(request, LoginParam.class);
        //校验登陆方式
        SpringValidUtil.myValid(loginParam);
        AbstractAuthenticationToken authRequest = null;
        switch (LoginTypeEnum.valueOf(loginParam.getLoginType())) {
            case PASSWORD -> authRequest = new UsernamePasswordAuthenticationToken(loginParam.getUsername(), loginParam.getPassword());
            case EMIL -> authRequest = new EmilCodeAuthenticationToken(loginParam.getEmil(), loginParam.getCode());
            case PHONE -> authRequest = new SmsCodeAuthenticationToken(loginParam.getPhone(), loginParam.getCode());
        }
        if (authRequest != null) {
            return this.getAuthenticationManager().authenticate(authRequest);
        }
        throw new AuthenticationServiceException("Authentication login type(" + loginParam.getLoginType() + ") not supported");
    }
}
