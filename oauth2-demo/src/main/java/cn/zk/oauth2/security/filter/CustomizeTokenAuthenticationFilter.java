package cn.zk.oauth2.security.filter;

import cn.zk.core.defaul.DefaultFiled;
import cn.zk.oauth2.security.support.ResponseUtils;
import cn.zk.oauth2.security.support.TokenUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

/** 验证过滤器
 * @author ZK
 * @date 2024/1/29
 */
public class CustomizeTokenAuthenticationFilter extends OncePerRequestFilter {

    private static final AntPathRequestMatcher DEFAULT_ANT_PATH_REQUEST_MATCHER
            = new AntPathRequestMatcher("/auth/api/check_token", "GET");

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if (!DEFAULT_ANT_PATH_REQUEST_MATCHER.matches(request)) {
            filterChain.doFilter(request, response);
            return;
        }
        String accessToken = request.getHeader(DefaultFiled.ACCESS_TOKEN_HEADER_NAME);
        String refreshToken = request.getHeader(DefaultFiled.REFRESH_TOKEN_HEADER_NAME);

        boolean checkToken = TokenUtils.checkToken(accessToken, refreshToken);

        if (checkToken) {
            ResponseUtils.write(response);
        }
    }


}
