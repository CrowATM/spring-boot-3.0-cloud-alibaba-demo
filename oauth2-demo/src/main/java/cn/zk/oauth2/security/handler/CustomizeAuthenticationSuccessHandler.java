package cn.zk.oauth2.security.handler;


import cn.zk.core.response.CustomRespBody;
import com.alibaba.fastjson2.JSON;
import com.alibaba.fastjson2.JSONWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * 登录成功处理逻辑
 */
@Slf4j
@Component
@RequiredArgsConstructor
public class CustomizeAuthenticationSuccessHandler implements AuthenticationSuccessHandler {


    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {

        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.getWriter().write(JSON.toJSONString(new CustomRespBody<Object>(0,
                        new HashMap<String, String>(){{put("token", "token-test");}},
                        null),
                JSONWriter.Feature.PrettyFormat));

    }
}
